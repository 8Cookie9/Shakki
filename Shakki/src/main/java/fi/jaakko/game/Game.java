package fi.jaakko.game;

import fi.jaakko.pieces.Colour;

public class Game {
    private int turn;
    private Board board;
    
    public Game(Board board){
        this.turn=1;
        this.board=board;
    }
    
    public void nextTurn(){
        this.turn++;
    }
    
    /**
     * Voidaanko siirtää shakkinappula tiettyyn kohtaan ja siirretäänjos voidaan.
     * Pitää huolta, että on oikean pelaajan vuoro
     * 
     * @param x siirrettävän x-koordinaatti
     * @param y siirrettävän y-koordinaatti
     * @param x2 x-koordinaatti, jonne yritetään siirtää
     * @param y2 y-koordinaatti, jonne yritetään siirtää
     * @return true jos siirto onnistuu; false, jos ei
     */
    public boolean tryMove(int x, int y, int x2, int y2){
        if(this.board.board()[x][y]!=null){
            if(this.board.board()[x][y].getColour()==this.getCurrentColour()){
                return this.board.movePiece(x, y, x2, y2);
            }
        }
        return false;
    }
    
    public Colour getCurrentColour(){
        if(this.turn%2==1){
            return Colour.WHITE;
        }else{
            return Colour.BLACK;
        }
    }
    
}
