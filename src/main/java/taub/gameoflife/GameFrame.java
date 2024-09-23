package taub.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class GameFrame extends JFrame
{
    int rows = 25;
    int cols = 53;
    GameOfLifeGrid grid = new GameOfLifeGrid(rows, cols);
    Timer timer = new Timer(1000, new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            grid.nextGeneration();
            grid.repaint();
        }
    });

    public GameFrame()
    {
        setSize(80, 400);
        setTitle("Board");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel();
        JButton playButton = new JButton("Play");
        JButton stopButton = new JButton("Stop");
        buttonPanel.add(playButton);
        buttonPanel.add(stopButton);
        playButton.setPreferredSize(new Dimension(100, 100));
        stopButton.setPreferredSize(new Dimension(100, 100));

        setLayout(new BorderLayout());

        add(buttonPanel, BorderLayout.SOUTH);

        try
        {
            Path p = Paths.get(ClassLoader.getSystemResource("glider.rle").toURI());
            File file = new File(String.valueOf(p));
            RleReader reader = new RleReader(file, grid);
            grid = reader.getFileArr();
            add(grid);
            // setup actions
            playButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    timer.start();
                }
            });

            stopButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    timer.stop();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


