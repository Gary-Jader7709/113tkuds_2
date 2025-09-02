public class lt_14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0]; // 假設第一個字符串是公共前綴
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) { // 判斷當前公共前綴是否是 strs[i] 的前綴
                prefix = prefix.substring(0, prefix.length() - 1); // 去掉最後一個字符，縮小公共前綴
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }

    public static void main(String[] args) {
        lt_14 solution = new lt_14();

        // 測試案例 1
        String[] strs1 = { "flower", "flow", "flight" };
        System.out.println(solution.longestCommonPrefix(strs1)); // 輸出: "fl"

        // 測試案例 2
        String[] strs2 = { "dog", "racecar", "car" };
        System.out.println(solution.longestCommonPrefix(strs2)); // 輸出: ""
    }
}
