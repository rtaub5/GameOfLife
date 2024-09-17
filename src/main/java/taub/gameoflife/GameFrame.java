package taub.gameoflife;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame
{
    public GameFrame()
    {

        setSize(80, 400);
        setTitle("Board");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int rows = 25;
        int cols = 53;

        JPanel buttonPanel = new JPanel();
        JButton playButton = new JButton("Play");
        buttonPanel.add(playButton);
        playButton.setPreferredSize(new Dimension(100, 100));

        setLayout(new BorderLayout());
        GameOfLifeGrid grid = new GameOfLifeGrid(rows, cols);
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
                grid.game.nextGeneration();
                grid.repaint();
            }
        });



    }

}
