package fi.jaakko.game;

import fi.jaakko.pieces.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private Piece[][] board;

    /**
     * Shakkilautaa kuvaava luokka.
     *
     * @param b jos true, luodaan shakkilauta ja laitetaan sinne nappulat
     * alkuasetelmaan; jos false, luodaan tyhjä shakilauta (testaamista varten)
     */
    public Board(boolean b) {
        this.board = new Piece[8][8];
        if (b) {
            this.setup();
        }
    }

    private void setup() { //laittaa pelinappulat alkuasemiin
        for (int i = 0; i < 8; i++) {
            this.addPiece(new Pawn(this.board(), i, 6, Colour.BLACK));
            this.addPiece(new Pawn(this.board(), i, 1, Colour.WHITE));
        }
        Piece b;
        this.addPiece(new Rook(this.board(), 0, 0, Colour.WHITE));
        this.addPiece(new Rook(this.board(), 7, 0, Colour.WHITE));
        this.addPiece(new Knight(this.board(), 1, 0, Colour.WHITE));
        this.addPiece(new Knight(this.board(), 6, 0, Colour.WHITE));
        this.addPiece(new Bishop(this.board(), 2, 0, Colour.WHITE));
        this.addPiece(new Bishop(this.board(), 5, 0, Colour.WHITE));
        this.addPiece(new Queen(this.board(), 3, 0, Colour.WHITE));
        this.addPiece(new King(this.board(), 4, 0, Colour.WHITE));
        this.addPiece(new Rook(this.board(), 0, 7, Colour.BLACK));
        this.addPiece(new Rook(this.board(), 7, 7, Colour.BLACK));
        this.addPiece(new Knight(this.board(), 1, 7, Colour.BLACK));
        this.addPiece(new Knight(this.board(), 6, 7, Colour.BLACK));
        this.addPiece(new Bishop(this.board(), 2, 7, Colour.BLACK));
        this.addPiece(new Bishop(this.board(), 5, 7, Colour.BLACK));
        this.addPiece(new Queen(this.board(), 3, 7, Colour.BLACK));
        this.addPiece(new King(this.board(), 4, 7, Colour.BLACK));
    }

    /**
     * Palauttaa pelilaudan.
     *
     * @return pelilauta (mitä missäkin ruudussa)
     */
    public Piece[][] board() {
        return this.board;
    }

    /**
     * Metodi lisää shakkinappulan pelilaudalle, jos kohta on tyhjä.
     *
     * @param piece Shakkinappula, joka lisätään sen sisältämään kohtaan
     */
    public void addPiece(Piece piece) {
        if (this.board[piece.getX()][piece.getY()] == null) {
            this.board[piece.getX()][piece.getY()] = piece;
        }
    }

    /**
     * Yrittää siirtää nappulaa toiseen paikkaan sääntöjä noudattaen.
     *
     * @param x Siirrettävän nappulan x-koordinaatti
     * @param y Siirrettävän nappulan y-koordinaatti
     * @param x2 X-koordinaatti, jonne nappula yritetään siirtää
     * @param y2 Y-koordinaatti, jonne nappula yritetään siirtää
     * @return true, jos siirto onnistuu; false, jos ei onnistu
     */
    public boolean movePiece(int x, int y, int x2, int y2) {
        if (this.board[x][y] == null) {
            return false;
        } else if (!this.board[x][y].moves().stream().anyMatch(i -> i[0] == x2 && i[1] == y2)) {
            return false;
        }
        this.board[x][y].move(x2, y2);
        this.board[x2][y2] = this.board[x][y];
        this.board[x][y] = null;
        if (this.board[x2][y2].getClass() == new Pawn(new Board(false).board(), 0, 0, Colour.BLACK).getClass()) {
            if ((this.board[x2][y2].getColour() == Colour.BLACK && y2 == 0) || (this.board[x2][y2].getColour() == Colour.WHITE && y2 == 7)) {
                this.board[x2][y2] = new Queen(this.board(), x2, y2, this.board[x2][y2].getColour());
            }
        }
        return true;
    }

    /**
     * Palauttaa mustat nappulat listana.
     *
     * @return mustat nappulat
     */
    public List<Piece> getBlackPieces() {
        ArrayList<Piece> black = new ArrayList<>();
        for (Piece p1[] : this.board) {
            for (Piece p : p1) {
                if (p != null) {
                    if (p.getColour().value() == -1) {
                        black.add(p);
                    }
                }
            }
        }
        return black;
    }

    /**
     * Palauttaa valkoiset nappulat listana.
     *
     * @return valkoiset nappulat
     */
    public List<Piece> getWhitePieces() {
        ArrayList<Piece> white = new ArrayList<>();
        for (Piece p1[] : this.board) {
            for (Piece p : p1) {
                if (p != null) {
                    if (p.getColour().value() == 1) {
                        white.add(p);
                    }
                }
            }
        }
        return white;
    }

    /**
     * Palauttaa kaikki nappulat listana.
     *
     * @return kaikki nappulat
     */
    public List<Piece> getAllPieces() {
        ArrayList<Piece> all = new ArrayList<>();
        all.addAll(this.getBlackPieces());
        all.addAll(this.getWhitePieces());
        return all;
    }
}
