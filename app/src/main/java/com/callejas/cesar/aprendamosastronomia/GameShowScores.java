package com.callejas.cesar.aprendamosastronomia;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.eduardo.chavez.dataBase.DataBaseContract;
import com.eduardo.chavez.dataBase.ScoreModel;

import java.util.ArrayList;

public class GameShowScores extends AppCompatActivity {
    private ScoreModel scoreModel;
    private ListView scoreListJuego1,scoreListJuego2,scoreListJuego3,scoreListJuego4;
    private TextView tituloScore1,tituloScore2,tituloScore3,tituloScore4;
    private ArrayList<String> itemList1 = new ArrayList<>();
    private ArrayList<String> itemList2 = new ArrayList<>();
    private ArrayList<String> itemList3 = new ArrayList<>();
    private ArrayList<String> itemList4 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_show_scores);

        tituloScore1 = (TextView) findViewById(R.id.scoreJuego1);
        tituloScore2 = (TextView) findViewById(R.id.scoreJuego2);
        tituloScore3 = (TextView) findViewById(R.id.scoreJuego3);
        tituloScore4 = (TextView) findViewById(R.id.scoreJuego4);

        scoreListJuego1 = (ListView)findViewById(R.id.listaScoreJuego1);
        scoreListJuego2 = (ListView)findViewById(R.id.listaScoreJuego2);
        scoreListJuego3 = (ListView)findViewById(R.id.listaScoreJuego3);
        scoreListJuego4 = (ListView) findViewById(R.id.listaScoreJuego4);

        scoreModel = new ScoreModel(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList1.clear();
        itemList2.clear();
        itemList3.clear();
        itemList4.clear();

        Cursor c = scoreModel.listAll();
        c.moveToFirst();

        while (c.isAfterLast()==false){
            //String id = c.getString(c.getColumnIndex(DataBaseContract._ID));

            String tipoJuego = c.getString(c.getColumnIndex(DataBaseContract.COLUMN_NAME_GAMETYPE));

            switch (tipoJuego){
                case "juego1":
                    tituloScore1.setText("Aventura Espacial");
                    String item = c.getString(c.getColumnIndex(DataBaseContract.COLUMN_NAME_PLAYERNAME))+"   Puntaje: ";
                    item += c.getString(c.getColumnIndex(DataBaseContract.COLUMN_NAME_SCORE));
                    itemList1.add(item);

                    break;

                case "juego2":
                    tituloScore2.setText(R.string.juego2);
                    String item2 = c.getString(c.getColumnIndex(DataBaseContract.COLUMN_NAME_PLAYERNAME))+"     Puntaje: ";
                    item2 += c.getString(c.getColumnIndex(DataBaseContract.COLUMN_NAME_SCORE));
                    itemList2.add(item2);
                    break;

                case  "juego3":
                    tituloScore3.setText(R.string.juego1);
                    String item3 = c.getString(c.getColumnIndex(DataBaseContract.COLUMN_NAME_PLAYERNAME))+"      Puntaje: ";
                    item3 += c.getString(c.getColumnIndex(DataBaseContract.COLUMN_NAME_SCORE));
                    itemList3.add(item3);
                    break;

                case  "juego4":
                    tituloScore4.setText(R.string.juego3);
                    String item4 =c.getString(c.getColumnIndex(DataBaseContract.COLUMN_NAME_PLAYERNAME))+"     Puntaje: ";
                    item4 += c.getString(c.getColumnIndex(DataBaseContract.COLUMN_NAME_SCORE));
                    itemList4.add(item4);
                    break;
            }

            c.moveToNext();
        }
        scoreModel.db.close();

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itemList1);
        scoreListJuego1.setAdapter(arrayAdapter1);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itemList2);
        scoreListJuego2.setAdapter(arrayAdapter2);

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itemList3);
        scoreListJuego3.setAdapter(arrayAdapter3);

        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itemList4);
        scoreListJuego4.setAdapter(arrayAdapter4);
    }
}
