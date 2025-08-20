package midterm;

import java.util.*;

public class M04_TieredTaxSimple {
    public static int computeTax(int income) {
        int tax = 0;
        if (income > 1000000) {
            tax += (income - 1000000) * 30 / 100;
            income = 1000000;
        }
        if (income > 500000) {
            tax += (income - 500000) * 20 / 100;
            income = 500000;
        }
        if (income > 120000) {
            tax += (income - 120000) * 12 / 100;
            income = 120000;
        }
        tax += income * 5 / 100;
        return tax;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int total = 0;
        for (int i = 0; i < n; i++) {
            int income = sc.nextInt();
            int tax = computeTax(income);
            total += tax;
            System.out.println("Tax: " + tax);
        }
        int avg = total / n;
        System.out.println("Average: " + avg);
        sc.close();
    }
}

/*
 * Time Complexity: O(n)
 * 說明：對每筆收入計算稅額為 O(1)，共 n 筆輸入 → 總計 O(n)。
 * 平均稅額計算為加總後再除以 n，為 O(1)，不影響整體複雜度。
 */
