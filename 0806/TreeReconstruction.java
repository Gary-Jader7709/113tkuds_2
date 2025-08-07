// 練習 3.8：樹的重建
// 檔名：TreeReconstruction.java

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class TreeReconstruction {

    // 根據前序與中序重建二元樹
    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
            int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd)
            return null;
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = inStart;
        while (inorder[rootIndex] != rootVal)
            rootIndex++;
        int leftSize = rootIndex - inStart;
        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftSize, inorder, inStart, rootIndex - 1);
        root.right = buildTreeHelper(preorder, preStart + leftSize + 1, preEnd, inorder, rootIndex + 1, inEnd);
        return root;
    }

    // 根據後序與中序重建
    public static TreeNode buildTreePostIn(int[] postorder, int[] inorder) {
        return buildPostHelper(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildPostHelper(int[] post, int postStart, int postEnd,
            int[] in, int inStart, int inEnd) {
        if (postStart > postEnd || inStart > inEnd)
            return null;
        int rootVal = post[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = inStart;
        while (in[rootIndex] != rootVal)
            rootIndex++;
        int leftSize = rootIndex - inStart;
        root.left = buildPostHelper(post, postStart, postStart + leftSize - 1, in, inStart, rootIndex - 1);
        root.right = buildPostHelper(post, postStart + leftSize, postEnd - 1, in, rootIndex + 1, inEnd);
        return root;
    }

    // 根據層序重建完全二元樹
    public static TreeNode buildFromLevelOrder(Integer[] data) {
        if (data.length == 0 || data[0] == null)
            return null;
        TreeNode root = new TreeNode(data[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < data.length) {
            TreeNode curr = q.poll();
            if (data[i] != null) {
                curr.left = new TreeNode(data[i]);
                q.offer(curr.left);
            }
            i++;
            if (i < data.length && data[i] != null) {
                curr.right = new TreeNode(data[i]);
                q.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void preorder(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void main(String[] args) {
        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };
        TreeNode root1 = buildTreePreIn(preorder, inorder);
        System.out.print("前序+中序重建：");
        preorder(root1);
        System.out.println();

        int[] postorder = { 9, 15, 7, 20, 3 };
        TreeNode root2 = buildTreePostIn(postorder, inorder);
        System.out.print("後序+中序重建：");
        preorder(root2);
        System.out.println();

        Integer[] levelOrder = { 1, 2, 3, 4, 5, 6, 7 };
        TreeNode root3 = buildFromLevelOrder(levelOrder);
        System.out.print("層序重建：");
        preorder(root3);
        System.out.println();
    }
}