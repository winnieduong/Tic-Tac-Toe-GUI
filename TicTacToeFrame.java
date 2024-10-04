import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private final JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private TicTacToe gameLogic;

    public TicTacToeFrame() {
        gameLogic = new TicTacToe();

        setTitle("Tic Tac Toe");
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        initializeBoard(boardPanel);

        add(boardPanel, BorderLayout.CENTER);

        // Quit Button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        add(quitButton, BorderLayout.SOUTH);

        JButton declareTieButton = new JButton("Declare Tie");
        declareTieButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            promptPlayAgain();
        });
        add(declareTieButton, BorderLayout.NORTH);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeBoard(JPanel boardPanel) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                boardPanel.add(buttons[row][col]);

                final int r = row;
                final int c = col;
                buttons[row][col].addActionListener(e -> handleButtonClick(r, c));
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        gameLogic.reset();
    }
    private void handleButtonClick(int row, int col) {
        if (!buttons[row][col].getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Invalid move! Try again.");
            return;
        }

        buttons[row][col].setText(String.valueOf(currentPlayer));
        gameLogic.makeMove(row, col, currentPlayer);

        if (gameLogic.checkWin()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            promptPlayAgain();
        } else if (gameLogic.checkTie()) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            promptPlayAgain();
        } else {
            switchPlayer();
        }
    }

    private void promptPlayAgain() {
        int response = JOptionPane.showConfirmDialog(this, "Would you like to play again?", "Play Again", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            resetBoard();
        } else {
            System.exit(0); 
        }
    }

}
