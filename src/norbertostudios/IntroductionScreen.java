package norbertostudios;

import norbertostudios.engine.assets.Assets;
import norbertostudios.engine.graphics.ScreenManager;
import norbertostudios.engine.util.Constants;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class IntroductionScreen implements Runnable {

    private JPanel contentPane;


    public static ScreenManager screen;

    //Splash Screen Timer
    long amountOfTime = System.currentTimeMillis();

    Thread t;
    boolean runn;


    public IntroductionScreen() {

        Assets.init(); // Init the game assets
        screen = Assets.screen;
        initDisplay();


        t = new Thread(this);
        t.start();


    }


    public void initDisplay() {

       // screen.setFullScreen(Assets.screenDisplay);

        Window window = screen.getFullScreenWindow();
        window.setFont(new Font("Dialog", Font.PLAIN, 240));
        window.setBackground(Color.BLUE);
//        window.setForeground(new Color(255, 255, 255));
        window.setForeground(new Color(0, 0, 0));
    }

    public void run() {
        runn = true;


        while (runn) {

            Graphics2D g = screen.getGraphics();

            ////// THis is where we have to draw the backGround
            //g.clearRect(0, 0, Assets.screen.getWidth(), Assets.screen.getHeight());
            //g.fillRect(0,0,Assets.screen.getWidth(),Assets.screen.getHeight());
            g.drawImage(new ImageIcon(this.getClass().getResource("/Images/NStudioLogo.gif")).getImage(),
                    120, 100,1189,659,null);


//            Assets.screen.getFullScreenWindow().setForeground(Color.BLACK);
            //////

            g.dispose();
            screen.update();


            if (System.currentTimeMillis() - amountOfTime > 10000) {


                new ShowGame();

                //screen.restoreScreen();

                runn = false;

                System.out.println("IM DONE");

//
            }
        }

    }

    public static void main(String[] args) {

       new IntroductionScreen();
    }
}

