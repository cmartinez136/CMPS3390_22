package project.cmartinez136.connectfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    private final int FPS = 1000/30;
    private Thread thread;
    private boolean isPlaying;
    private Board board;

    public GameView(Context context, Board board) {
        super(context);
        this.board = board;
    }

    @Override
    public void run(){
        while(isPlaying){
            update();
            draw();
            sleep();
        }
    }

    private void update() {
    }

    public void draw(){

    }

    public void pause(){
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void sleep(){
        try {
            Thread.sleep(FPS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP){
            int x = Math.round(event.getX());
            int y = Math.round(event.getY());
        }
        return true;
    }
}
