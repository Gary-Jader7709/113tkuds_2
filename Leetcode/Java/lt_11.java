public class lt_11 {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            // 計算當前水量
            int width = right - left;
            int currentArea = Math.min(height[left], height[right]) * width;
            maxArea = Math.max(maxArea, currentArea);

            // 移動較短的指針
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        lt_11 solution = new lt_11();

        // 測試案例 1
        int[] height1 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        System.out.println(solution.maxArea(height1)); // 輸出: 49

        // 測試案例 2
        int[] height2 = { 1, 1 };
        System.out.println(solution.maxArea(height2)); // 輸出: 1
    }
}
