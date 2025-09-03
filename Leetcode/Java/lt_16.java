import java.util.Arrays;

public class lt_16 {
    public int threeSumClosest(int[] nums, int target) {
        // 先對數組進行排序
        Arrays.sort(nums);

        int closestSum = Integer.MAX_VALUE; // 記錄最接近的和

        for (int i = 0; i < nums.length - 2; i++) {
            // 跳過重複元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // 更新最接近的和
                if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }

                // 根據當前和與目標的關係調整指針
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum; // 如果已經找到了精確的和
                }
            }
        }

        return closestSum;
    }

    public static void main(String[] args) {
        lt_16 solution = new lt_16();

        // 測試案例 1
        int[] nums1 = { -1, 2, 1, -4 };
        int target1 = 1;
        System.out.println(solution.threeSumClosest(nums1, target1)); // 輸出: 2

        // 測試案例 2
        int[] nums2 = { 0, 0, 0 };
        int target2 = 1;
        System.out.println(solution.threeSumClosest(nums2, target2)); // 輸出: 0
    }
}
