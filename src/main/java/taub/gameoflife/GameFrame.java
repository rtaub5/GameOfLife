package taub.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

        add(grid);
        add(buttonPanel, BorderLayout.SOUTH);

        grid.changeField(18, 4);
        grid.changeField(18, 5);
        grid.changeField(18, 6);


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

    }

}


