public class LT_24 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    public ListNode swapPairs(ListNode head) {
        ListNode d = new ListNode(0);
        d.next = head;
        ListNode p = d;
        while (p.next != null && p.next.next != null) {
            ListNode a = p.next, b = a.next;
            a.next = b.next;
            b.next = a;
            p.next = b;
            p = a;
        }
        return d.next;
    }

    static ListNode of(int... a) {
        ListNode d = new ListNode(0), c = d;
        for (int v : a) {
            c.next = new ListNode(v);
            c = c.next;
        }
        return d.next;
    }

    static void print(ListNode n) {
        while (n != null) {
            System.out.print(n.val + (n.next != null ? "->" : "\n"));
            n = n.next;
        }
    }

    public static void main(String[] args) {
        print(new LT_24().swapPairs(of(1, 2, 3, 4)));
    }
}
