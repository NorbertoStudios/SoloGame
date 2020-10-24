package norbertostudios;

import norbertostudios.engine.core.GameLoop;

import javax.swing.*;

////    Created     12/1/19, 12:24 PM
////    By:         Norberto Studios
////    
public class ShowGame
{
//    public static void main(String[] args) {
//        new ShowGame();
//    }

    public ShowGame()
    {
        SwingUtilities.invokeLater(() -> new GameLoop() );
    }
}
