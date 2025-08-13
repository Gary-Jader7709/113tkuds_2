import java.util.*;

public class BasicMinHeapPractice {
    private final List<Integer> heap = new ArrayList<>();

    public void insert(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }

    public int extractMin() {
        if (heap.isEmpty())
            throw new NoSuchElementException("heap is empty");
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    public int getMin() {
        if (heap.isEmpty())
            throw new NoSuchElementException("heap is empty");
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int p = (i - 1) / 2;
            if (heap.get(i) < heap.get(p)) {
                swap(i, p);
                i = p;
            } else
                break;
        }
    }

    private void heapifyDown(int i) {
        int n = heap.size();
        while (true) {
            int l = 2 * i + 1, r = 2 * i + 2, smallest = i;
            if (l < n && heap.get(l) < heap.get(smallest))
                smallest = l;
            if (r < n && heap.get(r) < heap.get(smallest))
                smallest = r;
            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else
                break;
        }
    }

    private void swap(int a, int b) {
        int t = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, t);
    }

    public static void main(String[] args) {
        BasicMinHeapPractice h = new BasicMinHeapPractice();
        int[] in = { 15, 10, 20, 8, 25, 5 };
        for (int v : in)
            h.insert(v);
        List<Integer> order = new ArrayList<>();
        while (!h.isEmpty())
            order.add(h.extractMin());
        System.out.println(order); // expected [5, 8, 10, 15, 20, 25]
    }
}
