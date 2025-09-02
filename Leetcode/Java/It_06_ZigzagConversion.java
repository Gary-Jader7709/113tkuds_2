public class It_06_ZigzagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        // 用來存儲每行的字符
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        // 填充每行
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }
            currentRow += goingDown ? 1 : -1;
        }

        // 合併每行的結果
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        It_06_ZigzagConversion solution = new It_06_ZigzagConversion();

        // 測試案例 1
        String s1 = "PAYPALISHIRING";
        int numRows1 = 3;
        System.out.println(solution.convert(s1, numRows1)); // 輸出: "PAHNAPLSIIGYIR"

        // 測試案例 2
        String s2 = "PAYPALISHIRING";
        int numRows2 = 4;
        System.out.println(solution.convert(s2, numRows2)); // 輸出: "PINALSIGYAHRPI"

        // 測試案例 3
        String s3 = "A";
        int numRows3 = 1;
        System.out.println(solution.convert(s3, numRows3)); // 輸出: "A"
    }
}
