package frca.boardgame;

import java.awt.Color;

/**
 *
 * @author Justin Tennant
 */
public class Space {

    private final String name;
    private final int scoreModifier;
    private final boolean extraTurn;
    private final boolean missTurn;
    
    private Color color;
    private int x;
    private int y;
    
    public Space(String name, Color color, int scoreModifier, boolean extraTurn, boolean missTurn) {
        this.name = name;
        this.scoreModifier = scoreModifier;
        this.extraTurn = extraTurn;
        this.missTurn = missTurn;
        this.color = color;
    }
    
    //Getter Methods
    
    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
    
    public void setColor(Color c)
    {
    	color = c;
    }
    
    public int getScoreModifier() {
        return scoreModifier;
    }

    public boolean isExtraTurn() {
        return extraTurn;
    }

    public boolean isMissTurn() {
        return missTurn;
    }
    
    //Getter & Setter for graphical positioning

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
