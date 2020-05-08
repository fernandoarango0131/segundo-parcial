package com.edu.uac.co.parcial_sqlite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdUsuario extends SQLiteOpenHelper {

    private static final int BD_VERSION = 1;

    public BdUsuario(Context context) {
        super(context, bdDef.BD_NAME, null, BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(bdDef.CREATE_TESTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
