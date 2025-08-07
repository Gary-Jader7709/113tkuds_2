// 練習 3.10：多路樹與決策樹
// 檔名：MultiWayTreeAndDecisionTree.java

import java.util.*;

class MultiTreeNode {
    String val;
    List<MultiTreeNode> children;

    MultiTreeNode(String val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}

public class MultiWayTreeAndDecisionTree {

    // 深度優先走訪
    public static void dfs(MultiTreeNode node) {
        if (node == null)
            return;
        System.out.print(node.val + " ");
        for (MultiTreeNode child : node.children) {
            dfs(child);
        }
    }

    // 廣度優先走訪
    public static void bfs(MultiTreeNode root) {
        if (root == null)
            return;
        Queue<MultiTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            MultiTreeNode node = queue.poll();
            System.out.print(node.val + " ");
            queue.addAll(node.children);
        }
    }

    // 計算高度
    public static int height(MultiTreeNode root) {
        if (root == null)
            return 0;
        int maxChild = 0;
        for (MultiTreeNode child : root.children) {
            maxChild = Math.max(maxChild, height(child));
        }
        return maxChild + 1;
    }

    // 計算度數（每個節點最大子節點數）
    public static int maxDegree(MultiTreeNode root) {
        if (root == null)
            return 0;
        int max = root.children.size();
        for (MultiTreeNode child : root.children) {
            max = Math.max(max, maxDegree(child));
        }
        return max;
    }

    // 模擬猜數字的決策樹
    public static MultiTreeNode buildGuessTree() {
        MultiTreeNode root = new MultiTreeNode("數字大於50？");
        MultiTreeNode yes = new MultiTreeNode("大於75？");
        MultiTreeNode no = new MultiTreeNode("小於25？");
        root.children.add(yes);
        root.children.add(no);

        yes.children.add(new MultiTreeNode("是 76~100"));
        yes.children.add(new MultiTreeNode("是 51~75"));

        no.children.add(new MultiTreeNode("是 0~24"));
        no.children.add(new MultiTreeNode("是 25~50"));

        return root;
    }

    public static void main(String[] args) {
        MultiTreeNode root = new MultiTreeNode("A");
        MultiTreeNode b = new MultiTreeNode("B");
        MultiTreeNode c = new MultiTreeNode("C");
        MultiTreeNode d = new MultiTreeNode("D");
        MultiTreeNode e = new MultiTreeNode("E");

        root.children.add(b);
        root.children.add(c);
        b.children.add(d);
        b.children.add(e);

        System.out.print("DFS: ");
        dfs(root);
        System.out.println();
        System.out.print("BFS: ");
        bfs(root);
        System.out.println();
        System.out.println("高度: " + height(root));
        System.out.println("最大度數: " + maxDegree(root));

        System.out.println("\n模擬猜數字決策樹 DFS:");
        dfs(buildGuessTree());
    }
}
