
package Build;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tictactoe {
    private static JFrame frame; // Main application window
    private static JButton[][] buttons; // Buttons representing the board
    private static char[][] board; // Game board state
    private static char currentPlayer; // Current player ('X' or 'O')

    public static void main(String[] args) {
        // Initialize variables
        board = new char[3][3];
        buttons = new JButton[3][3];
        currentPlayer = 'X';

        // Initialize the board with spaces
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }

        // Create the main frame
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on exit
        frame.setSize(400, 400); // Set the size of the window
        frame.setLayout(new GridLayout(3, 3)); // Use a 3x3 grid layout

        // Create buttons for the board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton(" "); // Empty button initially
                button.setFont(new Font("Arial", Font.PLAIN, 40)); // Set font for better visibility
                button.setFocusPainted(false); // Remove focus highlight
                final int r = row; // Capture the row
                final int c = col; // Capture the column

                // Add action listener to handle button clicks
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        makeMove(r, c, button); // Handle the move
                    }
                });

                buttons[row][col] = button; // Store button reference
                frame.add(button); // Add button to the frame
            }
        }

        // Make the frame visible
        frame.setVisible(true);
    }

    // Handle a player's move
    private static void makeMove(int row, int col, JButton button) {
        if (board[row][col] == ' ') { // Check if the cell is empty
            board[row][col] = currentPlayer; // Update the board
            button.setText(String.valueOf(currentPlayer)); // Update the button text

            if (haveWon(currentPlayer)) { // Check if the current player has won
                JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " has won!");
                resetGame(); // Reset the game after a win
            } else if (isBoardFull()) { // Check for a draw
                JOptionPane.showMessageDialog(frame, "It's a draw!");
                resetGame(); // Reset the game after a draw
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch the player
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid Move. Try Again!"); // Show invalid move message
        }
    }

    // Check if the current player has won
    private static boolean haveWon(char player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    // Check if the board is full
    private static boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Reset the game
    private static void resetGame() {
        currentPlayer = 'X'; // Reset to player X
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' '; // Clear the board
                buttons[row][col].setText(" "); // Reset button text
            }
        }
    }
}

