package fi.jaakko.board;

import fi.jaakko.pieces.*;

public class Board {

    private Piece[][] board;

    public Board(final boolean b) {
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

    public Piece[][] board() {
        return this.board;
    }

    public void addPiece(Piece p) {
        if (this.board[p.getX()][p.getY()] == null) {
            this.board[p.getX()][p.getY()] = p;
        }
    }

    public boolean movePiece(int x, int y, int x2, int y2) {
        if (this.board[x][y] == null) {
            return false;
        } else if (!this.board[x][y].moves().contains(new int[]{x2, y2})) {
            return false;
        }
        this.board[x][y].move(x2, y2);
        this.board[x2][y2] = this.board[x][y];
        this.board = null;
        return true;
    }

    @Override
    public String toString() {
        String s = "";
        s += "    0       1       2       3       4       5       6       7\n";
        for (int y = 0; y < 8; y++) {
            s += "" + (7 - y);
            for (int x = 0; x < 8; x++) {
                if (this.board[x][y] != null) {
                    s += this.board[x][y].toString();
                } else {
                    s += "[      ]";
                }
            }
            s += "\n";
        }
        return s;
    }
}
