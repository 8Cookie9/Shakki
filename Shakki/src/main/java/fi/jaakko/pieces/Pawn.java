package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    /**
     * Pawn-nappulan toiminta.
     *
     * @param board mihin lautaan nappula kuuluu
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param c nappulan väri
     */
    public Pawn(Piece[][] board, int x, int y, Colour c) {
        super(board, x, y, c);
    }

    /**
     * Kerää kaikki sallitut siirrot yhteen.
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
     * Siirrot joilla kaapataan nappula.
     *
     * @return kaikki toisen napin kaappaavat siirrot
     */
    @Override
    public List<int[]> capture() {
        ArrayList<int[]> moves = new ArrayList<>();
        if ((super.getColour().value() == -1 && super.getY() > 0)
                || (super.getColour().value() == 1 && super.getY() < 7)) { //ei saa mennä pelialueen ulkopuolelle
            if (super.getX() < 7) { //voiko kaapata ylävasemmalta
                if (super.getBoard()[super.getX() + 1][super.getY() + super.getColour().value()] != null
                        && super.getBoard()[super.getX() + 1][super.getY() + super.getColour().value()].getColour() != super.getColour()) { //erivärinen pelinappula kohteessa
                    moves.add(new int[]{super.getX() + 1, super.getY() + super.getColour().value()});
                }
            }
            if (super.getX() > 0) { //voiko kaapata yläoikealta
                if (super.getBoard()[super.getX() - 1][super.getY() + super.getColour().value()] != null
                        && super.getBoard()[super.getX() - 1][super.getY() + super.getColour().value()].getColour() != super.getColour()) { //erivärinen pelinappula kohteessa
                    moves.add(new int[]{super.getX() - 1, super.getY() + super.getColour().value()});
                }
            }
        }
        return moves;
    }

    /**
     * Liikkumissiirrot.
     *
     * @return kaikki siirrot, joilla vain liikutaan
     */
    @Override
    public List<int[]> regularMoves() { //1 eteenpäin, 2 eteenpäin alussa
        ArrayList<int[]> moves = new ArrayList<>();
        if (!((super.getColour().value() == 1 && super.getY() == 7)
                || (super.getColour().value() == -1 && super.getY() == 0))) {
            if (super.getBoard()[super.getX()][super.getY() + super.getColour().value()] == null
                    && super.getY() + super.getColour().value() < 8
                    && super.getY() + super.getColour().value() >= 0) {
                moves.add(new int[]{super.getX(), (super.getY() + super.getColour().value())});
                if (((super.getColour().value() == 1 && super.getY() == 1)
                        || (super.getColour().value() == -1 && super.getY() == 6))
                        && super.getBoard()[super.getX()][super.getY() + (2 * super.getColour().value())] == null) {  //voiko liikkua 2
                    moves.add(new int[]{super.getX(), super.getY() + (2 * super.getColour().value())});
                }
            }
        }
        return moves;
    }

    /**
     * Tekstimuoto nappulalle.
     *
     * @return Pelinappula tekstimuodossa väri (B tai W) + nimi
     */
    @Override
    public String toString() {
        if (super.getColour() == Colour.BLACK) {
            return "♟";
        } else {
            return "♙";
        }
    }
}
