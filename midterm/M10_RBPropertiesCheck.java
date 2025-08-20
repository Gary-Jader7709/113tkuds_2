package midterm;

import java.util.*;

public class M10_RBPropertiesCheck {
    static class Node {
        int val;
        char color;

        Node(int v, char c) {
            val = v;
            color = c;
        }
    }

    public static boolean isRed(char c) {
        return c == 'R';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            int v = sc.nextInt();
            String c = sc.next();
            if (v == -1) {
                nodes[i] = null;
            } else {
                nodes[i] = new Node(v, c.charAt(0));
            }
        }

        if (nodes[0] != null && nodes[0].color != 'B') {
            System.out.println("RootNotBlack");
            sc.close();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (nodes[i] == null)
                continue;
            if (isRed(nodes[i].color)) {
                int l = 2 * i + 1, r = 2 * i + 2;
                if (l < n && nodes[l] != null && isRed(nodes[l].color)) {
                    System.out.println("RedRedViolation at index " + l);
                    sc.close();
                    return;
                }
                if (r < n && nodes[r] != null && isRed(nodes[r].color)) {
                    System.out.println("RedRedViolation at index " + r);
                    sc.close();
                    return;
                }
            }
        }

        int expected = -1;
        boolean valid = true;
        for (int i = 0; i < n; i++) {
            if (nodes[i] == null) {
                int p = (i - 1) / 2;
                int cur = 0;
                while (p >= 0 && p < n && nodes[p] != null) {
                    if (nodes[p].color == 'B')
                        cur++;
                    if (p == 0)
                        break;
                    p = (p - 1) / 2;
                }
                if (expected == -1)
                    expected = cur;
                else if (expected != cur) {
                    valid = false;
                    break;
                }
            }
        }

        if (!valid) {
            System.out.println("BlackHeightMismatch");
        } else {
            System.out.println("RB Valid");
        }

        sc.close();
    }
}
