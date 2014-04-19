import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * @author Javier Chavez
 */
public class GameAreaPanel extends JPanel implements ActionListener {
    private List<Alien> aliens;
    private Ship ship;
    private InvadersGameFrame gameFrame;
    private Timer timer;

    /*
        dependency injection
    */
    public GameAreaPanel(InvadersGameFrame invadersGameFrame){
        gameFrame = invadersGameFrame;
        init();
        initGameData();

        timer = new Timer(1000, this);
        timer.setInitialDelay(0);
        timer.start();
    }

    public GameAreaPanel(List<Alien> aliens, Ship ship, InvadersGameFrame invadersGameFrame){
        gameFrame = invadersGameFrame;
        init();
        this.aliens = aliens;
        this.ship = ship;

        timer = new Timer(1000, this);
        timer.setInitialDelay(0);
        timer.start();
    }

    public void stop(){
        timer.stop();
        this.removeAll();
    }

    public void start(){
        timer.start();
        this.updateUI();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Alien a: aliens){
            a.paint(g);
        }
        ship.paint(g);
        if (curr != null){
            curr.paint(g);

        }
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
                System.out.println("alien.getX() = " + alien.getX());
            }
        }
        // Create a new ship @ the bottom middle
        ship = new Ship((GameData.GAME_BOARD_WIDTH*2)-(GameData.GAME_BOARD_WIDTH/2-40),
                (GameData.GAME_BOARD_HEIGHT*4)-200, 50*2, 40*2);

    }

    boolean reverse = false;
    int xDir = 50;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof Timer){
            if(!reverse) {
                for (int i = 0; i < aliens.size(); i++) {
                    Alien a = aliens.remove(i);
                    int x = a.getX();

                    if (x + xDir + a.getWidth() > GameData.GAME_BOARD_WIDTH*2-a.getWidth()) {
                        xDir = -xDir;
                        reverse = true;
                        aliens.add(i, new Alien(x, a.getY(), a.getWidth(), a.getHeight()));
                        break;
                    }
                    x += xDir;

                    //                System.out.println("xDir = " + xDir);
                    //                System.out.println("x = " + x);

                    aliens.add(i, new Alien(x, a.getY(), a.getWidth(), a.getHeight()));

                }
            }
            if(reverse){
                for(int i = 0; i < aliens.size(); i++){
                    Alien a = aliens.remove(i);
                    int x = a.getX();


                    if(x + xDir < 0 - a.getWidth()) {
                        xDir = 50;
                        reverse = false;
                        aliens.add(i, new Alien(x, a.getY(), a.getWidth(), a.getHeight()));
                        break;
                    }

                    x += xDir;

                    aliens.add(i, new Alien(x, a.getY(), a.getWidth(), a.getHeight()));

                }
            }

        }

        repaint();
    }

    public void moveShipRight(){
        ship = new Ship(ship.getX()+20, ship.getY(), ship.getWidth(), ship.getHeight());
        repaint();
    }
    public void moveShipLeft(){
        ship = new Ship(ship.getX()-20, ship.getY(), ship.getWidth(), ship.getHeight());
        repaint();
    }
    static private boolean isFiring = false;
    static private Laser curr = null;
    public void fireShip(){
        // if still firing then do not continue;
        if (isFiring){
            return;
        }
        // fire the ships gun
        curr = ship.fire();
        // using a thread we will move the missle across the screen
        Thread thread = new Thread(){
            public void run(){
                // while the missle is not null and still on the board keep moving it upward
                while (curr != null && curr.getY() > 0-GameData.MISSILE_HEIGHT){
                    // we are firing missle set checker
                    isFiring  = true;
                    // move the missle
                    curr = new Laser(curr.getX(), curr.getY()-GameData.MISSILE_SPEED,curr.getWidth(),curr.getHeight());
                    // check to see if we hit any aliens on the way
                    for (int i = 0; i < aliens.size(); i++) {

                        if(curr.intersects(aliens.get(i))){
                            // if we hit one we need to remove it
                            aliens.remove(i);
                            // also remove the missle
                            curr = null;
                            // no need to continue
                            int curKill = gameFrame.getAliensKilled();
                            gameFrame.setAliensKilled(curKill+1);


                            gameFrame.addToScore(10);
                            break;
                        }

                    }
                    // repaint the board
                    repaint();

                    try {
                        // sleep the thread that way we can the missle move
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // we exited while either hit something or no longer on board
                isFiring = false;
                // no longer need missle
                curr = null;
            }
        };

        thread.start();
    }

}
