public class AVLDeleteExercise {
    static final class Node {
        int key, height;
        Node left, right;

        Node(int k) {
            key = k;
            height = 1;
        }
    }

    static int h(Node n) {
        return n == null ? 0 : n.height;
    }

    static void upd(Node n) {
        n.height = Math.max(h(n.left), h(n.right)) + 1;
    }

    static int bf(Node n) {
        return n == null ? 0 : h(n.left) - h(n.right);
    }

    static Node rotR(Node y) {
        Node x = y.left, t2 = x.right;
        x.right = y;
        y.left = t2;
        upd(y);
        upd(x);
        return x;
    }

    static Node rotL(Node x) {
        Node y = x.right, t2 = y.left;
        y.left = x;
        x.right = t2;
        upd(x);
        upd(y);
        return y;
    }

    static Node rebalance(Node n) {
        upd(n);
        int b = bf(n);
        if (b > 1 && bf(n.left) >= 0)
            return rotR(n);
        if (b > 1 && bf(n.left) < 0) {
            n.left = rotL(n.left);
            return rotR(n);
        }
        if (b < -1 && bf(n.right) <= 0)
            return rotL(n);
        if (b < -1 && bf(n.right) > 0) {
            n.right = rotR(n.right);
            return rotL(n);
        }
        return n;
    }

    static Node insert(Node root, int key) {
        if (root == null)
            return new Node(key);
        if (key < root.key)
            root.left = insert(root.left, key);
        else if (key > root.key)
            root.right = insert(root.right, key);
        else
            return root;
        return rebalance(root);
    }

    static Node minNode(Node n) {
        while (n.left != null)
            n = n.left;
        return n;
    }

    static Node delete(Node root, int key) {
        if (root == null)
            return null;
        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {
            if (root.left == null || root.right == null) {
                Node t = (root.left != null) ? root.left : root.right;
                if (t == null)
                    return null;
                root = t;
            } else {
                Node t = minNode(root.right);
                root.key = t.key;
                root.right = delete(root.right, t.key);
            }
        }
        return rebalance(root);
    }

    static void inorder(Node n, StringBuilder sb) {
        if (n == null)
            return;
        inorder(n.left, sb);
        sb.append(n.key).append(" ");
        inorder(n.right, sb);
    }

    static boolean isAVL(Node n) {
        return check(n) != -1;
    }

    static int check(Node n) {
        if (n == null)
            return 0;
        int lh = check(n.left);
        if (lh == -1)
            return -1;
        int rh = check(n.right);
        if (rh == -1)
            return -1;
        if (Math.abs(lh - rh) > 1)
            return -1;
        return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args) {
        Node root = null;
        int[] vals = { 10, 20, 30, 40, 50, 25, 35, 5, 45 };
        for (int v : vals)
            root = insert(root, v);
        StringBuilder sb = new StringBuilder();
        inorder(root, sb);
        System.out.println("Before: " + sb + " | AVL=" + isAVL(root));

        root = delete(root, 5);
        root = delete(root, 40);
        root = delete(root, 30);

        sb.setLength(0);
        inorder(root, sb);
        System.out.println("After:  " + sb + " | AVL=" + isAVL(root));
    }
}
