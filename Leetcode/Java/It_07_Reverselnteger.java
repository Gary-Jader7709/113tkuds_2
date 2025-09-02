public class It_07_Reverselnteger {
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            // 檢查反轉後的結果是否會超出 32 位整數的範圍
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0; // 溢出，返回 0
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0; // 溢出，返回 0
            }

            result = result * 10 + digit;
        }
        return result;
    }

    public static void main(String[] args) {
        It_07_Reverselnteger solution = new It_07_Reverselnteger();

        // 測試案例 1
        int x1 = 123;
        System.out.println(solution.reverse(x1)); // 輸出: 321

        // 測試案例 2
        int x2 = -123;
        System.out.println(solution.reverse(x2)); // 輸出: -321

        // 測試案例 3
        int x3 = 120;
        System.out.println(solution.reverse(x3)); // 輸出: 21
    }
}
