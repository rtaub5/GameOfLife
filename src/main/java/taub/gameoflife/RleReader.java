package taub.gameoflife;

import java.io.*;


public class RleReader
{
    File file;
    BufferedReader reader;
    GameOfLifeGrid grid;


    public RleReader(File f, GameOfLifeGrid g) throws FileNotFoundException
    {
        file = f;
        reader = new BufferedReader(new FileReader(file));
        grid = g;
    }


    public GameOfLifeGrid getFileArr() throws IOException
    {
        String line = reader.readLine();
        int height = 0;
        while (line != null)
        {
            if (line.charAt(0) == 'x')
            {
                height = getHeight(line);
            } else if (line.charAt(0) != '#') {
                createPattern(line, height);
            }
            line = reader.readLine();
        }

        return grid;
    }


    private void createPattern(String line, int height)
    {
        int row = 1;
        int col = 1;
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
                String num = String.valueOf(line.charAt(ix));
                if (Character.isDigit(line.charAt(ix + 1)))
                {
                    num = num + line.charAt(ix);
                    ix = ix + 2;
                } else {
                    ix++;
                }
                if (line.charAt(ix) == 'o')
                {
                    for (int jx = 0; jx < Integer.parseInt(num); jx++)
                    {
                        grid.changeField(row, col);
                        row++;
                    }
                } else if (line.charAt(ix) == 'b') {
                    for (int jx = 0; jx < Integer.parseInt(num); jx++)
                    {
                        row++;
                    }
                }
            }
        }
    }

    private int getHeight(String line)
    {
        boolean upToHeight = false;
        StringBuilder height = new StringBuilder();
        for (int ix = 0; ix < line.length(); ix++)
        {

            if (Character.isDigit(line.charAt(ix)) && !upToHeight)
            {
                upToHeight = true;
                ix++;
            }
            if (Character.isDigit(line.charAt(ix)) && upToHeight)
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
