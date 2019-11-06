package norbertostudios.window;////

import norbertostudios.util.Constants;

import javax.swing.*;
import java.awt.*;

////    Created     10/30/19, 7:38 PM
////    By:         Norberto Studios
////    
public class Window extends JFrame
{
    private int width = Constants.WIDTH;
    private int height = Constants.HEIGHT;

    private Canvas canvas;

    public Window()
    {
        setTitle("2D Side Scroller");
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        canvas = new Canvas();
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setPreferredSize(new Dimension(width,height));

        add(canvas);

        setVisible(true);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
