import java.util.*;

public class PriorityQueueWithHeap {
    private static long tsGen = 0;

    private static class Task {
        final String name;
        int priority;
        final long ts;

        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
            this.ts = ++tsGen;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Task))
                return false;
            return Objects.equals(name, ((Task) o).name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return name + "(" + priority + ")";
        }
    }

    private final Comparator<Task> cmp = (a, b) -> a.priority != b.priority ? Integer.compare(b.priority, a.priority)
            : Long.compare(a.ts, b.ts);
    private final PriorityQueue<Task> pq = new PriorityQueue<>(cmp);
    private final Map<String, Task> index = new HashMap<>();

    public void addTask(String name, int priority) {
        if (index.containsKey(name))
            throw new IllegalArgumentException("duplicate task name");
        Task t = new Task(name, priority);
        index.put(name, t);
        pq.add(t);
    }

    public String executeNext() {
        Task t = pq.poll();
        if (t == null)
            return null;
        index.remove(t.name);
        return t.name;
    }

    public String peek() {
        Task t = pq.peek();
        return t == null ? null : t.name;
    }

    public void changePriority(String name, int newPriority) {
        Task t = index.get(name);
        if (t == null)
            return;
        pq.remove(t); // O(n) but acceptable for demo
        t.priority = newPriority;
        pq.add(t);
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap q = new PriorityQueueWithHeap();
        q.addTask("backup", 1);
        q.addTask("hotfix", 5);
        q.addTask("update", 3);
        System.out.println(q.peek()); // hotfix
        System.out.println(q.executeNext()); // hotfix
        System.out.println(q.executeNext()); // update
        System.out.println(q.executeNext()); // backup

        // changePriority demo
        q.addTask("A", 1);
        q.addTask("B", 2);
        q.changePriority("A", 10);
        System.out.println(q.executeNext()); // A
        System.out.println(q.executeNext()); // B
    }
}
