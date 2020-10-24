package norbertostudios.engine.core;////

import norbertostudios.engine.util.Constants;

////    Created     12/1/19, 1:15 PM
////    By:         Norberto Studios
////    
public class GameAction
{
    private String name;
    private int behavior;
    private int amount;
    private int state;

    public GameAction(String name)
    {
        this(name, Constants.NORMAL);
    }

    public GameAction(String name, int behavior)
    {
        this.name = name;
        this.behavior = behavior;
        reset();
    }

    public void reset()
    {
        state = Constants.STATE_RELEASED;
        amount = 0;
    }

    public synchronized void tap()
    {
        press();
        release();
    }

    public synchronized void press()
    {
        press(1);
    }

    public synchronized void press(int amount)
    {
        if(state != Constants.STATE_WAITING_FOR_RELEASE)
        {
            this.amount += amount;
            state = Constants.STATE_PRESSED;
        }
    }

    public synchronized void release()
    {
        state = Constants.STATE_RELEASED;
    }

    // Return Whether the kye was pressed or not since last check
    public boolean isPressed()
    {
        return getAmount() != 0;
    }


    public String getName() {
        return name;
    }

    public synchronized int getAmount() {
        int retVal = amount;
        if(retVal != 0)
        {
            if(state == Constants.STATE_RELEASED)
            {
                amount = 0;
            }else if(behavior == Constants.DETECT_INITIAL_PRESS_ONLY)
            {
                state = Constants.STATE_WAITING_FOR_RELEASE;
                amount = 0;
            }
        }
        return retVal;
    }
}
