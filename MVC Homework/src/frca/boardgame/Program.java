/*
 * Main class to initialize and run boardgame
 */
package frca.boardgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Test commit
 *
 * @author Justin Tennant
 */
public class Program {

    Dice dice;
    int CPI;
    LinkedList<View> playerList;
    Player currentPlayer;
    Player playerOne;
    Player playerTwo;
    JButton rollButton;
    JLabel diceLabel;
    JPanel scorePanel;
    JLabel panel1;
    JLabel panel2;

    public static void main(String[] args) {
        Program p = new Program();
        p.go();
    }

    public void go() {
        //Pre-made game board
        LinkedList<Space> spaces = new LinkedList<Space>();
        spaces.add(new Space("Test0", Color.cyan, 105, true, false));
        spaces.add(new Space("Test1", Color.cyan, 1, false, false));
        spaces.add(new Space("Test2", Color.cyan, 2, false, false));
        spaces.add(new Space("Test3", Color.cyan, 3, false, true));
        spaces.add(new Space("Test4", Color.cyan, 4, true, false));
        spaces.add(new Space("Test5", Color.cyan, 5, false, false));
        spaces.add(new Space("Test6", Color.cyan, 6, false, false));
        spaces.add(new Space("Test7", Color.cyan, 7, false, false));
        spaces.add(new Space("Test8", Color.cyan, 8, false, true));
        spaces.add(new Space("Test9", Color.cyan, 9, true, false));
        spaces.add(new Space("Test10", Color.cyan, 10, false, false));
        spaces.add(new Space("Test11", Color.cyan, 11, true, false));
        spaces.add(new Space("Test12", Color.cyan, 12, false, false));
        spaces.add(new Space("Test13", Color.cyan, 13, false, false));
        spaces.add(new Space("Test14", Color.cyan, 14, false, true));
        spaces.add(new Space("Test15", Color.red, 15, false, true));

        //create list of players
        playerList = new LinkedList<View>();

        //create the first player
        GamePiece playerOnePiece = new GamePiece("3.png");
        playerOne = new Player("Hulk", 0, 0, playerOnePiece);
        playerList.add(playerOne);

        //create the second player
        GamePiece playerTwoPiece = new GamePiece("2.png");
        playerTwo = new Player("Rey", 0, 0, playerTwoPiece);
        playerList.add(playerTwo);

        playerOne.setNext(playerTwo);
        playerTwo.setNext(playerOne);
        currentPlayer = playerOne;
        CPI = playerList.indexOf(playerOne);
        //add players as observers of the gameboard
        GameBoard gb = new GameBoard(spaces, playerList);

        Dimension wSize = new Dimension(800, 600);

        //make a 800x600 window
        JFrame bgWindow = new JFrame("Game Board");
        bgWindow.setSize(wSize);
        bgWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bgWindow.setLayout(new BorderLayout());

        //create a pair of dice and add the gameboard as a viewer
        dice = new Dice(gb);

        //create a layered jpane to display the board, the player pieces, and the dice
        JLayeredPane lPane = new JLayeredPane();
        lPane.setSize(bgWindow.getSize());
        lPane.setLayout(null);
        lPane.setBounds(bgWindow.getBounds());

        //add the gameboard to the background layer
        gb.setSize(800, 600);
        gb.setLocation(0, 0);
        lPane.add(gb, -1);

        //add player one's piece to the starting position
        playerOnePiece.setSize(100, 200);
        playerOnePiece.setPreferredSize(new Dimension(100, 200));
        playerOnePiece.setLocation(gb.spaces.get(0).getX(), gb.spaces.get(0).getY());
        lPane.add(playerOnePiece, 0);

        //add player two's piece to the starting position
        playerTwoPiece.setSize(100, 200);
        playerTwoPiece.setPreferredSize(new Dimension(100, 200));
        playerTwoPiece.setLocation(gb.spaces.get(0).getX() + 10, gb.spaces.get(0).getY() + 10); //offset so it doesnt overlap with player one
        lPane.add(playerTwoPiece, 1);

        //add the dice to the center of the window
        dice.setSize(bgWindow.getWidth() * 3 / 5, bgWindow.getHeight() * 3 / 5);
        dice.setLocation(bgWindow.getWidth() / 5, bgWindow.getHeight() / 5);
        lPane.add(dice, 0);

        //add button to roll dice        
        rollButton = new JButton();
        rollButton.addActionListener(new RollListener());

        //add the roll button to the pane
        rollButton.setSize(480, 40);
        rollButton.setLocation(160, 400);
        rollButton.setText(playerOne.getName() + "'s turn.");
        lPane.add(rollButton, 2);

        //add the label to the pane
        diceLabel = new JLabel();
        diceLabel.setSize(200, 80);
        diceLabel.setLocation(370, 340);
        lPane.add(diceLabel, 2);

        //add a pane that shows player names and scores
        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(2, 1));
        panel1 = new JLabel();
        panel2 = new JLabel();
        panel1.setText(playerOne.getName() + " : " + playerOne.getScore());
        panel2.setText(playerTwo.getName() + " : " + playerTwo.getScore());
        scorePanel.add(panel1);
        scorePanel.add(panel2);

        bgWindow.add(scorePanel, BorderLayout.EAST);
        lPane.setPreferredSize(wSize);
        bgWindow.add(lPane, BorderLayout.CENTER);
        bgWindow.setSize(gb.getSize());

        lPane.validate();
        bgWindow.pack();
        bgWindow.setVisible(true);
    }

    class RollListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            String currentName;
            String nextName;

            //Checks if the player has missing turn
            if (currentPlayer.missingTurn) {
                currentPlayer.missingTurn = false;
                rollButton.setText(currentPlayer.getName() + " misses this turn, click for " + currentPlayer.getNext().getName() + "'s turn");
                currentPlayer = currentPlayer.getNext();
                CPI = playerList.indexOf(currentPlayer);
                return;
            }
            //Dice is rolled
            dice.roll(CPI);
            panel1.setText(playerOne.getName() + " : " + playerOne.getScore());
            panel2.setText(playerTwo.getName() + " : " + playerTwo.getScore());
            if (currentPlayer.getScore() >= 100) {
                JOptionPane.showMessageDialog(null, currentPlayer.getName() + " wins");
                System.exit(0);
            }
            currentName = currentPlayer.getName();
            //Check if the player has extra turn
            if (currentPlayer.extraTurn) {
                currentPlayer.extraTurn = false;
                diceLabel.setText(currentName + " Rolled a:");
                rollButton.setText(currentName + "'s extra turn");
            } else {
                diceLabel.setText(currentName + " Rolled a:");
                currentPlayer = currentPlayer.getNext();
                CPI = playerList.indexOf(currentPlayer);
                currentName = currentPlayer.getName();
                rollButton.setText(currentName + "'s turn");
            }

        }
    }
}
