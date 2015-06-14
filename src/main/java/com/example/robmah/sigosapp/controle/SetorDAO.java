package com.example.robmah.sigosapp.controle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.robmah.sigosapp.modelos.Setor;
import com.example.robmah.sigosapp.modelos.Unidade;

import java.util.ArrayList;

/**
 * Created by Robson on 13/06/2015.
 */
public class SetorDAO {

    private SQLiteDatabase db;

    public SetorDAO(SQLiteDatabase db){
        this.db = db;
    }

    public ArrayList<Setor> getListSetor(){

        ArrayList<Setor> list = new ArrayList<Setor>();
        Cursor cursor = db.query("SETOR", null, null, null, null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {

                Setor setor = new Setor();
                setor.setId( cursor.getInt( cursor.getColumnIndex("_id") ) );
                setor.setNome( cursor.getString(cursor.getColumnIndex("NOME") ) );
                list.add( setor );

            }while( cursor.moveToNext() );
        }

        return list;

    }

    public ArrayList<Setor> getListSetorByUnidade(int unidadeID){

        ArrayList<Setor> list = new ArrayList<Setor>();
        Cursor cursor = db.query("SETOR", null, "UNIDADE = ?", new String[]{String.valueOf(unidadeID)}, null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {

                Setor setor = new Setor();
                setor.setId( cursor.getInt( cursor.getColumnIndex("_id") ) );
                setor.setNome( cursor.getString(cursor.getColumnIndex("NOME") ) );
                list.add( setor );

            }while( cursor.moveToNext() );
        }

        return list;
    }


    public Setor getSetorbyId(int id){

        Setor setor = new Setor();
        UnidadeDAO unidadeDAO = new UnidadeDAO(db);
        Cursor cursor = db.query("SETOR", null, "_id = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();


            setor.setId( cursor.getInt( cursor.getColumnIndex("_id") ) );
            setor.setNome( cursor.getString( cursor.getColumnIndex("NOME") ) );
            setor.setUnidade( unidadeDAO.getUnidadeById( cursor.getInt( cursor.getColumnIndex("UNIDADE") ) ) );
        }

        return setor;
    }

}
