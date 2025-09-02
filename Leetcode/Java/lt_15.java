import java.util.*;

public class lt_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 排序數組

        for (int i = 0; i < nums.length - 2; i++) {
            // 跳過重複的數字
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳過重複的數字
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        lt_15 solution = new lt_15();

        // 測試案例 1
        int[] nums1 = { -1, 0, 1, 2, -1, -4 };
        System.out.println(solution.threeSum(nums1)); // 輸出: [[-1, -1, 2], [-1, 0, 1]]

        // 測試案例 2
        int[] nums2 = { 0, 1, 1 };
        System.out.println(solution.threeSum(nums2)); // 輸出: []

        // 測試案例 3
        int[] nums3 = { 0, 0, 0 };
        System.out.println(solution.threeSum(nums3)); // 輸出: [[0, 0, 0]]
    }
}
