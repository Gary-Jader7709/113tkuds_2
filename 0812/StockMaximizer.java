import java.util.*;

public class StockMaximizer {

    // Greedy: split rising segments into profits and pick top K by max-heap.
    public static int maxProfitKTransactions(int[] prices, int K) {
        if (prices == null || prices.length < 2 || K <= 0)
            return 0;
        List<Integer> profits = new ArrayList<>();
        int i = 0, n = prices.length;
        while (i < n - 1) {
            while (i < n - 1 && prices[i + 1] <= prices[i])
                i++;
            int buy = prices[i];
            while (i < n - 1 && prices[i + 1] > prices[i])
                i++;
            int sell = prices[i];
            if (sell - buy > 0)
                profits.add(sell - buy);
        }
        if (profits.isEmpty())
            return 0;
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        max.addAll(profits);
        int ans = 0;
        while (K-- > 0 && !max.isEmpty())
            ans += max.poll();
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxProfitKTransactions(new int[] { 2, 4, 1 }, 2)); // 2
        System.out.println(maxProfitKTransactions(new int[] { 3, 2, 6, 5, 0, 3 }, 2)); // 7
        System.out.println(maxProfitKTransactions(new int[] { 1, 2, 3, 4, 5 }, 2)); // 4
    }
}
