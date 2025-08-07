// 練習 3.2：BST範圍查詢系統
// 檔名：BSTRangeQuerySystem.java

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BSTRangeQuerySystem {

    public static void rangeQuery(TreeNode root, int min, int max) {
        if (root == null)
            return;
        if (root.val > min)
            rangeQuery(root.left, min, max);
        if (root.val >= min && root.val <= max)
            System.out.print(root.val + " ");
        if (root.val < max)
            rangeQuery(root.right, min, max);
    }

    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null)
            return 0;
        if (root.val < min)
            return rangeCount(root.right, min, max);
        if (root.val > max)
            return rangeCount(root.left, min, max);
        return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null)
            return 0;
        if (root.val < min)
            return rangeSum(root.right, min, max);
        if (root.val > max)
            return rangeSum(root.left, min, max);
        return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    public static int closest(TreeNode root, int target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            if (target < root.val)
                root = root.left;
            else if (target > root.val)
                root = root.right;
            else
                break;
        }
        return closest;
    }

    public static TreeNode insert(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (val < root.val)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);
        return root;
    }

    public static void main(String[] args) {
        int[] nums = { 10, 5, 15, 3, 7, 13, 18 };
        TreeNode root = null;
        for (int num : nums) {
            root = insert(root, num);
        }

        System.out.print("範圍查詢 [6, 14]: ");
        rangeQuery(root, 6, 14);
        System.out.println();

        System.out.println("範圍內節點數量 [6, 14]: " + rangeCount(root, 6, 14));
        System.out.println("範圍內總和 [6, 14]: " + rangeSum(root, 6, 14));
        System.out.println("最接近 11 的節點: " + closest(root, 11));
    }
}
