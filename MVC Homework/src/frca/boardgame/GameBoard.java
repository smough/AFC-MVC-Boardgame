package frca.boardgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JComponent;

/**
 *
 * @author Frank Vescio
 */
public class GameBoard extends JComponent implements Model{

	LinkedList<Space> spaces = new LinkedList<Space>();
	private int winningScore;
	LinkedList<View> viewers = new LinkedList<View>();

	public GameBoard(LinkedList<Space> spaces, LinkedList<View> viewers)
	{
		this.spaces = spaces;
		this.viewers = viewers;
	}

	/*
	 * Draws the game board to the JFrame
	 */
	public void paintComponent(Graphics g)
	{	
		Graphics2D g2d = (Graphics2D) g;
		//int parentWidth = this.getParent().getBounds().width;
		//int parentHeight = this.getParent().getBounds().height;
		int parentWidth = this.getWidth();
		int parentHeight = this.getHeight();
		int j = spaces.size()/4;

		//make the top row
		for(int i = 0; i < j; i++)
		{
			Space toAdd = spaces.get(i);
			g2d.setColor(toAdd.getColor());
			g2d.fillRect(i*parentWidth/(j+1), 0, parentWidth/(j+1), parentHeight/(j+1));
			g2d.setColor(Color.black);
			g2d.drawString(toAdd.getName(), i*parentWidth/(j+1) + 10, 10);
			toAdd.setX(i*parentWidth/(j+1) + 10);
			toAdd.setY(10);
		}

		//make the right column
		for(int i = j; i < 2*j; i++)
		{
			Space toAdd = spaces.get(i);
			g2d.setColor(toAdd.getColor());
			g2d.fillRect(j*parentWidth/(j+1), (i-j)*parentHeight/(j+1), parentWidth/(j+1), parentHeight/(j+1));
			g2d.setColor(Color.black);
			g2d.drawString(toAdd.getName(), j*parentWidth/(j+1) + 10, (i-j)*parentHeight/(j+1) + 10);
			toAdd.setX(j*parentWidth/(j+1) + 10);
			toAdd.setY((i-j)*parentHeight/(j+1) + 10);
		}

		//add the bottom row
		for(int i = 2*j; i < 3*j; i++)
		{
			Space toAdd = spaces.get(i);
			g2d.setColor(toAdd.getColor());
			g2d.fillRect((3*j-i)*parentWidth/(j+1), j*parentHeight/(j+1), parentWidth/(j+1), parentHeight/(j+1));
			g2d.setColor(Color.black);
			g2d.drawString(toAdd.getName(), (3*j-i)*parentWidth/(j+1) + 10, j*parentHeight/(j+1) + 10);
			toAdd.setX((3*j-i)*parentWidth/(j+1) + 10);
			toAdd.setY(j*parentHeight/(j+1) + 10);
		}

		//add the left column
		for(int i = 3*j; i < 4*j; i++)
		{
			Space toAdd = spaces.get(i);
			g2d.setColor(toAdd.getColor());
			g2d.fillRect(0, (4*j - i)*parentHeight/(j+1), parentWidth/(j+1), parentHeight/(j+1));
			g2d.setColor(Color.black);
			g2d.drawString(toAdd.getName(), 10, (4*j-i)*parentHeight/(j+1) + 10);
			toAdd.setX(10);
			toAdd.setY((4*j-i)*parentHeight/(j+1) + 10);
		}
	}

	/*
	 * Returns a Point of a Space's X and Y coordinates
	 */
	public Point getSpaceLocation(Space space)
	{
		return new Point(space.getX(), space.getY());
	}

	public int getWinningScore() { return winningScore; } 
	
	public void setWinningScore(int newScore) { this.winningScore = newScore; }

	public void addSpace(String spaceName, Color color, int scoreModifier, boolean newExtra, boolean newMiss) {
		spaces.add(new Space(spaceName, color, scoreModifier, newExtra, newMiss));
	}

	public int getNumSpaces(){ return spaces.size(); }

	public Space getSpace(int index) { return spaces.get(index); }

	public void update(Object[] data)
	{
		Integer roll = (Integer) data[0];
		Integer playerNumber = (Integer) data[1];
		Player current = (Player) viewers.get(playerNumber);
		
		int newPos = current.getPosition();
		newPos += roll;
		newPos %= spaces.size();
		Space newSpace = spaces.get(newPos);

		//data to be passed to the player
		
		//space information to update score and give/take away turns
		Boolean skip = newSpace.isMissTurn();
		Boolean extra = newSpace.isExtraTurn();
		int scoreMod = newSpace.getScoreModifier();
		
		//coordinates of new space for player's piece
		Point newCoord = this.getSpaceLocation(newSpace);
		
		Object[] sending = {skip, extra, scoreMod, newCoord};
		
		current.update(sending);
		
	}
}
