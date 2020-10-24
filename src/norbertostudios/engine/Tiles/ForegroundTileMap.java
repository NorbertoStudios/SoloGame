package norbertostudios.engine.Tiles;////

import norbertostudios.engine.MainCamera;
import norbertostudios.engine.assets.Assets;

import java.awt.*;
import java.util.ArrayList;

////    Created     12/15/19, 12:17 AM
////    By:         Norberto Studios
////    
public class ForegroundTileMap{
    private static int[][] map; // to layout the mapGrid

    private static int index = 0; // index to access the images in the sprite Sheet

    private int xStart, xEnd, yStart, yEnd;

    public Tile tile;

    public static ArrayList<Rectangle> rectangleArrayList;

    // Constructor #2
    public ForegroundTileMap(int[][] existingMap) {


        rectangleArrayList = new ArrayList<>();
        map = new int[existingMap.length][existingMap[0].length];

        tile = new Tile(0, 0);

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                map[y][x] = existingMap[y][x];

                index = map[y][x];

                if (index != 0) {
                    rectangleArrayList.add(new Rectangle(x * 64 - MainCamera.x, y * 64 - MainCamera.y, 128, 128));
                }
            }
        }
    }

    public void update() {

        xStart = Math.max(0, MainCamera.x / 64 - 1);
        xEnd = Math.min(200, (MainCamera.x + 1440) / 64 + 1);
        yStart = Math.max(0, MainCamera.y / 64 - 1);
        yEnd = Math.min(24, (MainCamera.y + 900) / 64 + 1);

        if (xEnd >= 200) {
            xEnd = 200;
        }
        if (yEnd >= map.length) {
            yEnd = map.length;
        }


// FOR COLLISION

    }


    public void draw(Graphics2D g) {

        //tile.draw(g);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {

                index = map[y][x];

                if (index != 0) {

                    tile = new Tile(x, y);
                    tile.draw(g);
                    g.drawImage(Assets.objectHashMap.get(index - 129),
                            x * 64 - MainCamera.x,
                            y * 64 - MainCamera.y,
                            null);


                }
            }
        }

//        for (int i = 0; i < rectangleArrayList.size(); i++) {
//            g.drawRect(rectangleArrayList.get(i).x - MainCamera.x,rectangleArrayList.get(i).y - MainCamera.y,
//                    rectangleArrayList.get(i).width,
//                    rectangleArrayList.get(i).height);
//        }
    }
//    }

}
