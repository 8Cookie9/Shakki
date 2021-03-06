package fi.jaakko.pieces;

import fi.jaakko.game.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RookTest {

    public RookTest() {
    }

    private Board board;
    private Rook rook;

    @Before
    public void setUp() {
        board = new Board(false);
        rook = new Rook(board, 3, 3, Colour.WHITE);
        board.addPiece(rook);
    }

    @Test
    public void captureToimiiA() {
        assertTrue(this.rook.capture().isEmpty());
    }

    @Test
    public void captureToimiiB() {
        board.addPiece(new Pawn(board, 2, 2, Colour.BLACK));
        board.addPiece(new Pawn(board, 4, 2, Colour.BLACK));
        board.addPiece(new Pawn(board, 2, 4, Colour.BLACK));
        board.addPiece(new Pawn(board, 4, 4, Colour.BLACK));
        assertTrue(this.rook.capture().isEmpty());
    }

    @Test
    public void captureToimiiEiOmia() {
        board.addPiece(new Pawn(board, 3, 2, Colour.WHITE));
        board.addPiece(new Pawn(board, 2, 3, Colour.WHITE));
        board.addPiece(new Pawn(board, 3, 4, Colour.WHITE));
        board.addPiece(new Pawn(board, 4, 3, Colour.WHITE));
        assertTrue(this.rook.capture().isEmpty());
    }

    @Test
    public void captureToimii() {
        board.addPiece(new Pawn(board, 3, 0, Colour.BLACK));
        board.addPiece(new Pawn(board, 3, 7, Colour.BLACK));
        board.addPiece(new Pawn(board, 0, 3, Colour.BLACK));
        board.addPiece(new Pawn(board, 7, 3, Colour.BLACK));
        assertTrue(this.rook.capture().stream().anyMatch(i -> i[0] == 3 && i[1] == 0));
        assertTrue(this.rook.capture().stream().anyMatch(i -> i[0] == 3 && i[1] == 7));
        assertTrue(this.rook.capture().stream().anyMatch(i -> i[0] == 0 && i[1] == 3));
        assertTrue(this.rook.capture().stream().anyMatch(i -> i[0] == 7 && i[1] == 3));
    }

    @Test
    public void regularMovesEiSisallaCapturea() {
        board.addPiece(new Pawn(board, 3, 0, Colour.BLACK));
        board.addPiece(new Pawn(board, 3, 7, Colour.BLACK));
        board.addPiece(new Pawn(board, 0, 3, Colour.BLACK));
        board.addPiece(new Pawn(board, 7, 3, Colour.BLACK));
        assertFalse(this.rook.regularMoves().stream().anyMatch(i -> i[0] == 3 && i[1] == 0));
        assertFalse(this.rook.regularMoves().stream().anyMatch(i -> i[0] == 3 && i[1] == 7));
        assertFalse(this.rook.regularMoves().stream().anyMatch(i -> i[0] == 0 && i[1] == 3));
        assertFalse(this.rook.regularMoves().stream().anyMatch(i -> i[0] == 7 && i[1] == 3));
    }
    
    @Test
    public void regularMovesToimiiNormaalisti() {
        Rook r = new Rook(new Board(false),3,3,Colour.BLACK);
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==0&&i[1]==3));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==1&&i[1]==3));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==2&&i[1]==3));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==4&&i[1]==3));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==5&&i[1]==3));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==6&&i[1]==3));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==7&&i[1]==3));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==3&&i[1]==0));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==3&&i[1]==1));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==3&&i[1]==2));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==3&&i[1]==4));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==3&&i[1]==5));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==3&&i[1]==6));
        assertTrue(r.regularMoves().stream().anyMatch(i->i[0]==3&&i[1]==7));
    }

    @Test
    public void eiMeneRajojenYli() {
        assertFalse(this.rook.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
    }

    @Test
    public void eiVoiSiirtyäPaikallaan() {
        assertFalse(this.rook.regularMoves().stream().anyMatch(i -> i[0] == this.rook.getX() && i[1] == this.rook.getY()));
        assertFalse(this.rook.capture().stream().anyMatch(i -> i[0] == this.rook.getX() && i[1] == this.rook.getY()));
        assertFalse(this.rook.moves().stream().anyMatch(i -> i[0] == this.rook.getX() && i[1] == this.rook.getY()));
    }

    @Test
    public void testToString() {
        Rook black = new Rook(new Board(false),0,0,Colour.BLACK);
        Rook white = new Rook(new Board(false),0,0,Colour.WHITE);
        assertEquals("♜",black.toString());
        assertEquals("♖",white.toString());
    }
}
