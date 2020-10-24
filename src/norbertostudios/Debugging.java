package norbertostudios;////

import norbertostudios.engine.MainCamera;
import norbertostudios.engine.core.GameLoop;
import norbertostudios.engine.gameObjects.Player;
import norbertostudios.engine.gameObjects.enemy.GreenSlime;

import java.awt.*;

////    Created     12/18/19, 11:46 AM
////    By:         Norberto Studios
////    
public class Debugging
{
    Player player;
    GreenSlime greenSlime;

    public Debugging()
    {
        player = GameLoop.player;
        greenSlime = GameLoop.slime;
    }

    public void debugUpdate()
    {
    }

    public void debugDraw(Graphics2D g)
    {
        playerCollision(g);
    }



    public void playerCollision(Graphics2D g)
    {
        // left collider box
        g.drawRect((int) player.getPosition().getX() + 25, (int) player.getPosition().getY() + 40, 10, 70);
        // right collider box
        g.drawRect((int) player.getPosition().getX() + player.getTexture().getWidth() - 35, (int) player.getPosition().getY() + 40, 10, 70);
        // bottom collider box
        g.drawRect((int) player.getPosition().getX() + player.getTexture().getWidth() / 4 + 10, (int) player.getPosition().getY() + player.getTexture().getWidth(), player.getTexture().getWidth() / 2 - 20, 10);
        // top collider box
        g.drawRect((int) player.getPosition().getX() + player.getTexture().getWidth() / 4 + 10, (int) player.getPosition().getY() + 25, player.getTexture().getWidth() / 2 - 20, 5);
        //?????????????????????????????????????
        //
        //
        //
        // top slime collider box
        g.drawRect((int) greenSlime.getPosition().getX() + greenSlime.getTexture().getWidth() / 4 + 10 - MainCamera.x, (int) greenSlime.getPosition().getY()  - MainCamera.y + 70, greenSlime.getTexture().getWidth() / 2 - 20, 5);
        //?????????????????????????????????????
        // right slime collider box
        g.drawRect((int) greenSlime.getPosition().getX() + greenSlime.getTexture().getWidth() -25 - MainCamera.x, (int) greenSlime.getPosition().getY()  - MainCamera.y + 80, 30, 30);
        //?????????????????????????????????????
        // left slime collider box
        g.drawRect((int) greenSlime.getPosition().getX() - MainCamera.x + 15, (int) greenSlime.getPosition().getY()  - MainCamera.y + 80, 30, 30);
        //?????????????????????????????????????



    }


}
