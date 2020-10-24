package norbertostudios.engine.hud;////

import norbertostudios.engine.assets.Vector2d;

import java.awt.*;

////    Created     10/30/19, 11:23 AM
////    By:         Norberto Studios
////    
public class Text
{
    public static void drawText(Graphics g, String text, Vector2d pos,
                                boolean center, Color color, Font font)
    {
        g.setColor(color);
        g.setFont(font);
        Vector2d position = new Vector2d(pos.getX(),pos.getY());

        if(center)
        {
            FontMetrics fm = g.getFontMetrics();
            position.setX(position.getX() -fm.stringWidth(text)/2);
            position.setY(position.getY() -fm.getHeight()/2);
        }

        g.drawString(text,(int)position.getX(), (int)position.getY());
    }
}
