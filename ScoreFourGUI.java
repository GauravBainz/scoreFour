import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreFourGUI extends JFrame {
    private JButton[] buttons = new JButton[16];
    private JLabel statusLabel;
    private int currentPlayer;

    public ScoreFourGUI() {
        super("Score Four Game");
        setLayout(new GridLayout(5, 4));

        // Add buttons for the game board
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton("");
            final int index = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle button click (placing bead) here
                    placeBead(index);
                }
            });
            add(buttons[i]);
        }

        // Add status label for game information
        statusLabel = new JLabel("Player 1's turn");
        add(statusLabel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 450);
        setVisible(true);
    }
    private void disableAllButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false); // Disable each button
        }
    }

    private void placeBead(int buttonIndex) {
        JButton button = buttons[buttonIndex];
        if (button.getText().isEmpty()) { // Check if the button is empty
            if (currentPlayer == 1) {
                button.setBackground(Color.BLACK); // Set background color to black for Player 1's bead
                button.setText("1"); // Optionally set text or icon to indicate Player 1's bead
            } else {
                button.setBackground(Color.WHITE); // Set background color to white for Player 2's bead
                button.setText("2"); // Optionally set text or icon to indicate Player 2's bead
            }
            currentPlayer = (currentPlayer == 1) ? 2 : 1;// Toggle the current player

            statusLabel.setText("Player " + currentPlayer + "'s turn");// Update status label
            if (checkForWinner()) {
                statusLabel.setText("Player " + currentPlayer + " wins!"); // Update status label for winner
                disableAllButtons();
                // You can also add additional logic here for what happens when the game ends
            }
            // Update game status, check for win/draw, etc.
        } else {
            statusLabel.setText("choose another slot , this slot is already full");
        }
    }private boolean checkForWinner() {
        // Check horizontal win
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int index = row * 4 + col;
                JButton button = buttons[index];
                String beadType = button.getText();

                if (!beadType.isEmpty()) {
                    // Check for horizontal win
                    if (col < 2 && beadType.equals(buttons[index + 1].getText())
                            && beadType.equals(buttons[index + 2].getText())
                            && beadType.equals(buttons[index + 3].getText())) {
                        highlightWinningButtons(buttons[index], buttons[index + 1], buttons[index + 2], buttons[index + 3]);
                        return true;
                    }

                    // Check for vertical win
                    if (row < 2 && beadType.equals(buttons[index + 4].getText())
                            && beadType.equals(buttons[index + 8].getText())
                            && beadType.equals(buttons[index + 12].getText())) {
                        highlightWinningButtons(buttons[index], buttons[index + 4], buttons[index + 8], buttons[index + 12]);
                        return true;
                    }

                    // Check for diagonal win (top-left to bottom-right)
                    if (row < 2 && col > 1 && beadType.equals(buttons[index + 3].getText())
                            && beadType.equals(buttons[index + 6].getText())
                            && beadType.equals(buttons[index + 9].getText())) {
                        highlightWinningButtons(buttons[index], buttons[index + 3], buttons[index + 6], buttons[index + 9]);
                        return true;
                    }

                    // Check diagonal win (top-left to bottom-right)
                    if (row < 2 && col < 2 && beadType.equals(buttons[index + 5].getText())
                            && beadType.equals(buttons[index + 10].getText())
                            && beadType.equals(buttons[index + 15].getText())) {
                        highlightWinningButtons(buttons[index], buttons[index + 5], buttons[index + 10], buttons[index + 15]);
                        return true;
                    }
                }
            }
        }

        return false; // No winner yet
    }



    private void highlightWinningButtons(JButton... buttons) {
        for (JButton button : buttons) {
            button.setBackground(Color.GREEN);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScoreFourGUI::new);
    }
}
