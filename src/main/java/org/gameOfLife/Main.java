package org.gameOfLife;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main
{
    public static void main(String[] args)
    {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        // Press Ctrl+R or click the green arrow button in the gutter to run the code.

            int [][] gameBoard = {
                    {0, 0, 0},
                    {1, 1, 1},
                    {0, 0, 0}
            };

            GameOfLife game = new GameOfLife(gameBoard);
            game.nextGeneration();



        }
    }
