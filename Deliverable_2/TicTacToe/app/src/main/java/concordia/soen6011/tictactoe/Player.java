package concordia.soen6011.tictactoe;

public class Player {

    private int ID;
    private String name;
    private int Symbol; //1 = X, 0 = ZERO ( O )

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSymbol() {
        return Symbol;
    }

    public void setSymbol(int symbol) {
        Symbol = symbol;
    }
}
