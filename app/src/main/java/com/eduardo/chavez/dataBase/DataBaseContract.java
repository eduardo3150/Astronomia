package com.eduardo.chavez.dataBase;

import android.provider.BaseColumns;

/**
 * Created by Eduardo_Chavez on 14/11/2016.
 */

public class DataBaseContract implements BaseColumns {
    public static final String TABLE_NAME = "GAME_SCORES";
    public static final String _ID = "ID";
    public static final String COLUMN_NAME_GAMETYPE = "GAMETYPE";
    public static final String COLUMN_NAME_PLAYERNAME = "PLAYERNAME";
    public static final String COLUMN_NAME_SCORE = "SCORE";

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+
            TABLE_NAME + " ( "+
            _ID + " INTEGER PRIMARY KEY,"+
            COLUMN_NAME_GAMETYPE + " TEXT, "+
            COLUMN_NAME_PLAYERNAME + " TEXT, "+
            COLUMN_NAME_SCORE + " REAL ) ";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+TABLE_NAME;



}
