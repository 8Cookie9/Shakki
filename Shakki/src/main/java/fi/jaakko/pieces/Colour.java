package fi.jaakko.pieces;

public enum Colour {
    BLACK(-1),
    WHITE(1);
    private int value;
    Colour(int i){
        this.value=i;
    }
    public int value(){
        return this.value;
    }
}
