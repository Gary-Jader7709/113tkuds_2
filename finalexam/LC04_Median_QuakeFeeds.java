import java.io.*;
import java.util.*;

/** 地震速報雙資料源中位數（不合併，二分切割） */
public class LC04_Median_QuakeFeeds {
    static double findMedian(double[] A, double[] B) {
        if (A.length > B.length)
            return findMedian(B, A);
        int n = A.length, m = B.length;
        int totalLeft = (n + m + 1) / 2;
        int lo = 0, hi = n;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1; // 切 A
            int j = totalLeft - i; // 切 B
            double Aleft = (i == 0) ? -1e18 : A[i - 1];
            double Aright = (i == n) ? 1e18 : A[i];
            double Bleft = (j == 0) ? -1e18 : B[j - 1];
            double Bright = (j == m) ? 1e18 : B[j];
            if (Aleft <= Bright && Bleft <= Aright) {
                if (((n + m) & 1) == 1)
                    return Math.max(Aleft, Bleft);
                return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
            } else if (Aleft > Bright) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        throw new RuntimeException("unreachable");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        double[] A = new double[n], B = new double[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            A[i] = Double.parseDouble(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            B[i] = Double.parseDouble(st.nextToken());
        double ans = findMedian(A, B);
        System.out.printf(java.util.Locale.US, "%.1f%n", ans); // 保留 1 位
    }
}
