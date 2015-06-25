package com.example.robmah.sigosapp.controle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.robmah.sigosapp.modelos.Outro;

/**
 * Created by Marina on 24/06/2015.
 */
public class OutroDAO {

    private SQLiteDatabase db;
    private UsuarioDAO usuarioDAO;

    public OutroDAO(SQLiteDatabase db){
        this.db = db;
        this.usuarioDAO = new UsuarioDAO(db);
    }

    public Outro getOutro() {

        Outro outro = new Outro();
        Cursor cursor = db.query("OUTRO", null, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            outro.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            outro.setCpf(cursor.getString(cursor.getColumnIndex("CPF")));
        }

        usuarioDAO.getUsuario(outro);

        return outro;
    }

    public void saveOutro(Outro outro){

        usuarioDAO.saveUsuario(outro);

        ContentValues values = new ContentValues();
        values.put("CPF", outro.getCpf());
        values.put("USUARIO", outro.getUsuarioId());

        long id = db.insert("OUTRO", null, values);
        outro.setId((int) id);

    }


}
