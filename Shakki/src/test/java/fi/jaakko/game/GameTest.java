package fi.jaakko.game;

import fi.jaakko.pieces.Colour;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class GameTest {

    Game game;
    Board board;

    @Before
    public void setUp() {
        board = new Board(true);
        game = new Game(board);
    }

    @Test
    public void testNextTurn() {
        assertEquals(Colour.WHITE, game.getCurrentColour());
        game.nextTurn();
        assertEquals(Colour.BLACK, game.getCurrentColour());
    }

    @Test
    public void testTryMove() {
        assertFalse(this.game.tryMove(2, 2, 3, 3));
        assertFalse(this.game.tryMove(7, 6, 7, 4));
        assertTrue(this.game.tryMove(7, 1, 7, 3));
    }

    @Test
    public void testGameOver() {
        assertFalse(game.gameOver());
    }

    @Test
    public void oikeaVoittajaValkoinen() {
        board.forceMove(0, 0, 4, 7);
        assertTrue(game.gameOver());
        assertEquals(Colour.WHITE, game.getWinner());
    }

    @Test
    public void oikeaVoittajaMusta() {
        board.forceMove(0, 7, 4, 0);
        assertTrue(game.gameOver());
        assertEquals(Colour.BLACK, game.getWinner());
    }

    @Test
    public void testSetBoard() {
        game.setBoard(new Board(false));
        assertTrue(game.getBoard().getAllPieces().isEmpty());
    }

    @Test
    public void testGetMoves() {
        game.tryMove(4, 1, 4, 3);
        game.tryMove(5, 0, 2, 3);
        game.tryMove(3, 0, 5, 2);
        game.tryMove(5, 2, 5, 6);
        assertTrue(game.getMoves(0, 6).isEmpty());
    }

    @Test
    public void testCheckShakki() {
        game.tryMove(4, 1, 4, 3);
        game.tryMove(3, 0, 5, 2);
        game.tryMove(5, 2, 5, 6);
        assertTrue(game.check(Colour.WHITE));
    }

    @Test
    public void testCheckShakkiJaShakkimatti() {
        game.tryMove(4, 1, 4, 3);
        game.tryMove(5, 0, 2, 3);
        game.tryMove(3, 0, 5, 2);
        game.tryMove(5, 2, 5, 6);
        assertTrue(game.check(Colour.WHITE));
    }

    @Test
    public void testCheckmate() {
        game.tryMove(4, 1, 4, 3);
        game.tryMove(5, 0, 2, 3);
        game.tryMove(3, 0, 5, 2);
        game.tryMove(5, 2, 5, 6);
        assertTrue(game.checkmate(Colour.WHITE));
    }

    @Test
    public void testnewGame() {
        game.setBoard(new Board(false));
        game.newGame();
        assertTrue(game.getBoard().getAllPieces().size() == 32);
        assertEquals(Colour.WHITE, game.getCurrentColour());
    }

}
