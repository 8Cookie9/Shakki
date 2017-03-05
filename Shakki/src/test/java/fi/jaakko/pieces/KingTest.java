/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jaakko.pieces;

import fi.jaakko.game.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KingTest {

    Board board;
    King king;

    @Before
    public void setUp() {
        board = new Board(false);
        king = new King(board, 3, 3, Colour.WHITE);
        board.addPiece(king);
    }

    @Test
    public void captureTyhjaKunAinutNappula() {
        assertTrue(king.capture().isEmpty());
    }

    @Test
    public void captureEiSamanvarista() {
        board.addPiece(new King(board, 2, 4, Colour.WHITE));
        board.addPiece(new King(board, 3, 4, Colour.WHITE));
        board.addPiece(new King(board, 4, 4, Colour.WHITE));
        board.addPiece(new King(board, 4, 3, Colour.WHITE));
        board.addPiece(new King(board, 4, 2, Colour.WHITE));
        board.addPiece(new King(board, 3, 2, Colour.WHITE));
        board.addPiece(new King(board, 2, 2, Colour.WHITE));
        board.addPiece(new King(board, 2, 3, Colour.WHITE));
        assertTrue(king.capture().isEmpty());
    }

    @Test
    public void captureToimiiNormaalisti() {
        board.addPiece(new King(board, 2, 4, Colour.BLACK));
        board.addPiece(new King(board, 3, 4, Colour.BLACK));
        board.addPiece(new King(board, 4, 4, Colour.BLACK));
        board.addPiece(new King(board, 4, 3, Colour.BLACK));
        board.addPiece(new King(board, 4, 2, Colour.BLACK));
        board.addPiece(new King(board, 3, 2, Colour.BLACK));
        board.addPiece(new King(board, 2, 2, Colour.BLACK));
        board.addPiece(new King(board, 2, 3, Colour.BLACK));
        assertTrue(king.capture().isEmpty());
    }

    @Test
    public void voiLiikkuaNormaalisti() {
        assertTrue(king.moves().stream().anyMatch(i -> i[0] == 2 && i[1] == 2));
        assertTrue(king.moves().stream().anyMatch(i -> i[0] == 3 && i[1] == 2));
        assertTrue(king.moves().stream().anyMatch(i -> i[0] == 4 && i[1] == 2));
        assertTrue(king.moves().stream().anyMatch(i -> i[0] == 2 && i[1] == 3));
        assertTrue(king.moves().stream().anyMatch(i -> i[0] == 4 && i[1] == 3));
        assertTrue(king.moves().stream().anyMatch(i -> i[0] == 2 && i[1] == 4));
        assertTrue(king.moves().stream().anyMatch(i -> i[0] == 3 && i[1] == 4));
        assertTrue(king.moves().stream().anyMatch(i -> i[0] == 4 && i[1] == 4));
    }

    @Test
    public void eiMeneRajojenYli() {
        King k1 = new King(board, 1, 1, Colour.WHITE);
        King k2 = new King(board, 7, 7, Colour.WHITE);
        assertFalse(k1.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
        assertFalse(k2.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
    }

    @Test
    public void eiVoiSiirtyäPaikallaan() {
        assertFalse(this.king.regularMoves().stream().anyMatch(i -> i[0] == this.king.getX() && i[1] == this.king.getY()));
        assertFalse(this.king.capture().stream().anyMatch(i -> i[0] == this.king.getX() && i[1] == this.king.getY()));
        assertFalse(this.king.moves().stream().anyMatch(i -> i[0] == this.king.getX() && i[1] == this.king.getY()));
    }

    @Test
    public void testToString() {
        King black = new King(new Board(false), 0, 0, Colour.BLACK);
        King white = new King(new Board(false), 0, 0, Colour.WHITE);
        assertEquals("♚", black.toString());
        assertEquals("♔", white.toString());
    }
}
