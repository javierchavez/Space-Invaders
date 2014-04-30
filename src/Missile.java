import java.awt.*;

/**
 * @author Javier Chavez
 */
public class Missile extends GameObject {

    public Missile(int x, int y, int width, int height) {

        super(x,y,width,height);
    }


    public void paint(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}
