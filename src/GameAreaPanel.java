import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Javier Chavez
 */
public class GameAreaPanel extends JPanel {
    private List<Alien> aliens;
    private Ship ship;
    public GameAreaPanel(){
        init();
        initGameData();
    }
    /*
        dependency injection
     */
    public GameAreaPanel(List<Alien> aliens, Ship ship){
        init();
        this.aliens = aliens;
        this.ship = ship;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Alien a: aliens){
            a.paint(g);
        }
        ship.paint(g);
    }

    private void init() {
        this.setBackground(Color.BLACK);

    }
    private void initGameData(){
        aliens = new ArrayList<Alien>();
        for(int x = -10; x < 300; x+= 65) {
            for(int y = -5; y < 200; y += 30) {
                Alien alien = new Alien(x, y, 55, 25);
                aliens.add(alien);
            }
        }
        // Create a new ship @ the bottom middle
        ship = new Ship((GameData.GAME_BOARD_WIDTH*2)-(GameData.GAME_BOARD_WIDTH/2-40),
                (GameData.GAME_BOARD_HEIGHT*4)-55, 50, 40);

    }
}
