package norbertostudios.engine.gameObjects;////

import norbertostudios.engine.MainCamera;
import norbertostudios.engine.Tiles.BgroundTileMap;
import norbertostudios.engine.Tiles.GroundTileMap;
import norbertostudios.engine.Tiles.Tile;
import norbertostudios.engine.Tiles.TileMap;
import norbertostudios.engine.assets.Animation;
import norbertostudios.engine.assets.Assets;
import norbertostudios.engine.assets.Vector2d;
import norbertostudios.engine.util.Chronometer;
import norbertostudios.engine.util.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

////    Created     12/2/19, 12:31 PM
////    By:         Norberto Studios
////    
public class Player extends Creature {
    //needs movements
    // gravity
    // jump
    // update
    // draw

    Animation idle;
    Animation left;
    Animation right;
    Animation jump;

    public int walking;
    public int jumping;
    public int jumpingHeight;
    public boolean isFacingRight;
    private boolean spawning, visible;

    private Chronometer spawnTime, flickerTime;

    public Player(BufferedImage texture, Vector2d position, Vector2d velocity) {
        super(texture, position, velocity);

        walking = 10;
        isFacingRight = true;

        spawnTime = new Chronometer();
        flickerTime = new Chronometer();

        right = new Animation();
        left = new Animation();
        idle = new Animation();
        jump = new Animation();

        MainCamera.setValues(0, -100);

        gravity = 0.0;
        jumping = 0;
        jumpingHeight = 20;


        spawning = false;
        visible = true;

    }

    public void idle() {
        idle.addFrame(Assets.playerHashMap.get(0), 600);
        idle.addFrame(Assets.playerHashMap.get(3), 400);
    }

    public void walk() {
        if (walking > 0) {
            isFacingRight = true;
            animWalk(right);
        } else if (walking < 0) {
            isFacingRight = false;
            animWalk(left);
        }
    }

    public void animWalk(Animation anim) {
        anim.addFrame(Assets.playerHashMap.get(1), 160);
        anim.addFrame(Assets.playerHashMap.get(2), 160);
    }


    public void jump() {
        if (onGround) {
            isJumping = true;
            onGround = false;
        }

    }

    public void jumping() {
        if (isJumping) {
//            jumpAnim();

            velocity = new Vector2d(velocity.getX(), Constants.JUMP_SPEED);
            MainCamera.y = (int) position.getY() + 10;
            jumping++;
            if (jumping >= jumpingHeight) {
                jumping = 0;
                isJumping = false;
            }
        }
    }


    protected void fall() {
        if (isFalling) {
            velocity.setY(gravity += 0.009);
            MainCamera.y = (int) position.getY() + 50;
            //position = new Vector2d(position.getX(), position.getY() + gravity + 8);
            // gravity += 0.1;
//            jumpAgain = 3;
            if (onGround) {
                gravity = 0;
                isFalling = false;
                //  position = new Vector2d(position.getX(), position.getY() - 8);
                velocity.setY(0);
            }
        }

        if (!bottomCollision() && !isJumping) {
            isFalling = true;
            onGround = false;
        }
    }


    @Override
    public void update(long elapseTime) {
        super.update(elapseTime);

        jump.update(elapseTime);
        left.update(elapseTime);
        right.update(elapseTime);
        idle.update(elapseTime);


        jumping();
        fall();

//        System.out.println("---------------_(PX) "+position.getX());
//        System.out.println("---------------_(PY) "+position.getY());

        verticalCollision();
        horizontalCollision();

        if (!spawnTime.isRunning()) {
            spawning = false;
            visible = true;
        }

        if (spawning) {
            if (!flickerTime.isRunning()) {
                flickerTime.run(Constants.FLICKER_TIME);
                visible = !visible;
            }
        }


        spawnTime.update();
        flickerTime.update();

    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        // System.out.println("IM THE PLAYER DRAW");
        // g.drawRect((int) position.getX() + texture.getWidth() / 4, (int) position.getY() + 30, texture.getWidth() / 2, 100);
        if (!visible)
            return;

        flipPlayer(g);

    }

    public void flipPlayer(Graphics2D g) {

        //*/
        if (walking == 0) {
            g.drawImage(
                    idle.getImage(),
                    (int) (position.getX()),
                    (int) (position.getY()),
                    null
            );
        }
//*/
        else if (isFacingRight && walking > 0) {
            g.drawImage(
                    right.getImage(),
                    (int) (position.getX()),
                    (int) (position.getY()),
                    null
            );
        } else if (!isFacingRight && walking < 0) {
            g.drawImage(
                    left.getImage(),
                    (int) (position.getX()) + width,
                    (int) (position.getY()),
                    -width, height, null
            );
        }

        //*/
    }


    public float getMaxSpeed() {
        return Constants.MAX_SPEED;
    }


    public boolean leftCollision() {
        for (int i = 0; i < AABB.size(); i++) {
            rect = collision(i);
            if (rect.intersects(leftRecCollision())) {
                System.out.println("Left Collision");
                break;
            }
        }

        return getBounds().intersects(rect);
    }

    public boolean rightCollision() {
        for (int i = 0; i < AABB.size(); i++) {
            rect = collision(i);
            if (rect.intersects(rightRecCollision())) {
                System.out.println("Right Collision");
                break;
            }
        }

        return getBounds().intersects(rect);
    }

    public boolean bottomCollision() {
        for (int i = 0; i < AABB.size(); i++) {
            rect = collision(i);
            if (rect.intersects(bottomRecCollision())) {
                break;
            }
        }

        return getBounds().intersects(rect);
    }

    public boolean topCollision() {
        for (int i = 0; i < AABB.size(); i++) {
            rect = collision(i);

            if (rect.intersects(topRecCollision())) {
                //System.out.println("Collision");
                break;
            }
        }
        return getBounds().intersects(rect);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) position.getX() + texture.getWidth() / 4,
                (int) position.getY() + 30,
                texture.getWidth() / 2,
                100);
    }


    public void verticalCollision() {
        if (bottomCollision() || position.getY() == rect.y - rect.height + 1) {
            setOnGround(true);
            position.setY(rect.y - rect.height + 1);
        }
        if (topCollision()) {
            position.setY(rect.y + rect.height - rect.height / 4);
        }
    }

    public void horizontalCollision() {

        // Bounds the xStart
        if (position.getX() + 30 < 0) {
            position.setX(-30);
        }
        if (leftCollision()) {
            position.setX(rect.x + rect.width - rect.width / 4);
        }
        if (rightCollision()) {
            position.setX(rect.x - rect.width + rect.width / 4);
        }

    }


    public Rectangle leftRecCollision() {
        return new Rectangle((int) position.getX() + 20, (int) position.getY() + 40, 10, 70);
    }

    public Rectangle rightRecCollision() {
        return new Rectangle((int) position.getX() + texture.getWidth() - 40, (int) position.getY() + 40, 10, 70);
    }

    public Rectangle topRecCollision() {
        return new Rectangle((int) position.getX() + texture.getWidth() / 4 + 10, (int) position.getY() + 25, texture.getWidth() / 2 - 20, 5);
    }

    public Rectangle bottomRecCollision() {
        return new Rectangle((int) position.getX() + texture.getWidth() / 4 + 10, (int) position.getY() + texture.getWidth(), texture.getWidth() / 2 - 20, 10);
    }


    public void deadAndRespawn() {
        spawning = true;
        spawnTime.run(Constants.SPAWNING_TIME);
    }

    public boolean isSpawning() {
        return spawning;
    }
}