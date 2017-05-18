package com.callejas.cesar.aprendamosastronomia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class Engine extends View {
    private int enemyX =0 ;
    private int enemyY1 = 400;
    private int enemyY2 = 400;
    private int enemyY3  =400;
    protected static boolean status = true;
    protected static boolean statusPreg = true;
    private int scrollVertical = 8;
    private int w;
    private int h;
    private int playerX =50, playerY =0;
    private int moveSize=0;
    protected int score=0;
    private int backGroundX =0;
    boolean touch= false;
    private boolean estateColision;
    Random random= new Random();

    //Actores del juego
    Bitmap playerFigure;
    Bitmap enemyFigure1;
    Bitmap enemyFigure2;
    Bitmap enemyFigure3;
    Bitmap background;


    //Pintores de objetos
    Paint playerPaint;
    Paint enemyPaint;
    Paint scorePaint;
    Paint backPaint;

    //Iniciando metodos
    public Engine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPlayerFigure(Bitmap playerFigure) {
        this.playerFigure = playerFigure;
    }

    public void setEnemyFigure1(Bitmap enemyFigure) {
        this.enemyFigure1 = enemyFigure;

    }

    public void setEnemyFigure2(Bitmap enemyFigure) {
        this.enemyFigure2 = enemyFigure;

    }

    public void setEnemyFigure3(Bitmap enemyFigure) {
        this.enemyFigure3 = enemyFigure;

    }

    public void setBackground(Bitmap background) {
        this.background = background;
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        background = Bitmap.createScaledBitmap(background, background.getWidth(),h, true );
        playerFigure = Bitmap.createScaledBitmap(playerFigure,h/10,h/10,true);
        enemyFigure1 = Bitmap.createScaledBitmap(enemyFigure1,h/5,h/5,true);
        enemyFigure2 = Bitmap.createScaledBitmap(enemyFigure2,h/5,h/5,true);
        enemyFigure3 = Bitmap.createScaledBitmap(enemyFigure3,h/8,h/8,true);
        initShapes();
        //Dibujamos en pantalla
        canvas.drawBitmap(background, backGroundX , 0, backPaint);
        //Arreglando el fondo
        if(backGroundX<0) {
            canvas.drawBitmap(background,backGroundX+background.getWidth(),0,backPaint);
        }


        canvas.drawBitmap(playerFigure, playerX, playerY, playerPaint);
        canvas.drawBitmap(enemyFigure1, enemyX, enemyY1 , enemyPaint);
        canvas.drawBitmap(enemyFigure2, enemyX , enemyY2 , enemyPaint);
        canvas.drawBitmap(enemyFigure3, enemyX , enemyY3 , enemyPaint);

        if (colisionPreg()) {
            this.statusPreg = false;
        }

        if(colision()) {
            this.status = false;

        }
        canvas.drawText("Score: "+score, w-200,20, scorePaint);
    }

    private boolean colision() {
        if( ((playerX +playerFigure.getWidth())>= enemyX) && ((playerY+playerFigure.getHeight()) >= enemyY1) && (playerY <= (enemyY1+ enemyFigure1.getHeight()))){
            estateColision = true;
            return true;
        }
        if( ((playerX +playerFigure.getWidth())>= enemyX) && ((playerY+playerFigure.getHeight()) >= enemyY2) && (playerY <= (enemyY2+ enemyFigure1.getHeight()))){
            estateColision = true;
            return true;
        }
        return false;
    }

    private void initShapes() {
        if (playerPaint == null) playerPaint = new Paint();
        if(enemyPaint ==null){
            enemyPaint =new Paint();
        }
        if(scorePaint==null){
            scorePaint = new Paint();
            scorePaint.setColor(Color.WHITE);
            scorePaint.setTextSize(16);

        }if(enemyX <0){
            enemyX =w;
            score+=10;
            enemyY1 =random.nextInt(h+1);
            enemyY2 =random.nextInt(h+1);
            enemyY3=random.nextInt(h+1);
        }
        moveSize=w/30;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.w = w;
        this.h = h;
        super.onSizeChanged(w, h, oldw, oldh);
        enemyX =h;
        enemyY1 =20;
    }


    //Control y fisica
	//Realiza el movimiento de cuadros    
	public void nextFrame(){
        moveBackground();
        moveEnemy();
        if(touch){
            moveUp();
        }else{
            moveDown();
        }
    }
    private void moveEnemy(){
        enemyX -= 15;
        invalidate();
    }

    private void moveBackground(){
        backGroundX -= 10;
        if((backGroundX) <-background.getWidth()){
            backGroundX = 0;
        }
        invalidate();
    }

    //Control del jugador
    private void moveDown() {
        if(playerY <(h-(h/5)) ) {
            playerY += scrollVertical;
        }
        invalidate();
    }

    private void moveUp() {
        if(playerY >0 ) {
            playerY -= scrollVertical;
        }
        invalidate();
    }

    //Control de operaciones

    public void restart(){
        score=0;
        enemyX =0;
        enemyY1 = 400;
        enemyY2 = 400;
        enemyY3  =400;
        status=true;
    }
    //Como falso pausa y como verdadero reanuda
	public void setState(boolean bool){
		status = bool;
	}

    public void setTouch(boolean bool){
        touch = bool;
    }

    //Agregado por Eduardo
    //Aca llamo el score lo hago en un metodo para no tocar nada de lo que esta operado en el motor
    public  int  scoreFinal(){
        int scoreF;
        scoreF =score;
        return scoreF;
    }


    private boolean colisionPreg() {
        if( ((playerX +playerFigure.getWidth())>= enemyX) && ((playerY+playerFigure.getHeight()) >= enemyY3) && (playerY <= (enemyY3+ enemyFigure1.getHeight()))){
            estateColision = true;
            return true;
        }
        return false;
    }

    public void restartPreg(){
        enemyX=0;
        enemyY1 = 400;
        enemyY2 = 400;
        enemyY3  =400;
        status=true;
        statusPreg=true;
    }

}
