package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    /**
     *
     * @param board mihin lautaan nappula kuuluu
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param c nappulan väri
     */
    public Bishop(Piece[][] board, int x, int y, Colour c) {
        super(board, x, y, c);
    }

    /**
     *
     * @return kaikki sallitut siirrot
     */
    @Override
    public List<int[]> moves() {
        ArrayList<int[]> moves = new ArrayList<>();
        moves.addAll(this.regularMoves());
        moves.addAll(this.capture());
        return moves;
    }

    /**
     *
     * @return kaikki toisen napin kaappaavat siirrot
     */
    public List<int[]> capture() {
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = 1; i < 8; i++) { //yläoikealle
            if(!(super.getX()+i>7||super.getX()+i<0||super.getY()+i>7||super.getY()+i<0)){
                if (super.getBoard()[super.getX()+i][super.getY()+i] != null) {
                    if (super.getBoard()[super.getX()+i][super.getY()+i].getColour() != super.getColour()) {
                        moves.add(new int[]{super.getX()+i, super.getY()+i});
                    }
                    break;
                }
            }
        }
        return moves;
    }

    /**
     *
     * @return kaikki siirrot, joilla vain liikutaan
     */
    public List<int[]> regularMoves() {
        ArrayList<int[]> moves = new ArrayList<>();

        return moves;
    }

    /**
     *
     * @return Pelinappula tekstimuodossa väri (B tai W) + nimi
     */
    @Override
    public String toString() {
        return "[" + super.getColour() + "Bishop]";
    }
}
