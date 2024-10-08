package taub.gameoflife;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RleReader
{
    private File file;
    private BufferedReader reader;
    private GameOfLifeGrid grid;
    private int row = 1;
    private int col = 1;
    private String clipboard;
    private String rleContents;
    private int xRow = 0;
    private int yCol = 0;
    private int [][] mock = new int [100][100];

    public RleReader(String clip)
    {
        clipboard = clip;
        readClipboard();
    }

    public RleReader(File file, GameOfLifeGrid grid) throws FileNotFoundException
    {
        this.file = file;
        reader = new BufferedReader(new FileReader(file));
        this.grid = grid;
    }

    private void readClipboard()
    {
        try
        {
            if (clipboard.matches("^(http|https)://.*$"))
            {
                InputStream in = new URL(clipboard).openStream();
                rleContents = IOUtils.toString(in);
            } else if (isValidPath()) {
                FileInputStream fisTargetFile = new FileInputStream(new File(clipboard));
                rleContents = IOUtils.toString(fisTargetFile, "UTF-8");
            } else {
                rleContents = clipboard;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private boolean isValidPath()
    {
        try {
          new FileInputStream(clipboard);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public int[][] readRleString()
    {
        String [] lines = rleContents.split("\n");
        List<Integer>dimensions = new ArrayList <>();
        for (String line : lines)
        {
            if (line.charAt(0) == 'x')
            {
                dimensions = getHeightLength(line);
                xRow = (100 - dimensions.get(0)) / 2;
                yCol = (100 - dimensions.get(1)) / 2;
            } else if (line.charAt(0) != '#') {
                // createMockHeightFirst(line, dimensions.get(0));
                createMockWidthFirst(line, dimensions.get(0));
            }
        }
        return mock;
    }

    private void createMockHeightFirst(String line, int height)
    {
        for (int ix = 0; ix < line.length(); ix++)
        {
            if (!Character.isDigit(line.charAt(ix)))
            {
                if (line.charAt(ix) == 'o')
                {
                    mock[xRow][yCol] = 1;
                    xRow++;
                } else if (line.charAt(ix) == 'b') {
                    xRow++;
                } else if (line.charAt(ix) == '$') {
                    yCol++;
                    xRow = xRow - height;
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
                        mock[xRow][yCol] = 1;
                        xRow++;
                    }
                } else if (line.charAt(ix) == 'b') {
                    for (int jx = 0; jx < Integer.parseInt(num.toString()); jx++)
                    {
                        xRow++;
                    }
                }
            }
        }
    }

    private void createMockWidthFirst(String line, int height)
    {
        for (int ix = 0; ix < line.length(); ix++)
        {
            if (!Character.isDigit(line.charAt(ix)))
            {
                if (line.charAt(ix) == 'o')
                {
                    mock[xRow][yCol] = 1;
                    yCol++;
                } else if (line.charAt(ix) == 'b') {
                    yCol++;
                } else if (line.charAt(ix) == '$') {
                    xRow++;
                    yCol = yCol - height;
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
                        mock[xRow][yCol] = 1;
                        yCol++;
                    }
                } else if (line.charAt(ix) == 'b') {
                    for (int jx = 0; jx < Integer.parseInt(num.toString()); jx++)
                    {
                        yCol++;
                    }
                }
            }
        }
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

    private List<Integer> getHeightLength(String line)
    {
        List<Integer> dimensions = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(line);
        int ix = 0;
        while (m.find() && ix < 2)
        {
            dimensions.add(Integer.parseInt(m.group()));
            ix++;
        }
        return dimensions;
    }

}
