import java.util.Stack;

public class lt_20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        // 遍歷字符串
        for (char c : s.toCharArray()) {
            // 如果是開括號，則壓入堆疊
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // 如果是閉括號，檢查堆疊頂部是否有對應的開括號
            else {
                if (stack.isEmpty())
                    return false;
                char top = stack.pop();
                if (c == ')' && top != '(')
                    return false;
                if (c == '}' && top != '{')
                    return false;
                if (c == ']' && top != '[')
                    return false;
            }
        }

        // 如果堆疊是空的，則所有括號匹配成功
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        lt_20 solution = new lt_20();

        // 測試案例 1
        String s1 = "()";
        System.out.println(solution.isValid(s1)); // 輸出: true

        // 測試案例 2
        String s2 = "()[]{}";
        System.out.println(solution.isValid(s2)); // 輸出: true

        // 測試案例 3
        String s3 = "(]";
        System.out.println(solution.isValid(s3)); // 輸出: false

        // 測試案例 4
        String s4 = "([])";
        System.out.println(solution.isValid(s4)); // 輸出: true

        // 測試案例 5
        String s5 = "([)]";
        System.out.println(solution.isValid(s5)); // 輸出: false
    }
}
