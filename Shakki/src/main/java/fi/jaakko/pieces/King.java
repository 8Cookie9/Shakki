package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    /**
     *
     * @param board mihin lautaan nappula kuuluu
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param c nappulan väri
     */
    public King(Piece[][] board, int x, int y, Colour c) {
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
        //lisätään 
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
        return "[ " + super.getColour() + "King ]";
    }
}
