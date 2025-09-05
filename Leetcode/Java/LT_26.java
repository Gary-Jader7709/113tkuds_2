import java.util.Arrays;

public class LT_26 {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i == 0 || n != nums[i - 1])
                nums[i++] = n;
        return i;
    }

    public static void main(String[] args) {
        int[] a = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
        int k = new LT_26().removeDuplicates(a);
        System.out.println(k + " " + Arrays.toString(Arrays.copyOf(a, k)));
    }
}
