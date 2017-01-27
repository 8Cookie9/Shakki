package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
        
    public Pawn(int x,int y, Colour c){
        super(x,y,c);
    }
    
    public List<Integer[]> moves(){
        ArrayList<Integer[]> moves=new ArrayList<>();
        moves.addAll(this.regularMoves());
        moves.addAll(this.capture());
        return moves;
    }
    
    private List<Integer[]> enPassant(){
        
        return null;
    }
    
    private List<Integer[]> capture(){
        ArrayList<Integer[]> moves=new ArrayList<>();
        moves.addAll(this.enPassant());
        //lisätään 
        return moves;
    }
    
    private List<Integer[]> regularMoves(){
        ArrayList<Integer[]> moves=new ArrayList<>();
        if(super.getColour().value()==1&&super.getY()==1){  //voiko liikkua 2
            moves.add(new Integer[] {super.getX(),super.getY()+(2*super.getColour().value())});
        }
        return null;
    }
}
