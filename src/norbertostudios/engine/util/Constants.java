package norbertostudios.engine.util;////

import norbertostudios.engine.assets.Assets;
import norbertostudios.engine.core.GameBase;
import norbertostudios.engine.graphics.ScreenManager;

import java.awt.*;

////    Created     12/1/19, 1:12 PM
////    By:         Norberto Studios
////    
public class Constants {
    public static final DisplayMode POSSIBLE_MODES[] = {

           // new DisplayMode(1280, 800, 32, 0),
//            new DisplayMode(1024, 640, 32, 0),
//            new DisplayMode(2560, 1600, 32, 0),
//            new DisplayMode(800, 600, 16, 0),
//            new DisplayMode(800, 600, 32, 0),
//            new DisplayMode(800, 600, 24, 0),
//            new DisplayMode(640, 480, 16, 0),
//            new DisplayMode(640, 480, 32, 0),
//            new DisplayMode(640, 480, 24, 0),
//            new DisplayMode(1024, 768, 16, 0),
//            new DisplayMode(1920, 1080, 32, 0),
//            new DisplayMode(1024, 768, 24, 0),
            new DisplayMode(1440, 900, 32, 0),
    };

    public static final int WIDTH = 1440;
    public static final int HEIGHT = 900;

    /////////// Press Behavior

    public static final int NORMAL = 0;
    public static final int DETECT_INITIAL_PRESS_ONLY = 1;

    public static final int STATE_RELEASED = 0;
    public static final int STATE_PRESSED = 1;
    public static final int STATE_WAITING_FOR_RELEASE = 2;

    /////
    // key codes are defined in java.awt.KeyEvent.
    // most of the codes (except for some rare ones like
    // "alt graph") are less than 600.
    public static final int NUM_KEY_CODES = 600;


    /////////////
    public static final int DIE_TIME = 1000;

    public static final int STATE_NORMAL = 0;
    public static final int STATE_DYING = 1;
    public static final int STATE_DEAD = 2;
/////////////////


    public static final float JUMP_SPEED = -.65f;
    public static final float GRAVITY = 0.002f;
    public static final float MAX_SPEED = 0.5f;



    ////////////////////
    // Tiles
    public static final int MAP_WIDTH = 12800; // not yet assign
    public static final int MAP_HEIGHT = 1536; // not yet assign

    public static final int MAP_TILE_WIDTH = 64;
    public static final int MAP_TILE_HEIGHT = 64;
    public static final int MAP_TILE_SIZE = 64;  // 128 for this game





    public static final int SPAWNING_TIME = 3000;  // 128 for this game
    public static final int FLICKER_TIME = 200;  // 128 for this game
}
