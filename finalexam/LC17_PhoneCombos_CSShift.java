import java.io.*;
import java.util.*;

/** 手機門號組合（回溯） */
public class LC17_PhoneCombos_CSShift {
    static final String[] MAP = {
            "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    static void dfs(String digits, int i, StringBuilder path, List<String> out) {
        if (i == digits.length()) {
            out.add(path.toString());
            return;
        }
        String letters = MAP[digits.charAt(i) - '2'];
        for (int k = 0; k < letters.length(); k++) {
            path.append(letters.charAt(k));
            dfs(digits, i + 1, path, out);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String digits = br.readLine();
        if (digits == null)
            digits = "";
        if (digits.isEmpty()) {
            /* 無輸出 */ return;
        }
        List<String> ans = new ArrayList<>();
        dfs(digits, 0, new StringBuilder(), ans);
        StringBuilder sb = new StringBuilder();
        for (String s : ans)
            sb.append(s).append('\n');
        System.out.print(sb.toString());
    }
}
