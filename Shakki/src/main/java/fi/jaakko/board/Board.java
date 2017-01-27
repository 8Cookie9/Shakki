package fi.jaakko.board;

import fi.jaakko.pieces.*;

public class Board {

    private Piece[][] board;

    public Board() {
        this.board = new Piece[8][8];
        this.setup();
    }

    private void setup() {
        //luo napit ja sijoittaa ne oikeisiin kohtiin
    }

    public Piece[][] board() {
        return this.board;
    }
    
    public void addPiece(Piece p, int x, int y){
        if(this.board[x][y]==null){
            this.board[x][y]=p;
        }
    }
}
