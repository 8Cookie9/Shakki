package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {

    private ArrayList<int[]> mv;

    public Knight(Piece[][] board, final int x, final int y, final Colour c) {
        super(board, x, y, c);
        this.listMoves();
    }

    @Override
    public List<int[]> moves() {
        this.listMoves();
        ArrayList<int[]> moves = new ArrayList<>();
        moves.addAll(this.regularMoves());
        moves.addAll(this.capture());
        return moves;
    }

    public List<int[]> capture() {
        return this.mv.stream()
                .filter(i -> i[0]>=0 && i[0]<=7 && i[1]>=0 && i[1]<=7)
                .filter(i -> super.getBoard()[i[0]][i[1]] != null)
                .filter(i -> super.getBoard()[i[0]][i[1]].getColour() != super.getColour())
                .collect(Collectors.toList());
    }

    public List<int[]> regularMoves() { 
        return this.mv.stream()
                .filter(i -> i[0]>=0 && i[0]<=7 && i[1]>=0 && i[1]<=7)
                .filter(i -> super.getBoard()[i[0]][i[1]] == null)
                .collect(Collectors.toList());
    }
    
    private void listMoves(){
        mv = new ArrayList<>();
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
    public String toString() {
        return "["+super.getColour()+"Knight]";
    }
}
