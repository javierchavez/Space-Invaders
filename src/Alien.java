import java.awt.*;

/**
 * @author Javier Chavez
 */

public class Alien extends GameObject implements Shooter<Missile>{

    public Alien(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    @Override
    public Missile fire() {

        int x = rectangle.x + (rectangle.width/2) - (MISSILE_WIDTH/2);
        int y = (rectangle.y + rectangle.height + MISSILE_HEIGHT)-MISSILE_HEIGHT;
        return new Missile(x, y, MISSILE_WIDTH, MISSILE_HEIGHT);
    }

}
