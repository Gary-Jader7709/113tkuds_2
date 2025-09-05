import java.io.*;
import java.util.*;

/** 採購限額 4Sum（排序 + 雙層枚舉 + 兩指針 去重） */
public class LC18_4Sum_Procurement {
    static List<List<Long>> fourSum(long[] a, long target) {
        Arrays.sort(a);
        int n = a.length;
        List<List<Long>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && a[i] == a[i - 1])
                continue;
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && a[j] == a[j - 1])
                    continue;
                int l = j + 1, r = n - 1;
                while (l < r) {
                    long sum = a[i] + a[j] + a[l] + a[r];
                    if (sum == target) {
                        res.add(Arrays.asList(a[i], a[j], a[l], a[r]));
                        long lv = a[l], rv = a[r];
                        while (l < r && a[l] == lv)
                            l++;
                        while (l < r && a[r] == rv)
                            r--;
                    } else if (sum < target)
                        l++;
                    else
                        r--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long target = Long.parseLong(st.nextToken());
        long[] a = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Long.parseLong(st.nextToken());
        List<List<Long>> ans = fourSum(a, target);
        StringBuilder sb = new StringBuilder();
        for (List<Long> q : ans) {
            sb.append(q.get(0)).append(' ')
                    .append(q.get(1)).append(' ')
                    .append(q.get(2)).append(' ')
                    .append(q.get(3)).append('\n');
        }
        System.out.print(sb.toString());
    }
}
