package norbertostudios.core;////

import norbertostudios.cam.MainCamera;
import norbertostudios.creature.Player;
import norbertostudios.math.Vector2D;
import norbertostudios.tile.TileMap;
import norbertostudios.tile.TileMapManager;
import norbertostudios.util.Assets;
import norbertostudios.util.Constants;
import norbertostudios.util.Loader;

import java.awt.*;
import java.util.Arrays;

////    Created     10/30/19, 10:58 PM
////    By:         Norberto Studios
////    
public class GameState {
    TileMapManager mapManager;
    TileMap map;

    private Player player;

    public static final Vector2D PLAYER_START_POSITION = new Vector2D(Constants.WIDTH/2 - Assets.player.getWidth()/2,
            Constants.HEIGHT/2 - Assets.player.getHeight()/2);

    public GameState() {

        mapManager = new TileMapManager(Assets.tileMapXml); // Get the File
        map = new TileMap(Assets.tileSheet, mapManager.getMapData(), mapManager.getMapTileSize(), 128, 18); //Pass the data to create map


        player = new Player(Assets.player,PLAYER_START_POSITION, new Vector2D(),7,this);


    }

    public void update() {
        player.update();
    }

    public void draw(Graphics g) {

        int offsetX = Constants.WIDTH / 2 - Math.round((int)player.getPositionX()) - 128;

        offsetX = Math.min(offsetX, 0);
        offsetX = Math.max(offsetX, Constants.WIDTH - Constants.MAP_WIDTH);

        int x = offsetX *
                (Constants.WIDTH - Assets.background.getWidth(null)) /
                (Constants.WIDTH - Constants.MAP_WIDTH);


        g.drawImage(Assets.background,-x - MainCamera.x,0,Constants.MAP_WIDTH,Constants.HEIGHT,null);

//        too Heavy
       // map.drawMap(g); // spriteSheet map drawn
        player.draw(g);

    }

}
