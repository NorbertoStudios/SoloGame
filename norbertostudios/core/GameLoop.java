package norbertostudios.core;////

import java.awt.*;
import java.awt.image.BufferStrategy;

import norbertostudios.input.Keyboard;
import norbertostudios.util.Assets;
import norbertostudios.util.Constants;
import norbertostudios.window.Window;

////    Created     10/30/19, 10:53 PM
////    By:         Norberto Studios
////    
public class GameLoop extends GameBase {

    private Window              window;
    private Keyboard            keyboard;
    private GameState           gameState;

    private BufferStrategy      bufferStrategy;
    private Graphics            g;

    @Override
    protected void initialize() {
        Assets.init();
        window      = new Window();
        keyboard    = new Keyboard();
        gameState   = new GameState();

        startBufferStrategy();

        window.getCanvas().addKeyListener(keyboard);
    }

    public void startBufferStrategy() {
        // Double Buffering
        window.getCanvas().createBufferStrategy(3);
        bufferStrategy = window.getCanvas().getBufferStrategy();

    }

    @Override
    protected void gameUpdate()
    {
        keyboard.update();
        gameState.update();
    }

    @Override
    protected void gameRender() {
        //Rendering the game graphics
//        do {
//            do {
//                g = null;
//                try {
//                    g = bufferStrategy.getDrawGraphics();
//                    g.clearRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
//                    render(g);
//                } finally {
//                    if (g != null) {
//                        g.dispose();
//                    }
//                }
//            } while (bufferStrategy.contentsRestored());
//            bufferStrategy.show();
//        } while (bufferStrategy.contentsLost());

        g = bufferStrategy.getDrawGraphics();

        g.clearRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        render(g);

        g.dispose();
        bufferStrategy.show();

    }

    //This is where everything renders
    private void render(Graphics g) {
        this.g = g;

        gameState.draw(g);

        g.drawString("" + AVERAGEFPS, 20, 20);
        g.setColor(Color.black);
    }

}
