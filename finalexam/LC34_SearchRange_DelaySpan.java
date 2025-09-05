import java.io.*;
import java.util.*;

/** 延誤等級首末定位（雙二分） */
public class LC34_SearchRange_DelaySpan {
    static int lowerBound(int[] a, int t) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] < t)
                l = m + 1;
            else
                r = m;
        }
        return l;
    }

    static int[] searchRange(int[] a, int t) {
        int L = lowerBound(a, t);
        if (L == a.length || a[L] != t)
            return new int[] { -1, -1 };
        int R = lowerBound(a, t + 1) - 1;
        return new int[] { L, R };
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), target = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        if (n > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++)
                a[i] = Integer.parseInt(st.nextToken());
        } else {
            br.readLine();
        }
        int[] ans = searchRange(a, target);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
