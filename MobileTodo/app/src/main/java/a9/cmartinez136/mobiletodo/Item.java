package a9.cmartinez136.mobiletodo;

public class Item {
    private final String desc;

    public Item(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString(){return this.getDesc();}
}
