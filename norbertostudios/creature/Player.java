package norbertostudios.creature;////

import norbertostudios.cam.MainCamera;
import norbertostudios.core.GameState;
import norbertostudios.input.Keyboard;
import norbertostudios.math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

////    Created     11/5/19, 9:44 AM
////    By:         Norberto Studios
////    
public class Player extends Creature {
    private Vector2D acceleration;
    private Vector2D heading;

    private static final float JUMP_SPEED = -.95f;
    protected double gravity;
    protected double maxDY;
    protected double jumpHeight;
    private boolean onGround;
    private boolean isFalling;
    private boolean isJumping;

    public Player(BufferedImage texture, Vector2D position, Vector2D velocity, double maxVel, GameState gameState) {
        super(texture, position, velocity, maxVel, gameState);
        acceleration = new Vector2D();

        MainCamera.setValues(0, -100);

        isJumping = false;
        isFalling = true;
        onGround = false;
        gravity = 4.0;
        maxDY = 400;
        jumpHeight = 20;
    }

    protected void jump() {
        if (isJumping) {
            isFalling = false;
            onGround = false;
            position = new Vector2D(position.getX(), position.getY() - jumpHeight);
            jumpHeight--;
            //  if the jump height equals to the peek 0
            if (jumpHeight == 0) {
                isJumping = false;
                jumpHeight = 20;
                isFalling = true;
                // System.out.println("im on max "+ position.getY());
            }
        }
    }

    protected void fall() {
        if (isFalling) {
            position = new Vector2D(position.getX(), position.getY() + gravity);
            gravity += 0.5;
            if (position.getY() > maxDY) {
                position.setY(maxDY);
                gravity = 4;
            }
        }

    }

    @Override
    public void update() {
        // testing
        if (position.getY() >= maxDY)
        {
            onGround = true;
        }

        if (Keyboard.RIGHT) {
            if (MainCamera.offsetMaxX >= position.getX()) {
                //velocity.setY(position.getY()+maxVel);
                position.setX(position.getX() + maxVel);
            } else {
                MainCamera.moveRight((int) maxVel);
            }
        }
        if (Keyboard.LEFT) {
            if (MainCamera.offsetMinX <= position.getX()) {
                position.setX(position.getX() - maxVel);
            } else {
                MainCamera.moveLeft((int) maxVel);
            }
        }
        if (Keyboard.JUMP) {
            if(onGround) {
                isJumping = true;
            }
        }

        jump();
        fall();


    }

    @Override
    public void draw(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g;
        //g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(texture, (int) position.getX(), (int) position.getY() - MainCamera.y, null);
    }

    public double getPositionX() {
        return super.getPosition().getX();
    }
}
