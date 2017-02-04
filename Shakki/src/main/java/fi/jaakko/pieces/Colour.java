package fi.jaakko.pieces;

public enum Colour {
    BLACK(-1),
    WHITE(1);
    private final int value;

    Colour(final int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }
    
    @Override
    public String toString(){
        if(this.value==1){
            return "W";
        }else{
            return "B";
        }
    }
}
