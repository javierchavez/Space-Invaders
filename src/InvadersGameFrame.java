import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Javier Chavez
 */
public class InvadersGameFrame extends JFrame implements KeyListener {
    private static boolean isRunning = false;
    private GameAreaPanel gameArea = new GameAreaPanel(this);
    private JButton startPauseButton;
    private JLabel scoreLabel, aliensLabel, livesLabel;
    private int score, lives, aliensKilled;


    InvadersGameFrame(){
        init();
    }

    public void addToScore(int score) {
        this.score += score;
        scoreLabel.setText("Score: " + this.score);
    }

    public void setLives(int lives){
        this.lives = lives;
        livesLabel.setText("Lives: " + this.lives);
        if(lives == 0){
            lives = 3;
            livesLabel.setText("Lives: " + this.lives);
            gameArea.stop();
            isRunning = false;
        }
    }

    public void setAliensKilled(int aliensKilled){
        this.aliensKilled = aliensKilled;
        aliensLabel.setText("Aliens Killed: " + this.aliensKilled);
    }


    public int getScore() {
        return score;
    }

    public int getLives(){
        return lives;
    }

    public int getAliensKilled(){
        return aliensKilled;
    }

    private void init(){
        score =0;
        lives =3;
        aliensKilled =0;
        Color rightPanelColor = Color.white;
        Font f = new Font("Dialog", Font.BOLD, 20);

        JPanel controlPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3,1));
        rightPanel.setBackground(rightPanelColor);


        // this will hold the number values
        JPanel infoLabels = new JPanel();
        infoLabels.setLayout(new GridLayout(6,1,0,0));
        infoLabels.setBackground(rightPanelColor);


        //create labels
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(f);
        aliensLabel = new JLabel("Aliens Killed: " + aliensKilled);
        aliensLabel.setFont(f);
        livesLabel = new JLabel("Lives: " + lives);
        livesLabel.setFont(f);

        startPauseButton = new JButton("Start");
        startPauseButton.setBorderPainted(false);
        startPauseButton.setBackground(Color.decode("#b2f3b2"));
        startPauseButton.setForeground(Color.white);
        startPauseButton.setOpaque(true);
        startPauseButton.setFocusable(false);

        rightPanel.setPreferredSize(new Dimension(GameData.GAME_BOARD_HEIGHT,GameData.GAME_BOARD_WIDTH));
        startPauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                startPauseButton.setText(isRunning ? "Start" : "Pause");
                if(!isRunning) {
                    gameArea.start();
                    isRunning = true;
                    startPauseButton.setBackground(Color.decode("#ff4d4d"));
                } else {
                    gameArea.stop();
                    isRunning = false;
                    startPauseButton.setBackground(Color.decode("#b2f3b2"));
                }
            }
        });

        //create a panel to show main game with black background
//        gameArea.setBackground(Color.BLACK);


        Container pane = this.getContentPane();
        pane.setLayout(new BorderLayout());

        //add two panels to main
        pane.add(gameArea, BorderLayout.CENTER);
        pane.add(rightPanel, BorderLayout.LINE_END);


        // holds controls for game
        controlPanel.add(startPauseButton);
        controlPanel.setBackground(rightPanelColor);



        // add all labels to panel
        infoLabels.add(scoreLabel);
        infoLabels.add(aliensLabel);
        infoLabels.add(livesLabel);


        rightPanel.add(infoLabels);
        rightPanel.add(controlPanel);

        setTitle("Space Invaders");
        setSize(GameData.GAME_BOARD_WIDTH*4, GameData.GAME_BOARD_HEIGHT*4);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        addKeyListener(this);
        this.requestFocus();
        gameArea.stop();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && isRunning){
            System.out.println("right");
            gameArea.moveShipRight();

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && isRunning){
            gameArea.moveShipLeft();
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE && isRunning){
            System.out.println("space");
            gameArea.fireShip();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
