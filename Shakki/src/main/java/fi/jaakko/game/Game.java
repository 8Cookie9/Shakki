package fi.jaakko.game;

import fi.jaakko.pieces.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private int turn;
    private Board board;
    private Colour winner;

    /**
     * Luokka kuvaa peliä: pitää sisällään vuorojen vaihtumisen.
     *
     * @param board pelilauta jolla pelataan
     */
    public Game(Board board) {
        this.turn = 1;
        this.board = board;
    }

    /**
     * Aloittaa uuden pelin.
     */
    public void newGame() {
        this.board = new Board(true);
        this.turn = 1;
    }

    /**
     * Seuraava vuoro.
     */
    public void nextTurn() {
        this.turn++;
    }

    /**
     * Voidaanko siirtää shakkinappula tiettyyn kohtaan ja siirretään ,jos
     * voidaan. Pitää huolta, että on oikean pelaajan vuoro.
     *
     * @param x siirrettävän x-koordinaatti
     * @param y siirrettävän y-koordinaatti
     * @param x2 x-koordinaatti, jonne yritetään siirtää
     * @param y2 y-koordinaatti, jonne yritetään siirtää
     * @return true jos siirto onnistuu; false, jos ei
     */
    public boolean tryMove(int x, int y, int x2, int y2) {
        if (this.board.board()[x][y] != null) {
            if (this.board.board()[x][y].getColour() == this.getCurrentColour()) {
                return this.board.movePiece(x, y, x2, y2);
            }
        }
        return false;
    }

    /**
     * Kenen vuoro on.
     *
     * @return palauttaa pelaajan nappuloiden värin, jonka vuoro on
     */
    public Colour getCurrentColour() {
        if (this.turn % 2 == 1) {
            return Colour.WHITE;
        } else {
            return Colour.BLACK;
        }
    }

    /**
     * Kertoo onko peli loppunut.
     *
     * @return true - peli loppu; false - ei
     */
    public boolean gameOver() {
        if (!this.board.getBlackPieces().stream().anyMatch(i -> i.getClass() == new King(new Board(false), 0, 0, Colour.BLACK).getClass())) {
            this.winner = Colour.WHITE;
            return true;
        } else if (!this.board.getWhitePieces().stream().anyMatch(i -> i.getClass() == new King(new Board(false), 0, 0, Colour.BLACK).getClass())) {
            this.winner = Colour.BLACK;
            return true;
        }
        return false;
    }

    /**
     * Palauttaa voittajan.
     *
     * @return voittajan väri (Colour)
     */
    public Colour getWinner() {
        return this.winner;
    }

    public Board getBoard() {
        return this.board;
    }

    /**
     * Asettaa pöydäksi annetun.
     *
     * @param b uusi Board jolla korvataan vanha
     */
    public void setBoard(Board b) {
        this.board = b;
    }

    /**
     * Palauttaa sallitut siirrot nappulalle, kun tutkitaan vaarantuisiko
     * kuningas.
     *
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @return lista siirroista
     */
    public List<int[]> getMoves(int x, int y) {
        List<int[]> moves = new ArrayList<>();
        Piece piece = this.board.board()[x][y];
        Colour enemy;
        if (piece.getColour() == Colour.BLACK) {
            enemy = Colour.WHITE;
        } else {
            enemy = Colour.BLACK;
        }
        int[] kingPos = new int[2];
        for (Piece p : this.board.getSameColoredPieces(piece.getColour())) {
            if (p.getClass() == new King(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                kingPos[0] = p.getX();
                kingPos[1] = p.getY();
            }
        }
        for (int[] i : piece.moves()) {
            Board testBoard = this.testBoard(board);
            Game game = new Game(testBoard);
            testBoard.movePiece(piece.getX(), piece.getY(), i[0], i[1]);
            if (!game.check(enemy, kingPos[0], kingPos[1])) {
                moves.add(i);
            }
        }
        return moves;
    }

    /**
     * Kertoo onko shakki.
     *
     * @param c väri, jota tutkitaan
     * @return true jos shakki, false jos ei
     */
    public boolean check(Colour c) {
        int[] kingPos = new int[2];
        for (Piece p : this.board.getOtherColoredPieces(c)) {
            if (p.getClass() == new King(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                kingPos[0] = p.getX();
                kingPos[1] = p.getY();
            }
        }
        return check(c, kingPos[0], kingPos[1]);
    }

    private boolean check(Colour c, int kingX, int kingY) {
        for (Piece p : this.board.getSameColoredPieces(c)) {
            if (p.capture().stream().anyMatch(i -> i[0] == kingX && i[1] == kingY)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Kertoo onko shakkimatti.
     *
     * @param c väri, jota tutkitaan
     * @return true jos shakkimatti, false jos ei
     */
    public boolean checkmate(Colour c) {
        int[] kingPos = new int[2];
        for (Piece p : this.board.getOtherColoredPieces(c)) {
            if (p.getClass() == new King(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                kingPos[0] = p.getX();
                kingPos[1] = p.getY();
            }
        }
        boolean b = true;
        for (Piece p : this.board.getOtherColoredPieces(c)) {
            for (int[] i : p.moves()) {
                Board testBoard = this.testBoard(board);
                Game game = new Game(testBoard);
                testBoard.movePiece(p.getX(), p.getY(), i[0], i[1]);
                if (!game.check(c, kingPos[0], kingPos[1])) {
                    b = false;
                }
            }

        }
        return b;
    }

    private Board testBoard(Board b) {
        Board newGame = new Board(false);
        for (Piece p : b.getAllPieces()) {
            if (p.getClass() == new Pawn(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                newGame.addPiece(new Pawn(newGame, p.getX(), p.getY(), p.getColour()));
            } else if (p.getClass() == new Rook(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                newGame.addPiece(new Rook(newGame, p.getX(), p.getY(), p.getColour()));
            } else if (p.getClass() == new Knight(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                newGame.addPiece(new Knight(newGame, p.getX(), p.getY(), p.getColour()));
            } else if (p.getClass() == new Bishop(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                newGame.addPiece(new Bishop(newGame, p.getX(), p.getY(), p.getColour()));
            } else if (p.getClass() == new Queen(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                newGame.addPiece(new Queen(newGame, p.getX(), p.getY(), p.getColour()));
            } else if (p.getClass() == new King(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                newGame.addPiece(new TestKing(newGame, p.getX(), p.getY(), p.getColour()));
            }
        }
        return newGame;
    }
}
