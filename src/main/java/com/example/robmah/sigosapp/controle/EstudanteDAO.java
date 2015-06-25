package com.example.robmah.sigosapp.controle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.robmah.sigosapp.modelos.Estudante;
import com.example.robmah.sigosapp.modelos.Usuario;

/**
 * Created by Robson on 24/06/2015.
 */
public class EstudanteDAO {

    private SQLiteDatabase db;
    private UsuarioDAO usuarioDAO;

    public EstudanteDAO(SQLiteDatabase db){
        this.db = db;
        this.usuarioDAO = new UsuarioDAO(db);
    }

    public Estudante getEstudante() {

        Estudante estudante = new Estudante();
        Cursor cursor = db.query("ESTUDANTE", null, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            estudante.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            estudante.setMatricula(cursor.getString(cursor.getColumnIndex("MATRICULA")));

        }

        usuarioDAO.getUsuario(estudante);

        return estudante;
    }

    public void saveEstudante(Estudante estudante){

        usuarioDAO.saveUsuario(estudante);

        ContentValues values = new ContentValues();
        values.put("MATRICULA", estudante.getMatricula());
        values.put("USUARIO", estudante.getUsuarioId());

        long id = db.insert("ESTUDANTE", null, values);
        estudante.setId((int) id);

    }

}
