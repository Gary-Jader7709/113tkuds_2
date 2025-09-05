public class LT_25 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    private ListNode reverse(ListNode h) {
        ListNode p = null, c = h;
        while (c != null) {
            ListNode n = c.next;
            c.next = p;
            p = c;
            c = n;
        }
        return p;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode d = new ListNode(0);
        d.next = head;
        ListNode pre = d, end = d;
        while (true) {
            for (int i = 0; i < k && end != null; i++)
                end = end.next;
            if (end == null)
                break;
            ListNode start = pre.next, next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
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
        print(new LT_25().reverseKGroup(of(1, 2, 3, 4, 5), 2));
    }
}
