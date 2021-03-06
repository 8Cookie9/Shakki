package fi.jaakko.pieces;

import fi.jaakko.game.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {

    private ArrayList<int[]> mv;

    /**
     * Knight-nappulan toiminta.
     *
     * @param board mihin lautaan nappula kuuluu
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param c nappulan väri
     */
    public Knight(Board board, int x, int y, Colour c) {
        super(board, x, y, c);
        this.listMoves();
    }

    /**
     * Kaikki sallitut siirrot.
     *
     * @return kaikki sallitut siirrot
     */
    @Override
    public List<int[]> moves() {
        ArrayList<int[]> moves = new ArrayList<>();
        moves.addAll(regularMoves());
        moves.addAll(capture());
        return moves;
    }

    /**
     * Siirrot, joilla kaapataan toinen nappula.
     *
     * @return kaikki toisen napin kaappaavat siirrot
     */
    @Override
    public List<int[]> capture() {
        listMoves();
        return this.mv.stream()
                .filter(i -> i[0] >= 0 && i[0] <= 7 && i[1] >= 0 && i[1] <= 7)
                .filter(i -> super.getBoard().board()[i[0]][i[1]] != null)
                .filter(i -> super.getBoard().board()[i[0]][i[1]].getColour() != super.getColour())
                .collect(Collectors.toList());
    }

    /**
     * Siirrot, joilla vain liikutaan.
     *
     * @return kaikki siirrot, joilla vain liikutaan
     */
    @Override
    public List<int[]> regularMoves() {
        listMoves();
        return this.mv.stream()
                .filter(i -> i[0] >= 0 && i[0] <= 7 && i[1] >= 0 && i[1] <= 7)
                .filter(i -> super.getBoard().board()[i[0]][i[1]] == null)
                .collect(Collectors.toList());
    }

    private void listMoves() {
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

    /**
     * Knight tekstimuodossa.
     *
     * @return Pelinappula tekstimuodossa väri (B tai W) + nimi
     */
    @Override
    public String toString() {
        if (super.getColour() == Colour.BLACK) {
            return "♞";
        } else {
            return "♘";
        }
    }
}
