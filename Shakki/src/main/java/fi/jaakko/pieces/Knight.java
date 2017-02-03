package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {

    private final ArrayList<int[]> mv = new ArrayList<>();

    public Knight(Piece[][] board, final int x, final int y, final Colour c) {
        super(board, x, y, c);
        mv.add(new int[]{super.getX() - 1, super.getY() + 2});
        mv.add(new int[]{super.getX() + 1, super.getY() + 2});
        mv.add(new int[]{super.getX() - 2, super.getY() + 1});
        mv.add(new int[]{super.getX() - 2, super.getY() - 1});
        mv.add(new int[]{super.getX() - 1, super.getY() - 2});
        mv.add(new int[]{super.getX() + 1, super.getY() - 2});
        mv.add(new int[]{super.getX() + 2, super.getY() + 1});
        mv.add(new int[]{super.getX() + 2, super.getY() - 1});
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
        return this.mv.stream()
                .filter(i -> super.getBoard()[i[0]][i[1]] == null)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "[Knight]";
    }
}
