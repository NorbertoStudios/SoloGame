package norbertostudios.cam;////

import norbertostudios.creature.Creature;
import norbertostudios.creature.Player;
import norbertostudios.util.Constants;
import sun.applet.Main;

////    Created     11/5/19, 9:50 AM
////    By:         Norberto Studios
////    
public class MainCamera
{
    private Creature target = null;

    public static float offsetMaxX;
    public static float offsetMaxY;
    public static float offsetMinX;
    public static float offsetMinY;

    private float offsetX;
    private float offsetY;

    public static int x;
    public static int y;

 public static void setValues(int x, int y)
 {
     MainCamera.x = x;
     MainCamera.y = y;

     offsetMaxX = Constants.WIDTH /2;
     offsetMinX = Constants.WIDTH /2 - 300;
 }

 public static void moveLeft(int dx)
 {
     x -= dx;
 }
 public static void moveRight(int dx)
 {
     x += dx;
 }

}
