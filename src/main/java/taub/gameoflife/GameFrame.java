package taub.gameoflife;

import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame
{
    boolean running = true;
    public GameFrame()
    {
        setSize(80, 400);
        setTitle("Board");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel();
        JButton playButton = new JButton("Play");
        buttonPanel.add(playButton);
        playButton.setPreferredSize(new Dimension(100, 100));

        setLayout(new BorderLayout());

        int rows = 25;
        int cols = 53;
        GameOfLifeGrid grid = new GameOfLifeGrid(rows, cols);
        add(grid);
        add(buttonPanel, BorderLayout.SOUTH);

        grid.changeField(18, 4);
        grid.changeField(18, 5);
        grid.changeField(18, 6);

    }

}


