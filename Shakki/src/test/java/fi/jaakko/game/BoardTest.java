package fi.jaakko.game;

import fi.jaakko.pieces.Colour;
import fi.jaakko.pieces.Pawn;
import fi.jaakko.pieces.Piece;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    public BoardTest() {
    }

    @Test
    public void testBoard() {
        Board b = new Board(false);
        boolean bo = false;
        for (Piece[] p : b.board()) {
            for (Piece piece : p) {
                if (piece != null) {
                    bo = true;
                }
            }
        }
        assertFalse(bo);
    }

    @Test
    public void alkuasetelmanTestaus() {
        Board b = new Board(true);
        assertEquals("[ WRook ]", b.board()[0][0].toString());
        assertEquals("[ WRook ]", b.board()[7][0].toString());
        assertEquals("[ BRook ]", b.board()[0][7].toString());
        assertEquals("[ BRook ]", b.board()[7][7].toString());
        assertEquals("[WKnight]", b.board()[1][0].toString());
        assertEquals("[WKnight]", b.board()[6][0].toString());
        assertEquals("[BKnight]", b.board()[1][7].toString());
        assertEquals("[BKnight]", b.board()[6][7].toString());
        assertEquals("[WBishop]", b.board()[2][0].toString());
        assertEquals("[WBishop]", b.board()[5][0].toString());
        assertEquals("[BBishop]", b.board()[2][7].toString());
        assertEquals("[BBishop]", b.board()[5][7].toString());
        assertEquals("[WQueen ]", b.board()[3][0].toString());
        assertEquals("[BQueen ]", b.board()[3][7].toString());
        assertEquals("[ WKing ]", b.board()[4][0].toString());
        assertEquals("[ BKing ]", b.board()[4][7].toString());
        assertEquals("[ WPawn ]", b.board()[0][1].toString());
        assertEquals("[ WPawn ]", b.board()[1][1].toString());
        assertEquals("[ WPawn ]", b.board()[2][1].toString());
        assertEquals("[ WPawn ]", b.board()[3][1].toString());
        assertEquals("[ WPawn ]", b.board()[4][1].toString());
        assertEquals("[ WPawn ]", b.board()[5][1].toString());
        assertEquals("[ WPawn ]", b.board()[6][1].toString());
        assertEquals("[ WPawn ]", b.board()[7][1].toString());
        assertEquals("[ BPawn ]", b.board()[0][6].toString());
        assertEquals("[ BPawn ]", b.board()[1][6].toString());
        assertEquals("[ BPawn ]", b.board()[2][6].toString());
        assertEquals("[ BPawn ]", b.board()[3][6].toString());
        assertEquals("[ BPawn ]", b.board()[4][6].toString());
        assertEquals("[ BPawn ]", b.board()[5][6].toString());
        assertEquals("[ BPawn ]", b.board()[6][6].toString());
        assertEquals("[ BPawn ]", b.board()[7][6].toString());
    }

    @Test
    public void addPieceLisaaTyhjaan() {
        Board board = new Board(true);
        board.addPiece(new Pawn(board.board(), 3, 3, Colour.BLACK));
        assertTrue(board.board()[3][3].toString().equals("[ BPawn ]"));
    }

    @Test
    public void addPieceEiLisaaToisenPaalla() {
        Board board = new Board(true);
        board.addPiece(new Pawn(board.board(), 0, 0, Colour.BLACK));
        assertTrue(board.board()[0][0].toString().equals("[ WRook ]"));
    }

    @Test
    public void movePieceEiLiiutaTyhjaa() {
        Board board = new Board(true);
        assertFalse(board.movePiece(0, 4, 0, 3));
    }

    @Test
    public void movePieceEiVoiTehdaVaaraaSiirtoa() {
        Board board = new Board(true);
        assertFalse(board.movePiece(0, 1, 0, 4));
    }

    @Test
    public void movePiece() {
        Board board = new Board(true);
        assertTrue(board.movePiece(0, 1, 0, 3));
        assertTrue(board.board()[0][3] != null);
        assertTrue(board.board()[0][3].getX() == 0 && board.board()[0][3].getY() == 3);
    }

    @Test
    public void testToString() {
        Board board = new Board(true);
        assertEquals("    0        1        2        3        4        5        6        7\n"
                + "0[ WRook ][WKnight][WBishop][WQueen ][ WKing ][WBishop][WKnight][ WRook ]0\n"
                + "1[ WPawn ][ WPawn ][ WPawn ][ WPawn ][ WPawn ][ WPawn ][ WPawn ][ WPawn ]1\n2"
                + "[       ][       ][       ][       ][       ][       ][       ][       ]2\n3"
                + "[       ][       ][       ][       ][       ][       ][       ][       ]3\n4"
                + "[       ][       ][       ][       ][       ][       ][       ][       ]4\n5"
                + "[       ][       ][       ][       ][       ][       ][       ][       ]5\n6"
                + "[ BPawn ][ BPawn ][ BPawn ][ BPawn ][ BPawn ][ BPawn ][ BPawn ][ BPawn ]6\n7"
                + "[ BRook ][BKnight][BBishop][BQueen ][ BKing ][BBishop][BKnight][ BRook ]7\n"
                + "    0        1        2        3        4        5        6        7\n", board.toString());
    }

}
