package fi.jaakko.pieces;

import fi.jaakko.board.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KnightTest {

    public KnightTest() {
    }

    Board board;
    Knight knight;

    @Before
    public void setUp() {
        board = new Board(false);
        knight = new Knight(board.board(), 3, 3, Colour.WHITE);
        board.addPiece(knight);
    }

    @Test
    public void captureTyhjaKunAinutNappula() {
        assertTrue(knight.capture().isEmpty());
    }

    @Test
    public void captureEiSamanvarista() {
        board.addPiece(new Knight(board.board(), 5, 4, Colour.WHITE));
        board.addPiece(new Knight(board.board(), 4, 5, Colour.WHITE));
        board.addPiece(new Knight(board.board(), 5, 2, Colour.WHITE));
        board.addPiece(new Knight(board.board(), 4, 1, Colour.WHITE));
        board.addPiece(new Knight(board.board(), 2, 1, Colour.WHITE));
        board.addPiece(new Knight(board.board(), 1, 2, Colour.WHITE));
        board.addPiece(new Knight(board.board(), 2, 5, Colour.WHITE));
        board.addPiece(new Knight(board.board(), 1, 4, Colour.WHITE));
        assertTrue(knight.capture().isEmpty());
    }

    @Test
    public void captureToimiiNormaalisti() {
        board.addPiece(new Knight(board.board(), 5, 4, Colour.BLACK));
        board.addPiece(new Knight(board.board(), 4, 5, Colour.BLACK));
        board.addPiece(new Knight(board.board(), 5, 2, Colour.BLACK));
        board.addPiece(new Knight(board.board(), 4, 1, Colour.BLACK));
        board.addPiece(new Knight(board.board(), 2, 1, Colour.BLACK));
        board.addPiece(new Knight(board.board(), 1, 2, Colour.BLACK));
        board.addPiece(new Knight(board.board(), 2, 5, Colour.BLACK));
        board.addPiece(new Knight(board.board(), 1, 4, Colour.BLACK));
        assertTrue(knight.capture().stream().anyMatch(i -> i[0] == 5 && i[1] == 4));
        assertTrue(knight.capture().stream().anyMatch(i -> i[0] == 4 && i[1] == 5));
        assertTrue(knight.capture().stream().anyMatch(i -> i[0] == 5 && i[1] == 2));
        assertTrue(knight.capture().stream().anyMatch(i -> i[0] == 4 && i[1] == 1));
        assertTrue(knight.capture().stream().anyMatch(i -> i[0] == 2 && i[1] == 1));
        assertTrue(knight.capture().stream().anyMatch(i -> i[0] == 1 && i[1] == 2));
        assertTrue(knight.capture().stream().anyMatch(i -> i[0] == 2 && i[1] == 5));
        assertTrue(knight.capture().stream().anyMatch(i -> i[0] == 1 && i[1] == 4));
    }

    @Test
    public void eiMeneRajojenYli() {
        Board b = new Board(false);
        Knight k1 = new Knight(board.board(), 1, 1, Colour.WHITE);
        Knight k2 = new Knight(board.board(), 7, 7, Colour.WHITE);
        assertFalse(k1.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
        assertFalse(k2.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
    }

    @Test
    public void eiVoiSiirtyäPaikallaan() {
        assertFalse(this.knight.regularMoves().stream().anyMatch(i -> i[0] == this.knight.getX() && i[1] == this.knight.getY()));
        assertFalse(this.knight.capture().stream().anyMatch(i -> i[0] == this.knight.getX() && i[1] == this.knight.getY()));
        assertFalse(this.knight.moves().stream().anyMatch(i -> i[0] == this.knight.getX() && i[1] == this.knight.getY()));
    }

    @Test
    public void testToString() {
        assertEquals("[WKnight]", this.knight.toString());
    }
}
