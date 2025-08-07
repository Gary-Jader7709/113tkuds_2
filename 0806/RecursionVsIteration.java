public class RecursionVsIteration {
    public static void main(String[] args) {
        System.out.println("Recursive comb(5,2): " + combRecursive(5, 2));
        System.out.println("Iterative comb(5,2): " + combIterative(5, 2));

        int[] nums = { 2, 3, 4 };
        System.out.println("Product recursive: " + productRecursive(nums, 0));
        System.out.println("Product iterative: " + productIterative(nums));

        String s = "education";
        System.out.println("Vowel count: " + countVowels(s));

        String brackets = "(()())";
        System.out.println("Brackets matched? " + isBalanced(brackets));
    }

    public static int combRecursive(int n, int k) {
        if (k == 0 || k == n)
            return 1;
        return combRecursive(n - 1, k - 1) + combRecursive(n - 1, k);
    }

    public static int combIterative(int n, int k) {
        int res = 1;
        for (int i = 1; i <= k; i++) {
            res *= (n - i + 1);
            res /= i;
        }
        return res;
    }

    public static int productRecursive(int[] arr, int i) {
        if (i == arr.length)
            return 1;
        return arr[i] * productRecursive(arr, i + 1);
    }

    public static int productIterative(int[] arr) {
        int prod = 1;
        for (int i : arr)
            prod *= i;
        return prod;
    }

    public static int countVowels(String s) {
        return countVowelsHelper(s.toLowerCase(), 0);
    }

    public static int countVowelsHelper(String s, int i) {
        if (i == s.length())
            return 0;
        char c = s.charAt(i);
        return ("aeiou".indexOf(c) >= 0 ? 1 : 0) + countVowelsHelper(s, i + 1);
    }

    public static boolean isBalanced(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(')
                count++;
            else if (c == ')')
                count--;
            if (count < 0)
                return false;
        }
        return count == 0;
    }
}
