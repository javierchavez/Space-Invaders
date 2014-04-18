import java.awt.*;

/**
 * @author Javier Chavez
 */
public class Ship extends GameObject implements Shooter<Laser>{

    public Ship(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    @Override
    public Laser fire() {
        int x = rectangle.x + (rectangle.width/2) - (LASER_WIDTH/2);
        int y = rectangle.y - LASER_HEIGHT;
        return new Laser(x, y, LASER_WIDTH, LASER_HEIGHT);
    }

    public void paint(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}
