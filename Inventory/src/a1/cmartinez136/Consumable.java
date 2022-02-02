
package a1.cmartinez136;

//extends item class
public class Consumable extends Item{
    private int usesLeft;

    //constructor
    public Consumable() {
        super();  //calling base class super
        this.usesLeft = 0;
    }
    //constructor
    public Consumable(String name, double price, int qty, int usesLeft) {
        super(name, price, qty);
        this.usesLeft = Math.max(usesLeft, 0); // 0 or more
    }

    public int getUsesLeft() {
        return usesLeft;
    }

    public void setUsesLeft(int usesLeft) {
        this.usesLeft = Math.max(usesLeft, 0);

    }

    @Override
    public String toString(){
        return String.format("%s %5d |", super.toString(), this.usesLeft);
    }
}