import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Javier Chavez
 */

public class Alien extends GameObject implements Shooter<Missile> {
    private int xDir, yDir;


    public Alien(int x, int y, int width, int height) {
        super(x,y,width,height);
        xDir = 1;
    }

    @Override
    public Missile fire() {

        int x = rectangle.x + (rectangle.width/2) - (MISSILE_WIDTH/2);
        int y = (rectangle.y + rectangle.height + MISSILE_HEIGHT)-MISSILE_HEIGHT;
        return new Missile(x, y, MISSILE_WIDTH, MISSILE_HEIGHT);
    }

    public void paint(Graphics g) {
        //this is strictly for aesthetics

        g.setColor(Color.green);
//        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.fill3DRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, true);
    }


}
