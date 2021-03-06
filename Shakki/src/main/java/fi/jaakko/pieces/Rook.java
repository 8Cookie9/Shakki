package fi.jaakko.pieces;

import fi.jaakko.game.Board;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    /**
     * Rook-nappulan toiminta.
     *
     * @param board mihin lautaan nappula kuuluu
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param c nappulan väri
     */
    public Rook(Board board, int x, int y, Colour c) {
        super(board, x, y, c);
    }

    /**
     * Kaikki sallitut siirrot.
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
     * Siirrot, joilla kaapataan toinen nappula.
     *
     * @return kaikki toisen napin kaappaavat siirrot
     */
    @Override
    public List<int[]> capture() {
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = Math.min(super.getX() + 1, 7); i < 8; i++) { //oikealle
            if (super.getBoard().board()[i][super.getY()] != null) {
                if (super.getBoard().board()[i][super.getY()].getColour() != super.getColour()) {
                    moves.add(new int[]{i, super.getY()});
                }
                break;
            }
        }
        for (int i = Math.max(super.getX() - 1, 0); i >= 0; i--) { //vasemmalle
            if (super.getBoard().board()[i][super.getY()] != null) {
                if (super.getBoard().board()[i][super.getY()].getColour() != super.getColour()) {
                    moves.add(new int[]{i, super.getY()});
                }
                break;
            }
        }
        for (int i = Math.min(super.getY() + 1, 7); i < 8; i++) { //ylös
            if (super.getBoard().board()[super.getX()][i] != null) {
                if (super.getBoard().board()[super.getX()][i].getColour() != super.getColour()) {
                    moves.add(new int[]{super.getX(), i});
                }
                break;
            }
        }
        for (int i = Math.max(super.getY() - 1, 0); i >= 0; i--) { //alas
            if (super.getBoard().board()[super.getX()][i] != null) {
                if (super.getBoard().board()[super.getX()][i].getColour() != super.getColour()) {
                    moves.add(new int[]{super.getX(), i});
                }
                break;
            }
        }
        return moves;
    }

    /**
     * Siirrot, joilla liikutaan.
     *
     * @return kaikki siirrot, joilla vain liikutaan
     */
    @Override
    public List<int[]> regularMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = Math.min(super.getX() + 1, 7); i < 8; i++) { //oikealle
            if (super.getBoard().board()[i][super.getY()] != null) {
                break;
            } else {
                moves.add(new int[]{i, super.getY()});
            }
        }
        for (int i = Math.max(super.getX() - 1, 0); i >= 0; i--) { //vasemmalle
            if (super.getBoard().board()[i][super.getY()] != null) {
                break;
            } else {
                moves.add(new int[]{i, super.getY()});
            }
        }
        for (int i = Math.min(super.getY() + 1, 7); i < 8; i++) { //ylös
            if (super.getBoard().board()[super.getX()][i] != null) {
                break;
            } else {
                moves.add(new int[]{super.getX(), i});
            }
        }
        for (int i = Math.max(super.getY() - 1, 0); i >= 0; i--) { //alas
            if (super.getBoard().board()[super.getX()][i] != null) {
                break;
            } else {
                moves.add(new int[]{super.getX(), i});
            }
        }
        return moves;
    }

    /**
     * Rook tekstimuodossa.
     *
     * @return Pelinappula tekstimuodossa väri (B tai W) + nimi
     */
    @Override
    public String toString() {
        if (super.getColour() == Colour.BLACK) {
            return "♜";
        } else {
            return "♖";
        }
    }
}
