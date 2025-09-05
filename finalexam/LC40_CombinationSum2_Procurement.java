import java.io.*;
import java.util.*;

/** 防災物資組合總和 II（每數最多用一次，需去重） */
public class LC40_CombinationSum2_Procurement {
    static void dfs(int[] c, int i, int rest, Deque<Integer> path, List<List<Integer>> out) {
        if (rest == 0) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int k = i; k < c.length && c[k] <= rest; k++) {
            if (k > i && c[k] == c[k - 1])
                continue; // 同層去重
            path.addLast(c[k]);
            dfs(c, k + 1, rest - c[k], path, out);
            path.removeLast();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), target = Integer.parseInt(st.nextToken());
        int[] c = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            c[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(c);
        List<List<Integer>> ans = new ArrayList<>();
        dfs(c, 0, target, new ArrayDeque<>(), ans);
        StringBuilder sb = new StringBuilder();
        for (List<Integer> one : ans) {
            for (int i = 0; i < one.size(); i++) {
                sb.append(one.get(i));
                if (i + 1 < one.size())
                    sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
