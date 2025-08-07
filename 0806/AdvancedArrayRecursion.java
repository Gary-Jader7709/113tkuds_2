import java.util.*;

public class AdvancedArrayRecursion {
    public static void main(String[] args) {
        int[] arr = { 4, 2, 7, 1, 5 };
        quickSort(arr, 0, arr.length - 1);
        System.out.println("QuickSort: " + Arrays.toString(arr));

        int[] a = { 1, 3, 5 };
        int[] b = { 2, 4, 6 };
        int[] merged = mergeArrays(a, b, 0, 0);
        System.out.println("Merged: " + Arrays.toString(merged));

        int[] data = { 7, 2, 1, 4, 6 };
        System.out.println("Kth smallest (3): " + kthSmallest(data, 3));

        int[] nums = { 1, 2, 3, 7 };
        System.out.println("Subset sum = 6 exists? " + subsetSum(nums, 0, 6));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static int[] mergeArrays(int[] a, int[] b, int i, int j) {
        if (i == a.length)
            return Arrays.copyOfRange(b, j, b.length);
        if (j == b.length)
            return Arrays.copyOfRange(a, i, a.length);
        if (a[i] < b[j]) {
            return merge(a[i], mergeArrays(a, b, i + 1, j));
        } else {
            return merge(b[j], mergeArrays(a, b, i, j + 1));
        }
    }

    private static int[] merge(int val, int[] rest) {
        int[] result = new int[rest.length + 1];
        result[0] = val;
        System.arraycopy(rest, 0, result, 1, rest.length);
        return result;
    }

    public static int kthSmallest(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k - 1];
    }

    public static boolean subsetSum(int[] arr, int index, int target) {
        if (target == 0)
            return true;
        if (index == arr.length)
            return false;
        return subsetSum(arr, index + 1, target - arr[index]) || subsetSum(arr, index + 1, target);
    }
}
