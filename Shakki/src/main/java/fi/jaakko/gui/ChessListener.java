package fi.jaakko.gui;

import fi.jaakko.game.*;
import fi.jaakko.pieces.Colour;
import fi.jaakko.pieces.Pawn;
import fi.jaakko.pieces.Piece;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class ChessListener implements MouseListener {

    private int chosenX;
    private int chosenY;
    private boolean chosen;
    private Game game;
    private Container container;
    private List<int[]> regularMoves;
    private List<int[]> capture;
    private boolean checkmate;
    private boolean check;
    private int movesSinceLastCaptureOrPawnMovement;

    /**
     * Luo uuden kuuntelijan hiiren painalluksi varten.
     *
     * @param game Game-olio, jonka perusteella piirretään pelilauta.
     * @param container Container, jonne piirretään pelilauta.
     */
    public ChessListener(Game game, Container container) {
        this.chosen = false;
        this.game = game;
        this.container = container;
        this.capture = new ArrayList<>();
        this.regularMoves = new ArrayList<>();
        this.check = false;
        this.checkmate = false;
        this.movesSinceLastCaptureOrPawnMovement = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String[] values = e.getComponent().getName().split(" ");
        int x = Integer.parseInt(values[0]);
        int y = Integer.parseInt(values[1]);
        if (this.chosen) {
            if ((this.game.getMoves(this.chosenX, this.chosenY).stream().anyMatch(i -> i[0] == x && i[1] == y))) {
                if (this.game.getBoard().board()[this.chosenX][this.chosenY].capture().stream().anyMatch(i -> i[0] == x && i[1] == y) || this.game.getBoard().board()[this.chosenX][this.chosenY].getClass() == new Pawn(new Board(false), 0, 0, Colour.BLACK).getClass()) {
                    this.movesSinceLastCaptureOrPawnMovement = 0;
                } else {
                    this.movesSinceLastCaptureOrPawnMovement++;
                }
                if (this.game.tryMove(this.chosenX, this.chosenY, x, y)) {
                    this.check = this.game.check(this.game.getCurrentColour());
                    this.checkmate = this.game.checkmate(this.game.getCurrentColour());
                    this.game.nextTurn();
                    this.regularMoves = new ArrayList<>();
                    this.capture = new ArrayList<>();
                    this.chosen = false;
                }
            } else {
                this.chosen = false;
                this.regularMoves = new ArrayList<>();
                this.capture = new ArrayList<>();
            }
        } else {
            if (this.game.getBoard().board()[x][y] != null) {
                if (this.game.getBoard().board()[x][y].getColour() == this.game.getCurrentColour()) {
                    this.chosen = true;
                    this.chosenX = x;
                    this.chosenY = y;
                    this.regularMoves = this.game.getBoard().board()[x][y].regularMoves();
                    this.capture = this.game.getBoard().board()[x][y].capture();
                }
            }
        }
        container.removeAll();
        container.revalidate();
        GridLayout chessLayout = new GridLayout(8, 8);
        container.setLayout(chessLayout);
        for (int y1 = 0; y1 < 8; y1++) {
            for (int x1 = 0; x1 < 8; x1++) {
                JLabel label;
                if (this.game.getBoard().board()[x1][y1] == null) {
                    label = new JLabel("");
                } else {
                    label = new JLabel(this.game.getBoard().board()[x1][y1].toString());
                }
                label.setFont(new Font("Arial Unicode MS", Font.PLAIN, 43));
                label.setBorder(BorderFactory.createLineBorder(Color.black));
                label.setOpaque(true);
                int x2 = x1;
                int y2 = y1;
                if (this.chosenX == x2 && this.chosenY == y2 && this.chosen) {
                    label.setBackground(Color.yellow);
                } else if (this.capture.stream().anyMatch(i -> i[0] == x2 && i[1] == y2) && this.game.getMoves(x, y).stream().anyMatch(i -> i[0] == x2 && i[1] == y2)) {
                    label.setBackground(Color.red);
                } else if (this.regularMoves.stream().anyMatch(i -> i[0] == x2 && i[1] == y2) && this.game.getMoves(x, y).stream().anyMatch(i -> i[0] == x2 && i[1] == y2)) {
                    label.setBackground(Color.green);
                } else {
                    if (this.game.getBoard().board()[x1][y1] != null) {
                        if (this.game.getBoard().board()[x1][y1].getColour() == this.game.getCurrentColour()) {
                            if (y1 % 2 == x1 % 2) {
                                label.setBackground(new Color(200, 200, 150));
                            } else {
                                label.setBackground(new Color(230, 230, 180));
                            }
                        } else {
                            if (y1 % 2 == x1 % 2) {
                                label.setBackground(Color.lightGray);
                            } else {
                                label.setBackground(Color.white);
                            }
                        }
                    } else {
                        if (y1 % 2 == x1 % 2) {
                            label.setBackground(Color.lightGray);
                        } else {
                            label.setBackground(Color.white);
                        }
                    }
                }
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setName(x1 + " " + y1);
                label.addMouseListener(this);
                container.add(label);
            }
        }
        boolean stalemate = true;
        for (Piece p : this.game.getBoard().getSameColoredPieces(this.game.getCurrentColour())) {
            if (!this.game.getMoves(p.getX(), p.getY()).isEmpty()) {
                stalemate = false;
                break;
            }
        }
        if (this.checkmate && this.check) {
            Colour col;
            if (this.game.getCurrentColour() == Colour.BLACK) {
                col = Colour.WHITE;
            } else {
                col = Colour.BLACK;
            }
            JOptionPane.showMessageDialog(container, col.col() + " voitti pelin, paina 'ok' aloittaaksesi uuden pelin.");
            this.check = false;
            this.checkmate = false;
            this.movesSinceLastCaptureOrPawnMovement = 0;
            this.game.newGame();
            this.mouseClicked(e);
        } else if (this.game.gameOver()) {
            JOptionPane.showMessageDialog(container, this.game.getWinner().col() + " voitti pelin, paina 'ok' aloittaaksesi uuden pelin.");
            this.check = false;
            this.checkmate = false;
            this.movesSinceLastCaptureOrPawnMovement = 0;
            this.game.newGame();
            this.mouseClicked(e);
        } else if (stalemate) {
            JOptionPane.showMessageDialog(container, "Tasapeli, paina 'ok' aloittaaksesi uuden pelin.");
            this.check = false;
            this.checkmate = false;
            this.movesSinceLastCaptureOrPawnMovement = 0;
            this.game.newGame();
            this.mouseClicked(e);
        } else if (this.movesSinceLastCaptureOrPawnMovement >= 50) {
            JOptionPane.showMessageDialog(container, "Tasapeli, paina 'ok' aloittaaksesi uuden pelin.");
            this.check = false;
            this.checkmate = false;
            this.movesSinceLastCaptureOrPawnMovement = 0;
            this.game.newGame();
            this.mouseClicked(e);
        }
        container.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
