package taub.gameoflife;

import javax.swing.*;
import java.awt.*;

public class GameOfLifeComponent extends JComponent
{


    private GameOfLife game;
    private int unitMeasure;
    private int startX;
    private int startY;

    public GameOfLifeComponent(int rows, int cols)
    {
        game = new GameOfLife(rows, cols);
    }

    public GameOfLifeComponent(int[][] matrix)
    {
        game = new GameOfLife(matrix);
    }

    public GameOfLife getGame()
    {
        return game;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Color lightMagenta = new Color(255, 0, 255, 25);
        Color darkMagenta = new Color(255, 0, 255, 200);

        g2.setStroke(new BasicStroke(2));
        unitMeasure = 6;
        startY = 20;

        for (int row = 0; row < game.getRows() * unitMeasure; row = row + unitMeasure)
        {
            startX = 30;
            for (int column = 0; column < game.getColumns(); column++)
            {

                if (game.getOrigGameBoard()[row / unitMeasure][column] == 1)
                {
                    g2.setColor(darkMagenta);
                } else {
                    g2.setColor(lightMagenta);
                }
                g2.fillRect(startX, startY, unitMeasure, unitMeasure);
                startX = startX + unitMeasure;
            }
             startY = startY + unitMeasure;
        }
    }


    public void changeField(int row, int col)
    {
        game.setOrigBoardField(row, col, 1);
    }

    public void regenerateBoard(int [][] mock)
    {
        game.regenerateBoard(mock);
    }


    public void nextGeneration()
    {
        game.nextGeneration();
    }


    public int[][] getGameOfLifeBoard()
    {
        return game.getOrigGameBoard();
    }

    public void toggleCell(int x, int y)
    {
        int [][] mock = game.getOrigGameBoard();
        startX = 30;
        startY = 20;
        int row = (y - startY) / unitMeasure;
        int col = (x - startX) / unitMeasure;
        if (mock[row][col] == 0)
        {
             game.setOrigBoardField(row, col, 1);
        } else {
             game.setOrigBoardField(row, col, 0);
        }
        repaint();
    }
}
