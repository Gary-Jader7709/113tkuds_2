import java.util.*;

public class lt_03_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, maxLength = 0;

        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
                maxLength = Math.max(maxLength, right - left);
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        lt_03_LongestSubstringWithoutRepeatingCharacters solution = new lt_03_LongestSubstringWithoutRepeatingCharacters();
        String s1 = "abcabcbb";
        System.out.println(solution.lengthOfLongestSubstring(s1)); // 輸出: 3
        String s2 = "bbbbb";
        System.out.println(solution.lengthOfLongestSubstring(s2)); // 輸出: 1
        String s3 = "pwwkew";
        System.out.println(solution.lengthOfLongestSubstring(s3)); // 輸出: 3
    }
}
