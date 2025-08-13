import java.util.*;

public class ValidMaxHeapChecker {

    public static boolean isValidMaxHeap(int[] a) {
        for (int i = 0; i <= (a.length - 2) / 2; i++) {
            int l = 2 * i + 1, r = 2 * i + 2;
            if (l < a.length && a[i] < a[l])
                return false;
            if (r < a.length && a[i] < a[r])
                return false;
        }
        return true;
    }

    public static int firstViolationIndex(int[] a) {
        for (int i = 0; i <= (a.length - 2) / 2; i++) {
            int l = 2 * i + 1, r = 2 * i + 2;
            if (l < a.length && a[i] < a[l])
                return l;
            if (r < a.length && a[i] < a[r])
                return r;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] tests = {
                { 100, 90, 80, 70, 60, 75, 65 },
                { 100, 90, 80, 95, 60, 75, 65 },
                { 50 },
                {}
        };
        for (int[] t : tests) {
            boolean ok = isValidMaxHeap(t);
            int idx = firstViolationIndex(t);
            System.out.println(Arrays.toString(t) + " -> " + ok + (ok ? "" : (" (violation at index " + idx + ")")));
        }
    }
}
