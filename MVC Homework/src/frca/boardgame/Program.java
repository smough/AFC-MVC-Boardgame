/*
 * Main class to initialize and run boardgame
 */
package frca.boardgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

/**
 * Test commit
 * @author Justin Tennant
 */
public class Program {
    
    public static void main(String[] args)
    {
        //Pre-made game board
        LinkedList<Space> spaces = new LinkedList<Space>();
        spaces.add(new Space("Test0", Color.cyan, 0, false, false));
        spaces.add(new Space("Test1", Color.cyan, 0, false, false));
        spaces.add(new Space("Test2", Color.cyan, 0, false, false));
        spaces.add(new Space("Test3", Color.cyan, 0, false, false));
        spaces.add(new Space("Test4", Color.cyan, 0, false, false));
        spaces.add(new Space("Test5", Color.cyan, 0, false, false));
        spaces.add(new Space("Test6", Color.cyan, 0, false, false));
        spaces.add(new Space("Test7", Color.cyan, 0, false, false));
        spaces.add(new Space("Test8", Color.cyan, 0, false, false));
        spaces.add(new Space("Test9", Color.cyan, 0, false, false));
        spaces.add(new Space("Test10", Color.cyan, 0, false, false));
        spaces.add(new Space("Test11", Color.cyan, 0, false, false));
        spaces.add(new Space("Test12", Color.cyan, 0, false, false));
        spaces.add(new Space("Test13", Color.cyan, 0, false, false));
        spaces.add(new Space("Test14", Color.cyan, 0, false, false));
        spaces.add(new Space("Test15", Color.red, 0, false, false));
        
        //create list of players
        LinkedList<View> playerList = new LinkedList<View>();

        //create the first player
        GamePiece playerOnePiece = new GamePiece("3.png");
        Player playerOne = new Player("Hulk", 0, 0, playerOnePiece);
        playerList.add(playerOne);
        
        //create the second player
        GamePiece playerTwoPiece = new GamePiece("2.png");
        Player playerTwo = new Player("Rey", 0, 0, playerTwoPiece);
        playerList.add(playerTwo);
        
        //add players as observers of the gameboard
        GameBoard gb = new GameBoard(spaces, playerList);
        
        Dimension wSize = new Dimension(800, 600);
        
        //make a 800x600 window
        JFrame bgWindow = new JFrame("Game Board");
        bgWindow.setSize(wSize);
        bgWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bgWindow.setLayout(new GridLayout(1,1));
        
        //create a pair of dice and add the gameboard as a viewer
        final Dice dice = new Dice(gb);
        
        //create a layered jpane to display the board, the player pieces, and the dice
        JLayeredPane lPane = new JLayeredPane();
        lPane.setSize(bgWindow.getSize());
        lPane.setLayout(null);
        lPane.setBounds(bgWindow.getBounds());
        
        //add the gameboard to the background layer
        gb.setSize(800, 600);
        gb.setLocation(0,0);
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
        dice.setSize(bgWindow.getWidth()*3/5, bgWindow.getHeight()*3/5);
        dice.setLocation(bgWindow.getWidth()/5, bgWindow.getHeight()/5);
        lPane.add(dice, 0);
        
        //add button to roll dice        
        JButton rollButton = new JButton();
        rollButton.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent arg0)
			{
				dice.roll(0);
			}
        });
        
        //add the roll button to the pane
        rollButton.setSize(200, 40);
        rollButton.setLocation(400, 300);
        lPane.add(rollButton);
        
        bgWindow.add(lPane);
        bgWindow.setSize(gb.getSize());
        
        bgWindow.setVisible(true);
        
//        //main game loop
//        boolean testBool = true;
//        while(testBool) //this shouldnt be the actual check the loop does
//        {
//        	//TODO add main game loop
//        }
    }
}