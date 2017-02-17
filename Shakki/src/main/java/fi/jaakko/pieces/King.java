package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class King extends Piece {

    private ArrayList<int[]> mv;

    /**
     * King-nappulan toiminta.
     * @param board mihin lautaan nappula kuuluu
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param c nappulan väri
     */
    public King(Piece[][] board, int x, int y, Colour c) {
        super(board, x, y, c);
    }

    /**
     *Kaikki sallitut siirrot.
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
     *Siirrot, joilla kaapataan toinen nappula.
     * @return kaikki toisen napin kaappaavat siirrot
     */
    public List<int[]> capture() {
        listMoves();
        return this.mv.stream()
                .filter(i -> i[0] >= 0 && i[0] <= 7 && i[1] >= 0 && i[1] <= 7)
                .filter(i -> super.getBoard()[i[0]][i[1]] != null)
                .filter(i -> super.getBoard()[i[0]][i[1]].getColour() != super.getColour())
                .collect(Collectors.toList());
    }

    /**
     *Siirror, joilla ainoastaan liikutaan.
     * @return kaikki siirrot, joilla vain liikutaan
     */
    public List<int[]> regularMoves() {
        listMoves();
        return this.mv.stream()
                .filter(i -> i[0] >= 0 && i[0] <= 7 && i[1] >= 0 && i[1] <= 7)
                .filter(i -> super.getBoard()[i[0]][i[1]] == null)
                .collect(Collectors.toList());
    }

    private void listMoves() {
        mv = new ArrayList<>();
        mv.add(new int[]{super.getX() - 1, super.getY() + 1});
        mv.add(new int[]{super.getX(), super.getY() + 1});
        mv.add(new int[]{super.getX() + 1, super.getY() + 1});
        mv.add(new int[]{super.getX() + 1, super.getY()});
        mv.add(new int[]{super.getX() + 1, super.getY() - 1});
        mv.add(new int[]{super.getX(), super.getY() - 1});
        mv.add(new int[]{super.getX() - 1, super.getY() - 1});
        mv.add(new int[]{super.getX() - 1, super.getY()});
    }

    /**
     *King tekstimuodossa.
     * @return Pelinappula tekstimuodossa väri (B tai W) + nimi
     */
    @Override
    public String toString() {
        return "[ " + super.getColour() + "King ]";
    }
}
