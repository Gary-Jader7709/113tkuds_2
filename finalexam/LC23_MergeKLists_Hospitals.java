import java.io.*;
import java.util.*;

/** 多院區即時叫號合併（最小堆 O(N log k)） */
public class LC23_MergeKLists_Hospitals {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }

    static ListNode mergeKLists(List<ListNode> lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode h : lists)
            if (h != null)
                pq.offer(h);
        ListNode d = new ListNode(0), c = d;
        while (!pq.isEmpty()) {
            ListNode x = pq.poll();
            c.next = x;
            c = c.next;
            if (x.next != null)
                pq.offer(x.next);
        }
        return d.next;
    }

    static ListNode buildLine(String line) {
        StringTokenizer st = new StringTokenizer(line);
        ListNode d = new ListNode(0), c = d;
        while (st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken());
            if (v == -1)
                break;
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
        int k = Integer.parseInt(br.readLine().trim());
        List<ListNode> lists = new ArrayList<>();
        for (int i = 0; i < k; i++)
            lists.add(buildLine(br.readLine()));
        ListNode head = mergeKLists(lists);
        if (head != null)
            print(head);
    }
}
