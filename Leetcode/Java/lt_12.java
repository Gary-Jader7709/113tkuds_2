public class lt_12 {
    public String intToRoman(int num) {
        // 定義羅馬數字符號及其對應的數值
        String[] romanSymbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        StringBuilder result = new StringBuilder();

        // 根據數字大小依次匹配羅馬數字
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                result.append(romanSymbols[i]);
                num -= values[i];
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        lt_12 solution = new lt_12();

        // 測試案例 1
        int num1 = 3749;
        System.out.println(solution.intToRoman(num1)); // 輸出: "MMMDCCXLIX"

        // 測試案例 2
        int num2 = 58;
        System.out.println(solution.intToRoman(num2)); // 輸出: "LVIII"

        // 測試案例 3
        int num3 = 1994;
        System.out.println(solution.intToRoman(num3)); // 輸出: "MCMXCIV"
    }
}
