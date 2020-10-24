package norbertostudios.engine.input;////

import norbertostudios.engine.core.GameAction;
import norbertostudios.engine.util.Constants;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

////    Created     12/1/19, 1:27 PM
////    By:         Norberto Studios
////    
public class InputManager implements KeyListener
{

    private GameAction[] keyActions = new GameAction[Constants.NUM_KEY_CODES];
    private Component comp;

    public InputManager(Component comp)
    {
        this.comp = comp;

        comp.addKeyListener(this);

        // allow input of the TAB key and other keys normally
        // used for focus traversal
        comp.setFocusTraversalKeysEnabled(false);
    }

    public void mapToKey(GameAction gameAction, int keyCode)
    {
        keyActions[keyCode] = gameAction;
    }

    public void clearMap(GameAction gameAction)
    {
        for (int i = 0; i < keyActions.length; i++) {
            if(keyActions[i] == gameAction)
            {
                keyActions[i] = null;
            }
        }

        gameAction.reset();
    }

    public ArrayList getMaps(GameAction gameCode)
    {
        ArrayList list = new ArrayList();

        for (int i = 0; i < keyActions.length; i++) {
            if(keyActions[i] == gameCode)
            {
                list.add(getKeyName(i));
            }
        }

        return list;
    }

    public void resetAllGameActions()
    {
        for (int i = 0; i < keyActions.length; i++) {
            if(keyActions[i] != null)
            {
                keyActions[i].reset();
            }
        }
    }

    public static String getKeyName(int keyCode)
    {
        return KeyEvent.getKeyText(keyCode);
    }

    private GameAction getKeyAction(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if(keyCode < keyActions.length)
        {
            return keyActions[keyCode];
        }
        else{
            return null;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        GameAction gameAction = getKeyAction(e);
        if(gameAction != null)
        {
            gameAction.press();
        }

        // make sure the key isn't processed for anything else
        e.consume();

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        GameAction gameAction = getKeyAction(e);
        if(gameAction != null)
        {
            gameAction.release();
        }

        // make sure the key isn't processed for anything else
        e.consume();
    }
}
