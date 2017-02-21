package fi.jaakko.game;

import fi.jaakko.pieces.Colour;
import fi.jaakko.pieces.King;

public class Game {

    private int turn;
    private Board board;
    private Colour winner;
    
    /**
     * Luokka kuvaa peliä: pitää sisällään vuorojen vaihtumisen.
     * 
     * @param board pelilauta jolla pelataan
     */
    public Game(Board board) {
        this.turn = 1;
        this.board = board;
    }
    /**
     * Seuraava vuoro.
     */
    public void nextTurn() {
        this.turn++;
    }

    /**
     * Voidaanko siirtää shakkinappula tiettyyn kohtaan ja siirretään ,jos
     * voidaan. Pitää huolta, että on oikean pelaajan vuoro.
     *
     * @param x siirrettävän x-koordinaatti
     * @param y siirrettävän y-koordinaatti
     * @param x2 x-koordinaatti, jonne yritetään siirtää
     * @param y2 y-koordinaatti, jonne yritetään siirtää
     * @return true jos siirto onnistuu; false, jos ei
     */
    public boolean tryMove(int x, int y, int x2, int y2) {
        if (this.board.board()[x][y] != null) {
            if (this.board.board()[x][y].getColour() == this.getCurrentColour()) {
                return this.board.movePiece(x, y, x2, y2);
            }
        }
        return false;
    }
    
    /**
     * Kenen vuoro on.
     * 
     * @return palauttaa pelaajan nappuloiden värin, jonka vuoro on
     */
    public Colour getCurrentColour() {
        if (this.turn % 2 == 1) {
            return Colour.WHITE;
        } else {
            return Colour.BLACK;
        }
    }
    
    public boolean gameOver(){
        if(!this.board.getBlackPieces().stream().anyMatch(i -> i.getClass()==new King(new Board(false).board(),0,0,Colour.BLACK).getClass())){
            this.winner=Colour.WHITE;
            return true;
        }else if(!this.board.getWhitePieces().stream().anyMatch(i -> i.getClass()==new King(new Board(false).board(),0,0,Colour.BLACK).getClass())){
            this.winner=Colour.BLACK;
            return true;
        }
        return false;
    }
    
    public Colour getWinner(){
        return this.winner;
    }
    
    public Board getBoard(){
        return this.board;
    }
}
