import java.util.*;

public class LT_30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s.isEmpty() || words.length == 0)
            return res;
        int wlen = words[0].length();
        Map<String, Integer> need = new HashMap<>();
        for (String w : words)
            need.put(w, need.getOrDefault(w, 0) + 1);
        for (int off = 0; off < wlen; off++) {
            int left = off, count = 0;
            Map<String, Integer> win = new HashMap<>();
            for (int r = off; r + wlen <= s.length(); r += wlen) {
                String w = s.substring(r, r + wlen);
                if (need.containsKey(w)) {
                    win.put(w, win.getOrDefault(w, 0) + 1);
                    count++;
                    while (win.get(w) > need.get(w)) {
                        String lw = s.substring(left, left + wlen);
                        win.put(lw, win.get(lw) - 1);
                        left += wlen;
                        count--;
                    }
                    if (count == words.length) {
                        res.add(left);
                        String lw = s.substring(left, left + wlen);
                        win.put(lw, win.get(lw) - 1);
                        left += wlen;
                        count--;
                    }
                } else {
                    win.clear();
                    count = 0;
                    left = r + wlen;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LT_30().findSubstring("barfoothefoobarman", new String[] { "foo", "bar" })); // [0,9]
    }
}
