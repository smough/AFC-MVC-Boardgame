package frca.boardgame;

/**
 *
 * @author Justin Tennant
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class GamePiece extends JComponent
{
    
    private int position;
    private Point point;
    private BufferedImage pieceImg;
    
    public GamePiece()
    {
        position = 0;
        point = new Point(0, 0);
        try {
            pieceImg = ImageIO.read(getClass().getResource("1.png"));
        } catch (IOException ex) {
           System.out.println("Error in retrieving image file");
        }
    }
    
    public GamePiece (Point position)
    {
        this.position = 0;
        point = position;
        try {
            pieceImg = ImageIO.read(getClass().getResource("/1.png"));
        } catch (IOException ex) {
           System.out.println("Error in retrieving image file");
        }
    }
    
    public GamePiece (String imagePath)
    {
        position = 0;
        point = new Point(0, 0);
        try {
            pieceImg = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException ex) {
           System.out.println("Error in retrieving image file");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(pieceImg, point.x, point.y, null);
    }
   

    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    public void setCoordinates(Point p) 
    { 
    	//point = p; 
    	//this.repaint();
    	this.setLocation(p);
    }

    public void setPieceImg(BufferedImage pieceImg) {
        this.pieceImg = pieceImg;
    }
    
    
}

