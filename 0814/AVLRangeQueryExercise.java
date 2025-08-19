import java.util.ArrayList;

public class AVLRangeQueryExercise {
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

    static void rangeQuery(Node n, int lo, int hi, java.util.List<Integer> out) {
        if (n == null)
            return;
        if (n.key > lo)
            rangeQuery(n.left, lo, hi, out);
        if (lo <= n.key && n.key <= hi)
            out.add(n.key);
        if (n.key < hi)
            rangeQuery(n.right, lo, hi, out);
    }

    public static void main(String[] args) {
        Node root = null;
        int[] vals = { 20, 10, 30, 5, 14, 25, 40, 22, 28, 2, 7, 12, 16 };
        for (int v : vals)
            root = insert(root, v);

        java.util.List<Integer> ans = new ArrayList<>();
        rangeQuery(root, 12, 28, ans);
        System.out.println("Range [12,28]: " + ans);
    }
}
