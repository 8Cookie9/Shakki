package fi.jaakko.pieces;

import fi.jaakko.game.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class King extends Piece {

    private ArrayList<int[]> mv;

    /**
     * King-nappulan toiminta.
     *
     * @param board mihin lautaan nappula kuuluu
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param c nappulan väri
     */
    public King(Board board, int x, int y, Colour c) {
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
    public List<int[]> capture() {
        listMoves();
        ArrayList<int[]> moves = new ArrayList<>();
        Board newGame;
        List<int[]> mvs = this.mv.stream()
                .filter(i -> i[0] >= 0 && i[0] <= 7 && i[1] >= 0 && i[1] <= 7)
                .filter(i -> super.getBoard().board()[i[0]][i[1]] != null)
                .filter(i -> super.getBoard().board()[i[0]][i[1]].getColour() != super.getColour())
                .collect(Collectors.toList());
        for (int[] i : mvs) {
            newGame = testBoard();
            newGame.board()[i[0]][i[1]] = null;
            newGame.addPiece(new King(newGame, i[0], i[1], super.getColour()));
            boolean b = true;
            for (Piece p : newGame.getOtherColoredPieces(super.getColour())) {
                if (p.capture().stream().anyMatch(a -> a[0] == i[0] && a[1] == i[1])) {
                    b = false;
                    break;
                }
            }
            if (b) {
                moves.add(i);
            }
        }
        return moves;
    }

    @Override
    public List<int[]> regularMoves() {
        listMoves();
        ArrayList<int[]> moves = new ArrayList<>();
        Board newGame;
        List<int[]> mvs = this.mv.stream()
                .filter(i -> i[0] >= 0 && i[0] <= 7 && i[1] >= 0 && i[1] <= 7)
                .filter(i -> super.getBoard().board()[i[0]][i[1]] == null)
                .collect(Collectors.toList());
        for (int[] i : mvs) {
            newGame = testBoard();
            newGame.board()[i[0]][i[1]] = null;
            newGame.addPiece(new King(newGame, i[0], i[1], super.getColour()));
            boolean b = true;
            for (Piece p : newGame.getOtherColoredPieces(super.getColour())) {
                if (p.capture().stream().anyMatch(a -> a[0] == i[0] && a[1] == i[1])) {
                    b = false;
                    break;
                }
            }
            if (b) {
                moves.add(i);
            }
        }
        return moves;
    }

    private Board testBoard() {
        Board newGame = new Board(false);
        for (Piece p : super.getBoard().getAllPieces()) {
            if (p.getClass() == new Pawn(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                newGame.addPiece(new Pawn(newGame, p.getX(), p.getY(), p.getColour()));
            } else if (p.getClass() == new Rook(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                newGame.addPiece(new Rook(newGame, p.getX(), p.getY(), p.getColour()));
            } else if (p.getClass() == new Knight(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                newGame.addPiece(new Knight(newGame, p.getX(), p.getY(), p.getColour()));
            } else if (p.getClass() == new Bishop(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                newGame.addPiece(new Bishop(newGame, p.getX(), p.getY(), p.getColour()));
            } else if (p.getClass() == new Queen(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                newGame.addPiece(new Queen(newGame, p.getX(), p.getY(), p.getColour()));
            } else if (p.getClass() == new King(new Board(false), 0, 0, Colour.BLACK).getClass() && p.getColour() != this.getColour()) {
                newGame.addPiece(new TestKing(newGame, p.getX(), p.getY(), p.getColour()));
            }
        }
        return newGame;
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
     * King tekstimuodossa.
     *
     * @return Pelinappula tekstimuodossa väri (B tai W) + nimi
     */
    @Override
    public String toString() {
        if (super.getColour() == Colour.BLACK) {
            return "♚";
        } else {
            return "♔";
        }
    }
}
