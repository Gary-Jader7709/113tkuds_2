import java.util.*;
public class LT_23 {
    static class ListNode { int val; ListNode next; ListNode(int v){val=v;} }
    public ListNode mergeKLists(ListNode[] lists){
        PriorityQueue<ListNode> pq=new PriorityQueue<>(Comparator.comparingInt(a->a.val));
        for(ListNode n:lists) if(n!=null) pq.offer(n);
        ListNode d=new ListNode(-1),c=d;
        while(!pq.isEmpty()){
            ListNode n=pq.poll(); c.next=n; c=c.next;
            if(n.next!=null) pq.offer(n.next);
        }
        return d.next;
    }
    static ListNode of(int... a){ ListNode d=new ListNode(0),c=d; for(int v:a){c.next=new ListNode(v); c=c.next;} return d.next; }
    static void print(ListNode n){ while(n!=null){ System.out.print(n.val+(n.next!=null?"->":"\n")); n=n.next; } }
    public static void main(String[] args){
        LT_23 s=new LT_23();
        ListNode[] lists=new ListNode[]{of(1,4,5),of(1,3,4),of(2,6)};
        print(s.mergeKLists(lists));
    }
}
