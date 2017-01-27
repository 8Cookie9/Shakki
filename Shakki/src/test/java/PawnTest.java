import fi.jaakko.board.Board;
import fi.jaakko.pieces.Colour;
import fi.jaakko.pieces.Pawn;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PawnTest {
    
    private Board board;
    private Pawn pawn;
    
    
    @Before
    public void setUp() {
        board = new Board(false);
        pawn = new Pawn(board.board(), 0, 1, Colour.WHITE);
        
    }
    
    @Test
    public void voiLiikkuaTyhjaan(){
        assertTrue(pawn.regularMoves().contains(new Integer[]{0,2}));
    }
    
    @Test
    public void eiVoiLiikkuaToisenPaalleA(){
        board.addPiece(new Pawn(board.board(), 0, 2, Colour.BLACK));
        assertTrue(pawn.regularMoves().isEmpty());
    }
    
    @Test
    public void eiVoiLiikkuaToisenPaalleB(){
        board.addPiece(new Pawn(board.board(), 0, 3, Colour.BLACK));
        assertTrue(pawn.regularMoves().size()==1);
    }
    
    @Test
    public void eiVoiLiikkuaKentaltaUlosYlos(){
        Pawn p=new Pawn(board.board(), 0, 7, Colour.WHITE);
        board.addPiece(p);
        assertTrue(pawn.regularMoves().isEmpty());
    }
    
    @Test
    public void eiVoiLiikkuaKentaltaUlosAlas(){
        Pawn p=new Pawn(board.board(), 0, 0, Colour.BLACK);
        board.addPiece(p);
        assertTrue(pawn.regularMoves().isEmpty());
    }
    
    @Test
    public void voiKaapata(){
        Pawn p=new Pawn(board.board(), 1, 2, Colour.BLACK);
        board.addPiece(p);
        assertTrue(!pawn.capture().isEmpty());
    }
    
    @Test
    public void eiVoiKaapata(){
        assertTrue(pawn.capture().isEmpty());
    }
    
    @Test
    public void kaikkiLiikkeetToimiiKaappaus(){
        Pawn p=new Pawn(board.board(), 1, 2, Colour.BLACK);
        board.addPiece(p);
        assertTrue(pawn.moves().contains(new Integer[]{1,2}));
    }
    
    @Test
    public void kaikkiLiikkeetToimiiEiVoiLiikkua(){
        Pawn p=new Pawn(board.board(), 0, 2, Colour.BLACK);
        board.addPiece(p);
        assertTrue(pawn.moves().isEmpty());
    }
    
    @Test
    public void kaikkiLiikkeetToimiiKaappausEiVoiLiikkuaUlosKentalta(){
        Pawn p=new Pawn(board.board(), 0, 7, Colour.BLACK);
        board.addPiece(p);
        assertTrue(p.moves().isEmpty());
    }
}
