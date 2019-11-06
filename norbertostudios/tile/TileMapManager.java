package norbertostudios.tile;////

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

////    Created     11/3/19, 1:08 PM
////    By:         Norberto Studios
////

// This class need from the xml file the tileWidth, tileHeight, width and height
public class TileMapManager {
    //This class is designed to read the xml file and get the properties

    private int[][] mapData;
    int mapWidth = 0;
    int mapHeight = 0;

    // mapTileWidth and mapTileHeight must be same size for this implementation
    int mapTileWidth;
    int mapTileHeight;

    // To Store The Data from the file
    String[] data = new String[10];


    public TileMapManager(File file) {
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

                //System.out.println("__________-------------________-----\n" + data[i]);
            }

        } catch (Exception o) {
            System.err.println("File Cant be read. TileManager Class");
        }

        //Save the data from the file to a 2D Array
       mapData = new int[mapHeight][mapWidth];

        // Removing the comas from file
        String[] block = data[0].split(",");

        int rows = -1;

        for (int i = 0; i < mapWidth * mapHeight; i++) {

            if (i % mapWidth == 0) {
                rows++;
            }
            // Parsing the String to integers numbers to then be set to the tileMap class
            //System.out.println(i%20 +" -- "+tt);
            mapData[rows][i % 20] = Integer.parseInt(block[i].replaceAll("\\s+", ""));
        }

//        System.out.println(Arrays.deepToString(mapData));
    }

    public int[][] getMapData() {
        return mapData;
    }

    public int getMapTileSize()
    {
        return mapTileWidth;
    }
}
