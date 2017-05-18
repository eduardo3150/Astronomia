package com.eduardo.chavez.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Eduardo_Chavez on 14/11/2016.
 */

public class ScoreModel {
    Context context;

    public ScoreDbHelper scoreDbHelper;
    public SQLiteDatabase db;

    public ScoreModel(Context context){
        this.context=context;
        scoreDbHelper = new ScoreDbHelper(context);
        db = scoreDbHelper.getWritableDatabase();
    }

    public long insert(String juegoTipo, String nombreUsuario, int score){
        ContentValues values = new ContentValues();

        //Valores de los campos, columna y valor
        values.put(DataBaseContract.COLUMN_NAME_GAMETYPE,juegoTipo);
        values.put(DataBaseContract.COLUMN_NAME_PLAYERNAME,nombreUsuario);
        values.put(DataBaseContract.COLUMN_NAME_SCORE,score);

        //Insertar
        long newRowId = db.insert(DataBaseContract.TABLE_NAME,null,values);
        return newRowId;

    }

    //LISTAR TO DO
    public Cursor listAll(){
        db = scoreDbHelper.getReadableDatabase();
        //Especificamos lo que queremos
        String [] projection = {
                DataBaseContract._ID,
                DataBaseContract.COLUMN_NAME_GAMETYPE,
                DataBaseContract.COLUMN_NAME_PLAYERNAME,
                DataBaseContract.COLUMN_NAME_SCORE
        };

        //Consultamos
        Cursor c = db.query(DataBaseContract.TABLE_NAME,projection,null,null,null,null,null,null
        );

        return c;
    }


    //Listar con filtro
    public Cursor listFilter(String filtro){
        db = scoreDbHelper.getReadableDatabase();

        String [] projection = {
                DataBaseContract._ID,
                DataBaseContract.COLUMN_NAME_GAMETYPE,
                DataBaseContract.COLUMN_NAME_PLAYERNAME,
                DataBaseContract.COLUMN_NAME_SCORE
        };

        //Condicion de filtro
        String seleccion = DataBaseContract.COLUMN_NAME_GAMETYPE + " like = %?%";
        String [] seleccionArgs = {filtro};

        //Ordenar descendente
        String sortOrder = DataBaseContract.COLUMN_NAME_SCORE + " DESC";

        //Consultar
        Cursor c = db.query(DataBaseContract.TABLE_NAME,projection,seleccion,seleccionArgs,null,null,sortOrder);


        return c;
    }



    public void delete(long id){
        String selection = DataBaseContract._ID + " =?";
        String[] selectionArgs = {
                String.valueOf(id)
        };
        db.delete(DataBaseContract.TABLE_NAME,selection,selectionArgs);
    }

    public void update(long id, String juegoTipo, String nombreUsuario, int score) {
        ContentValues values = new ContentValues();

        values.put(DataBaseContract.COLUMN_NAME_GAMETYPE,juegoTipo);
        values.put(DataBaseContract.COLUMN_NAME_PLAYERNAME,nombreUsuario);
        values.put(DataBaseContract.COLUMN_NAME_SCORE,score);

        String selection = DataBaseContract._ID + " =?";
        String [] selectionArgs = {
                String.valueOf(id)
        };

        db.update(DataBaseContract.TABLE_NAME,values,selection,selectionArgs);
    }
}
