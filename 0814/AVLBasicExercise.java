class AVLBasicExercise {
    static final class Node {
        int key, height;
        Node left, right;

        Node(int k) {
            this.key = k;
            this.height = 1;
        }
    }

    static int h(Node n) {
        return n == null ? 0 : n.height;
    }

    static void upd(Node n) {
        n.height = Math.max(h(n.left), h(n.right)) + 1;
    }

    static int balance(Node n) {
        return n == null ? 0 : h(n.left) - h(n.right);
    }

    static Node rotateRight(Node y) {
        Node x = y.left, t2 = x.right;
        x.right = y;
        y.left = t2;
        upd(y);
        upd(x);
        return x;
    }

    static Node rotateLeft(Node x) {
        Node y = x.right, t2 = y.left;
        y.left = x;
        x.right = t2;
        upd(x);
        upd(y);
        return y;
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
        upd(root);
        int bf = balance(root);
        if (bf > 1 && key < root.left.key)
            return rotateRight(root);
        if (bf < -1 && key > root.right.key)
            return rotateLeft(root);
        if (bf > 1 && key > root.left.key) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        if (bf < -1 && key < root.right.key) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        return root;
    }

    static boolean search(Node root, int key) {
        Node cur = root;
        while (cur != null) {
            if (key == cur.key)
                return true;
            cur = key < cur.key ? cur.left : cur.right;
        }
        return false;
    }

    static int height(Node root) {
        return h(root);
    }

    static boolean isValidAVL(Node root) {
        return check(root) != -1;
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

    static void inorder(Node n, StringBuilder sb) {
        if (n == null)
            return;
        inorder(n.left, sb);
        sb.append(n.key).append("(").append(balance(n)).append(") ");
        inorder(n.right, sb);
    }

    public static void main(String[] args) {
        Node root = null;
        int[] vals = { 10, 20, 30, 40, 50, 25 };
        for (int v : vals)
            root = insert(root, v);
        StringBuilder sb = new StringBuilder();
        inorder(root, sb);
        System.out.println("Inorder: " + sb);
        System.out.println("Height: " + height(root));
        System.out.println("Search 25: " + search(root, 25));
        System.out.println("Search 35: " + search(root, 35));
        System.out.println("Valid AVL: " + isValidAVL(root));
    }
}
