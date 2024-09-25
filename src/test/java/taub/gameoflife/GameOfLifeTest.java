package taub.gameoflife;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.net.URL;

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

        assertArrayEquals(expectedBoard, grid.getOrigGameBoard());

    }

    @Test
    public void rleReaderTest() throws IOException, URISyntaxException
    {
        //given
        Path p = Paths.get(ClassLoader.getSystemResource("glider.rle").toURI());
        File file = p.toFile();
        GameOfLifeGrid grid = new GameOfLifeGrid(4, 4);
        RleReader reader = new RleReader(file, grid);

        //when
        reader.readRleFile();

        //then
        int [][] expectedBoard = {{0, 0, 0, 0},
                                  {0, 0, 1, 0},
                                  {0, 0, 0, 1},
                                  {0, 1, 1, 1}};

        assertArrayEquals(expectedBoard, grid.getGameOfLifeBoard());

    }

}