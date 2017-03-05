package fi.jaakko.pieces;

import fi.jaakko.game.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestKing extends Piece {
    private List<int[]> mv;
    
    
    /**
     * King-nappulan toimintaa kuvaava luokka, joka antaa siirtää kaapattvaksi.
     *
     * @param board mihin lautaan nappula kuuluu
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param c nappulan väri
     */
    public TestKing(Board board, int x, int y, Colour c) {
        super(board, x, y, c);
    }

    @Override
    public List<int[]> moves() {
        ArrayList<int[]> moves = new ArrayList<>();
        moves.addAll(this.regularMoves());
        moves.addAll(this.capture());
        return moves;
    }

    @Override
    public List<int[]> regularMoves() {
        listMoves();
        return this.mv.stream()
                .filter(i -> i[0] >= 0 && i[0] <= 7 && i[1] >= 0 && i[1] <= 7)
                .filter(i -> super.getBoard().board()[i[0]][i[1]] == null)
                .collect(Collectors.toList());
    }

    @Override
    public List<int[]> capture() {
        listMoves();
        return this.mv.stream()
                .filter(i -> i[0] >= 0 && i[0] <= 7 && i[1] >= 0 && i[1] <= 7)
                .filter(i -> super.getBoard().board()[i[0]][i[1]] != null)
                .filter(i -> super.getBoard().board()[i[0]][i[1]].getColour() != super.getColour())
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
}
