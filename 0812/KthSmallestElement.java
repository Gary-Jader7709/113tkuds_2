import java.util.*;

public class KthSmallestElement {

    public static int kthWithMaxHeap(int[] nums, int k) {
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        for (int v : nums) {
            max.add(v);
            if (max.size() > k)
                max.poll();
        }
        if (max.size() < k)
            throw new IllegalArgumentException("k too large");
        return max.peek();
    }

    public static int kthWithMinHeap(int[] nums, int k) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for (int v : nums)
            min.add(v);
        if (k < 1 || k > nums.length)
            throw new IllegalArgumentException("invalid k");
        int ans = -1;
        for (int i = 0; i < k; i++)
            ans = min.poll();
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(kthWithMaxHeap(new int[] { 7, 10, 4, 3, 20, 15 }, 3)); // 7
        System.out.println(kthWithMinHeap(new int[] { 7, 10, 4, 3, 20, 15 }, 3)); // 7
        System.out.println(kthWithMaxHeap(new int[] { 1 }, 1)); // 1
        System.out.println(kthWithMaxHeap(new int[] { 3, 1, 4, 1, 5, 9, 2, 6 }, 4)); // 3
    }
}
