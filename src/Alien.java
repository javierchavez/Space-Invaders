import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Javier Chavez
 */

public class Alien extends GameObject implements Shooter<Missile> {
    private int xDir, yDir;


    public Alien(int x, int y, int width, int height) {
        super(x,y,width,height);
        xDir = 1;
        setImage("");
    }

    @Override
    public Missile fire() {

        int x = rectangle.x + (rectangle.width/2) - (MISSILE_WIDTH/2);
        int y = (rectangle.y + rectangle.height + MISSILE_HEIGHT)-MISSILE_HEIGHT;
        return new Missile(x, y, MISSILE_WIDTH, MISSILE_HEIGHT);
    }

    public void paint(Graphics g) {
        //this is strictly for aesthetics
        if (image!= null){
            g.drawImage(image, rectangle.x, rectangle.y, getWidth(), getHeight(), null);
        }else{
            g.setColor(Color.green);
            g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }

    private BufferedImage image;

    public void setImage(String imageName) {
        // Using ClassLoader can find a resource in jar, too.
        ClassLoader cl = getClass().getClassLoader();
        InputStream in = cl.getResourceAsStream("si_large.jpg");
        try {
            image = ImageIO.read(in);
        } catch (IOException ex) {
            System.err.println("Error loading: " + imageName);
        }
    }

}
