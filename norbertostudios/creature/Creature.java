package norbertostudios.creature;////

import norbertostudios.core.GameState;
import norbertostudios.gameObjects.MovingGameObjects;
import norbertostudios.math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

////    Created     11/5/19, 9:42 AM
////    By:         Norberto Studios
////    
public abstract class Creature extends MovingGameObjects
{

    public Creature(BufferedImage texture, Vector2D position, Vector2D velocity, double maxVel, GameState gameState) {
        super(texture, position, velocity, maxVel, gameState);
    }
}
