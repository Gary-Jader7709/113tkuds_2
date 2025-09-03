public class lt_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 創建一個虛擬節點，指向頭節點
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // 讓快指針先走 n 步
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // 快慢指針同時向前走，直到快指針到達尾部
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除慢指針所指的節點
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        lt_19 solution = new lt_19();

        // 測試案例 1: head = [1, 2, 3, 4, 5], n = 2
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

        ListNode result1 = solution.removeNthFromEnd(head1, 2);
        printList(result1); // 輸出: [1, 2, 3, 5]

        // 測試案例 2: head = [1], n = 1
        ListNode head2 = new ListNode(1);
        ListNode result2 = solution.removeNthFromEnd(head2, 1);
        printList(result2); // 輸出: []

        // 測試案例 3: head = [1, 2], n = 1
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        ListNode result3 = solution.removeNthFromEnd(head3, 1);
        printList(result3); // 輸出: [1]
    }

    // 打印鏈表的方法
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}

// 定義 ListNode 類
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
