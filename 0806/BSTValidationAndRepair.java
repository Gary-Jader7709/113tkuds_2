import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BSTValidationAndRepair {

    // 驗證是否為有效 BST
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private static boolean isValidBSTHelper(TreeNode node, Integer min, Integer max) {
        if (node == null)
            return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max))
            return false;
        return isValidBSTHelper(node.left, min, node.val) && isValidBSTHelper(node.right, node.val, max);
    }

    // 修復 BST 中兩個錯誤節點
    static TreeNode first = null, second = null, prev = new TreeNode(Integer.MIN_VALUE);

    public static void recoverTree(TreeNode root) {
        first = second = null;
        prev = new TreeNode(Integer.MIN_VALUE);
        inorder(root);
        if (first != null && second != null) {
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }

    private static void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null)
                first = prev;
            second = root;
        }
        prev = root;
        inorder(root.right);
    }

    // 計算要移除幾個節點才能變 BST
    public static int minRemovalsToBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrderList(root, list);
        return list.size() - longestIncreasingSubsequence(list);
    }

    private static void inOrderList(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        inOrderList(node.left, list);
        list.add(node.val);
        inOrderList(node.right, list);
    }

    private static int longestIncreasingSubsequence(List<Integer> nums) {
        List<Integer> dp = new ArrayList<>();
        for (int num : nums) {
            int idx = Collections.binarySearch(dp, num);
            if (idx < 0)
                idx = -idx - 1;
            if (idx == dp.size())
                dp.add(num);
            else
                dp.set(idx, num);
        }
        return dp.size();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2); // 這是錯誤節點應在左邊

        System.out.println("修復前是否為 BST: " + isValidBST(root));
        recoverTree(root);
        System.out.println("修復後是否為 BST: " + isValidBST(root));
        System.out.println("最少需要移除節點數: " + minRemovalsToBST(root));
    }
}
