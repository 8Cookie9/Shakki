package fi.jaakko.board;

import fi.jaakko.pieces.*;

public class Board {

    private Piece[][] board;

    public Board(boolean b) {
        this.board = new Piece[8][8];
        if(b){
            this.setup();
        }
    }

    private void setup() {
        //luo napit ja sijoittaa ne oikeisiin kohtiin
    }

    public Piece[][] board() {
        return this.board;
    }
    
    public void addPiece(Piece p){
        if(this.board[p.getX()][p.getY()]==null){
            this.board[p.getX()][p.getY()]=p;
        }
    }
}
