import java.io.*;
import java.util.*;

/** 回收站清單移除指定元素（就地覆寫） */
public class LC27_RemoveElement_Recycle {
    static int removeElement(int[] a, int val) {
        int w = 0;
        for (int x : a)
            if (x != val)
                a[w++] = x;
        return w;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), val = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        if (n > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++)
                a[i] = Integer.parseInt(st.nextToken());
        } else {
            br.readLine();
        }
        int k = removeElement(a, val);
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
