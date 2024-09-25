package taub.gameoflife;

import javax.swing.*;
import java.awt.*;

public class GameOfLifeGrid extends JComponent
{
    private GameOfLife game;

    public GameOfLifeGrid(int rows, int cols)
    {
        game = new GameOfLife(rows, cols);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Color lightMagenta = new Color(255, 0, 255, 25);
        Color darkMagenta = new Color(255, 0, 255, 200);

        g2.setStroke(new BasicStroke(2));
        int unitMeasure = 25;
        int startY = 55;

        for (int row = 0; row < game.getRows() * unitMeasure; row = row + unitMeasure)
        {
            int startX = 65;
            for (int column = 0; column < game.getColumns(); column++)
            {

                if (game.getOrigGameBoard()[row / unitMeasure][column] == 1)
                {
                    g2.setColor(darkMagenta);
                } else {
                    g2.setColor(lightMagenta);
                }
                g2.fillRect(startX, startY, unitMeasure, unitMeasure);
                startX = startX + 25;
            }
             startY = startY + unitMeasure;
        }

    }


    public void changeField(int row, int col)
    {
        game.setOrigBoardFieldLive(row, col);
    }


    public void nextGeneration()
    {
        game.nextGeneration();
    }


    public int[][] getGameOfLifeBoard()
    {
        return game.getOrigGameBoard();
    }
}
