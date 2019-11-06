package norbertostudios.core;////

////    Created     10/30/19, 7:38 PM
////    By:         Norberto Studios
////    
public abstract class GameBase implements Runnable {

    protected abstract void initialize();

    protected abstract void gameUpdate();

    protected abstract void gameRender();

    private Thread thread;
    private volatile boolean running;


    //FPS
    private final int FPS = 60;
    private double TARGETTIME = 1000000000 / FPS;
    private double delta = 0;
    protected int AVERAGEFPS = FPS;


    public GameBase() {
        initialize();
        start();
    }

    private void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleep() {
        try {
            thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long now = 0;
        long lastTime = System.nanoTime();
        // To see and Debug
        int frames = 0;
        long time = 0;

        running = true;

        while (running) {

            now = System.nanoTime();
            delta += (now - lastTime) / TARGETTIME;
            time += (now - lastTime);
            lastTime = now;

            if (delta >= 1) {
                gameUpdate();
                gameRender();
                delta--;
                frames++;
            }
            if (time >= 1000000000) {
                AVERAGEFPS = frames;
                frames = 0;
                time = 0;
            }
             sleep();
        }
        stop();
    }
}
