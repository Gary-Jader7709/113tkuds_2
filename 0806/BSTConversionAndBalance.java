// 練習 3.9：BST 轉換與平衡
// 檔名：BSTConversionAndBalance.java

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BSTConversionAndBalance {

    // 將 BST 轉換為排序雙向鏈結串列
    static TreeNode prev = null, head = null;

    public static TreeNode bstToDoublyList(TreeNode root) {
        if (root == null)
            return null;
        prev = null;
        head = null;
        inorderLink(root);
        return head;
    }

    private static void inorderLink(TreeNode node) {
        if (node == null)
            return;
        inorderLink(node.left);
        if (prev != null) {
            prev.right = node;
            node.left = prev;
        } else {
            head = node;
        }
        prev = node;
        inorderLink(node.right);
    }

    // 排序陣列轉換為平衡 BST
    public static TreeNode sortedArrayToBST(int[] arr) {
        return buildBST(arr, 0, arr.length - 1);
    }

    private static TreeNode buildBST(int[] arr, int left, int right) {
        if (left > right)
            return null;
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = buildBST(arr, left, mid - 1);
        node.right = buildBST(arr, mid + 1, right);
        return node;
    }

    // 檢查 BST 是否平衡
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null)
            return 0;
        int left = checkHeight(node.left);
        int right = checkHeight(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right) + 1;
    }

    // 將 BST 每個節點值改為大於等於它的總和
    static int sum = 0;

    public static void convertGreater(TreeNode node) {
        if (node == null)
            return;
        convertGreater(node.right);
        sum += node.val;
        node.val = sum;
        convertGreater(node.left);
    }

    public static void printInOrder(TreeNode root) {
        if (root == null)
            return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    public static void printDoublyList(TreeNode head) {
        TreeNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        TreeNode bst = sortedArrayToBST(arr);
        System.out.print("排序陣列轉 BST：");
        printInOrder(bst);
        System.out.println();
        System.out.println("是否平衡：" + isBalanced(bst));

        convertGreater(bst);
        System.out.print("轉換為大於等於總和：");
        printInOrder(bst);
        System.out.println();

        TreeNode head = bstToDoublyList(bst);
        System.out.print("轉為雙向串列：");
        printDoublyList(head);
    }
}
