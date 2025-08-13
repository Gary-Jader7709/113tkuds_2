import java.util.*;

public class MergeKSortedArrays {

    private static class Node {
        int val, ai, ei;

        Node(int val, int ai, int ei) {
            this.val = val;
            this.ai = ai;
            this.ei = ei;
        }
    }

    public static List<Integer> merge(List<int[]> arrays) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < arrays.size(); i++) {
            if (arrays.get(i).length > 0) {
                pq.add(new Node(arrays.get(i)[0], i, 0));
            }
        }
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            res.add(cur.val);
            int ni = cur.ei + 1;
            int[] arr = arrays.get(cur.ai);
            if (ni < arr.length)
                pq.add(new Node(arr[ni], cur.ai, ni));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(merge(Arrays.asList(
                new int[] { 1, 4, 5 }, new int[] { 1, 3, 4 }, new int[] { 2, 6 }))); // [1,1,2,3,4,4,5,6]

        System.out.println(merge(Arrays.asList(
                new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 }, new int[] { 7, 8, 9 }))); // [1..9]

        System.out.println(merge(Arrays.asList(
                new int[] { 1 }, new int[] { 0 }))); // [0,1]
    }
}
