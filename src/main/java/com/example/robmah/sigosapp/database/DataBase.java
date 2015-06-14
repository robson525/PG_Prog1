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
        db.execSQL(ScriptsSQL.getCreateTipoOcorrencia());
        db.execSQL(ScriptsSQL.getCreateOcorrencia());
        db.execSQL(ScriptsSQL.getCreateEnvio());

        /*********************************************/
        String[] Unidade = ScriptsSQL.getDefaultUnidade();
        for(int i=0; i < Unidade.length; i++){
            db.execSQL(Unidade[i]);
        }

        String[] Setor = ScriptsSQL.getDefaultSetor();
        for(int i=0; i < Setor.length; i++){
            db.execSQL(Setor[i]);
        }

        String[] TipoOcorrencia = ScriptsSQL.getDefaultTipoOcorrencia();
        for(int i=0; i < TipoOcorrencia.length; i++){
            db.execSQL(TipoOcorrencia[i]);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
