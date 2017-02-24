package fi.jaakko.pieces;

public enum Colour {

    BLACK(-1),
    WHITE(1);
    private final int value;

    Colour(final int i) {
        this.value = i;
    }

    /**
     * Arvo värille.
     *
     * @return värin numeroarvo: WHITE = 1; BLACK = -1
     */
    public int value() {
        return this.value;
    }
    
    /**
     * Värin nimi, käytetään pelin voittamisen yhtedessä.
     * @return palauttaa joko "Valkoinen" tai "Musta" riippuen väristä.
     */
    public String col() {
        if (this.value == 1) {
            return "Valkoinen";
        } else {
            return "Musta";
        }
    }

    /**
     * Väri tekstimuodossa.
     *
     * @return Värin ensimmäinen kirjain: WHITE = "W"; BLACK = "B"
     */
    @Override
    public String toString() {
        if (this.value == 1) {
            return "W";
        } else {
            return "B";
        }
    }
}
