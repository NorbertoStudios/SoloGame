package norbertostudios.engine.gameObjects.enemy;////

import norbertostudios.engine.MainCamera;
import norbertostudios.engine.assets.Animation;
import norbertostudios.engine.assets.Assets;
import norbertostudios.engine.assets.Vector2d;
import norbertostudios.engine.core.GameLoop;
import norbertostudios.engine.gameObjects.Creature;
import norbertostudios.engine.gameObjects.Player;
import norbertostudios.engine.hud.HUD;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

////    Created     12/18/19, 2:33 PM
////    By:         Norberto Studios
////    
public class GreenSlime extends Creature {

    Animation walk;

    public int walking = 0;

    public boolean deadState;

    int xPos;

    public GreenSlime(BufferedImage texture, Vector2d position, Vector2d velocity) {
        super(texture, position, velocity);

        walk = new Animation();
        animWalk();

        xPos = (int) position.getX();
        moveLeft();

        deadState = false;

    }


    public void animWalk() {
        walk.addFrame(Assets.enemiesHashMap.get(1), 160);
        walk.addFrame(Assets.enemiesHashMap.get(48), 160);
//        walk.addFrame(Assets.enemiesHashMap.get(56), 160);
    }

    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        walk.update(elapsedTime);

        updateMovement();

//        System.out.println();
        //  verticalCollision();
//        horizontalCollision();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);


       // g.drawImage(walk.getImage(), (int) position.getX() - MainCamera.x, (int) position.getY() - MainCamera.y, null);
          flipPlayer(g);
    }


    public void updateMovement() {

        if (position.getX() < 1100) {
            moveRight();
        }
        if (position.getX() > xPos) {
            moveLeft();
        }

    }

    public void moveLeft() {
        walking = 1;
        velocity.setX(-0.1);
    }

    public void moveRight() {
        walking  = -1;
        velocity.setX(0.1);
    }


//    public Rectangle getBounds() {
//        return new Rectangle((int) position.getX() + texture.getWidth() / 4,
//                (int) position.getY() + 30,
//                texture.getWidth() / 2,
//                100);
//    }

    public void flipPlayer(Graphics2D g) {

        //*/
        if (walking == 0) {
            g.drawImage(
                    Assets.enemiesHashMap.get(56),
                    (int) (position.getX()) - MainCamera.x,
                    (int) (position.getY()) - MainCamera.y,
                    null
            );
        }
//*/
        if (walking > 0) {
            g.drawImage(
                    walk.getImage(),
                    (int) position.getX() - MainCamera.x, (int) position.getY() - MainCamera.y, null
            );
        } else if ( walking < 0) {
            g.drawImage(
                    walk.getImage(),
                    (int) (position.getX()) + width - MainCamera.x,
                    (int) (position.getY()) -MainCamera.y,
                    -width, height, null
            );
        }

        //*/
    }



    public Rectangle leftRecCollision()
    {
        return new Rectangle((int) position.getX() - MainCamera.x + 15, (int) position.getY()  - MainCamera.y + 80, 30, 30);
    }
    public Rectangle rightRecCollision()
    {
        return new Rectangle((int) position.getX() + texture.getWidth() -25 - MainCamera.x, (int) position.getY()  - MainCamera.y + 80, 30, 30);
    }
    public Rectangle topRecCollision()
    {
        return new Rectangle((int) position.getX() + texture.getWidth() / 4 + 10 - MainCamera.x, (int) position.getY()  - MainCamera.y + 70, texture.getWidth() / 2 - 20, 5);
    }
    public Rectangle bottomRecCollision()
    {
        return new Rectangle((int) position.getX() + texture.getWidth() / 4 + 10, (int) position.getY() + texture.getWidth(), texture.getWidth() / 2 - 20, 10);
    }


    public void setDeadState(boolean deadState) {
        this.deadState = deadState;
    }
}
