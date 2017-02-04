package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(Piece[][] board, final int x, final int y, final Colour c) {
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
        for(int i=Math.min(super.getX()+1, 7);i<8;i++){ //oikealle
            if(super.getBoard()[i][super.getY()]!=null){
                if(super.getBoard()[i][super.getY()].getColour()!=super.getColour()){
                    moves.add(new int[] {i,super.getY()});
                }
                break;
            }
        }
        for(int i=Math.max(super.getX()-1, 0);i>=0;i--){ //vasemmalle
            if(super.getBoard()[i][super.getY()]!=null){
                if(super.getBoard()[i][super.getY()].getColour()!=super.getColour()){
                    moves.add(new int[] {i,super.getY()});
                }
                break;
            }
        }
        for(int i=Math.min(super.getY()+1, 7);i<8;i++){ //ylös
            if(super.getBoard()[super.getX()][i]!=null){
                if(super.getBoard()[super.getX()][i].getColour()!=super.getColour()){
                    moves.add(new int[] {super.getX(),i});
                }
                break;
            }
        }
        for(int i=Math.max(super.getY()-1, 0);i>=0;i--){ //alas
            if(super.getBoard()[super.getX()][i]!=null){
                if(super.getBoard()[super.getX()][i].getColour()!=super.getColour()){
                    moves.add(new int[] {super.getX(),i});
                }
                break;
            }
        } 
        return moves;
    }
    

    public List<int[]> regularMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        for(int i=Math.min(super.getX()+1, 7);i<8;i++){ //oikealle
            if(super.getBoard()[i][super.getY()]!=null){
                break;
            }else{
                moves.add(new int[] {i,super.getY()});
            }
        }  
        for(int i=Math.max(super.getX()-1, 0);i>=0;i--){ //vasemmalle
            if(super.getBoard()[i][super.getY()]!=null){
                break;
            }else{
                moves.add(new int[] {i,super.getY()});
            }
        }
        for(int i=Math.min(super.getY()+1, 7);i<8;i++){ //ylös
            if(super.getBoard()[super.getX()][i]!=null){
                break;
            }else{
                moves.add(new int[] {super.getX(),i});
            }
        } 
        for(int i=Math.max(super.getY()-1, 0);i>=0;i--){ //alas
            if(super.getBoard()[super.getX()][i]!=null){
                break;
            }else{
                moves.add(new int[] {super.getX(),i});
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        return "[ "+super.getColour()+"Rook ]";
    }
}
