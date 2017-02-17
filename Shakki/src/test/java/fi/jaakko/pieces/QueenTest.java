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

public class QueenTest {

    Board board;
    Queen queen;

    @Before
    public void setUp() {
        board = new Board(false);
        queen = new Queen(board.board(), 3, 3, Colour.WHITE);
        board.addPiece(queen);
    }

    @Test
    public void captureTyhjaKunAinutNappula() {
        assertTrue(queen.capture().isEmpty());
    }

    @Test
    public void captureEiSamanvarista() {
        board.addPiece(new Queen(board.board(), 2, 4, Colour.WHITE));
        board.addPiece(new Queen(board.board(), 3, 4, Colour.WHITE));
        board.addPiece(new Queen(board.board(), 4, 4, Colour.WHITE));
        board.addPiece(new Queen(board.board(), 4, 3, Colour.WHITE));
        board.addPiece(new Queen(board.board(), 4, 2, Colour.WHITE));
        board.addPiece(new Queen(board.board(), 3, 2, Colour.WHITE));
        board.addPiece(new Queen(board.board(), 2, 2, Colour.WHITE));
        board.addPiece(new Queen(board.board(), 2, 3, Colour.WHITE));
        assertTrue(queen.capture().isEmpty());
    }

    @Test
    public void captureToimiiNormaalisti() {
        board.addPiece(new Queen(board.board(), 2, 4, Colour.BLACK));
        board.addPiece(new Queen(board.board(), 3, 4, Colour.BLACK));
        board.addPiece(new Queen(board.board(), 4, 4, Colour.BLACK));
        board.addPiece(new Queen(board.board(), 4, 3, Colour.BLACK));
        board.addPiece(new Queen(board.board(), 4, 2, Colour.BLACK));
        board.addPiece(new Queen(board.board(), 3, 2, Colour.BLACK));
        board.addPiece(new Queen(board.board(), 2, 2, Colour.BLACK));
        board.addPiece(new Queen(board.board(), 2, 3, Colour.BLACK));
        assertTrue(queen.capture().stream().anyMatch(i -> i[0] == 2 && i[1] == 4));
        assertTrue(queen.capture().stream().anyMatch(i -> i[0] == 4 && i[1] == 4));
        assertTrue(queen.capture().stream().anyMatch(i -> i[0] == 4 && i[1] == 2));
        assertTrue(queen.capture().stream().anyMatch(i -> i[0] == 2 && i[1] == 2));
        assertTrue(queen.capture().stream().anyMatch(i -> i[0] == 3 && i[1] == 4));
        assertTrue(queen.capture().stream().anyMatch(i -> i[0] == 4 && i[1] == 3));
        assertTrue(queen.capture().stream().anyMatch(i -> i[0] == 3 && i[1] == 2));
        assertTrue(queen.capture().stream().anyMatch(i -> i[0] == 2 && i[1] == 3));
    }

    @Test
    public void eiMeneRajojenYli() {
        Queen k1 = new Queen(board.board(), 1, 1, Colour.WHITE);
        Queen k2 = new Queen(board.board(), 7, 7, Colour.WHITE);
        Queen k3 = new Queen(board.board(), 7, 0, Colour.WHITE);
        Queen k4 = new Queen(board.board(), 7, 7, Colour.WHITE);
        assertFalse(k1.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
        assertFalse(k2.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
        assertFalse(k3.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
        assertFalse(k4.moves().stream().anyMatch(i -> i[0] < 0 || i[1] < 0 || i[0] > 7 || i[1] > 7));
    }

    @Test
    public void eiVoiSiirtyäPaikallaan() {
        assertFalse(this.queen.regularMoves().stream().anyMatch(i -> i[0] == this.queen.getX() && i[1] == this.queen.getY()));
        assertFalse(this.queen.capture().stream().anyMatch(i -> i[0] == this.queen.getX() && i[1] == this.queen.getY()));
        assertFalse(this.queen.moves().stream().anyMatch(i -> i[0] == this.queen.getX() && i[1] == this.queen.getY()));
    }

    @Test
    public void testToString() {
        assertEquals("[WQueen ]", this.queen.toString());
    }
}
