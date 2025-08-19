public class AVLRotationExercise {
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

    static Node rightRotate(Node y) {
        Node x = y.left, t2 = x.right;
        x.right = y;
        y.left = t2;
        upd(y);
        upd(x);
        return x;
    }

    static Node leftRotate(Node x) {
        Node y = x.right, t2 = y.left;
        y.left = x;
        x.right = t2;
        upd(x);
        upd(y);
        return y;
    }

    static Node rotateLR(Node z) {
        z.left = leftRotate(z.left);
        return rightRotate(z);
    }

    static Node rotateRL(Node z) {
        z.right = rightRotate(z.right);
        return leftRotate(z);
    }

    static void inorder(Node n, StringBuilder sb) {
        if (n == null)
            return;
        inorder(n.left, sb);
        sb.append(n.key).append(" ");
        inorder(n.right, sb);
    }

    public static void main(String[] args) {
        Node ll = new Node(10);
        ll.left = new Node(5);
        ll.left.left = new Node(3);
        upd(ll.left.left);
        upd(ll.left);
        upd(ll);
        ll = rightRotate(ll);

        Node rr = new Node(10);
        rr.right = new Node(15);
        rr.right.right = new Node(20);
        upd(rr.right.right);
        upd(rr.right);
        upd(rr);
        rr = leftRotate(rr);

        Node lr = new Node(10);
        lr.left = new Node(5);
        lr.left.right = new Node(7);
        upd(lr.left.right);
        upd(lr.left);
        upd(lr);
        lr = rotateLR(lr);

        Node rl = new Node(10);
        rl.right = new Node(15);
        rl.right.left = new Node(12);
        upd(rl.right.left);
        upd(rl.right);
        upd(rl);
        rl = rotateRL(rl);

        StringBuilder a = new StringBuilder(), b = new StringBuilder(),
                c = new StringBuilder(), d = new StringBuilder();
        inorder(ll, a);
        inorder(rr, b);
        inorder(lr, c);
        inorder(rl, d);
        System.out.println("LL fixed: " + a);
        System.out.println("RR fixed: " + b);
        System.out.println("LR fixed: " + c);
        System.out.println("RL fixed: " + d);
    }
}
