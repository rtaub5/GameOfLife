package taub.gameoflife;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


public class GameOfLIfeControllerTest
{
private static String glider_Str = """
        #N Glider
        #O Richard K. Guy
        #C The smallest, most common, and first discovered spaceship. Diagonal, has period 4 and speed c/4.
        #C www.conwaylife.com/wiki/index.php?title=Glider
        x = 3, y = 3, rule = B3/S23
        bob$2bo$3o!
        """.trim().replace("\n", "\r\n");
    @Test
    void toggleCell()
    {
        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        RleReader reader = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view, reader);
        doReturn(100).when(model).getRows();
        doReturn(100).when(model).getRows(); // used to return

        //when
         controller.toggleCell(50, 100);

        //then
        verify(model).setOrigBoardField(5, 10, 1); // method wont return anything instead

        verify(view).repaint();


    }

    @Test
    public void paste() {
        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        RleReader reader = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view, reader);

        //when
        controller.paste(glider_Str);

        //when
        verify(reader).readClipboard(glider_Str);
        verify(view).repaint();

    }

    @Test
    public void pasteUrl()
    {
        //given
        GameOfLife model = mock();
        GameOfLifeComponent view = mock();
        RleReader reader = mock();
        GameOfLifeController controller = new GameOfLifeController(model, view, reader);
        String url = "https://conwaylife.com/patterns/glider.rle";
        String rle = "";

        //when
        controller.paste(rle);

        //when
        verify(reader).readClipboard(rle);
        verify(view).repaint();
    }

    // probably won't pass on github
  //  @Test
 //   public void pasteFile()
 //   {
//        //given
//        GameOfLife model = mock();
//        GameOfLifeComponent view = mock();
//        GameOfLifeController controller = new GameOfLifeController(model, view);
//        String File = "glider.rle";
//        String rle = "";
//
//        //when
//        controller.paste(rle);
//
//        //when
//        verify(model).loadrle(glider_Str.replace("\n", "\r\n"));
//        verify(view).repaint();
  //  }
}


