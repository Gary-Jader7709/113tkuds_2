import java.util.*;

public class MultiLevelCacheSystem {

    private static class Entry {
        final int key;
        String value;
        int freq;
        long lastAccess;

        Entry(int key, String value, long now) {
            this.key = key;
            this.value = value;
            this.freq = 1;
            this.lastAccess = now;
        }
    }

    private static class Level {
        final int capacity;
        final int cost;
        final Map<Integer, Entry> map = new HashMap<>();
        final LinkedHashMap<Integer, Entry> lru = new LinkedHashMap<>(16, 0.75f, true);
        final PriorityQueue<Entry> evictHeap;

        Level(int capacity, int cost) {
            this.capacity = capacity;
            this.cost = cost;
            this.evictHeap = new PriorityQueue<>((a, b) -> {
                long sa = score(a, cost), sb = score(b, cost);
                int cmp = Long.compare(sa, sb);
                if (cmp != 0)
                    return cmp; // min-heap by score
                return Long.compare(a.lastAccess, b.lastAccess);
            });
        }

        static long score(Entry e, int cost) {
            // Higher score means more favored; heap stores by MIN score for eviction.
            // Here we transform to a low-is-worse score:
            // lower freq and older lastAccess produce lower score.
            return (long) e.freq * 1000L - cost * 10L + e.lastAccess / 1_000_000L;
        }

        void touch(Entry e, long now) {
            e.freq++;
            e.lastAccess = now;
            // rebuild participation in heap on next reinsert
        }

        void putEntry(Entry e) {
            map.put(e.key, e);
            lru.put(e.key, e);
            evictHeap.add(e);
            ensureCapacity();
        }

        Entry removeKey(int key) {
            Entry e = map.remove(key);
            if (e != null) {
                lru.remove(key);
                evictHeap.remove(e);
            }
            return e;
        }

        void ensureCapacity() {
            while (map.size() > capacity) {
                Entry victim = evictHeap.poll();
                if (victim == null)
                    break;
                if (!map.containsKey(victim.key))
                    continue;
                map.remove(victim.key);
                lru.remove(victim.key);
            }
        }

        boolean contains(int key) {
            return map.containsKey(key);
        }

        Entry get(int key) {
            Entry e = map.get(key);
            if (e != null)
                lru.get(key);
            return e;
        }
    }

    private final Level L1 = new Level(2, 1);
    private final Level L2 = new Level(5, 3);
    private final Level L3 = new Level(10, 10);
    private long clock = System.nanoTime();

    public String get(int key) {
        clock = System.nanoTime();
        Entry e = L1.get(key);
        if (e != null) {
            L1.touch(e, clock);
            return e.value;
        }
        e = L2.get(key);
        if (e != null) {
            L2.touch(e, clock);
            promote(e, L2, L1);
            return e.value;
        }
        e = L3.get(key);
        if (e != null) {
            L3.touch(e, clock);
            promote(e, L3, L2);
            // optional promote to L1 if very hot:
            if (e.freq >= 3)
                promote(e, L2, L1);
            return e.value;
        }
        return null;
    }

    public void put(int key, String value) {
        clock = System.nanoTime();
        Entry e = L1.get(key);
        if (e != null) {
            e.value = value;
            L1.touch(e, clock);
            return;
        }
        e = L2.get(key);
        if (e != null) {
            e.value = value;
            L2.touch(e, clock);
            return;
        }
        e = L3.get(key);
        if (e != null) {
            e.value = value;
            L3.touch(e, clock);
            return;
        }
        Entry ne = new Entry(key, value, clock);
        L3.putEntry(ne);
        // optionally promote on frequent future accesses
    }

    private void promote(Entry e, Level from, Level to) {
        from.removeKey(e.key);
        to.putEntry(e);
    }

    // Debug snapshot
    private static String keys(Level lv) {
        List<Integer> ks = new ArrayList<>(lv.map.keySet());
        Collections.sort(ks);
        return ks.toString();
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println("L1:" + keys(cache.L1) + " L2:" + keys(cache.L2) + " L3:" + keys(cache.L3));

        cache.get(1);
        cache.get(1);
        cache.get(2);
        System.out.println("After hot accesses:");
        System.out.println("L1:" + keys(cache.L1) + " L2:" + keys(cache.L2) + " L3:" + keys(cache.L3));

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        System.out.println("After more puts:");
        System.out.println("L1:" + keys(cache.L1) + " L2:" + keys(cache.L2) + " L3:" + keys(cache.L3));
    }
}
