import java.io.*;
import java.util.*;

/** 緊急通報格式括號檢查 */
public class LC20_ValidParentheses_AlertFormat {
    static boolean isValid(String s) {
        Deque<Character> st = new ArrayDeque<>();
        Map<Character, Character> map = Map.of(')', '(', ']', '[', '}', '{');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsValue(c))
                st.push(c);
            else if (map.containsKey(c)) {
                if (st.isEmpty() || st.peek() != map.get(c))
                    return false;
                st.pop();
            } else
                return false; // 非法字元（保險）
        }
        return st.isEmpty();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null)
            s = "";
        System.out.println(isValid(s) ? "true" : "false");
    }
}
