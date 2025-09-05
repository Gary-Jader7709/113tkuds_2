import java.io.*;
import java.util.*;

/** 高鐵連假加班車 Two Sum */
public class LC01_TwoSum_THSRHoliday {
    public static int[] twoSum(int[] nums, long target) {
        Map<Long, Integer> need = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long x = nums[i];
            if (need.containsKey(x))
                return new int[] { need.get(x), i };
            need.put(target - x, i);
        }
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long target = Long.parseLong(st.nextToken());
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());
        int[] ans = twoSum(a, target);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
