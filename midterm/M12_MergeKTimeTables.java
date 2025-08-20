package midterm;

import java.util.*;

public class M12_MergeKTimeTables {
    static class Entry {
        int time;
        int listIdx;
        int pos;

        Entry(int t, int li, int p) {
            time = t;
            listIdx = li;
            pos = p;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                cur.add(sc.nextInt());
            }
            lists.add(cur);
        }

        PriorityQueue<Entry> pq = new PriorityQueue<>(new Comparator<Entry>() {
            public int compare(Entry a, Entry b) {
                return a.time - b.time;
            }
        });

        for (int i = 0; i < K; i++) {
            if (!lists.get(i).isEmpty()) {
                pq.offer(new Entry(lists.get(i).get(0), i, 0));
            }
        }

        List<Integer> merged = new ArrayList<>();
        while (!pq.isEmpty()) {
            Entry e = pq.poll();
            merged.add(e.time);
            int ni = e.listIdx;
            int np = e.pos + 1;
            if (np < lists.get(ni).size()) {
                pq.offer(new Entry(lists.get(ni).get(np), ni, np));
            }
        }

        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i));
            if (i < merged.size() - 1)
                System.out.print(" ");
        }
        sc.close();
    }
}
