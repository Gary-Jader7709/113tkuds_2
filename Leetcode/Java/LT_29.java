public class LT_29 {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        long a = Math.abs((long) dividend), b = Math.abs((long) divisor);
        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
        long res = 0;
        while (a >= b) {
            long t = b, m = 1;
            while ((t << 1) <= a) {
                t <<= 1;
                m <<= 1;
            }
            a -= t;
            res += m;
        }
        return (int) (sign == 1 ? res : -res);
    }

    public static void main(String[] args) {
        System.out.println(new LT_29().divide(10, 3)); // 3
        System.out.println(new LT_29().divide(7, -3)); // -2
    }
}
