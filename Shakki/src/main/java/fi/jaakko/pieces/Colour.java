package fi.jaakko.pieces;

public enum Colour {

    BLACK(-1),
    WHITE(1);
    private final int value;

    Colour(final int i) {
        this.value = i;
    }

    /**
     *
     * @return värin numeroarvo: WHITE = 1; BLACK = -1
     */
    public int value() {
        return this.value;
    }

    /**
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
