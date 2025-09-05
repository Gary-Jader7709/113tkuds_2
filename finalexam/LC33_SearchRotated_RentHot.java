import java.io.*;
import java.util.*;

/** 旋轉陣列搜尋（改良二分） */
public class LC33_SearchRotated_RentHot {
    static int search(int[] a, int t) {
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int m = (l + r) >>> 1;
            if (a[m] == t)
                return m;
            if (a[l] <= a[m]) { // 左半有序
                if (a[l] <= t && t < a[m])
                    r = m - 1;
                else
                    l = m + 1;
            } else { // 右半有序
                if (a[m] < t && t <= a[r])
                    l = m + 1;
                else
                    r = m - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), target = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());
        System.out.println(search(a, target));
    }
}
