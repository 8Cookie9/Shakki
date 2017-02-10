package fi.jaakko.pieces;

import java.util.List;

public abstract class Piece {

    private int x;
    private int y;
    private final Colour colour;
    private final Piece[][] board;

    /**
     *
     * @param board mihin lautaan nappula kuuluu
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param c nappulan väri
     */
    public Piece(Piece[][] board, int x, int y, Colour c) {
        this.x = x;
        this.y = y;
        this.colour = c;
        this.board = board;
    }

    public Colour getColour() {
        return this.colour;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * Siirtää nappulan uuteen paikkaan
     *
     * @param newx uusi x-koordinaatti
     * @param newy uusi y-koordinaatti
     */
    public void move(int newx, int newy) {
        this.x = newx;
        this.y = newy;
    }

    public Piece[][] getBoard() {
        return this.board;
    }

    /**
     *
     * @return palauttaa sallitut siirrot listana
     */
    public abstract List<int[]> moves();
}
