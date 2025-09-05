import java.io.*;
import java.util.*;

/** 高鐵站點三元組 3Sum（排序 + 兩指針 去重） */
public class LC15_3Sum_THSRStops {
    static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            if (nums[i] > 0)
                break;
            int l = i + 1, r = n - 1;
            while (l < r) {
                long sum = (long) nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    int lv = nums[l], rv = nums[r];
                    while (l < r && nums[l] == lv)
                        l++;
                    while (l < r && nums[r] == rv)
                        r--;
                } else if (sum < 0)
                    l++;
                else
                    r--;
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());
        List<List<Integer>> ans = threeSum(a);
        StringBuilder sb = new StringBuilder();
        for (List<Integer> t : ans) {
            sb.append(t.get(0)).append(' ')
                    .append(t.get(1)).append(' ')
                    .append(t.get(2)).append('\n');
        }
        System.out.print(sb.toString());
    }
}
