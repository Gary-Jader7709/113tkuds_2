public class TicTacToeBoard {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = '-';

        placeMove(board, 0, 0, 'X');
        placeMove(board, 1, 1, 'O');
        placeMove(board, 0, 1, 'X');
        placeMove(board, 2, 2, 'O');
        placeMove(board, 0, 2, 'X');

        printBoard(board);
        if (checkWin(board, 'X'))
            System.out.println("X 獲勝");
        else if (checkWin(board, 'O'))
            System.out.println("O 獲勝");
        else if (isFull(board))
            System.out.println("平手");
        else
            System.out.println("遊戲尚未結束");
    }

    public static boolean placeMove(char[][] board, int row, int col, char player) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != '-')
            return false;
        board[row][col] = player;
        return true;
    }

    public static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++)
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player))
                return true;
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public static boolean isFull(char[][] board) {
        for (char[] row : board)
            for (char c : row)
                if (c == '-')
                    return false;
        return true;
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row)
                System.out.print(c + " ");
            System.out.println();
        }
    }
}
