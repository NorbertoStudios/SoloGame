package norbertostudios.engine.hud;////

import norbertostudios.engine.assets.Assets;
import norbertostudios.engine.assets.Vector2d;
import norbertostudios.engine.core.GameLoop;

import java.awt.*;

////    Created     10/30/19, 8:56 AM
////    By:         Norberto Studios
////    
public class HUD {
    private int score = 0;
    private int lives = 3;


    public void addScore(int value, Vector2d position, GameLoop gameState) {
        gameState.getMessages().add(new Message(position, true,
                "+"+value+" score", Color.WHITE, false
        , Assets.fontMed,gameState));
        score += value;
    }

    public void addLives(int value) {
        lives += value;
    }

    public int getLives() {
        return lives;
    }

    public void delLives() {
        lives--;
    }

    public void drawLives(Graphics g) {
        Vector2d livesPos = new Vector2d(25, 25);

        g.drawImage(Assets.playerUI, (int) livesPos.getX(), (int) livesPos.getY(), 60,60,null);

        g.drawImage(Assets.numerals[10], (int) livesPos.getX() + 40, (int) livesPos.getY() +5, 60,60, null);

        String livesToString = Integer.toString(lives);

        Vector2d pos = new Vector2d(livesPos.getX(), livesPos.getY());

        for (int i = 0; i < livesToString.length(); i++) {

           int number = Integer.parseInt(livesToString.substring(i,i +1));

           if(number <= 0)
           {
               break;
           }
            g.drawImage(Assets.numerals[number],
                    (int) pos.getX() + 60, (int) pos.getY() + 5,60,60, null);
            pos.setX(pos.getX() + 20);
        }


    }

    public void drawScore(Graphics g) {

        Vector2d pos = new Vector2d(850, 25);

        String scoreToString = Integer.toString(score);

        for (int i = 0; i < scoreToString.length(); i++) {
            g.drawImage(Assets.numerals[Integer.parseInt(scoreToString.substring(i, i + 1))],
                    (int) pos.getX(), (int) pos.getY(), 60,60,null);
            pos.setX(pos.getX() + 20);
        }
    }

}
