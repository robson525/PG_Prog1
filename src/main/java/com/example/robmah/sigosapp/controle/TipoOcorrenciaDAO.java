package com.example.robmah.sigosapp.controle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.robmah.sigosapp.modelos.TipoOcorrencia;

import java.util.ArrayList;

/**
 * Created by Robson on 14/06/2015.
 */
public class TipoOcorrenciaDAO {

    private SQLiteDatabase db;

    public TipoOcorrenciaDAO(SQLiteDatabase db){
        this.db = db;
    }


    public ArrayList<TipoOcorrencia> getListTipoOcorrencias(){

        ArrayList<TipoOcorrencia> list = new ArrayList<TipoOcorrencia>();
        Cursor cursor = db.query("OCORRENCIA_TIPO", null, null, null, null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {

                TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
                tipoOcorrencia.setId( cursor.getInt( cursor.getColumnIndex("_id") ) );
                tipoOcorrencia.setNome( cursor.getString(cursor.getColumnIndex("NOME") ) );
                list.add( tipoOcorrencia );

            }while( cursor.moveToNext() );
        }

        return list;

    }

    public TipoOcorrencia getTipoOcorrenciaByID(int id){

        TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
        Cursor cursor = db.query("OCORRENCIA_TIPO", null, "_id = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            tipoOcorrencia.setId( cursor.getInt( cursor.getColumnIndex("_id") ) );
            tipoOcorrencia.setNome( cursor.getString( cursor.getColumnIndex("NOME") ) );
        }

        return tipoOcorrencia;

    }


}
