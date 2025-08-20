package midterm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;

        Item(String n, int q) {
            this.name = n;
            this.qty = q;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            items.add(new Item(name, qty));
        }

        PriorityQueue<Item> pq = new PriorityQueue<>(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty)
                    return a.qty - b.qty;
                return a.name.compareTo(b.name);
            }
        });

        for (Item it : items) {
            pq.offer(it);
            if (pq.size() > k)
                pq.poll();
        }

        List<Item> result = new ArrayList<>();
        while (!pq.isEmpty())
            result.add(pq.poll());

        result.sort(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (b.qty != a.qty)
                    return b.qty - a.qty;
                return a.name.compareTo(b.name);
            }
        });

        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }

        sc.close();
    }
}
