package fi.jaakko.pieces;

import fi.jaakko.game.Board;
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
        board.addPiece(pawn);

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
    public void voiKaapataKaikki() {
        Pawn p1 = new Pawn(board.board(), 1, 2, Colour.BLACK);
        Pawn p2 = new Pawn(board.board(), 0, 1, Colour.WHITE);
        Pawn p3 = new Pawn(board.board(), 2, 1, Colour.WHITE);
        board.addPiece(p1);
        board.addPiece(p2);
        board.addPiece(p3);
        assertTrue(p1.capture().stream().allMatch(i->(i[0]==0&&i[1]==1)||(i[0]==2&&i[1]==1)));
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

    @Test
    public void liikkuminenToimii() {
        pawn.move(3, 4);
        assertTrue(pawn.getX() == 3 && pawn.getY() == 4);

    }

    @Test
    public void testToString() {
        Pawn black = new Pawn(new Board(false).board(),0,0,Colour.BLACK);
        Pawn white = new Pawn(new Board(false).board(),0,0,Colour.WHITE);
        assertEquals("♟",black.toString());
        assertEquals("♙",white.toString());
    }
}
