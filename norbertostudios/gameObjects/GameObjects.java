package norbertostudios.gameObjects;////

import norbertostudios.math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

////    Created     11/5/19, 10:53 AM
////    By:         Norberto Studios
////    
public abstract class GameObjects {
    protected BufferedImage texture;
    protected Vector2D position;

    public abstract void update();
    public abstract void draw(Graphics g);

    public GameObjects(BufferedImage texture, Vector2D position) {
        this.position = position;
        this.texture = texture;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

}
