public class It_05_LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;

        // 中心擴展法
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // 單個字符中心
            int len2 = expandAroundCenter(s, i, i + 1); // 兩個字符中心
            int len = Math.max(len1, len2); // 取兩者最大值

            // 如果找到更長的回文子串，更新 start 和 end
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    // 以 center 為中心擴展回文
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        It_05_LongestPalindromicSubstring solution = new It_05_LongestPalindromicSubstring();

        // 測試案例 1
        String s1 = "babad";
        System.out.println(solution.longestPalindrome(s1)); // 輸出: "bab" 或 "aba"

        // 測試案例 2
        String s2 = "cbbd";
        System.out.println(solution.longestPalindrome(s2)); // 輸出: "bb"
    }
}
