package midterm;

import java.util.*;

public class M05_GCD_LCM_Recursive {
    public static long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long g = gcd(a, b);
        long l = (a / g) * b;
        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
        sc.close();
    }
}

/*
 * Time Complexity: O(log min(a, b))
 * 說明：歐幾里得演算法遞迴，每次取餘數，數字快速減少。
 * 最壞情況在 Fibonacci 序列，遞迴深度 O(log min(a, b))。
 */
