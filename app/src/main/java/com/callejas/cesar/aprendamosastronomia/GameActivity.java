package com.callejas.cesar.aprendamosastronomia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.eduardo.chavez.dataBase.ScoreModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {
    Engine engine;
    Handler handler;
    Runnable r;
    private int frames = 40;
    Bitmap playerFigure, enemyFigure1, enemyFigure2, preguntaTrigger, back;
    private Integer selectorPreg[];
    private String pregunta[];
    private String respuesta[];
    private String titulo;
    private String resCorrect;
    private ScoreModel scoreModel;
    final Context context = this;
    private static String TIPOJUEGO ="juego1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        engine = (Engine) findViewById(R.id.scene);

        //Declarando personajes
        playerFigure = BitmapFactory.decodeResource(getResources(), R.drawable.nave_ed);
        enemyFigure1 = BitmapFactory.decodeResource(getResources(), R.drawable.meteoro1);
        enemyFigure2 = BitmapFactory.decodeResource(getResources(), R.drawable.meteoro2);
        preguntaTrigger = BitmapFactory.decodeResource(getResources(), R.drawable.pregunta_trigger);
        back = BitmapFactory.decodeResource(getResources(), R.drawable.starfield);

        //Asignacion de los drawables
        engine.setPlayerFigure(playerFigure);
        engine.setEnemyFigure1(enemyFigure1);
        engine.setEnemyFigure2(enemyFigure2);
        engine.setEnemyFigure3(preguntaTrigger);
        engine.setBackground(back);

        scoreModel = new ScoreModel(this);

        startEngine();
    }

    protected void startEngine(){
        handler = new Handler();
        r = new Runnable() {
            public void run() {
                if(engine.status == true&&engine.statusPreg==true)
                {
                    engine.nextFrame();
                    handler.postDelayed(this, (1000/frames));

                }
                else if(engine.status==false&&engine.statusPreg==false)
                {
                    gameOver();
                }
                else if (engine.status==true&&engine.statusPreg==false)
                {
                    preguntaSel();
                }
                else {
                    gameOver();
                }
            }
        };
        handler.postDelayed(r,500);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                engine.setTouch(true);
                return true;
            case (MotionEvent.ACTION_UP):
                engine.setTouch(false);
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        engine.restart();
    }


    public void preguntaSel(){
        //SELECCIONAR PREGUNTAS
        selectorPreg = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        pregunta = getResources().getStringArray(R.array.preguntas);
        ArrayList<Integer> selectorArray = new ArrayList<Integer>(Arrays.asList(selectorPreg));
        Collections.shuffle(selectorArray);

        int preg = selectorArray.get(0);
        switch(preg){
            case 1:
                respuesta = getResources().getStringArray(R.array.respuestas1);
                resCorrect = getResources().getString(R.string.preg1Corr);

                break;

            case 2:
                respuesta = getResources().getStringArray(R.array.respuestas2);
                resCorrect = getResources().getString(R.string.preg2Corr);
                break;

            case 3:
                respuesta = getResources().getStringArray(R.array.respuestas3);
                resCorrect = getResources().getString(R.string.preg3Corr);
                break;

            case 4:
                respuesta = getResources().getStringArray(R.array.respuestas4);
                resCorrect = getResources().getString(R.string.preg4Corr);
                break;

            case 5:
                respuesta = getResources().getStringArray(R.array.respuestas5);
                resCorrect = getResources().getString(R.string.preg5Corr);
                break;

            case 6:
                respuesta = getResources().getStringArray(R.array.respuestas6);
                resCorrect = getResources().getString(R.string.preg6Corr);
                break;

            case 7:
                respuesta = getResources().getStringArray(R.array.respuestas7);
                resCorrect = getResources().getString(R.string.preg7Corr);
                break;

            case 8:
                respuesta = getResources().getStringArray(R.array.respuestas8);
                resCorrect = getResources().getString(R.string.preg8Corr);
                break;

            case 9:
                respuesta = getResources().getStringArray(R.array.respuestas9);
                resCorrect = getResources().getString(R.string.preg9Corr);
                break;

            case 10:
                respuesta = getResources().getStringArray(R.array.respuestas10);
                resCorrect = getResources().getString(R.string.preg10Corr);
                break;
        }
        titulo = pregunta[preg-1];

        ArrayList<String> respuestas = new ArrayList<String>(Arrays.asList(respuesta));
        Collections.shuffle(respuestas);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GameActivity.this,
                android.R.layout.select_dialog_item,respuestas);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(GameActivity.this);
        alertDialog.setTitle(titulo);
        alertDialog.setCancelable(false);
        alertDialog.setAdapter(arrayAdapter,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                if (strName.equals(resCorrect)){
                    engine.score +=100;
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(GameActivity.this);
                    builderInner.setTitle(R.string.resYes);
                    builderInner.setCancelable(false);
                    builderInner.setMessage("100 puntos agregados");
                    builderInner.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,int which) {
                            engine.restartPreg();
                            handler.postDelayed(r,500);
                            dialog.dismiss();
                        }
                    });
                    builderInner.show();

                } else {
                AlertDialog.Builder builderInner = new AlertDialog.Builder(
                        GameActivity.this);
                builderInner.setTitle(R.string.resNo);
                builderInner.setCancelable(false);
                builderInner.setMessage("Puede continuar jugando");
                builderInner.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        engine.restartPreg();
                        handler.postDelayed(r,500);
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
            }
        });
        alertDialog.show();
    }





    public void gameOver(){
        //ACA PIDO INPUT AL USUARIO
        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.dialog,null);

        AlertDialog.Builder alertDialog= new AlertDialog.Builder(context);
        alertDialog.setView(dialogView);
        final EditText userInput = (EditText) dialogView.findViewById(R.id.editTextDialogUserInput);
        alertDialog.setTitle(R.string.scoreTitle)
                .setCancelable(false)
                .setMessage("Tu puntaje es: " +String.valueOf(engine.scoreFinal()))
                .setPositiveButton("Agregar puntaje", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombre = userInput.getText().toString();
                        int puntaje = engine.scoreFinal();
                        //AGREGANDO DATA A LA BASE DE DATOS
                        scoreModel.insert(TIPOJUEGO,nombre,puntaje);
                        scoreModel.db.close();
                        Toast.makeText(context,"Puntaje agregado",Toast.LENGTH_LONG).show();
                        //Reiniciando
                        engine.restart();
                        handler.postDelayed(r,500);
                    }
                })
                .setNegativeButton("No agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        engine.restart();
                        handler.postDelayed(r,500);
                    }
                })
                .show();

    }

}
