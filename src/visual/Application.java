package visual;

import javax.swing.*;
import java.awt.*;

public class Application extends Canvas
{
    private JFrame frame;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;


    public Application()
    {
        // Setting the Frame
        frame = new JFrame();

        frame.setTitle("Game Prototype Frame");
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
