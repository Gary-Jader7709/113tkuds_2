import java.util.HashMap;
import java.util.Map;

public class lt_13 {
    public int romanToInt(String s) {
        // 定義羅馬數字符號及其對應的數值
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int value = romanMap.get(s.charAt(i));
            // 如果當前字符代表的數字小於後一個字符，則需要減去這個數字
            if (i + 1 < n && value < romanMap.get(s.charAt(i + 1))) {
                result -= value;
            } else {
                result += value;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        lt_13 solution = new lt_13();

        // 測試案例 1
        String s1 = "III";
        System.out.println(solution.romanToInt(s1)); // 輸出: 3

        // 測試案例 2
        String s2 = "LVIII";
        System.out.println(solution.romanToInt(s2)); // 輸出: 58

        // 測試案例 3
        String s3 = "MCMXCIV";
        System.out.println(solution.romanToInt(s3)); // 輸出: 1994
    }
}
