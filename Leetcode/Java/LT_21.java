public class LT_21 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
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
        LT_21 s = new LT_21();
        print(s.mergeTwoLists(of(1, 2, 4), of(1, 3, 4)));
    }
}
