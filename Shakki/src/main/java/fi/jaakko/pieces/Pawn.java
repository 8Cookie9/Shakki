package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Piece[][] board, int x, int y, Colour c) {
        super(board, x, y, c);
    }

    public List<Integer[]> moves() {
        ArrayList<Integer[]> moves = new ArrayList<>();
        moves.addAll(this.regularMoves());
        moves.addAll(this.capture());
        return moves;
    }

    public List<Integer[]> enPassant() {

        return null;
    }

    public List<Integer[]> capture() {
        ArrayList<Integer[]> moves = new ArrayList<>();
        moves.addAll(this.enPassant());
        //lisätään 
        return moves;
    }

    public List<Integer[]> regularMoves() {
        ArrayList<Integer[]> moves = new ArrayList<>();
        if(super.getBoard()[super.getX()][super.getY() + super.getColour().value()]==null&&super.getY() + super.getColour().value()<8&&super.getY() + super.getColour().value()>=0){
            moves.add(new Integer[]{super.getX(),(super.getY() + super.getColour().value())});
            if (super.getColour().value() == 1 && super.getY() == 1) {  //voiko liikkua 2
                moves.add(new Integer[]{super.getX(), super.getY() + (2 * super.getColour().value())});
            }
        }
        return moves;
    }
    
    @Override
    public String toString(){
        return "Pawn";
    }
}
