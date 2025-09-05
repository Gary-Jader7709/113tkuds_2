import java.io.*;
import java.util.*;

/** 北捷刷卡最長無重複片段 */
public class LC03_NoRepeat_TaipeiMetroTap {
    public static int lengthOfLongestSubstring(String s) {
        int ans = 0, left = 0;
        int[] last = new int[256];
        Arrays.fill(last, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (last[c] >= left)
                left = last[c] + 1;
            last[c] = i;
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null)
            s = "";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
