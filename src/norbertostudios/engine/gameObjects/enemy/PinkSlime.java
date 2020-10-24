package norbertostudios.engine.gameObjects.enemy;////

import norbertostudios.engine.MainCamera;
import norbertostudios.engine.assets.Animation;
import norbertostudios.engine.assets.Assets;
import norbertostudios.engine.assets.Vector2d;
import norbertostudios.engine.gameObjects.Creature;

import java.awt.*;
import java.awt.image.BufferedImage;

////    Created     12/18/19, 2:33 PM
////    By:         Norberto Studios
////    
public class PinkSlime extends Creature {

    Animation walk;

    int walking = 0;

    int xPos;

    public PinkSlime(BufferedImage texture, Vector2d position, Vector2d velocity) {
        super(texture, position, velocity);

        walk = new Animation();
        animWalk();

        xPos = (int) position.getX();
        moveLeft();

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

        /*/
        if (walking == 0) {
            g.drawImage(
                    idle.getImage(),
                    (int) (position.getX()),
                    (int) (position.getY()),
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


}