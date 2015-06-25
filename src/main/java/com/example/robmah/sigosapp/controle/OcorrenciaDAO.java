package com.example.robmah.sigosapp.controle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.robmah.sigosapp.modelos.Ocorrencia;

import java.util.ArrayList;

/**
 * Created by Robson on 14/06/2015.
 */
public class OcorrenciaDAO {

    private SQLiteDatabase db;

    public OcorrenciaDAO(SQLiteDatabase db){
        this.db = db;
    }

    public int getPapel(String papel){

        if(papel.equals( "Vítima" )){
            return 1;
        }else if(papel.equals( "Testemunha" )){
            return 2;
        }else{
            return 0;
        }

    }

    public void saveOcorrencia(Ocorrencia ocorrencia){

        ContentValues values = new ContentValues();
        values.put("DESCRICAO", ocorrencia.getDescricao());
        values.put("TIPO_OCORRENCIA", ocorrencia.getTipo().getId() );
        values.put("PAPEL", ocorrencia.getPapel());
        values.put("SETOR", ocorrencia.getSetor().getId());
        values.put("USUARIO", ocorrencia.getUsuario().getUsuarioId());

        long id = db.insert("OCORRENCIA", null, values);
        ocorrencia.setId( (int)id );

    }

    public ArrayList<Ocorrencia> getListOcorrencias(){

        TipoOcorrenciaDAO tipoOcorrenciaDAO = new TipoOcorrenciaDAO(db);
        SetorDAO setorDAO = new SetorDAO(db);
        UsuarioDAO usuarioDAO = new UsuarioDAO(db);
        ArrayList<Ocorrencia> list = new ArrayList<Ocorrencia>();
        Cursor cursor = db.query("OCORRENCIA", null, null, null, null, null, null, null);


        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {

                Ocorrencia ocorrencia = new Ocorrencia();
                ocorrencia.setId( cursor.getInt( cursor.getColumnIndex("_id") ) );
                ocorrencia.setDescricao(cursor.getString(cursor.getColumnIndex("DESCRICAO")));
                ocorrencia.setPapel(cursor.getInt(cursor.getColumnIndex("PAPEL")));
                ocorrencia.setTipo(  tipoOcorrenciaDAO.getTipoOcorrenciaByID( cursor.getInt(cursor.getColumnIndex("TIPO_OCORRENCIA") ) ));
                ocorrencia.setSetor( setorDAO.getSetorbyId( cursor.getInt( cursor.getColumnIndex("SETOR") ) ) );
                ocorrencia.setUsuario( usuarioDAO.getUsuario() );

                list.add( ocorrencia );

            }while( cursor.moveToNext() );
        }

        return list;

    }

}
