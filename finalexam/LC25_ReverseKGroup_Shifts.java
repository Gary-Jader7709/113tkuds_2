import java.io.*;
import java.util.*;

/** 班表 k 組反轉（不足 k 尾段保留） */
public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    static ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null)
            return head;
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

    static ListNode reverse(ListNode h) {
        ListNode p = null, c = h;
        while (c != null) {
            ListNode n = c.next;
            c.next = p;
            p = c;
            c = n;
        }
        return p;
    }

    static ListNode buildFromLine(String line) {
        StringTokenizer st = new StringTokenizer(line);
        ListNode d = new ListNode(0), c = d;
        while (st.hasMoreTokens()) {
            c.next = new ListNode(Integer.parseInt(st.nextToken()));
            c = c.next;
        }
        return d.next;
    }

    static void print(ListNode h) {
        StringBuilder sb = new StringBuilder();
        while (h != null) {
            sb.append(h.val);
            if (h.next != null)
                sb.append(' ');
            h = h.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine().trim());
        ListNode head = buildFromLine(br.readLine());
        head = reverseKGroup(head, k);
        if (head != null)
            print(head);
    }
}
