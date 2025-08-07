// 練習 3.4：BST第k小/大元素
// 檔名：BSTKthElement.java

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BSTKthElement {

    public static TreeNode insert(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (val < root.val)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);
        return root;
    }

    public static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0)
                return root.val;
            root = root.right;
        }
        return -1;
    }

    public static int kthLargest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
            if (--k == 0)
                return root.val;
            root = root.left;
        }
        return -1;
    }

    public static void kthRange(TreeNode root, int k1, int k2, List<Integer> result) {
        inOrder(root, result);
        for (int i = k1 - 1; i <= k2 - 1 && i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }

    private static void inOrder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    public static void main(String[] args) {
        int[] nums = { 20, 10, 30, 5, 15, 25, 35 };
        TreeNode root = null;
        for (int num : nums) {
            root = insert(root, num);
        }

        System.out.println("第3小元素: " + kthSmallest(root, 3));
        System.out.println("第2大元素: " + kthLargest(root, 2));

        System.out.print("第2小到第5小元素: ");
        kthRange(root, 2, 5, new ArrayList<>());
        System.out.println();
    }
}
