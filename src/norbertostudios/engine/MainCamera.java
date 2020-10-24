package norbertostudios.engine;////

import norbertostudios.engine.util.Constants;

////    Created     12/15/19, 12:40 AM
////    By:         Norberto Studios
////    
public class MainCamera
{
    public static float offsetMaxX;
    public static float offsetMaxY;
    public static float offsetMinX;
    public static float offsetMinY;

    public static int x;
    public static int y;

    public static void setValues(int x, int y)
    {
        MainCamera.x = x;
        MainCamera.y = y;

        offsetMaxX = 800;
        offsetMinX = 300;

        offsetMaxY = Constants.HEIGHT /2;
        offsetMinY = Constants.HEIGHT /2 - 300;
    }

    public static void moveLeft(int dx)
    {
        x -= dx;
    }
    public static void moveRight(int dx)
    {
        x += dx;
    }
    public static void moveUp(int dy)
    {
        y -= dy;
    }
    public static void moveDown(int dy)
    {
        y += dy;
    }

}
