package frca.boardgame;

/**
 *
 * @author Justin Tennant
 */
public class Player implements View{
   
    private final String playerName;
    private int score;
    private int position;
    public boolean missingTurn;
    private GamePiece piece;

    public Player(String newName, int newScore, int newPosition, String path) {
        playerName = newName;
        score = newScore;
        position = newPosition;
        piece = new GamePiece(path);
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

    public int rollDice() {
        Dice dice = new Dice();
        return dice.roll();
    }

	public void update(Object[] data)
	{
		// TODO Auto-generated method stub
		
	}
}
