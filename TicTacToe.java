public class TicTacToe {
    private char[][] board; // 3x3 board for Tic Tac Toe
    private int movesCount; // To track the number of moves made

    // Constructor: Initialize the board and reset the game
    public TicTacToe() {
        board = new char[3][3];
        reset(); // Initialize the board to empty at the start
    }

    // Make a move on the board
    public boolean makeMove(int row, int col, char player) {
        // Ensure the row and column are within bounds and the cell is empty
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            board[row][col] = player; // Mark the move for the player ('X' or 'O')
            movesCount++; // Increment the moves counter
            return true; // Successful move
        }
        return false; // Invalid move (spot is already taken)
    }

    // Check if the current player has won the game
    public boolean checkWin() {
        // Check rows for a win
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != ' ') {
                return true;
            }
        }

        // Check columns for a win
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != ' ') {
                return true;
            }
        }

        // Check diagonals for a win
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return true;
        }

        return false; // No win detected
    }

    // Check if the game is a tie (i.e., the board is full without a winner)
    public boolean checkTie() {
        // If all cells are filled and there is no winner, it's a tie
        return movesCount == 9 && !checkWin();
    }

    // Reset the board for a new game
    public void reset() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' '; // Set all cells to empty
            }
        }
        movesCount = 0; // Reset the move counter
    }

    // (Optional) Display the current board (for debugging or logging)
    public void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
