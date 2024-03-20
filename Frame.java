package board.BoardV2;
import board.interfaces.BeadColour;
import board.interfaces.PlayerType;
import board.player.AIPlayer;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Gaurav Bains
 */
public class Frame extends JFrame {
    private static Board board;
    private JLabel pegLabel;
    private static PlayerType human = PlayerType.PLAYER_ONE;
    private static PlayerType ai = PlayerType.PLAYER_TWO;
    static AIPlayer aiPlayer = new AIPlayer(board, PlayerType.PLAYER_TWO);

    public Frame(Board board) {
        //initializing the frame and board
        this.board = board;
        setSize(600, 600);
        setTitle("Hoodoo Jo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //adding the panel for the board
        JPanel boardPanel = new JPanel(new GridLayout(4, 4));
        boardPanel.setBackground(Color.lightGray);
        add(boardPanel);

        //adding pegPanel
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Peg peg = board.getBoard()[i][j];
                JPanel pegPanel = createPegPanel(peg, i, j);
                boardPanel.add(pegPanel);
            }
        }

        setVisible(true);
    }

    //creates all the panels for the pegs
    private JPanel createPegPanel(Peg peg, int row, int column) {
        JPanelWithIndices pegPanel = new JPanelWithIndices(row, column);
        pegPanel.setLayout(new GridLayout(5, 1));

        //4 levels per
        for (int level = 0; level < 4; level++) {
            JLabel beadLabel = new JLabel();
            configureBeadLabel(beadLabel, peg.getColourAt(level));
            pegPanel.add(beadLabel);
        }

        pegPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

        //adding place bead button to add a bead on a peg
        JButton placeBeadButton = new JButton("Place Bead");
        placeBeadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //getting location of pegs so knows where to place
                int row = pegPanel.getRow();
                int column = pegPanel.getColumn();

                //allows you to place bead and then Ai will go after
                board.placeBead(row, column, human);
                updateBeadLabels(pegPanel, peg);


                if (Board.checkWin(human)) {
                    JOptionPane.showMessageDialog(null, "Game Over, You win!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    board.clear();
                    updateWholeBoard();
                    updateBeadLabels(pegPanel, peg);
                }
                else if (Board.checkWin(ai)) {
                    JOptionPane.showMessageDialog(null, "Game Over, AI wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    board.clear();
                    updateWholeBoard();
                    updateBeadLabels(pegPanel, peg);
                }
                else{
                    aiTurn(pegPanel,peg);
                    updateWholeBoard();
                }
            }
        });
        pegPanel.add(placeBeadButton); // Adding the button to the panel
        return pegPanel;
            }

    private static void configureBeadLabel(JLabel beadLabel, BeadColour beadColour) {
        beadLabel.setPreferredSize(new Dimension(50, 50)); // Set bead size
        beadLabel.setOpaque(true); // Enable background color
        if (beadColour != null) {
            beadLabel.setBackground(beadColour == BeadColour.WHITE ? Color.WHITE : Color.BLACK);
        } else {
            beadLabel.setBackground(Color.LIGHT_GRAY); // Set default color for empty spaces
        }
    }

    private static void updateBeadLabels(JPanelWithIndices pegPanel, Peg peg) {
        //updates the labels so they display on screen
        Component[] components = pegPanel.getComponents();
        for (int i = 0; i < 4; i++) {
            JLabel beadLabel = (JLabel) components[3-i];
            BeadColour colour = peg.getColourAt(i);
            configureBeadLabel(beadLabel, colour);
        }
    }

    private void aiTurn(JPanelWithIndices pegPanel, Peg peg) {
        //method for the Ai player to go
        AIPlayer aiPlayer = new AIPlayer(board, PlayerType.PLAYER_ONE);
        aiPlayer.playNextMove();
        updateBeadLabels(pegPanel, peg);
        updateWholeBoard();
    }
    public void updateWholeBoard() {
        Peg[][] pegs = board.getBoard();
        for (int i = 0; i < pegs.length; i++) {
            for (int j = 0; j < pegs[i].length; j++) {
                JPanelWithIndices pegPanel = (JPanelWithIndices) ((JPanel) getContentPane().getComponent(0)).getComponent(i * 4 + j);
                Peg peg = pegs[i][j];
                updateBeadLabels(pegPanel, peg);
            }
        }
        repaint();
    }
    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Frame(new Board()).setVisible(true);
            }
        });
    }


}


