import java.util.*;

public class LT_22 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    void backtrack(List<String> a, StringBuilder sb, int open, int close, int n) {
        if (sb.length() == 2 * n) {
            a.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            backtrack(a, sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            backtrack(a, sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new LT_22().generateParenthesis(3));
    }
}
