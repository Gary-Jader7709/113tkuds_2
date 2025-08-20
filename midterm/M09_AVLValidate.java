package midterm;

import java.util.*;

public class M09_AVLValidate {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    public static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1)
            return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode node = q.poll();
            if (i < arr.length && arr[i] != -1) {
                node.left = new TreeNode(arr[i]);
                q.add(node.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1) {
                node.right = new TreeNode(arr[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }

    public static boolean isBST(TreeNode root, long min, long max) {
        if (root == null)
            return true;
        if (root.val <= min || root.val >= max)
            return false;
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    static class AVLResult {
        boolean valid;
        int height;

        AVLResult(boolean v, int h) {
            valid = v;
            height = h;
        }
    }

    public static AVLResult checkAVL(TreeNode root) {
        if (root == null)
            return new AVLResult(true, 0);
        AVLResult left = checkAVL(root.left);
        AVLResult right = checkAVL(root.right);
        if (!left.valid || !right.valid)
            return new AVLResult(false, 0);
        if (Math.abs(left.height - right.height) > 1)
            return new AVLResult(false, 0);
        return new AVLResult(true, Math.max(left.height, right.height) + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        TreeNode root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else {
            AVLResult res = checkAVL(root);
            if (!res.valid) {
                System.out.println("Invalid AVL");
            } else {
                System.out.println("Valid");
            }
        }
        sc.close();
    }
}

/*
 * Time Complexity: O(n)
 * 說明：檢查 BST 需要對所有節點遞迴一次 O(n)，
 * 檢查 AVL（高度與平衡因子）同樣需走訪所有節點 O(n)。
 * 合併後整體複雜度為 O(n)。
 */
