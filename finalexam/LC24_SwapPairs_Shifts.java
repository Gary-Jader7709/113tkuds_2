import java.io.*;
import java.util.*;

/** 班表兩兩交換（相鄰節點成對交換） */
public class LC24_SwapPairs_Shifts {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    static ListNode swapPairs(ListNode head) {
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

    static ListNode buildFromLine(String line) {
        if (line == null || line.isEmpty())
            return null;
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
        ListNode head = buildFromLine(br.readLine());
        head = swapPairs(head);
        if (head != null)
            print(head);
    }
}
