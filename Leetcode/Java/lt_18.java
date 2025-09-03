import java.util.*;

public class lt_18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 排序數組

        for (int i = 0; i < nums.length - 3; i++) {
            // 跳過重複的元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                // 跳過重複的元素
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 跳過重複的元素
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        lt_18 solution = new lt_18();

        // 測試案例 1
        int[] nums1 = { 1, 0, -1, 0, -2, 2 };
        int target1 = 0;
        System.out.println(solution.fourSum(nums1, target1)); // 輸出: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

        // 測試案例 2
        int[] nums2 = { 2, 2, 2, 2, 2 };
        int target2 = 8;
        System.out.println(solution.fourSum(nums2, target2)); // 輸出: [[2,2,2,2]]
    }
}
