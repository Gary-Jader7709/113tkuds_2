import java.util.Arrays;

public class LT_27 {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int n : nums)
            if (n != val)
                nums[i++] = n;
        return i;
    }

    public static void main(String[] args) {
        int[] a = { 3, 2, 2, 3 };
        int k = new LT_27().removeElement(a, 3);
        System.out.println(k + " " + Arrays.toString(Arrays.copyOf(a, k)));
    }
}
