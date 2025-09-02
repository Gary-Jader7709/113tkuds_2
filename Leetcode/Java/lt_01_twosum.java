// File name: lt_01_twosum.java
// Problem: Two Sum

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Use a hashmap to store the numbers and their indices as we iterate through
        // the array
        Map<Integer, Integer> map = new HashMap<>();

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // Calculate the complement of the current number
            int complement = target - nums[i];

            // If the complement is already in the hashmap, return the indices
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            // Otherwise, add the current number and its index to the hashmap
            map.put(nums[i], i);
        }

        // The problem guarantees there is exactly one solution, so this won't be
        // reached
        throw new IllegalArgumentException("No solution found");
    }

    // 加入 main 方法
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = sol.twoSum(nums, target);
        System.out.println("[" + result[0] + ", " + result[1] + "]");
    }
}
