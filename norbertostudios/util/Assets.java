package norbertostudios.util;////

import java.awt.image.BufferedImage;
import java.io.File;

////    Created     10/30/19, 11:04 PM
////    By:         Norberto Studios
////    
public class Assets
{
   public static BufferedImage background;
   public static BufferedImage tileSheet;
   public static File tileMapXml;

   public static BufferedImage playerSheet;
   public static BufferedImage player;

   private static CropSpriteSheet cropSpriteSheet;


   public static void init()
   {



      background = Loader.ImageLoader("/Backgrounds/backgroundCastles.png");

      playerSheet = Loader.ImageLoader("/Spritesheets/Player.png");

      tileSheet = Loader.ImageLoader("/Spritesheets/my2dtest.png");

      tileMapXml = Loader.FileLoader("Resources/Files/My2dTestMap.tmx");


      cropSpriteSheet = new CropSpriteSheet(playerSheet,128,12);

      player = cropSpriteSheet.cropSprite(1);



      System.out.println("****** Assets Loaded Completed ******");
   }

}
