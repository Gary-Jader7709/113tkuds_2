import java.util.*;

public class MovingAverageStream {
    private final int size;
    private final Deque<Integer> window = new ArrayDeque<>();
    private long sum = 0;

    // for median
    private final PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> hi = new PriorityQueue<>();
    private final Map<Integer, Integer> delayed = new HashMap<>();
    private int loSize = 0, hiSize = 0;

    // for min/max
    private final TreeMap<Integer, Integer> multiset = new TreeMap<>();

    public MovingAverageStream(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("size must be > 0");
        this.size = size;
    }

    public double next(int val) {
        window.addLast(val);
        sum += val;
        addMedian(val);
        addMulti(val);
        if (window.size() > size) {
            int out = window.removeFirst();
            sum -= out;
            removeMedian(out);
            removeMulti(out);
        }
        return sum * 1.0 / window.size();
    }

    public double getMedian() {
        if (window.isEmpty())
            throw new NoSuchElementException("empty");
        if ((loSize + hiSize) % 2 == 1)
            return lo.peek();
        return (lo.peek() + hi.peek()) / 2.0;
    }

    public int getMin() {
        if (window.isEmpty())
            throw new NoSuchElementException("empty");
        return multiset.firstKey();
    }

    public int getMax() {
        if (window.isEmpty())
            throw new NoSuchElementException("empty");
        return multiset.lastKey();
    }

    private void addMedian(int x) {
        if (lo.isEmpty() || x <= lo.peek()) {
            lo.add(x);
            loSize++;
        } else {
            hi.add(x);
            hiSize++;
        }
        rebalance();
    }

    private void removeMedian(int x) {
        delayed.put(x, delayed.getOrDefault(x, 0) + 1);
        if (!lo.isEmpty() && x <= lo.peek())
            loSize--;
        else
            hiSize--;
        prune(lo);
        prune(hi);
        rebalance();
    }

    private void rebalance() {
        if (loSize > hiSize + 1) {
            hi.add(lo.poll());
            loSize--;
            hiSize++;
            prune(lo);
        } else if (loSize < hiSize) {
            lo.add(hi.poll());
            hiSize--;
            loSize++;
            prune(hi);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int x = heap.peek();
            int c = delayed.getOrDefault(x, 0);
            if (c == 0)
                break;
            heap.poll();
            if (c == 1)
                delayed.remove(x);
            else
                delayed.put(x, c - 1);
        }
    }

    private void addMulti(int x) {
        multiset.put(x, multiset.getOrDefault(x, 0) + 1);
    }

    private void removeMulti(int x) {
        int c = multiset.getOrDefault(x, 0);
        if (c <= 1)
            multiset.remove(x);
        else
            multiset.put(x, c - 1);
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1)); // 1.0
        System.out.println(ma.next(10)); // 5.5
        System.out.println(ma.next(3)); // 4.666...
        System.out.println(ma.next(5)); // 6.0
        System.out.println(ma.getMedian()); // 5.0
        System.out.println(ma.getMin()); // 3
        System.out.println(ma.getMax()); // 10
    }
}
