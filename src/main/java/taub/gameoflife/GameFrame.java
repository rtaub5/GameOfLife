package taub.gameoflife;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



public class GameFrame extends JFrame
{

    GameOfLifeGrid grid;

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
        setSize(700, 1500);
        setTitle("Board");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel();
        JButton playButton = new JButton("Play");
        JButton stopButton = new JButton("Stop");
        JButton pasteButton = new JButton("Paste");
        buttonPanel.add(playButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(pasteButton);
        playButton.setPreferredSize(new Dimension(100, 100));
        stopButton.setPreferredSize(new Dimension(100, 100));
        pasteButton.setPreferredSize(new Dimension(100, 100));

        setLayout(new BorderLayout());

        add(buttonPanel, BorderLayout.SOUTH);
        grid = new GameOfLifeGrid(100, 100);
        add(grid);

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

        pasteButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    String clipboard = (String) toolkit.getSystemClipboard().getData(DataFlavor.stringFlavor);
                    RleReader reader = new RleReader(clipboard);
                   int [][] mock = reader.readRleString();
                    grid.regenerateBoard(mock);
                    grid.repaint();
                } catch (UnsupportedFlavorException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }


            }
        });

    }

}


