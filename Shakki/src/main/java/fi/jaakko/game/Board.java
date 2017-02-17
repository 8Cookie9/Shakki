package fi.jaakko.game;

import fi.jaakko.pieces.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Piece> black;
    private List<Piece> white;
    private Piece[][] board;

    /**
     *Shakkilautaa kuvaava luokka.
     * 
     * @param b jos true, luodaan shakkilauta ja laitetaan sinne nappulat
     * alkuasetelmaan; jos false, luodaan tyhjä shakilauta (testaamista varten)
     */
    public Board(boolean b) {
        this.board = new Piece[8][8];
        this.white = new ArrayList<>();
        this.black = new ArrayList<>();
        if (b) {
            this.setup();
        }
    }

    private void setup() { //laittaa pelinappulat alkuasemiin
        for (int i = 0; i < 8; i++) {
            Piece b = new Pawn(this.board(), i, 6, Colour.BLACK);
            this.addPiece(b);
            this.black.add(b);
            Piece w = new Pawn(this.board(), i, 1, Colour.WHITE);
            this.addPiece(w);
            this.white.add(w);
        }
        Piece b;
        Piece w = new Rook(this.board(), 0, 0, Colour.WHITE);
        this.addPiece(w);
        this.addPiece(w);
        w = new Rook(this.board(), 7, 0, Colour.WHITE);
        this.addPiece(w);
        this.addPiece(w);
        w = new Knight(this.board(), 1, 0, Colour.WHITE);
        this.addPiece(w);
        this.addPiece(w);
        w = new Knight(this.board(), 6, 0, Colour.WHITE);
        this.addPiece(w);
        this.addPiece(w);
        w = new Bishop(this.board(), 2, 0, Colour.WHITE);
        this.addPiece(w);
        this.addPiece(w);
        w = new Bishop(this.board(), 5, 0, Colour.WHITE);
        this.addPiece(w);
        this.addPiece(w);
        w = new Queen(this.board(), 3, 0, Colour.WHITE);
        this.addPiece(w);
        this.addPiece(w);
        w = new King(this.board(), 4, 0, Colour.WHITE);
        this.addPiece(w);
        this.addPiece(w);
        b = new Rook(this.board(), 0, 7, Colour.BLACK);
        this.addPiece(b);
        this.black.add(b);
        b = new Rook(this.board(), 7, 7, Colour.BLACK);
        this.addPiece(b);
        this.black.add(b);
        b = new Knight(this.board(), 1, 7, Colour.BLACK);
        this.addPiece(b);
        this.black.add(b);
        b = new Knight(this.board(), 6, 7, Colour.BLACK);
        this.addPiece(b);
        this.black.add(b);
        b = new Bishop(this.board(), 2, 7, Colour.BLACK);
        this.addPiece(b);
        this.black.add(b);
        b = new Bishop(this.board(), 5, 7, Colour.BLACK);
        this.addPiece(b);
        this.black.add(b);
        b = new Queen(this.board(), 3, 7, Colour.BLACK);
        this.addPiece(b);
        this.black.add(b);
        b = new King(this.board(), 4, 7, Colour.BLACK);
        this.addPiece(b);
        this.black.add(b);
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
            if (piece.getColour().value() == 1) {
                this.white.add(piece);
            } else {
                this.black.add(piece);
            }
        }
    }

    /**
     *Yrittää siirtää nappulaa toiseen paikkaan sääntöjä noudattaen.
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
        return true;
    }

    public List<Piece> getBlackPieces() {
        return this.black;
    }

    public List<Piece> getWhitePieces() {
        return this.white;
    }

    /**
     *Pelilauta tekstimuodossa tekstikäyttöliittymää varten.
     * 
     * @return palauttaa shakkilaudan ja nappuloiden tekstimuotoisen esityksen;
     * käytetään testausta varten
     */
    @Override
    public String toString() {
        String s = "";
        s += "    0        1        2        3        4        5        6        7\n";
        for (int y = 0; y < 8; y++) {
            s += "" + y;
            for (int x = 0; x < 8; x++) {
                if (this.board[x][y] != null) {
                    s += this.board[x][y].toString();
                } else {
                    s += "[       ]";
                }
            }
            s += "" + y + "\n";
        }
        s += "    0        1        2        3        4        5        6        7\n";
        return s;
    }
}
