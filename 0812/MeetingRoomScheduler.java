import java.util.*;

public class MeetingRoomScheduler {

    public static int minRooms(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] it : intervals) {
            if (!pq.isEmpty() && pq.peek() <= it[0])
                pq.poll();
            pq.add(it[1]);
        }
        return pq.size();
    }

    // Exact maximal total time for N=1 using weighted interval scheduling.
    public static List<int[]> bestScheduleSingleRoom(int[][] intervals) {
        int n = intervals.length;
        int[][] arr = Arrays.copyOf(intervals, n);
        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));
        int[] end = new int[n];
        for (int i = 0; i < n; i++)
            end[i] = arr[i][1];

        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            int s = arr[i][0];
            int j = Arrays.binarySearch(end, 0, i, s);
            if (j < 0)
                j = -j - 2;
            p[i] = j;
        }

        int[] w = new int[n];
        for (int i = 0; i < n; i++)
            w[i] = arr[i][1] - arr[i][0];

        int[] dp = new int[n];
        int[] choice = new int[n];
        for (int i = 0; i < n; i++) {
            int take = w[i] + (p[i] >= 0 ? dp[p[i]] : 0);
            int skip = (i > 0 ? dp[i - 1] : 0);
            if (take >= skip) {
                dp[i] = take;
                choice[i] = 1;
            } else {
                dp[i] = skip;
                choice[i] = 0;
            }
        }

        List<int[]> res = new ArrayList<>();
        for (int i = n - 1; i >= 0;) {
            if (choice[i] == 1) {
                res.add(arr[i]);
                i = p[i];
            } else
                i--;
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(minRooms(new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } })); // 2
        System.out.println(minRooms(new int[][] { { 9, 10 }, { 4, 9 }, { 4, 17 } })); // 2
        System.out.println(minRooms(new int[][] { { 1, 5 }, { 8, 9 }, { 8, 9 } })); // 2

        int[][] one = { { 1, 4 }, { 2, 3 }, { 4, 6 } };
        List<int[]> pick = bestScheduleSingleRoom(one);
        int total = 0;
        for (int[] it : pick)
            total += it[1] - it[0];
        System.out.println("single room schedule: " + format(pick) + ", total=" + total); // [[1,4],[4,6]], total=5
    }

    private static String format(List<int[]> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(Arrays.toString(list.get(i)));
            if (i + 1 < list.size())
                sb.append(",");
        }
        return sb.append("]").toString();
    }
}
