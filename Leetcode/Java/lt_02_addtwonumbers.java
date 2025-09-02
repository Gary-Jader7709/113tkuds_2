// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0); // 虛擬頭節點，方便處理邊界情況
        ListNode current = dummyHead; // 用來構建結果鏈表
        int carry = 0; // 進位值

        // 直到兩個鏈表都處理完，並且沒有進位
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry; // 初始的總和為進位

            if (l1 != null) {
                sum += l1.val; // 加上 l1 的當前值
                l1 = l1.next; // 移動到 l1 的下一個節點
            }

            if (l2 != null) {
                sum += l2.val; // 加上 l2 的當前值
                l2 = l2.next; // 移動到 l2 的下一個節點
            }

            carry = sum / 10; // 計算新的進位
            current.next = new ListNode(sum % 10); // 創建新的節點，保存當前位數
            current = current.next; // 移動到新的節點
        }

        return dummyHead.next; // 返回去掉虛擬頭節點的結果鏈表
    }
}

public class lt_02_addtwonumbers {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 建立測試鏈表 l1: [2,4,3] 代表 342
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        // 建立測試鏈表 l2: [5,6,4] 代表 465
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        // 調用 addTwoNumbers 方法
        ListNode result = solution.addTwoNumbers(l1, l2);

        // 打印結果鏈表，應該是 [7, 0, 8] 代表 807
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
