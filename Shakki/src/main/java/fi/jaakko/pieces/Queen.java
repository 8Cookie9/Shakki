package fi.jaakko.pieces;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    /**
     * Queen-nappulan toiminta.
     * @param board mihin lautaan nappula kuuluu
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param c nappulan väri
     */
    public Queen(Piece[][] board, int x, int y, Colour c) {
        super(board, x, y, c);
    }

    /**
     * Kaikkisallitut siirrot.
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
     * @return kaikki toisen napin kaappaavat siirrot
     */
    @Override
    public List<int[]> capture() {
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            if (condition(i, i)) {
                if (super.getBoard()[super.getX() + i][super.getY() + i].getColour() != super.getColour()) {
                    moves.add(new int[]{super.getX() + i, super.getY() + i});
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (condition(-1 * i, i)) {
                if (super.getBoard()[super.getX() - i][super.getY() + i].getColour() != super.getColour()) {
                    moves.add(new int[]{super.getX() - i, super.getY() + i});
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (condition(i, -1 * i)) {
                if (super.getBoard()[super.getX() + i][super.getY() - i].getColour() != super.getColour()) {
                    moves.add(new int[]{super.getX() + i, super.getY() - i});
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (condition(-1 * i, -1 * i)) {
                if (super.getBoard()[super.getX() - i][super.getY() - i].getColour() != super.getColour()) {
                    moves.add(new int[]{super.getX() - i, super.getY() - i});
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (condition(i, 0)) {
                if (super.getBoard()[super.getX() + i][super.getY()].getColour() != super.getColour()) {
                    moves.add(new int[]{super.getX() + i, super.getY()});
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (condition(-1 * i, 0)) {
                if (super.getBoard()[super.getX() - i][super.getY()].getColour() != super.getColour()) {
                    moves.add(new int[]{super.getX() - i, super.getY()});
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (condition(0, i)) {
                if (super.getBoard()[super.getX()][super.getY() + i].getColour() != super.getColour()) {
                    moves.add(new int[]{super.getX(), super.getY() + i});
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (condition(0, -1 * i)) {
                if (super.getBoard()[super.getX()][super.getY() - i].getColour() != super.getColour()) {
                    moves.add(new int[]{super.getX(), super.getY() - i});
                }
                break;
            }
        }
        return moves;
    }

    private boolean condition(int dx, int dy) {
        if (((super.getX() + dx) <= 7) && ((super.getX() + dx) >= 0) && ((super.getY() + dy) <= 7) && ((super.getY() + dy) >= 0)) {
            return super.getBoard()[(super.getX() + dx)][(super.getY() + dy)] != null;
        } else {
            return false;
        }
    }

    /**
     * Kaikkisiirrot, joilla vain liikutaan.
     * @return kaikki siirrot, joilla vain liikutaan
     */
    @Override
    public List<int[]> regularMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            if (super.getX() + i <= 7 && super.getY() + i <= 7) {
                if (super.getBoard()[super.getX() + i][super.getY() + i] == null) {
                    moves.add(new int[]{super.getX() + i, super.getY() + i});
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (super.getX() - i >= 0 && super.getY() + i <= 7) {
                if (super.getBoard()[super.getX() - i][super.getY() + i] == null) {
                    moves.add(new int[]{super.getX() - i, super.getY() + i});
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (super.getX() + i <= 7 && super.getY() - i >= 0) {
                if (super.getBoard()[super.getX() + i][super.getY() - i] == null) {
                    moves.add(new int[]{super.getX() + i, super.getY() - i});
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (super.getX() - i >= 0 && super.getY() - i >= 0) {
                if (super.getBoard()[super.getX() - i][super.getY() - i] == null) {
                    moves.add(new int[]{super.getX() - i, super.getY() - i});
                } else {
                    break;
                }
            }
        }
        for (int i = Math.min(super.getX() + 1, 7); i < 8; i++) {
            if (super.getBoard()[i][super.getY()] != null) {
                break;
            } else {
                moves.add(new int[]{i, super.getY()});
            }
        }
        for (int i = Math.max(super.getX() - 1, 0); i >= 0; i--) {
            if (super.getBoard()[i][super.getY()] != null) {
                break;
            } else {
                moves.add(new int[]{i, super.getY()});
            }
        }
        for (int i = Math.min(super.getY() + 1, 7); i < 8; i++) {
            if (super.getBoard()[super.getX()][i] != null) {
                break;
            } else {
                moves.add(new int[]{super.getX(), i});
            }
        }
        for (int i = Math.max(super.getY() - 1, 0); i >= 0; i--) {
            if (super.getBoard()[super.getX()][i] != null) {
                break;
            } else {
                moves.add(new int[]{super.getX(), i});
            }
        }
        return moves;
    }

    /**
     * Queen tekstimuodossa.
     * @return Pelinappula tekstimuodossa väri (B tai W) + nimi
     */
    @Override
    public String toString() {
        if (super.getColour() == Colour.BLACK) {
            return "♛";
        } else {
            return "♕";
        }
    }
}