package norbertostudios.engine.assets;////

import norbertostudios.engine.gameObjects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

////    Created     12/2/19, 12:35 PM
////    By:         Norberto Studios
////    
public class Sprite extends GameObject
{
    protected BufferedImage texture;
    protected Vector2d velocity;  // velocity (pixels per Milliseconds)

    protected double maxVelocity;

    protected int width;
    protected int height;

    public Sprite(BufferedImage texture, Vector2d position, Vector2d velocity) {
        super(position);
        this.texture = texture;
        this.velocity = velocity;

        width = texture.getWidth();
        height = texture.getHeight();
    }

    @Override
    public void update(long elapsedTime) {

        position.setX(position.getX() + velocity.getX() * elapsedTime);
        position.setY(position.getY() + velocity.getY() * elapsedTime);

    }

    @Override
    public void draw(Graphics2D g) {

    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }

    // get current Sprite Image
    public BufferedImage getTexture() {
        return texture;
    }

}
