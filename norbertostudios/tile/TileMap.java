package norbertostudios.tile;////

import norbertostudios.util.CropSpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

////    Created     11/3/19, 2:37 PM
////    By:         Norberto Studios
////

// TILE MAP ENGINE && DRAWER
public class TileMap {
    private int[][] map; // to layout the mapGrid
    private BufferedImage tilesSheet; // the Image from the spriteSheet

    private int index = -1; // index to access the images in the sprite Sheet
    private int mapTileSize;
    private int spriteTileSize;
    private int emptySprite;

    private CropSpriteSheet cropSpriteSheet;

    // Default
    public TileMap(BufferedImage tilesSheet, int[][] existingMap, int emptySprite) {
        map = new int[existingMap.length][existingMap[0].length];
        this.tilesSheet = tilesSheet;
        this.emptySprite = emptySprite;
        mapTileSize = 64;
        spriteTileSize = 64;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                map[y][x] = existingMap[y][x];
            }
        }
        cropSpriteSheet = new CropSpriteSheet(tilesSheet, spriteTileSize,emptySprite);
    }

    // Constructor #2
    // Needs a SpriteSheet with one block designated to be empty
    public TileMap(BufferedImage tilesSheet, int[][] existingMap, int mapTileSize, int spriteTileSize, int emptySprite) {
        map = new int[existingMap.length][existingMap[0].length];
        this.mapTileSize = mapTileSize;
        this.spriteTileSize = spriteTileSize;
        this.tilesSheet = tilesSheet;
        this.emptySprite = emptySprite;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                map[y][x] = existingMap[y][x];
            }
        }
        cropSpriteSheet = new CropSpriteSheet(tilesSheet, spriteTileSize,emptySprite);
    }

    /*/
    // To crop the SpriteSheet into the spriteTileSize indicated on the constructor
    private BufferedImage cropSpriteSheet(int index) {
        int rows = tilesSheet.getHeight() / spriteTileSize;
        int cols = tilesSheet.getWidth() / spriteTileSize;

        BufferedImage[] sprites = new BufferedImage[rows * cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                sprites[(r * cols) + c] = tilesSheet.getSubimage(
                        c * spriteTileSize,
                        r * spriteTileSize,
                        spriteTileSize, spriteTileSize
                );
            }
        }

        // Returns the Sprite crop to a Buffered Image
        if (index == 0) {
            return sprites[emptySprite]; // the empty space must be assign
        } else {
            return sprites[index - 1];
        }
    }
    */

    // Drawing the SpriteSheet Map
    public void drawMap(Graphics g) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                index = map[y][x];
               // cropSpriteSheet is on a separate Class to be use in different sprite sheet
                g.drawImage(cropSpriteSheet.cropSprite(index),
                        x * mapTileSize,
                        y * mapTileSize,
                        null);
            }
        }
    }

}
