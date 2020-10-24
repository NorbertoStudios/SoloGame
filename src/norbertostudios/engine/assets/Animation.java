package norbertostudios.engine.assets;////

import java.awt.image.BufferedImage;
import java.util.ArrayList;

////    Created     12/2/19, 12:35 PM
////    By:         Norberto Studios
////    
public class Animation
{
    private ArrayList frames;
    private int currFrameIndex;
    private long animTime;
    private long totalDuration;

    public Animation()
    {
        this(new ArrayList(), 0);
    }

    public Animation(ArrayList frames, long totalDuration)
    {
        this.frames = frames;
        this.totalDuration = totalDuration;
        start();
    }

    // Start the animation over from the beginning
    public synchronized void start()
    {
        animTime = 0;
        currFrameIndex = 0;
    }

    public synchronized void update(long elapsedTime)
    {
        if(frames.size() > 1)
        {
            animTime += elapsedTime;
            if(animTime >= totalDuration)
            {
                animTime = animTime % totalDuration;
                currFrameIndex = 0;
            }
            while (animTime > getFrame(currFrameIndex).endTime)
            {
                currFrameIndex++;
            }
        }
    }

    public synchronized void addFrame(BufferedImage image, long duration)
    {
        totalDuration += duration;
        frames.add(new AnimFrame(image, totalDuration));
    }

    public Object clone()
    {
        return new Animation(frames, totalDuration);
    }

    // return the image of the current frame or null if no images
    public synchronized BufferedImage getImage()
    {
        if(frames.size() == 0)
        {
            return null;
        }else {
                //return Asset.playerHashMap.get(currFrameIndex);
            return getFrame(currFrameIndex).image;
        }

    }

    // Get the image frame
    private  AnimFrame getFrame(int i)
    {
        return (AnimFrame) frames.get(i);
    }

    ///////// Inner class for and end timer
    private class AnimFrame
    {
        BufferedImage image;
        long endTime;

        public AnimFrame(BufferedImage image, long endTime)
        {
            this.image = image;
            this.endTime = endTime;
        }
    }

}
