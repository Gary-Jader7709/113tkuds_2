public class lt_10_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[i][j] 代表 s 的前 i 個字符與 p 的前 j 個字符是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // 空字符串和空模式是匹配的

        // 處理模式串 p 的特殊情況，比如 'a*'、'b*' 等情況
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2]; // 只有 '*' 出現的情況
            }
        }

        // 填充 dp 表格
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1]; // 如果字符匹配或 '.' 匹配
                } else if (p.charAt(j - 1) == '*') {
                    // '*' 的處理有兩種情況：
                    dp[i][j] = dp[i][j - 2]
                            || (dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        lt_10_RegularExpressionMatching solution = new lt_10_RegularExpressionMatching();

        // 測試案例 1
        String s1 = "aa";
        String p1 = "a*";
        System.out.println(solution.isMatch(s1, p1)); // 輸出: true

        // 測試案例 2
        String s2 = "mississippi";
        String p2 = "mis*is*p*.";
        System.out.println(solution.isMatch(s2, p2)); // 輸出: false

        // 測試案例 3
        String s3 = "ab";
        String p3 = ".*";
        System.out.println(solution.isMatch(s3, p3)); // 輸出: true
    }
}
