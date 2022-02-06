package a1.cmartinez136;

public class Weapon extends Item{
    private int attack;

    public Weapon() {
        super();
        this.attack = 0;
    }

    public Weapon(String name, double price, int qty, int attack) {
        super(name, price, qty);
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String toString(){
        return String.format("%s %-20s |", super.toString(), this.attack);
    }
}
