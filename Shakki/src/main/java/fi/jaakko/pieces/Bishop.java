package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Piece[][] board, final int x, final int y, final Colour c) {
        super(board, x, y, c);
    }

    @Override
    public List<int[]> moves() {
        ArrayList<int[]> moves = new ArrayList<>();
        moves.addAll(this.regularMoves());
        moves.addAll(this.capture());
        return moves;
    }

    public List<int[]> capture() {
        ArrayList<int[]> moves = new ArrayList<>();
        //lisätään 
        return moves;
    }

    public List<int[]> regularMoves() {
        ArrayList<int[]> moves = new ArrayList<>();

        return moves;
    }

    @Override
    public String toString() {
        return "[Bishop]";
    }
}
