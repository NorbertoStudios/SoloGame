package norbertostudios.engine.assets;////

import norbertostudios.engine.Tiles.*;
import norbertostudios.engine.graphics.ScreenManager;
import norbertostudios.engine.util.Constants;
import norbertostudios.engine.util.CropSpriteSheet;
import norbertostudios.engine.util.Loader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

////    Created     12/2/19, 11:46 AM
////    By:         Norberto Studios
////    
public class Assets
{
    private static BufferedImage enemiesSpriteSheet;
    private static BufferedImage objectSpriteSheet;
    private static BufferedImage groundMapTileSheet;
    private static BufferedImage playerSheet;

    public static BufferedImage background;
    public static BufferedImage player;
    public static BufferedImage playerUI;

    public static HashMap<Integer, BufferedImage> playerHashMap, groundMapHashMap, foregroundMapHashMap, objectHashMap, enemiesHashMap;

    private static CropSpriteSheet playerCropSpriteSheet, mapCropSpriteSheet, objectCropSpriteSheet, enemiesCropSpriteSheet;


    private static TileMapLoader mapManager;
    private static File tileMapXml;

    public static int[][] groundMapData;
    private static int[][] foregroundMapData;
    private static int[][] bGroundMapData;

    public static int mapTileSize;

    public static BgroundTileMap bGroundTileMap;
    public static ForegroundTileMap foregroundTileMap;
    public static GroundTileMap groundTileMap;


    //Font
    public static Font fontBig;
    public static Font fontMed;

    public static BufferedImage[] numerals = new BufferedImage[11];

    public static ArrayList<Rectangle> AABBCollision;


    public static ScreenManager screen;
    public static DisplayMode screenDisplay;



    /////////////////-INIT-CONSTRUCTOR-///////////////////////////

    public static void init()
    {

        // font
        fontBig = Loader.loadFont("/Fonts/kenvector_future.ttf", 42);
        fontMed = Loader.loadFont("/Fonts/kenvector_future.ttf", 20);

        screen = new ScreenManager();
        DisplayMode displayMode = screen.findFirstCompatibleMode(Constants.POSSIBLE_MODES);
        screen.setFullScreen(displayMode);

        ///??// Map ///???///

        groundMapTileSheet = Loader.ImageLoader("/SpriteSheets/ground.png");

        tileMapXml = Loader.FileLoader("Resources/Map/map3.tmx"); // Get the xml file
        mapManager = new TileMapLoader(tileMapXml);

        groundMapData = mapManager.getGroundMapData(); // save the xml file data for use
        foregroundMapData = mapManager.getForegroundMapData(); // save the xml file data for use
        bGroundMapData = mapManager.getbGroundMapData(); // save the xml file data for use

        mapTileSize = mapManager.getMapTileSize(); // save the xml tile size from file data  // redundant *****

        mapCropSpriteSheet = new CropSpriteSheet(groundMapTileSheet, 128);
        groundMapHashMap = mapCropSpriteSheet.cropSpriteHashMap();

        bGroundTileMap      = new BgroundTileMap(bGroundMapData);
        groundTileMap       = new GroundTileMap(groundMapData);
        foregroundTileMap   = new ForegroundTileMap(foregroundMapData);

        ////--/// Player ///////////

        playerSheet = Loader.ImageLoader("/SpriteSheets/Player.png");
        playerCropSpriteSheet = new CropSpriteSheet(playerSheet, 128);

        playerHashMap = playerCropSpriteSheet.cropSpriteHashMap();
        player = playerHashMap.get(0);

        playerUI = Loader.ImageLoader("/UI/hudPlayer_blue.png");

        //////////
        background = Loader.ImageLoader("/Backgrounds/backgroundEmpty.png");
        //////////

        //// Objects in the screen
        objectSpriteSheet = Loader.ImageLoader("/SpriteSheets/spritesheet_tiles2.png");
        objectCropSpriteSheet = new CropSpriteSheet(objectSpriteSheet, 128);

        objectHashMap = objectCropSpriteSheet.cropSpriteHashMap();
        ///////////////////////

        //////// Enemies ////////

        enemiesSpriteSheet = Loader.ImageLoader("/SpriteSheets/spritesheet_enemies.png");
        enemiesCropSpriteSheet = new CropSpriteSheet(enemiesSpriteSheet, 128);

        enemiesHashMap = enemiesCropSpriteSheet.cropSpriteHashMap();
        //////////////////

        // UI Score numbers
        for (int i = 0; i < numerals.length; i++) {
            numerals[i] = Loader.ImageLoader("/UI/hud" + (i) + ".png");

        }


        System.out.println("****** Assets Loaded Completed ******");
    }

}
