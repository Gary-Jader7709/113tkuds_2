import java.io.*;
import java.util.*;

/** 連假油量促銷最大區間（雙指針） */
public class LC11_MaxArea_FuelHoliday {
    static long maxArea(int[] h) {
        int l = 0, r = h.length - 1;
        long best = 0;
        while (l < r) {
            long area = (long) (r - l) * Math.min(h[l], h[r]);
            if (area > best)
                best = area;
            if (h[l] <= h[r])
                l++;
            else
                r--;
        }
        return best;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());
        System.out.println(maxArea(a));
    }
}
