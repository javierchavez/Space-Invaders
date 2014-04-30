import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Javier Chavez
 */
public class Ship extends GameObject implements Shooter<Laser>{

    public Ship(int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage("");
    }

    @Override
    public Laser fire() {
        int x = rectangle.x + (rectangle.width/2) - (LASER_WIDTH/2);
        int y = rectangle.y - LASER_HEIGHT;
        return new Laser(x, y, LASER_WIDTH, LASER_HEIGHT);
    }

    public void paint(Graphics g) {

        if (image!= null){
            g.drawImage(image, rectangle.x, rectangle.y, getWidth(), getHeight(), null);
        }else{
            g.setColor(Color.white);
            g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }

    private BufferedImage image;

    public void setImage(String imageName) {
        // Using ClassLoader can find a resource in jar, too.
        ClassLoader cl = getClass().getClassLoader();
        InputStream in = cl.getResourceAsStream("ship_cannon.jpg");
        try {
            image = ImageIO.read(in);
        } catch (IOException ex) {
            System.err.println("Error loading: " + imageName);
        }
    }


}
