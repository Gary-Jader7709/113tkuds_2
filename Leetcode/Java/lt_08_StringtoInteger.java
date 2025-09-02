public class lt_08_StringtoInteger {
    public int myAtoi(String s) {
        int i = 0, n = s.length(), sign = 1, result = 0;

        // Step 1: Ignore leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Step 2: Check if the next character is '+' or '-'
        if (i < n && s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (i < n && s.charAt(i) == '+') {
            i++;
        }

        // Step 3: Convert the digits and ignore non-digit characters
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            // Handle overflow and underflow
            if (result > Integer.MAX_VALUE / 10
                    || (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
            i++;
        }

        return sign * result;
    }

    public static void main(String[] args) {
        lt_08_StringtoInteger solution = new lt_08_StringtoInteger();

        // 測試案例 1
        String s1 = "42";
        System.out.println(solution.myAtoi(s1)); // 輸出: 42

        // 測試案例 2
        String s2 = "   -42";
        System.out.println(solution.myAtoi(s2)); // 輸出: -42

        // 測試案例 3
        String s3 = "4193 with words";
        System.out.println(solution.myAtoi(s3)); // 輸出: 4193

        // 測試案例 4
        String s4 = "words and 987";
        System.out.println(solution.myAtoi(s4)); // 輸出: 0

        // 測試案例 5
        String s5 = "-91283472332";
        System.out.println(solution.myAtoi(s5)); // 輸出: -2147483648 (Integer.MIN_VALUE)
    }
}
