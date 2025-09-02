public class lt_09_PalindromeNumber {
    public boolean isPalindrome(int x) {
        // 負數和以 0 結尾的數字都不是回文
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        // 如果 x 等於 reversed 或者 x 等於 reversed 除以 10（處理奇數位數情況）
        return x == reversed || x == reversed / 10;
    }

    public static void main(String[] args) {
        lt_09_PalindromeNumber solution = new lt_09_PalindromeNumber();

        // 測試案例 1
        int x1 = 121;
        System.out.println(solution.isPalindrome(x1)); // 輸出: true

        // 測試案例 2
        int x2 = -121;
        System.out.println(solution.isPalindrome(x2)); // 輸出: false

        // 測試案例 3
        int x3 = 10;
        System.out.println(solution.isPalindrome(x3)); // 輸出: false
    }
}
