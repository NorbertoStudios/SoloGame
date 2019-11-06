package norbertostudios;////

import norbertostudios.core.GameLoop;
import norbertostudios.window.Window;

import javax.swing.*;

////    Created     10/30/19, 10:48 PM
////    By:         Norberto Studios
////    
public class ShowGame
{
    private ShowGame()
    {
        SwingUtilities.invokeLater(() -> new GameLoop());
    }
    public static void main(String[] args) {
       new ShowGame();
//    new GameLoop();
    }
}
