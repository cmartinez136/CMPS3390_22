package project.cmartinez136.connectfour;

public class Grid {
    public boolean empty;
    public Board.Turn player;

    public Grid(){
        empty = true;
    }

    public void setPlayer(Board.Turn player){
        this.player = player;
        empty = false;
    }
}
