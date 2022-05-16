package project.cmartinez136.connectfour;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Board {
    private int numCols;
    private int numRows;
    public boolean isWinner;
    public Grid[][] gameBoard;
    private boolean win = false;
    private View boardView;
    private ViewHolder viewHolder;
    private ImageView[][] gridBoard;
    private static final int numRow = 6;
    private static final int numCol = 7;

    private class ViewHolder{
        public TextView winnerText;
        public ImageView turnIndicatorImageView;
    }

    public enum Turn{
        P1, P2
    }

    public Turn turn;

    public Board(int c, int r){
        numCols = c;
        numRows = r;
        gameBoard = new Grid[c][r];

        resetGame();
    }

    public void resetGame() {
        isWinner = false;
        turn = Turn.P1;
        for(int c=0; c<numCols; c++){
            for(int r=0; r<numRows; r++){
                gameBoard[c][r]= new Grid();
            }
        }
    }

    public int lastRow(int c){
        for(int r = numRows-1; r>=0; r--){
            if(gameBoard[c][r].empty){
                return r;
            }
        }
        return -1;
    }

    public void setChip(int c, int r){
        gameBoard[c][r].setPlayer(turn);
    }

    public void playerTurn(){
        if(turn == Turn.P1){
            turn = Turn.P2;
        } else {
            turn = Turn.P1;
        }
    }

    public boolean checkForWinner(int col, int row){
        //vertical check
        for(int c = 0; c<numCols; c++){
            if(checkFourInARow(turn, 0,1,col,0,0) || checkFourInARow(turn,1,1,col,0,0) || checkFourInARow(turn,-1,1,col,0,0)){
                isWinner = true;
                return true;
            }
        }
        //horizontal check
        for(int r=0; r<numRows; r++){
            if(checkFourInARow(turn,1,0,0,row,0) || checkFourInARow(turn,1,1,0,row,0) || checkFourInARow(turn, -1,1,numCols-1,row,0)){
                isWinner = true;
                return true;
            }
        }
        //diagonal up check
        //starting at middle point
        for(int c=0; c<numCols - 3; c++){
            for(int r=3; r<numRows; r++){
                if(checkFourInARow(turn,0,1,col+1,row-1,0) || checkFourInARow(turn,1,1,col+2,row-2,0) || checkFourInARow(turn, -1,1,col+3,row-3,0)){
                    isWinner = true;
                    return true;
                }
            }
        }
        //diagonal down check
        /*for(int c=0; c<numCols-3; c++){
            for(int r=3; r<numRows; r++){
                if(checkFourInARow(turn,1,0,col-1,row-1,0) || checkFourInARow(turn,1,1,col-2,row-2,0) || checkFourInARow(turn,1,-1,col+3,row+3,0)){
                    // ?????????????????????????????????????????????????????????????? //
                    isWinner = true;
                    return true;
                }
            }
        }*/
        return false;
    }

    private boolean checkFourInARow(Turn player, int dirX, int dirY, int col, int row, int count){
        if(count >=4){
            return true;
        }
        if(col < 0 || col >=numCols || row<0 || row >=numRows){
            return false;
        }
        Grid board = gameBoard[col][row];
        if(board.player == player) { //if player turn
            return checkFourInARow(player,dirX, dirY, col+dirX, row+dirY,count+1); //goes towards chip count, 4 = win
        } else{
            return checkFourInARow(player, dirX,dirY,col+dirX,row+dirY,0);
        }
    }














}