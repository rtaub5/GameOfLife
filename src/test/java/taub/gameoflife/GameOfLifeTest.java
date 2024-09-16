package taub.gameoflife;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest
{
    @Test
    public void nextGen()
    {
        //given
        int [][] gameBoard = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        GameOfLife grid = new GameOfLife(gameBoard);

        //when
        grid.nextGeneration();

        //then
        int[][] expectedBoard = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };

        assertEquals(boardToString(expectedBoard), grid.toString());
    }

    public String boardToString(int[][] board)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++)
        {
            sb.append(Arrays.toString(board[i]));
            sb.append("\n");
        }
        return sb.toString();
    }
}