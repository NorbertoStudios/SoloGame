package norbertostudios.util;////

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

////    Created     10/30/19, 11:05 PM
////    By:         Norberto Studios
////    
public class Loader
{
    public static BufferedImage ImageLoader(String path) {
        try {
            return ImageIO.read(Loader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File FileLoader(String path)
    {
        try {
            return new File(path);
        }catch (Exception o){
            System.out.println("FileLoader File Not Found");}
        return null;
    }

}
