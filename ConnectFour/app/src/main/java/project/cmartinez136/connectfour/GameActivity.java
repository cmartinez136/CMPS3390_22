package project.cmartinez136.connectfour;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class GameActivity extends AppCompatActivity {
    private ImageView[][] grid;
    private View boardView;
    private Board board;
    private ViewHolder viewHolder;
    private static final int numRow = 6;
    private static final int numCol = 7;
    MediaPlayer victory;

    private class ViewHolder{
        public TextView winnerText;
        public ImageView turnIndicatorImageView;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        board = new Board(numCol, numRow);
        boardView = findViewById(R.id.board_row);
        buildGrid();
        boardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_UP: {
                        int c = colAtX(motionEvent.getX());
                        if(c != -1){
                            placeChip(c);
                        }
                    }
                }
                return true;
            }
        });

        //for ease of testing winner check
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        viewHolder = new ViewHolder();
        viewHolder.turnIndicatorImageView = findViewById(R.id.turn_indicator_image_view);
        viewHolder.turnIndicatorImageView.setImageResource(resourceForTurn());
        viewHolder.winnerText = findViewById(R.id.winner_text);
        viewHolder.winnerText.setVisibility(View.GONE); //hide winner text until winner declared

    }
    private void reset() {
        board.resetGame();
        viewHolder.winnerText.setVisibility(View.GONE); //removing winner text
        viewHolder.turnIndicatorImageView.setImageResource(resourceForTurn());
        //setting up board again
        for(int r=0; r<numRow; r++){
            for(int c=0; c<numCol; c++){
                grid[r][c].setImageResource(android.R.color.transparent);
            }
        }
    }

    private  void win() {
        int color = board.turn == Board.Turn.P1 ? getColor(R.color.player1) : getColor(R.color.player2);
        viewHolder.winnerText.setTextColor(color);
        viewHolder.winnerText.setVisibility(View.VISIBLE);
        victory = MediaPlayer.create(GameActivity.this,R.raw.fanfare);
        victory.start();

    }

    public void placeChip(int c) {
        if(board.isWinner){
            return;
        }
        int r = board.lastRow(c);
        if(r == -1){
            return;
        }
        final ImageView gBoard = grid[r][c];
        float move = -(gBoard.getHeight() * r + gBoard.getHeight() + 15);
        gBoard.setY(move);
        gBoard.setImageResource(resourceForTurn());
        //moves chip object
        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, Math.abs(move));
        anim.setDuration(800);
        anim.setFillAfter(true);
        gBoard.startAnimation(anim);
        board.setChip(c,r); //place chip on board
        if(board.checkForWinner(c,r)){
            win();
        } else{
            changeTurn(); //if no win, next player goes
        }
    }

    private void changeTurn() {
        board.playerTurn();
        viewHolder.turnIndicatorImageView.setImageResource(resourceForTurn());
    }

    private int resourceForTurn() {
        switch(board.turn){
            case P1:
                return R.drawable.chipred;
            case P2:
                return R.drawable.chipyellow;
        }
        return R.drawable.chipyellow;
    }

    private int colAtX(float x) {
        float cWidth = grid[0][0].getWidth();
        int c = (int) x / (int) cWidth;
        if(c < 0 || c>6){
            return -1;
        }
        return c;
    }

    private void buildGrid() {
        grid = new ImageView[numRow][numCol];
        for(int r=0; r<numRow; r++){
            ViewGroup row = (ViewGroup) ((ViewGroup) boardView).getChildAt(r);
            row.setClipChildren(false); //chip wont clip through board image
            for(int c=0; c<numCol; c++){
                ImageView imageView = (ImageView) row.getChildAt(c);
                imageView.setImageResource(android.R.color.transparent);
                grid[r][c] = imageView;
            }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }

}
