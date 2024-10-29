package taub.gameoflife;


public class GameOfLifeController
{
    private final GameOfLife model;
    private final GameOfLifeComponent view;
    private final RleReader reader;

    // You are allowed to add more parameters to the controller
    public GameOfLifeController(GameOfLife model, GameOfLifeComponent view, RleReader reader)
    {
        this.model = model;
        this.view = view;
        this.reader = reader;
    }
    public void startTimer()
    {

    }

    public void stopTimer()
    {

    }

    public void paste(String clipboard)
    {
        reader.readClipboard(clipboard);
        int[][] mock = reader.getMock();
        view.regenerateBoard(mock);
        view.repaint();
    }


    public void toggleCell(int x, int y)
    {
        view.toggleCell(x, y);

    }

}
