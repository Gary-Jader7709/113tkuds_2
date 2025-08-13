import java.util.*;

public class SlidingWindowMedian {
    private PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> hi = new PriorityQueue<>();
    private Map<Integer, Integer> delayed = new HashMap<>();
    private int loSize = 0, hiSize = 0;

    private void addNum(int num) {
        if (lo.isEmpty() || num <= lo.peek()) {
            lo.add(num);
            loSize++;
        } else {
            hi.add(num);
            hiSize++;
        }
        rebalance();
    }

    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (!lo.isEmpty() && num <= lo.peek())
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
            int cnt = delayed.getOrDefault(x, 0);
            if (cnt == 0)
                break;
            heap.poll();
            if (cnt == 1)
                delayed.remove(x);
            else
                delayed.put(x, cnt - 1);
        }
    }

    private double getMedian() {
        if ((loSize + hiSize) % 2 == 1)
            return lo.peek();
        return ((double) lo.peek() + (double) hi.peek()) / 2.0;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (k <= 0 || k > nums.length)
            throw new IllegalArgumentException("invalid k");
        lo.clear();
        hi.clear();
        delayed.clear();
        loSize = hiSize = 0;

        double[] ans = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            addNum(nums[i]);
            if (i >= k)
                removeNum(nums[i - k]);
            if (i >= k - 1)
                ans[i - k + 1] = getMedian();
        }
        return ans;
    }

    public static void main(String[] args) {
        SlidingWindowMedian s = new SlidingWindowMedian();
        System.out.println(Arrays.toString(
                s.medianSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3))); // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

        System.out.println(Arrays.toString(
                s.medianSlidingWindow(new int[] { 1, 2, 3, 4 }, 2))); // [1.5, 2.5, 3.5]
    }
}
