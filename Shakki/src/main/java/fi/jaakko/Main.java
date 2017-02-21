package fi.jaakko;

import fi.jaakko.game.*;
import fi.jaakko.gui.ChessGUI;
import java.util.List;
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Main {
    
    /**
     * Graafinen käyttöliittymä
     * @param args komentorivi argumentit, ei käytetä
     */
    public static void main(final String[] args) {
        Board board = new Board(true);
        Game game = new Game(board);
        ChessGUI chess = new ChessGUI(board);
        SwingUtilities.invokeLater(chess);
    }
}
