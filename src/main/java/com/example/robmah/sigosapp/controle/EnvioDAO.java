package com.example.robmah.sigosapp.controle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.robmah.sigosapp.modelos.Envio;
import com.example.robmah.sigosapp.modelos.Ocorrencia;

import java.util.Date;

/**
 * Created by Robson on 14/06/2015.
 */
public class EnvioDAO {

    private SQLiteDatabase db;

    public EnvioDAO(SQLiteDatabase db){
        this.db = db;
    }

    public void enviarOcorrencia( Ocorrencia ocorrencia){

        OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO(db);
        ocorrenciaDAO.saveOcorrencia(ocorrencia);

        Envio envio = new Envio();
        envio.setData(new Date());
        envio.setOcorrencia(ocorrencia);

        /* Enviando */
        envio.setStatus(Envio.ENVIANDO);
        /**/

        /* Enviado */
        envio.setStatus(Envio.ENVIADO);

        this.SalvarEnvio(envio);



    }

    private void SalvarEnvio(Envio envio){

        ContentValues values = new ContentValues();
        values.put("DATA", System.currentTimeMillis());
        values.put("STATUS", envio.getStatus());
        values.put("OCORRENCIA", envio.getOcorrencia().getId());

        long id = db.insert("ENVIO", null, values);
        envio.setId( (int)id );

    }


    private Envio getEnvioByOcorrencia(int OcorrenciaID){

        OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO(db);
        Envio envio = new Envio();

        Cursor cursor = db.query("ENVIO", null, "OCORRENCIA = ?", new String[]{String.valueOf(OcorrenciaID)}, null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            envio.setId( cursor.getInt( cursor.getColumnIndex("_id") ) );
            envio.setStatus( cursor.getInt(cursor.getColumnIndex("STATUS") ) );

        }

        return envio;
    }

    public Envio getEnvioByOcorrencia(Ocorrencia ocorrencia) {

        Envio envio = new Envio();

        Cursor cursor = db.query("ENVIO", null, "OCORRENCIA = ?", new String[]{String.valueOf(ocorrencia.getId())}, null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            envio.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            envio.setData(new Date( cursor.getLong(cursor.getColumnIndex("DATA"))) );
            envio.setStatus( cursor.getInt(cursor.getColumnIndex("STATUS") ) );
            envio.setOcorrencia(ocorrencia);
        }

        return envio;
    }
}
