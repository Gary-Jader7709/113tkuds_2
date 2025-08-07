// 練習 3.3：樹的鏡像與對稱
// 檔名：TreeMirrorAndSymmetry.java

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class TreeMirrorAndSymmetry {

    public static boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        return (t1.val == t2.val) &&
                isMirror(t1.left, t2.right) &&
                isMirror(t1.right, t2.left);
    }

    public static TreeNode mirror(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = mirror(root.left);
        TreeNode right = mirror(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)
            return false;
        if (isSameTree(s, t))
            return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;
        return (a.val == b.val) && isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    public static boolean isMirrorTree(TreeNode a, TreeNode b) {
        return isMirror(a, b);
    }

    public static void printPreOrder(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(root));

        System.out.print("原始樹前序走訪: ");
        printPreOrder(root);
        System.out.println();

        TreeNode mirrored = mirror(root);
        System.out.print("鏡像樹前序走訪: ");
        printPreOrder(mirrored);
        System.out.println();

        TreeNode subtree = new TreeNode(2);
        subtree.left = new TreeNode(4);
        subtree.right = new TreeNode(3);

        System.out.println("是否為子樹: " + isSubtree(mirrored, subtree));
        System.out.println("是否互為鏡像: " + isMirrorTree(root, mirrored));
    }
}
