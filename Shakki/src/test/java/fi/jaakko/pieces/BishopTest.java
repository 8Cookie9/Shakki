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

public class BishopTest {

    Board board;
    Bishop bishop;

    @Before
    public void setUp() {
        board = new Board(false);
        bishop = new Bishop(board, 3, 3, Colour.WHITE);
        board.addPiece(bishop);
    }

    @Test
    public void captureTyhjaKunAinutNappula() {
        assertTrue(bishop.capture().isEmpty());
    }

    @Test
    public void captureEiSamanvarista() {
        board.addPiece(new Bishop(board, 2, 4, Colour.WHITE));
        board.addPiece(new Bishop(board, 3, 4, Colour.WHITE));
        board.addPiece(new Bishop(board, 4, 4, Colour.WHITE));
        board.addPiece(new Bishop(board, 4, 3, Colour.WHITE));
        board.addPiece(new Bishop(board, 4, 2, Colour.WHITE));
        board.addPiece(new Bishop(board, 3, 2, Colour.WHITE));
        board.addPiece(new Bishop(board, 2, 2, Colour.WHITE));
        board.addPiece(new Bishop(board, 2, 3, Colour.WHITE));
        assertTrue(bishop.capture().isEmpty());
    }

    @Test
    public void captureToimiiNormaalisti() {
        board.addPiece(new Bishop(board, 2, 4, Colour.BLACK));
        board.addPiece(new Bishop(board, 3, 4, Colour.BLACK));
        board.addPiece(new Bishop(board, 4, 4, Colour.BLACK));
        board.addPiece(new Bishop(board, 4, 3, Colour.BLACK));
        board.addPiece(new Bishop(board, 4, 2, Colour.BLACK));
        board.addPiece(new Bishop(board, 3, 2, Colour.BLACK));
        board.addPiece(new Bishop(board, 2, 2, Colour.BLACK));
        board.addPiece(new Bishop(board, 2, 3, Colour.BLACK));
        assertTrue(bishop.capture().stream().anyMatch(i -> i[0] == 2 && i[1] == 4));
        assertTrue(bishop.capture().stream().anyMatch(i -> i[0] == 4 && i[1] == 4));
        assertTrue(bishop.capture().stream().anyMatch(i -> i[0] == 4 && i[1] == 2));
        assertTrue(bishop.capture().stream().anyMatch(i -> i[0] == 2 && i[1] == 2));
    }

    @Test
    public void eiMeneRajojenYli() {
        Bishop k1 = new Bishop(board, 0, 0, Colour.WHITE);
        Bishop k2 = new Bishop(board, 0, 7, Colour.WHITE);
        Bishop k3 = new Bishop(board, 7, 0, Colour.WHITE);
        Bishop k4 = new Bishop(board, 7, 7, Colour.WHITE);
        assertFalse(k1.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
        assertFalse(k2.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
        assertFalse(k3.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
        assertFalse(k4.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
    }
    
    @Test
    public void regularMovesToimiiNormaalisti() {
        Bishop bis = new Bishop(new Board(false),3,3,Colour.BLACK);
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==0&&i[1]==0));
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==1&&i[1]==1));
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==2&&i[1]==2));
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==4&&i[1]==4));
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==5&&i[1]==5));
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==6&&i[1]==6));
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==7&&i[1]==7));
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==4&&i[1]==2));
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==5&&i[1]==1));
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==6&&i[1]==0));
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==2&&i[1]==4));
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==1&&i[1]==5));
        assertTrue(bis.regularMoves().stream().anyMatch(i->i[0]==0&&i[1]==6));
    }

    @Test
    public void eiVoiSiirtyäPaikallaan() {
        assertFalse(this.bishop.regularMoves().stream().anyMatch(i -> i[0] == this.bishop.getX() && i[1] == this.bishop.getY()));
        assertFalse(this.bishop.capture().stream().anyMatch(i -> i[0] == this.bishop.getX() && i[1] == this.bishop.getY()));
        assertFalse(this.bishop.moves().stream().anyMatch(i -> i[0] == this.bishop.getX() && i[1] == this.bishop.getY()));
    }

    @Test
    public void testToString() {
        Bishop black = new Bishop(new Board(false),0,0,Colour.BLACK);
        Bishop white = new Bishop(new Board(false),0,0,Colour.WHITE);
        assertEquals("♝",black.toString());
        assertEquals("♗",white.toString());
    }
}
