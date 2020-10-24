package norbertostudios.engine.hud;////

import norbertostudios.engine.assets.Vector2d;
import norbertostudios.engine.core.GameLoop;

import java.awt.*;

////    Created     10/30/19, 11:31 AM
////    By:         Norberto Studios
////    
public class Message
{
    private float alpha;
    private String text;
    private GameLoop gameState;
    private Vector2d position;
    private Color color;
    private boolean center;
    private  boolean fade;
    private Font font;
    private final float deltaApha = 0.01f;


    public Message(Vector2d position, boolean fade, String text,
                   Color color, boolean center, Font font, GameLoop gameState)
    {
        this.font = font;
        this.text = text;
        this.gameState = gameState;
        this.position = position;
        this.fade = false;
        this.center = center;
        this.color = color;

        if(fade)
            alpha = 1;
        else
            alpha = 0;
    }

    public void draw(Graphics2D g2d)
    {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));

        Text.drawText(g2d,text,position,center,color,font);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));

        position.setY(position.getY() - 1);

        if(fade)
            alpha -= deltaApha;
        else
            alpha += deltaApha;

        if(fade && alpha < 0)
        {
            gameState.getMessages().remove(this);
        }

        if(!fade && alpha > 1)
        {
            fade = true;
            alpha = 1;
        }
    }
}
