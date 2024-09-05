package org.gameOfLife;

import java.util.Arrays;

public class GameOfLife
{
    private int[][] gameBoard;

    public GameOfLife(int[][] board)
    {
        gameBoard = board;
    }


    public int[][] getGameBoard()
    {
        return gameBoard;
    }

    public void nextGeneration()
    {
        for (int row = 0; row < gameBoard.length; ++row)
        {
            for (int col = 0; col < gameBoard[row].length; ++col)
            {
                determineLiveOrDead(new GOLBox(this, row, col));
            }
        }
        System.out.println(this);
    }

    private void determineLiveOrDead(GOLBox box)
    {
        if (box.getCountLiveNeighbors() == 3)
        {
            gameBoard[box.getRow()][box.getCol()] = 1;
        }
        else if(box.getCountLiveNeighbors() != 2)
        {
            gameBoard[box.getRow()][box.getCol()] = 0;
        }
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gameBoard.length; ++i)
        {
            sb.append(Arrays.toString(gameBoard[i]));
            sb.append("\n");
        }
        return sb.toString();
    }

}
