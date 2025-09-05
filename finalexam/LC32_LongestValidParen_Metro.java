import java.io.*;
import java.util.*;

/** 北捷進出站最長有效片段（棧索引法） */
public class LC32_LongestValidParen_Metro {
    static int longestValidParentheses(String s) {
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                st.push(i);
            else {
                st.pop();
                if (st.isEmpty())
                    st.push(i);
                else
                    ans = Math.max(ans, i - st.peek());
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null)
            s = "";
        System.out.println(longestValidParentheses(s));
    }
}
