// 練習 3.7：樹的路徑問題
// 檔名：TreePathProblems.java

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class TreePathProblems {

    // 找出所有從根到葉的路徑
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsPaths(root, new ArrayList<>(), result);
        return result;
    }

    private static void dfsPaths(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null)
            return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            dfsPaths(node.left, path, result);
            dfsPaths(node.right, path, result);
        }
        path.remove(path.size() - 1);
    }

    // 判斷是否有根到葉總和等於目標值
    public static boolean hasPathSum(TreeNode root, int target) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == target;
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }

    // 找出最大根到葉總和
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null)
            return 0;
        return root.val + Math.max(maxRootToLeafSum(root.left), maxRootToLeafSum(root.right));
    }

    // 任意兩節點最大路徑和（樹的直徑）
    static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    private static int maxGain(TreeNode node) {
        if (node == null)
            return 0;
        int left = Math.max(maxGain(node.left), 0);
        int right = Math.max(maxGain(node.right), 0);
        maxSum = Math.max(maxSum, left + right + node.val);
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);

        System.out.println("所有根到葉路徑: " + allRootToLeafPaths(root));
        System.out.println("是否存在總和22的路徑: " + hasPathSum(root, 22));
        System.out.println("最大根到葉總和: " + maxRootToLeafSum(root));
        System.out.println("任意兩節點最大路徑總和: " + maxPathSum(root));
    }
}