import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Javier Chavez
 */
public class InvadersGameFrame extends JFrame{
    private boolean isRunning = false;
    private JPanel gameArea = new JPanel();
    private JButton startPauseButton;
    private JLabel scoreLabel, aliensLabel, livesLabel;

    InvadersGameFrame(){
        init();
    }
    /*
    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void setLives(int lives){
        livesLabel.setText("Live: " + lives);
    }

    public void setAliensKilled(int aliensKilled){
        aliensLabel.setText("Aliens Killed: " + aliensKilled);
    }
    */
    private void init(){
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
        scoreLabel = new JLabel("Score: TBD");
        scoreLabel.setFont(f);
        aliensLabel = new JLabel("Aliens Killed: TBD");
        aliensLabel.setFont(f);
        livesLabel = new JLabel("Lives: TBD");
        livesLabel.setFont(f);

        startPauseButton = new JButton("Start");
        startPauseButton.setBorderPainted(false);
        startPauseButton.setBackground(Color.decode("#b2f3b2"));
        startPauseButton.setForeground(Color.white);
        startPauseButton.setOpaque(true);

        rightPanel.setPreferredSize(new Dimension(GameData.GAME_BOARD_HEIGHT,GameData.GAME_BOARD_WIDTH));
        startPauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                startPauseButton.setText(isRunning ? "Start" : "Pause");
                if(!isRunning) {
                    isRunning = true;
                    startPauseButton.setBackground(Color.decode("#ff4d4d"));
                } else {
                    isRunning = false;
                    startPauseButton.setBackground(Color.decode("#b2f3b2"));
                }
            }
        });

        //create a panel to show main game with black background
        gameArea.setBackground(Color.BLACK);


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
    }
}
