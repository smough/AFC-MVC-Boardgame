package frca.boardgame;

import java.awt.Point;

/**
 *
 * @author Justin Tennant
 */
public class Player implements View{
   
    private final String playerName;
    private int score;
    private int position;
    public boolean missingTurn = false;
    public boolean extraTurn = false;
    private GamePiece piece;
    private Player next;

    public Player(String newName, int newScore, int newPosition, GamePiece piece) {
        playerName = newName;
        score = newScore;
        position = newPosition;
        this.piece = piece;
    }

    public String getName() {
        return this.playerName;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int newPosition) {
        this.position = newPosition;
    }

    public int getScore() {
        return this.score;
    }

    public void modifyScore(int scoreModifier) {
        this.score = this.score + scoreModifier;
    }

	public void update(Object[] data)
	{
		// TODO Auto-generated method stub
		//hi im a comment
		Boolean skip = (Boolean) data[0];
		Boolean extra = (Boolean) data[1];
		Integer scoreMod = (Integer) data[2];
		Point newCoord = (Point) data[3];
		
		missingTurn = skip;
		extraTurn = extra;
		this.modifyScore(scoreMod);
		this.piece.setCoordinates(newCoord);
	}
        
        public void setNext(Player nextPlayer){
            this.next = nextPlayer;
        }
        public Player getNext(){
            return this.next;
        }
}
