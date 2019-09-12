package visual;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame
{
    JPanel main;

    public Application()
    {
        // Setting the Frame
        setTitle("Game Prototype Frame");
        setResizable(false);
        setBounds(2300,200,700,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        main = new JPanel();
        main.setBackground(Color.BLUE);

        add(main);
    }
}
