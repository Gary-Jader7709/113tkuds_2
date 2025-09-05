import java.io.*;
import java.util.*;

/** 護理紀錄刪除倒數第 N 筆（單趟、雙指標） */
public class LC19_RemoveNth_Node_Clinic {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode d = new ListNode(0);
        d.next = head;
        ListNode fast = d, slow = d;
        for (int i = 0; i < n; i++)
            fast = fast.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return d.next;
    }

    static ListNode build(int[] a) {
        ListNode d = new ListNode(0), c = d;
        for (int v : a) {
            c.next = new ListNode(v);
            c = c.next;
        }
        return d.next;
    }

    static void print(ListNode h, StringBuilder sb) {
        while (h != null) {
            sb.append(h.val);
            if (h.next != null)
                sb.append(' ');
            h = h.next;
        }
        sb.append('\n');
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine().trim());
        ListNode head = build(a);
        head = removeNthFromEnd(head, k);
        StringBuilder sb = new StringBuilder();
        if (head != null)
            print(head, sb);
        else
            sb.append('\n'); // 刪到空串列
        System.out.print(sb.toString());
    }
}
