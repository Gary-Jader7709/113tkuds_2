import java.io.*;

/** 公告全文搜尋（KMP） */
public class LC28_StrStr_NoticeSearch {
    static int strStr(String hay, String nee) {
        if (nee.length() == 0)
            return 0;
        int m = nee.length();
        int[] lps = new int[m];
        for (int i = 1, len = 0; i < m;) {
            if (nee.charAt(i) == nee.charAt(len))
                lps[i++] = ++len;
            else if (len > 0)
                len = lps[len - 1];
            else
                lps[i++] = 0;
        }
        for (int i = 0, j = 0; i < hay.length();) {
            if (hay.charAt(i) == nee.charAt(j)) {
                i++;
                j++;
                if (j == m)
                    return i - m;
            } else if (j > 0)
                j = lps[j - 1];
            else
                i++;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String hay = br.readLine();
        if (hay == null)
            hay = "";
        String nee = br.readLine();
        if (nee == null)
            nee = "";
        System.out.println(strStr(hay, nee));
    }
}
