import java.io.*;
import java.util.*;

/** 合併兩院區掛號清單（合併兩個已排序鏈結串列） */
public class LC21_MergeTwoLists_Clinics {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    static ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode d = new ListNode(0), c = d;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                c.next = a;
                a = a.next;
            } else {
                c.next = b;
                b = b.next;
            }
            c = c.next;
        }
        c.next = (a != null) ? a : b;
        return d.next;
    }

    static ListNode build(int[] arr) {
        ListNode d = new ListNode(0), c = d;
        for (int v : arr) {
            c.next = new ListNode(v);
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[] A = new int[n], B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            A[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            B[i] = Integer.parseInt(st.nextToken());
        ListNode head = mergeTwoLists(build(A), build(B));
        print(head);
    }
}
