import java.io.*;
import java.util.*;

/** 去重學生成績單（已排序就地壓縮） */
public class LC26_RemoveDuplicates_Scores {
    static int removeDuplicates(int[] a) {
        if (a.length == 0)
            return 0;
        int w = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[w - 1])
                a[w++] = a[i];
        }
        return w;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] a = new int[n];
        if (n > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++)
                a[i] = Integer.parseInt(st.nextToken());
        } else {
            br.readLine();
        }
        int k = removeDuplicates(a);
        System.out.println(k);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(a[i]);
            if (i + 1 < k)
                sb.append(' ');
        }
        if (k > 0)
            System.out.println(sb.toString());
    }
}
