package midterm;

import java.util.*;

public class M11_HeapSortWithTie {
    static class Item {
        int score;
        int index;

        Item(int s, int i) {
            score = s;
            index = i;
        }
    }

    public static void heapify(Item[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && compare(arr[l], arr[largest]) > 0)
            largest = l;
        if (r < n && compare(arr[r], arr[largest]) > 0)
            largest = r;

        if (largest != i) {
            Item tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            heapify(arr, n, largest);
        }
    }

    public static int compare(Item a, Item b) {
        if (a.score != b.score)
            return a.score - b.score;
        return b.index - a.index;
    }

    public static void heapSort(Item[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            Item tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Item[] arr = new Item[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Item(sc.nextInt(), i);
        }
        heapSort(arr, n);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score);
            if (i < n - 1)
                System.out.print(" ");
        }
        sc.close();
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：建堆 O(n)，每次取最大值並重新 heapify O(log n)，共 n 次 → O(n log n)。
 * tie-breaking (同分數時依 index) 在比較函數中為 O(1)，不影響整體複雜度。
 */
