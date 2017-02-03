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
        for(int i=0;i<8;i++){
            this.addPiece(new Pawn(this.board(),i,6,Colour.BLACK));
            this.addPiece(new Pawn(this.board(),i,1,Colour.WHITE));
        }
        this.addPiece(new Rook(this.board(),0,0,Colour.WHITE));
        this.addPiece(new Rook(this.board(),7,0,Colour.WHITE));
        this.addPiece(new Knight(this.board(),1,0,Colour.WHITE));
        this.addPiece(new Knight(this.board(),6,0,Colour.WHITE));
        this.addPiece(new Bishop(this.board(),2,0,Colour.WHITE));
        this.addPiece(new Bishop(this.board(),5,0,Colour.WHITE));
        this.addPiece(new Queen(this.board(),3,0,Colour.WHITE));
        this.addPiece(new King(this.board(),4,0,Colour.WHITE));
        this.addPiece(new Rook(this.board(),7,0,Colour.BLACK));
        this.addPiece(new Rook(this.board(),7,7,Colour.BLACK));
        this.addPiece(new Knight(this.board(),1,7,Colour.BLACK));
        this.addPiece(new Knight(this.board(),6,7,Colour.BLACK));
        this.addPiece(new Bishop(this.board(),2,7,Colour.BLACK));
        this.addPiece(new Bishop(this.board(),5,7,Colour.BLACK));
        this.addPiece(new Queen(this.board(),3,7,Colour.BLACK));
        this.addPiece(new King(this.board(),4,7,Colour.BLACK));  
    }

    public Piece[][] board() {
        return this.board;
    }
    
    public void addPiece(Piece p){
        if(this.board[p.getX()][p.getY()]==null){
            this.board[p.getX()][p.getY()]=p;
        }
    }
    
    @Override
    public String toString(){
        String s="";
        for(int x=0;x<8;x++){
            for(int y=0;y<8;y++){
                s+=this.board[x][y].toString()+" ";
            }
            s+="\n";
        }
        return s;
    }
}
