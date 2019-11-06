package norbertostudios.window;////

import java.awt.*;

////    Created     10/30/19, 9:38 PM
////    By:         Norberto Studios
////    
public class FullScreen
{
    private GraphicsDevice device;

    public FullScreen()
    {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = graphicsEnvironment.getDefaultScreenDevice();
    }



}
