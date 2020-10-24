package norbertostudios.engine.Tiles;////

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Arrays;

////    Created     12/15/19, 12:18 AM
////    By:         Norberto Studios
////    
public class TileMapLoader {
    private int[][] groundMapData;
    private int[][] foregroundMapData;
    private int[][] bGroundMapData;

    int mapWidth = 0;
    int mapHeight = 0;

    // mapTileWidth and mapTileHeight must be same size for this implementation
    int mapTileWidth;
    int mapTileHeight;

    // To Store The Data from the file
    String[] data = new String[10];


    public TileMapLoader(File file) {
        addTileMap(file);
    }

    private void addTileMap(File file) {

        int layers = 0; // haven't used it yet

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("map");
            Node node = list.item(0);
            Element element = (Element) node;

            // imagePath = element.getAttribute("name");
            mapTileWidth = Integer.parseInt(element.getAttribute("tilewidth"));
            mapTileHeight = Integer.parseInt(element.getAttribute("tileheight"));

            System.out.println("MapTileWidth " + mapTileWidth);
            System.out.println("MapTileHeight " + mapTileHeight);

            //reading the layer of the xml file
            list = doc.getElementsByTagName("layer");
            layers = list.getLength();


            for (int i = 0; i < layers; i++) {
                node = list.item(i);
                element = (Element) node;
                if (i <= 0) {
                    mapWidth = Integer.parseInt(element.getAttribute("width"));
                    mapHeight = Integer.parseInt(element.getAttribute("height"));
                }

                data[i] = element.getElementsByTagName("data").item(0).getTextContent();


//                System.out.println("__________-------------________-----\n" + data[1]);
            }

        } catch (Exception o) {
            System.err.println("File Cant be read. TileManager Class");
        }

        //Save the data from the file to a 2D Array
        groundMapData = new int[mapHeight][mapWidth];
        bGroundMapData = new int[mapHeight][mapWidth];
        foregroundMapData = new int[mapHeight][mapWidth];

        // Removing the comas from file
        String[] foreground = data[2].split(",");
        String[] ground = data[1].split(",");
        String[] bGround = data[0].split(",");


        int rows = -1;

        for (int i = 0; i < mapWidth * mapHeight; i++) {

            if (i % mapWidth == 0) {
                rows++;
            }
            // Parsing the String to integers numbers to then be set to the tileMap class
            //System.out.println(i%20 +" -- "+tt);
            groundMapData[rows][i % mapWidth] = Integer.parseInt(ground[i].replaceAll("\\s+", ""));
            bGroundMapData[rows][i % mapWidth] = Integer.parseInt(bGround[i].replaceAll("\\s+", ""));
            foregroundMapData[rows][i % mapWidth] = Integer.parseInt(foreground[i].replaceAll("\\s+", ""));
        }

       // System.out.println(Arrays.deepToString(groundMapData) + "--------------");
       // System.out.println(Arrays.deepToString(foregroundMapData) + "--------9999------");
       // System.out.println(Arrays.deepToString(bGroundMapData) + "--------11111------");
    }

    public int[][] getbGroundMapData() {
        return bGroundMapData;
    }

    public int[][] getForegroundMapData() {
        return foregroundMapData;
    }

    public int[][] getGroundMapData() {
        return groundMapData;
    }

    public int getMapTileSize() {
        return mapTileWidth;
    } // the map tile size from the xml file
}
