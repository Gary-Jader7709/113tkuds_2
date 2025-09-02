public class It_04_MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 合併兩個已經排序的陣列
        int m = nums1.length;
        int n = nums2.length;

        // 確保 nums1 是較小的陣列
        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            m = nums1.length;
            n = nums2.length;
        }

        // 二分查找的範圍
        int left = 0, right = m;

        while (left <= right) {
            int partition1 = (left + right) / 2;
            int partition2 = (m + n + 1) / 2 - partition1;

            // 計算兩個分割點的邊界元素
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            // 檢查分割是否正確
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // 如果是奇數長度，返回中位數的左半部分
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeft1, maxLeft2);
                }
                // 如果是偶數長度，返回中位數
                return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
            } else if (maxLeft1 > minRight2) {
                right = partition1 - 1; // 向左移動
            } else {
                left = partition1 + 1; // 向右移動
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted");
    }

    public static void main(String[] args) {
        It_04_MedianofTwoSortedArrays solution = new It_04_MedianofTwoSortedArrays();

        // 測試案例 1
        int[] nums1 = { 1, 3 };
        int[] nums2 = { 2 };
        System.out.println(solution.findMedianSortedArrays(nums1, nums2)); // 輸出: 2.0

        // 測試案例 2
        int[] nums1_2 = { 1, 2 };
        int[] nums2_2 = { 3, 4 };
        System.out.println(solution.findMedianSortedArrays(nums1_2, nums2_2)); // 輸出: 2.5
    }
}
