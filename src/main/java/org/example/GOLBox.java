package org.example;

public class GOLBox
{
    private GameOfLife game;
    private int row;
    private int col;
    private Neighbor upperLeft = null;
    private Neighbor upper = null;
    private Neighbor upperRight = null;
    private Neighbor left = null;
    private Neighbor right = null;
    private Neighbor lowerLeft = null;
    private Neighbor lower = null;
    private Neighbor lowerRight = null;
    private int countLiveNeighbors = 0;

    public GOLBox(GameOfLife gameOfLife, int i, int j)
    {
        game = gameOfLife;
        row = i;
        col = j;
        setNeighbors();
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public int getCountLiveNeighbors()
    {
        return countLiveNeighbors;
    }

    private void setNeighbors()
    {
        int lastRow = game.getGameBoard().length - 1;
        int lastCol = game.getGameBoard()[0].length - 1;

        /*
            0 0 0
            0 1 0
            0 0 0
         */
        if (row != 0 && row != lastRow && col != 0 && col != lastCol)
        {
            setUpperLeft();
            setUpper();
            setUpperRight();
            setLeft();
            setRight();
            setLowerLeft();
            setLower();
            setLowerRight();
        }
        else if (row == 0)
        {
            /*
                1 0 0
                0 0 0
                0 0 0
             */
            if (col == 0)
            {
                setRight();
                setLowerRight();
                setLower();
            }
            /*
                0 0 1
                0 0 0
                0 0 0
             */
            else if (col == lastCol)
            {
                setLeft();
                setLowerLeft();
                setLower();
            }
            /*
                0 1 0
                0 0 0
                0 0 0
             */
            else
            {
                setLeft();
                setRight();
                setLowerLeft();
                setLower();
                setLowerRight();
            }
        }
        else if (col == 0)
        {
            /*
                0 0 0
                1 0 0
                0 0 0
             */
            if (row != lastRow)
            {
                setUpper();
                setUpperRight();
                setRight();
                setLower();
                setLowerRight();
            }
            /*
                0 0 0
                0 0 0
                1 0 0
             */
            else
            {
                setUpper();
                setUpperRight();
                setRight();
            }
        }
        else if (row == lastRow)
        {
            /*
                0 0 0
                0 0 0
                0 1 0
             */
            if (col != lastCol)
            {
                setUpperLeft();
                setUpper();
                setUpperRight();
                setLeft();
                setRight();
            }
            /*
                0 0 0
                0 0 0
                0 0 1
             */
            else
            {
                setUpperLeft();
                setUpper();
                setLeft();
            }
        }
        /*
            0 0 0
            0 0 1
            0 0 0
         */
        else
        {
            setUpperLeft();
            setUpper();
            setLeft();
            setLowerLeft();
            setLower();
        }
    }

    private void setUpperLeft()
    {
        upperLeft = new Neighbor(row - 1, col - 1);
        upperLeft.setIsOn(game.getGameBoard()[upperLeft.row][upperLeft.col]);
        countLiveNeighbors += upperLeft.isOn;
    }

    private void setUpper()
    {
        upper = new Neighbor(row - 1, col);
        upper.setIsOn(game.getGameBoard()[upper.row][upper.col]);
        countLiveNeighbors += upper.isOn;
    }

    private void setUpperRight()
    {
        upperRight = new Neighbor(row - 1, col + 1);
        upperRight.setIsOn(game.getGameBoard()[upperRight.row][upperRight.col]);
        countLiveNeighbors += upperRight.isOn;
    }

    private void setLeft()
    {
        left = new Neighbor(row, col - 1);
        left.setIsOn(game.getGameBoard()[left.row][left.col]);
        countLiveNeighbors += left.isOn;
    }

    private void setRight()
    {
        right = new Neighbor(row, col + 1);
        right.setIsOn(game.getGameBoard()[right.row][right.col]);
        countLiveNeighbors += right.isOn;
    }


    private void setLowerLeft()
    {
        lowerLeft = new Neighbor(row + 1, col - 1);
        lowerLeft.setIsOn(game.getGameBoard()[lowerLeft.row][lowerLeft.col]);
        countLiveNeighbors += lowerLeft.isOn;
    }

    private void setLower()
    {
        lower = new Neighbor(row + 1, col);
        lower.setIsOn(game.getGameBoard()[lower.row][lower.col]);
        countLiveNeighbors += lower.isOn;
    }

    private void setLowerRight()
    {
        lowerRight = new Neighbor(row + 1, col + 1);
        lowerRight.setIsOn(game.getGameBoard()[lowerRight.row][lowerRight.col]);
        countLiveNeighbors += lowerRight.isOn;
    }


    private class Neighbor
    {
        private int row;
        private int col;
        private int isOn;
      Neighbor(int i, int j)
      {
          row = i;
          col = j;
      }

      void setIsOn(int on_Off)
      {
          isOn = on_Off;
      }

    }


}
