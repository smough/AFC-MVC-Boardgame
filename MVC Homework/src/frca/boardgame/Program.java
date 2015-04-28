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
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

/**
 *
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
        
        LinkedList<View> playerList = new LinkedList<View>();
        playerList.add(new Player("Hulk", 0, 0, "1.jpg"));
        playerList.add(new Player("Rey", 0, 0, "2.jpg"));        
        
        GameBoard gb = new GameBoard(spaces, playerList);
        
        //To-do: add file import (ie: loadGame(File file))
        
        Dimension wSize = new Dimension(800, 600);
        
        JFrame bgWindow = new JFrame("Game Board");
        bgWindow.setSize(wSize);
        bgWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bgWindow.setLayout(new GridLayout(1,1));
        
        GamePiece piece = new GamePiece();
        
        Dice dice = new Dice();
        
        JLayeredPane lPane = new JLayeredPane();
        lPane.setSize(bgWindow.getSize());
        //lPane.setLayout(new GridLayout(1,1));
        lPane.setLayout(null);
        lPane.setBounds(bgWindow.getBounds());
        
        //gb.setBounds(bgWindow.getBounds());
        //gb.setPreferredSize(bgWindow.getSize());
        gb.setSize(800, 600);
        gb.setLocation(0,0);
        lPane.add(gb, -1);
        
        piece.setSize(100, 200);
        //piece.setBounds(new Rectangle(100, 200));
        piece.setPreferredSize(new Dimension(100, 200));
        piece.setLocation(gb.spaces.get(0).getX(), gb.spaces.get(0).getY());
        //piece.setLocation(100, 200);
        lPane.add(piece, 0);
        
        dice.setSize(bgWindow.getWidth()*3/5, bgWindow.getHeight()*3/5);
        dice.setLocation(bgWindow.getWidth()/5, bgWindow.getHeight()/5);
        lPane.add(dice, 0);
        
        System.out.println(bgWindow.getWidth() + " " + bgWindow.getHeight());
        System.out.println(gb.getBounds());
        
        bgWindow.add(lPane);
        bgWindow.setSize(gb.getSize());
        
        //bgWindow.pack();
        bgWindow.setVisible(true);
    }
}