package norbertostudios.input;////

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

////    Created     10/31/19, 10:51 AM
////    By:         Norberto Studios
////    
public class Keyboard implements KeyListener
{
    public boolean[] keys;

    public static boolean UP, DOWN, LEFT, RIGHT, SHOOT, JUMP, DUCK, SCAPE;

    public Keyboard()
    {
        keys = new boolean[256];

        UP      = false;
        DOWN    = false;
        LEFT    = false;
        RIGHT   = false;
        SHOOT   = false;
        JUMP    = false;
        DUCK    = false;
    }

    public void update()
    {
        UP = keys[KeyEvent.VK_UP];
        DOWN = keys[KeyEvent.VK_DOWN];
        LEFT = keys[KeyEvent.VK_LEFT];
        RIGHT = keys[KeyEvent.VK_RIGHT];
        SHOOT = keys[KeyEvent.VK_C];
        JUMP = keys[KeyEvent.VK_SPACE];
        DUCK = keys[KeyEvent.VK_X];

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Not Used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
