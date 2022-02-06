package a1.cmartinez136;

public class Material extends Item{
    private String muses;

    public Material() {
        super();
        this.muses = "";
    }

    public Material(String name, double price, int qty, String muses) {
        super(name, price, qty);
        this.muses = muses;
    }

    public String getmuses() {
        return muses;
    }

    public void setmuses(String mUses) {
        this.muses = muses;
    }

    @Override
    public String toString(){
        return String.format("%s %7s %-20s |", super.toString(), "|", this.muses);
    }
}

