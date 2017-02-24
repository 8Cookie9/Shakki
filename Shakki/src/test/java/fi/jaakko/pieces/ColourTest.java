package fi.jaakko.pieces;

import org.junit.Test;
import static org.junit.Assert.*;

public class ColourTest {

    public ColourTest() {
    }

    @Test
    public void testToString() {
        assertEquals("B", Colour.BLACK.toString());
        assertEquals("W", Colour.WHITE.toString());
    }
    
    @Test
    public void testCol() {
        assertEquals("Musta", Colour.BLACK.col());
        assertEquals("Valkoinen", Colour.WHITE.col());
    }

}
