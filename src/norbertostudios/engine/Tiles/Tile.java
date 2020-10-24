package norbertostudios.engine.Tiles;////

import norbertostudios.engine.MainCamera;
import norbertostudios.engine.assets.Assets;
import norbertostudios.engine.assets.Vector2d;

import java.awt.*;

////    Created     12/15/19, 12:17 AM
////    By:         Norberto Studios
////    
public class Tile {
    public static Vector2d position;
    public int id;

    private int[][] map; // to layout the mapGrid

    public Tile(int x, int y) {

        map = new int[Assets.groundMapData.length][Assets.groundMapData[0].length];
        position = new Vector2d(x, y);

//        System.out.println(rect);
    }

    // Collision

    public static Rectangle getBounds() {
        return new Rectangle((int)position.getX() * 64, (int)position.getY() * 64, 128, 128);
    }


    public void draw(Graphics2D g) {

        getBounds();
//        g.drawImage(Assets.mapHashMap.get(id - 1),
//                (int) position.getX() * 64 - MainCamera.x,
//                (int) position.getY() * 64 - MainCamera.y,
//                null);
       // g.drawRect((int)position.getX() * 64, (int)position.getY() * 64, 128, 128);
       // g.drawRect(getBounds().x,getBounds().y,getBounds().width,getBounds().height);
    }
}
