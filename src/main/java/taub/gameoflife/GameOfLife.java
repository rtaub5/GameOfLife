package taub.gameoflife;

import java.util.Arrays;

public class GameOfLife
{
    private int[][] origGameBoard;
    private int[][] newGameBoard;
    private int rows;
    private int columns;

    public GameOfLife(int[][] board)
    {
        origGameBoard = board;
        setNewGameBoard();
    }

    public GameOfLife(int rows, int cols)
    {
        origGameBoard = new int[rows][cols];
        this.rows = rows;
        this.columns = cols;
        setNewGameBoard();
    }

    public void regenerateBoard(int [][] mock)
    {
        origGameBoard = mock;
        setNewGameBoard();
    }



    public int getRows()
    {
        return rows;
    }

    public int getColumns()
    {
        return columns;
    }

    public int[][] getOrigGameBoard()
    {
        return origGameBoard;
    }



    public void setOrigBoardFieldLive(int row, int col)
    {
         origGameBoard[row][col] = 1;
         newGameBoard[row][col] = 1;
    }

    public void setNewGameBoard()
    {
        newGameBoard = new int[origGameBoard.length][origGameBoard[0].length];
        for (int i = 0; i < origGameBoard.length; i++)
        {
            newGameBoard[i] = origGameBoard[i].clone();
        }
    }

    private void setOrigGameBoard()
    {
        origGameBoard = new int[newGameBoard.length][newGameBoard[0].length];
        for (int i = 0; i < newGameBoard.length; i++)
        {
            origGameBoard[i] = newGameBoard[i].clone();
        }
    }

    public void nextGeneration()
    {
        for (int row = 0; row < origGameBoard.length; row++)
        {
            for (int col = 0; col < origGameBoard[row].length; col++)
            {
                determineLiveOrDead(row, col);
            }
        }
        setOrigGameBoard();
    }

   private void determineLiveOrDead(int row, int col)
   {
       int neighbors = isAlive(row - 1, col - 1) + isAlive(row - 1, col) + isAlive(row - 1, col + 1)
                        + isAlive(row, col - 1) + isAlive(row, col + 1)
                        + isAlive(row + 1, col - 1) + isAlive(row + 1, col) + isAlive(row + 1, col + 1);
       if (neighbors == 3)
       {
           newGameBoard[row][col] = 1;
       } else if (neighbors != 2)
       {
           newGameBoard[row][col] = 0;
       }
   }

    private int isAlive(int row, int col)
    {
        if (row < 0 || row >= origGameBoard.length)
        {
            return 0;
        }
        if (col < 0 || col >= origGameBoard[0].length)
        {
            return 0;
        }
        return origGameBoard[row][col];
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newGameBoard.length; i++)
        {
            sb.append(Arrays.toString(newGameBoard[i]));
            sb.append("\n");
        }
        return sb.toString();
    }

}
