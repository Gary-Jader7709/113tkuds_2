import java.util.*;

public class lt_17 {
    public List<String> letterCombinations(String digits) {
        // 如果輸入為空字符串，直接返回空列表
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }

        // 定義數字到字母的映射
        String[] mapping = {
                "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        List<String> result = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder(), result, mapping);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current, List<String> result, String[] mapping) {
        // 當字符串的長度與 digits 相同時，將當前組合加入結果
        if (current.length() == digits.length()) {
            result.add(current.toString());
            return;
        }

        // 取得當前數字對應的字母
        String letters = mapping[digits.charAt(index) - '0'];

        // 對當前字母組合進行回溯
        for (char letter : letters.toCharArray()) {
            current.append(letter);
            backtrack(digits, index + 1, current, result, mapping);
            current.deleteCharAt(current.length() - 1); // 回溯，刪除最後一個字符
        }
    }

    public static void main(String[] args) {
        lt_17 solution = new lt_17();

        // 測試案例 1
        String digits1 = "23";
        System.out.println(solution.letterCombinations(digits1)); // 輸出: [ad, ae, af, bd, be, bf, cd, ce, cf]

        // 測試案例 2
        String digits2 = "";
        System.out.println(solution.letterCombinations(digits2)); // 輸出: []

        // 測試案例 3
        String digits3 = "2";
        System.out.println(solution.letterCombinations(digits3)); // 輸出: [a, b, c]
    }
}
