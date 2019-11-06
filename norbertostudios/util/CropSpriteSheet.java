package norbertostudios.util;////

import java.awt.image.BufferedImage;

////    Created     11/5/19, 10:34 AM
////    By:         Norberto Studios
////    
public class CropSpriteSheet
{
    BufferedImage tilesSheet;
    int spriteTileSize;
    int emptySprite;

    public CropSpriteSheet(BufferedImage tilesSheet, int spriteTileSize, int emptySprite)
    {
        this.tilesSheet = tilesSheet;
        this.spriteTileSize = spriteTileSize;
        this.emptySprite = emptySprite;
    }

    public BufferedImage cropSprite(int index) {
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
}
