class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BinaryTreeBasicOperations {
    public static int sum(TreeNode root) {
        if (root == null)
            return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    public static int count(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + count(root.left) + count(root.right);
    }

    public static int max(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(max(root.left), max(root.right)));
    }

    public static int min(TreeNode root) {
        if (root == null)
            return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(min(root.left), min(root.right)));
    }

    public static int treeWidth(TreeNode root) {
        if (root == null)
            return 0;
        java.util.Queue<TreeNode> q = new java.util.LinkedList<>();
        q.add(root);
        int maxWidth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            maxWidth = Math.max(maxWidth, size);
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
        }
        return maxWidth;
    }

    public static boolean isCompleteTree(TreeNode root) {
        if (root == null)
            return true;
        java.util.Queue<TreeNode> q = new java.util.LinkedList<>();
        q.add(root);
        boolean foundNull = false;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                foundNull = true;
            } else {
                if (foundNull)
                    return false;
                q.add(node.left);
                q.add(node.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(20);

        System.out.println("總和: " + sum(root));
        System.out.println("平均: " + (double) sum(root) / count(root));
        System.out.println("最大值: " + max(root));
        System.out.println("最小值: " + min(root));
        System.out.println("寬度: " + treeWidth(root));
        System.out.println("是否為完全二元樹: " + isCompleteTree(root));
    }
}