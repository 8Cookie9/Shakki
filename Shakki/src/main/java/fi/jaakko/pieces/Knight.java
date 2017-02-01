package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {
    
    private ArrayList<Integer[]> mv = new ArrayList<>();
    
    public Knight(Piece[][] board, int x, int y, Colour c) {
        super(board, x, y, c);
        mv.add(new Integer[]{super.getX()-1,super.getY()+2});
        mv.add(new Integer[]{super.getX()+1,super.getY()+2});
        mv.add(new Integer[]{super.getX()-2,super.getY()+1});
        mv.add(new Integer[]{super.getX()-2,super.getY()-1});
        mv.add(new Integer[]{super.getX()-1,super.getY()-2});
        mv.add(new Integer[]{super.getX()+1,super.getY()-2});
        mv.add(new Integer[]{super.getX()+2,super.getY()+1});
        mv.add(new Integer[]{super.getX()+2,super.getY()-1});
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
        return this.mv.stream().filter(i -> super.getBoard()[i[0]][i[1]]==null).collect(Collectors.toList());
    }
}
