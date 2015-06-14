package com.example.robmah.sigosapp.controle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.robmah.sigosapp.modelos.Unidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 13/06/2015.
 */
public class UnidadeDAO {

    private SQLiteDatabase db;

    public UnidadeDAO(SQLiteDatabase db){
        this.db = db;
    }

    public ArrayList<Unidade> getListUnidade(){

        ArrayList<Unidade> list = new ArrayList<Unidade>();
        Cursor cursor = db.query("UNIDADE", null, null, null, null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {

                Unidade unidade = new Unidade();
                unidade.setId( cursor.getInt( cursor.getColumnIndex("_id") ) );
                unidade.setNome( cursor.getString(cursor.getColumnIndex("NOME") ) );
                list.add( unidade );

            }while( cursor.moveToNext() );
        }

        return list;

    }

    public Unidade getUnidadeById(int id){

        Unidade unidade = new Unidade();
        Cursor cursor = db.query("UNIDADE", null, "_id = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            unidade.setId( cursor.getInt( cursor.getColumnIndex("_id") ) );
            unidade.setNome( cursor.getString(cursor.getColumnIndex("NOME") ) );
        }

        return  unidade;

    }

}
