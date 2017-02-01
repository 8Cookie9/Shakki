package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Piece[][] board, int x, int y, Colour c) {
        super(board, x, y, c);
    }

    public List<Integer[]> moves() {
        ArrayList<Integer[]> moves = new ArrayList<>();
        moves.addAll(this.regularMoves());
        moves.addAll(this.capture());
        return moves;
    }


    public List<Integer[]> capture() {
        ArrayList<Integer[]> moves = new ArrayList<>();
        //lisätään 
        return moves;
    }

    public List<Integer[]> regularMoves() {
        ArrayList<Integer[]> moves = new ArrayList<>();
        
        return moves;
    }
}
