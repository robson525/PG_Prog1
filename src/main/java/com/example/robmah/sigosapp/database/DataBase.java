package com.example.robmah.sigosapp.database;

/**
 * Created by Robson on 25/05/2015.
 */

import android.content.Context;
import android.database.sqlite.*;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context){
        super(context, "SigosApp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptsSQL.getCreateUnidade());
        db.execSQL(ScriptsSQL.getCreateSetor());
        db.execSQL(ScriptsSQL.getCreateUsuario());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
