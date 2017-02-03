package fi.jaakko.pieces;

import fi.jaakko.board.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PawnTest {

    public PawnTest() {
    }

    private Board board;
    private Pawn pawn;

    @Before
    public void setUp() {
        board = new Board(false);
        pawn = new Pawn(board.board(), 0, 1, Colour.WHITE);

    }

    @Test
    public void voiLiikkuaTyhjaan() {
        assertTrue(pawn.moves().stream().anyMatch(i -> i[0] == 0 && i[1] == 2));
    }

    @Test
    public void eiVoiLiikkuaToisenPaalleA() {
        board.addPiece(new Pawn(board.board(), 0, 2, Colour.BLACK));
        assertTrue(pawn.moves().isEmpty());
    }

    @Test
    public void eiVoiLiikkuaToisenPaalleB() {
        board.addPiece(new Pawn(board.board(), 0, 3, Colour.BLACK));
        assertTrue(!pawn.moves().stream().anyMatch(i -> i[0] == 0 && i[1] == 3));
    }

    @Test
    public void eiVoiLiikkuaKentaltaUlosYlos() {
        Pawn p = new Pawn(board.board(), 3, 7, Colour.WHITE);
        board.addPiece(p);
        assertTrue(p.moves().isEmpty());
    }

    @Test
    public void eiVoiLiikkuaKentaltaUlosAlas() {
        Pawn p = new Pawn(board.board(), 3, 0, Colour.BLACK);
        board.addPiece(p);
        assertTrue(p.moves().isEmpty());
    }

    @Test
    public void voiKaapata() {
        Pawn p = new Pawn(board.board(), 1, 2, Colour.BLACK);
        board.addPiece(p);
        assertTrue(!pawn.capture().isEmpty());
    }

    @Test
    public void eiVoiKaapata() {
        assertTrue(pawn.capture().isEmpty());
    }

    @Test
    public void kaikkiLiikkeetToimiiKaappaus() {
        Pawn p = new Pawn(board.board(), 1, 2, Colour.BLACK);
        board.addPiece(p);
        assertTrue(pawn.moves().stream().anyMatch(i -> i[0] == 1 && i[1] == 2));
    }

    @Test
    public void kaikkiLiikkeetToimiiEiVoiLiikkua() {
        Pawn p = new Pawn(board.board(), 0, 2, Colour.BLACK);
        board.addPiece(p);
        assertTrue(pawn.moves().isEmpty());
    }
}
