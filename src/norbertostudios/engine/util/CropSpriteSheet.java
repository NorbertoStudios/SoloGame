package norbertostudios.engine.util;////

import java.awt.image.BufferedImage;
import java.util.HashMap;

////    Created     12/2/19, 12:07 PM
////    By:         Norberto Studios
////    
public class CropSpriteSheet
{
    private BufferedImage tileSheet;
    private int spriteTileSize;

    public CropSpriteSheet(BufferedImage tileSheet, int spriteTileSize)
    {
        this.tileSheet = tileSheet;
        this.spriteTileSize = spriteTileSize;
    }


    // Returning a single image from the sprite Sheet
    public BufferedImage cropSingleSprite(int index)
    {
        int rows = tileSheet.getHeight() / spriteTileSize;
        int cols = tileSheet.getWidth() / spriteTileSize;

        BufferedImage[] sprites = new BufferedImage[rows * cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                sprites[(r * cols) + c] = tileSheet.getSubimage(
                        c * spriteTileSize,
                        r * spriteTileSize,
                        spriteTileSize,spriteTileSize
                );
            }
        }

        // Returns the single sprite at the specify index
        return sprites[index];
    }


    // The HashMap Version which returns a HasMap of Buffered Images with an
    // index to access it
    public HashMap<Integer, BufferedImage> cropSpriteHashMap()
    {
        HashMap<Integer, BufferedImage> spriteCrop = new HashMap<>();

        int rows = tileSheet.getHeight() / spriteTileSize;
        int cols = tileSheet.getWidth() / spriteTileSize;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                spriteCrop.put(((r * cols) + c), tileSheet.getSubimage(
                        c * spriteTileSize,
                        r * spriteTileSize,
                        spriteTileSize,
                        spriteTileSize
                ));
            }
        }

        return spriteCrop;
    }



}
