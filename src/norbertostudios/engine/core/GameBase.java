package norbertostudios.engine.core;////

import norbertostudios.engine.assets.Assets;
import norbertostudios.engine.graphics.ScreenManager;
import norbertostudios.engine.util.Constants;

import java.awt.*;

////    Created     12/1/19, 12:23 PM
////    By:         Norberto Studios
////    
public abstract class GameBase implements Runnable {
    protected abstract void gameInit();

    protected abstract void gameUpdate(long elapsedTime);

    protected abstract void gameRender(Graphics2D g);

    private Thread thread;
    private volatile boolean running;

    public static ScreenManager screen;

    public GameBase() {

        screen = Assets.screen;
        initDisplay();
        gameInit();
        start();
    }

    public void run() {
        System.out.println("Thread Started");
        // Game Loop
        gameLoop();



    }

    public void gameLoop() {

        long startTime = System.currentTimeMillis();
        long currTime = startTime;

        running = true;

        while (running) {
//
            long elapsedTime = System.currentTimeMillis() - currTime;
            currTime += elapsedTime;

            gameUpdate(elapsedTime);

            Graphics2D g = screen.getGraphics();
            g.clearRect(0,0,screen.getWidth(),screen.getHeight());
            gameRender(g);
            g.dispose();
            screen.update();

        }
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {

        try {
            running = false;
        } finally {
            System.out.println("Im Stoping Everything");
           // screen.restoreScreen();
            System.exit(0);
        }
    }

    public void initDisplay() {
        //screen = new ScreenManager();
        //DisplayMode displayMode = screen.findFirstCompatibleMode(Constants.POSSIBLE_MODES);
        //screen.setFullScreen(displayMode);

        Window window = screen.getFullScreenWindow();
        window.setFont(new Font("Dialog", Font.PLAIN, 24));
        window.setBackground(Color.BLUE);
        window.setForeground(Color.white);
    }

}
