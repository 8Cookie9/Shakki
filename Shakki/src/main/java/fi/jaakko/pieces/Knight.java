package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {

    private ArrayList<int[]> mv;

    /**
     *
     * @param board mihin lautaan nappula kuuluu
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param c nappulan väri
     */
    public Knight(Piece[][] board, int x, int y, Colour c) {
        super(board, x, y, c);
        this.listMoves();
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
        return this.mv.stream()
                .filter(i -> i[0] >= 0 && i[0] <= 7 && i[1] >= 0 && i[1] <= 7)
                .filter(i -> super.getBoard()[i[0]][i[1]] != null)
                .filter(i -> super.getBoard()[i[0]][i[1]].getColour() != super.getColour())
                .collect(Collectors.toList());
    }

    /**
     *
     * @return kaikki siirrot, joilla vain liikutaan
     */
    public List<int[]> regularMoves() {
        return this.mv.stream()
                .filter(i -> i[0] >= 0 && i[0] <= 7 && i[1] >= 0 && i[1] <= 7)
                .filter(i -> super.getBoard()[i[0]][i[1]] == null)
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
     *
     * @return Pelinappula tekstimuodossa väri (B tai W) + nimi
     */
    @Override
    public String toString() {
        return "[" + super.getColour() + "Knight]";
    }
}
