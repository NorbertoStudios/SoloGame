package norbertostudios.gameObjects;////

import norbertostudios.core.GameState;
import norbertostudios.math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

////    Created     11/5/19, 11:02 AM
////    By:         Norberto Studios
////    
public  abstract class MovingGameObjects extends GameObjects {

    protected Vector2D velocity;

    protected int width;
    protected int height;

    protected double maxVel;

    protected GameState gameState;

    public MovingGameObjects(BufferedImage texture, Vector2D position, Vector2D velocity, double maxVel, GameState gameState) {
        super(texture, position);
        this.velocity = velocity;
        this.maxVel = maxVel;
        this.gameState = gameState;
        width = texture.getWidth();
        height = texture.getHeight();
    }


    public Vector2D getCenter() {

        return new Vector2D(position.getX() + width / 2, position.getY() + height / 2);
    }

}
