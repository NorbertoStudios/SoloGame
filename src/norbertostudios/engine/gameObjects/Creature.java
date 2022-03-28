package norbertostudios.engine.gameObjects;////

import norbertostudios.engine.MainCamera;
import norbertostudios.engine.Tiles.BgroundTileMap;
import norbertostudios.engine.Tiles.GroundTileMap;
import norbertostudios.engine.assets.Vector2d;
import norbertostudios.engine.util.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

////    Created     12/2/19, 12:33 PM
////    By:         Norberto Studios
////    
public abstract class Creature extends GameObject {

    private int state;
    private long stateTime;


    protected double gravity;

    protected BufferedImage texture;
    protected Vector2d velocity;  // velocity (pixels per Milliseconds)

    protected double maxVelocity;

    protected int width;
    protected int height;


    public boolean isFalling;
    public boolean onGround;
    public boolean isJumping;


    protected ArrayList<Rectangle> AABB;


    protected Rectangle rect;

    public Creature(BufferedImage texture, Vector2d position, Vector2d velocity) {
        super(position);
        this.texture = texture;
        this.velocity = velocity;

        isFalling = true;
        isJumping = false;
        onGround = false;

        width = texture.getWidth();
        height = texture.getHeight();

        rect = new Rectangle(0, 0, 0, 0);
        AABB = new ArrayList<>();

        arrayListRectangle();
    }

    @Override
    public void update(long elapsedTime) {

        position.setX(position.getX() + velocity.getX() * elapsedTime);
        position.setY(position.getY() + velocity.getY() * elapsedTime);


//        fall();

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


    public boolean isAlive() {
        return (state == Constants.STATE_NORMAL);
    }

    // Getting all the rectangles that can collide with
    public void arrayListRectangle()
    {
        int start = GroundTileMap.groundRectangleArrayList.size();
        int size = GroundTileMap.groundRectangleArrayList.size() + BgroundTileMap.rectangleArrayList.size();

        //Starting the Arraylist with the ground titles
        for (int i = 0; i < start; i++)
        {
            AABB.add(GroundTileMap.groundRectangleArrayList.get(i));
        }

        for (int i = start; i < size; i++)
        {
            AABB.add(BgroundTileMap.rectangleArrayList.get(i - GroundTileMap.groundRectangleArrayList.size()));
        }

    }

    public Rectangle collision(int i) {
        return new Rectangle(AABB.get(i).x - MainCamera.x,
                AABB.get(i).y - MainCamera.y,
                AABB.get(i).width,
                AABB.get(i).height);
    }
//    public Rectangle collision(int i) {
//        return new Rectangle(GroundTileMap.groundRectangleArrayList.get(i).x - MainCamera.x,
//                GroundTileMap.groundRectangleArrayList.get(i).y - MainCamera.y,
//                GroundTileMap.groundRectangleArrayList.get(i).width,
//                GroundTileMap.groundRectangleArrayList.get(i).height);
//    }
//
//    public Rectangle bGcollision(int i) {
//        return new Rectangle(BgroundTileMap.rectangleArrayList.get(i).x - MainCamera.x,
//                BgroundTileMap.rectangleArrayList.get(i).y - MainCamera.y,
//                BgroundTileMap.rectangleArrayList.get(i).width,
//                BgroundTileMap.rectangleArrayList.get(i).height);
//    }
//

//    public boolean leftCollision() {
//        for (int i = 0; i < AABB.size(); i++) {
//            rect = collision(i);
//            if (rect.intersects((int) position.getX() + 20, (int) position.getY() + 40, 10, 70)) {
//                System.out.println("Left Collision");
//                break;
//            }
//        }
//
//        return getBounds().intersects(rect);
//    }
//
//    public boolean rightCollision() {
//        for (int i = 0; i < AABB.size(); i++) {
//            rect = collision(i);
//            if (rect.intersects((int) position.getX() + texture.getWidth() - 40, (int) position.getY() + 40, 10, 70)) {
//                System.out.println("Right Collision");
//                break;
//            }
//        }
//
//        return getBounds().intersects(rect);
//    }
//
//    public boolean bottomCollision() {
//        for (int i = 0; i < AABB.size(); i++) {
//            rect = collision(i);
//            if (rect.intersects((int) position.getX() + texture.getWidth() / 4 + 10, (int) position.getY() + texture.getWidth(), texture.getWidth() / 2 - 20, 10)) {
//                break;
//            }
//        }
//
//        return getBounds().intersects(rect);
//    }
//
//    public boolean topCollision() {
//        for (int i = 0; i < AABB.size(); i++) {
//            rect = collision(i);
//
//            if (rect.intersects((int) position.getX() + texture.getWidth() / 4 + 10, (int) position.getY() + 25, texture.getWidth() / 2 - 20, 5)) {
//                //System.out.println("Collision");
//                break;
//            }
//        }
//        return getBounds().intersects(rect);
//    }
//
//    public Rectangle getBounds() {
//        return new Rectangle((int) position.getX() + texture.getWidth() / 4,
//                (int) position.getY() + 30,
//                texture.getWidth() / 2,
//                100);
//    }

/*/
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

//*/

//    public void verticalCollision() {
//        if (bottomCollision() || position.getY() == rect.y - rect.height + 1) {
//            setOnGround(true);
//            position.setY(rect.y - rect.height + 1);
//        }
//        if (topCollision()) {
//            position.setY(rect.y + rect.height - rect.height / 4);
//        }
//    }
//
//    public void horizontalCollision() {
//
//        // Bounds the xStart
//        if (position.getX() + 30 < 0) {
//            position.setX(-30);
//        }
//        if (leftCollision()) {
//            position.setX(rect.x + rect.width - rect.width / 4);
//        }
//        if (rightCollision()) {
//            position.setX(rect.x - rect.width + rect.width / 4);
//        }
//
//    }


    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }



/*/

    protected void collideWith() {

        ArrayList<Creature> movingObjects = gameState.getMovingObjectArrayList();

        for (int i = 0; i < movingObjects.size(); i++) {

            Creature m = movingObjects.get(i);

            if (m.equals(this)) {
                continue;
            }

            double distance = m.getCenter().sub(getCenter()).getMagnitude();

            if (distance < m.width / 2 + width / 2 && movingObjects.contains(this)) {
                objectCollision(m, this);
            }
        }
    }

    public void objectCollision(Creature a, Creature b) {
        if (a instanceof Player && ((Player) a).isSpawning()) {
            return;
        }
        if (b instanceof Player && ((Player) b).isSpawning()) {
            return;
        }
        if (!(a instanceof Slime && b instanceof Slime)) {
            // gameState.playExplosion(getCenter());
            // a.destroy();
            // b.destroy();
        }
    }


//*/

}
