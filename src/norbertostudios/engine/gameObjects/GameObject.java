package norbertostudios.engine.gameObjects;////

import norbertostudios.engine.assets.Vector2d;

import java.awt.*;

////    Created     12/2/19, 1:40 PM
////    By:         Norberto Studios
////    
public abstract class GameObject
{
    protected Vector2d position;  // in pixels

    public abstract void update(long elapsedTime);
    public abstract void draw(Graphics2D g);

    public GameObject(Vector2d position)
    {
        this.position = position;
    }

    public Vector2d getPosition()
    {
        return position;
    }
    public void setPosition(Vector2d position)
    {
        this.position = position;
    }

    public Vector2d getCenter()
    {
        return new Vector2d(position.getX()/2, position.getY()/2);
    }
}
