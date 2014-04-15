import javax.swing.*;

public class SpaceInvaders {

    public static void main ( String [] args ) {
        SwingUtilities.invokeLater ( new Runnable() {

            public void run () {
                JFrame frame = new InvadersGameFrame();
                frame.setVisible ( true );
            }
        });
    }
}
