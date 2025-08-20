package midterm;

import java.util.Scanner;

public class M01_BuildHeap {
    static boolean isMax;

    public static void heapify(int[] arr, int n, int i) {
        int extreme = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (isMax) {
            if (left < n && arr[left] > arr[extreme]) {
                extreme = left;
            }
            if (right < n && arr[right] > arr[extreme]) {
                extreme = right;
            }
        } else {
            if (left < n && arr[left] < arr[extreme]) {
                extreme = left;
            }
            if (right < n && arr[right] < arr[extreme]) {
                extreme = right;
            }
        }

        if (extreme != i) {
            int temp = arr[i];
            arr[i] = arr[extreme];
            arr[extreme] = temp;
            heapify(arr, n, extreme);
        }
    }

    public static void buildHeap(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        isMax = type.equals("max");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        buildHeap(arr, n);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1)
                System.out.print(" ");
        }
        sc.close();
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆，每個節點的 heapifyDown 高度不一，總計為 Σ O(log h)，
 * 推導結果為 O(n)，比逐一插入 O(n log n) 更快。
 */
