package com.example.robmah.sigosapp.controle;

import android.content.ContentValues;
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
        values.put("DATA", "");
        values.put("STATUS", envio.getStatus());
        values.put("OCORRENCIA", envio.getOcorrencia().getId());

        long id = db.insert("ENVIO", null, values);
        envio.setId( (int)id );

    }

}
