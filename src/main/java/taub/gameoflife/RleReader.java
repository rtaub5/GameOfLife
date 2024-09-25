package taub.gameoflife;

import java.io.*;


public class RleReader
{
    File file;
    BufferedReader reader;
    GameOfLifeGrid grid;
    private int row = 1;
    private int col = 1;

    public RleReader(File file, GameOfLifeGrid grid) throws FileNotFoundException
    {
        this.file = file;
        reader = new BufferedReader(new FileReader(file));
        this.grid = grid;
    }


    public void readRleFile() throws IOException
    {
        String line = reader.readLine();

        int height = 0;
        while (line != null)
        {
            if (line.charAt(0) == 'x')
            {
                height = getHeight(line);
            } else if (line.charAt(0) != '#') {
                // createPattern(line, height);
                createPattern2(line, height);
            }
            line = reader.readLine();
        }

    }

    // This method works when the pattern is column first
    private void createPattern(String line, int height)
    {
        for (int ix = 0; ix < line.length(); ix++)
        {
            if (!Character.isDigit(line.charAt(ix)))
            {
                if (line.charAt(ix) == 'o')
                {
                    grid.changeField(row, col);
                    row++;
                } else if (line.charAt(ix) == 'b') {
                    row++;
                } else if (line.charAt(ix) == '$') {
                    col++;
                    row = row - height;
                }
            } else {
                StringBuilder num = new StringBuilder();
                num.append(line.charAt(ix));
                if (Character.isDigit(line.charAt(ix + 1)))
                {
                    num.append(line.charAt(ix + 1));
                    ix = ix + 2;
                } else {
                    ix++;
                }
                if (line.charAt(ix) == 'o')
                {
                    for (int jx = 0; jx < Integer.parseInt(num.toString()); jx++)
                    {
                        grid.changeField(row, col);
                        row++;
                    }
                } else if (line.charAt(ix) == 'b') {
                    for (int jx = 0; jx < Integer.parseInt(num.toString()); jx++)
                    {
                            row++;
                    }
                }
            }
        }

    }

    // This method works when the pattern is row first
    private void createPattern2(String line, int height)
    {
        for (int ix = 0; ix < line.length(); ix++)
        {
            if (!Character.isDigit(line.charAt(ix)))
            {
                if (line.charAt(ix) == 'o')
                {
                    grid.changeField(row, col);
                    col++;
                } else if (line.charAt(ix) == 'b') {
                    col++;
                } else if (line.charAt(ix) == '$') {
                    row++;
                    col = col - height;
                }
            } else {
                StringBuilder num = new StringBuilder();
                num.append(line.charAt(ix));
                if (Character.isDigit(line.charAt(ix + 1)))
                {
                    num.append(line.charAt(ix + 1));
                    ix = ix + 2;
                } else {
                    ix++;
                }
                if (line.charAt(ix) == 'o')
                {
                    for (int jx = 0; jx < Integer.parseInt(num.toString()); jx++)
                    {
                        grid.changeField(row, col);
                        col++;
                    }
                } else if (line.charAt(ix) == 'b') {
                    for (int jx = 0; jx < Integer.parseInt(num.toString()); jx++)
                    {
                            col++;
                    }
                }
            }
        }
    }


    private int getHeight(String line)
    {
        StringBuilder height = new StringBuilder();
        for (int ix = 0; ix < line.length(); ix++)
        {

            if (Character.isDigit(line.charAt(ix)))
            {
                height.append(line.charAt(ix));
                if (Character.isDigit(line.charAt(ix + 1)))
                {
                    height.append(line.charAt(ix + 1));
                }
                break;
            }
        }

        int heightInt = Integer.parseInt(height.toString());
        return heightInt;
    }

}
