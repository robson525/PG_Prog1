package com.example.robmah.sigosapp.controle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.robmah.sigosapp.modelos.Funcionario;

/**
 * Created by Robson on 24/06/2015.
 */
public class FuncionarioDAO {

    private SQLiteDatabase db;
    private UsuarioDAO usuarioDAO;

    public FuncionarioDAO(SQLiteDatabase db){
        this.db = db;
        this.usuarioDAO = new UsuarioDAO(db);
    }

    public Funcionario getFuncionario() {

        Funcionario funcionario = new Funcionario();
        Cursor cursor = db.query("FUNCIONARIO", null, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            funcionario.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            funcionario.setSiape(cursor.getString(cursor.getColumnIndex("SIAPE")));
        }

        usuarioDAO.getUsuario(funcionario);

        return funcionario;
    }

    public void saveFuncionario(Funcionario funcionario){

        usuarioDAO.saveUsuario(funcionario);

        ContentValues values = new ContentValues();
        values.put("SIAPE", funcionario.getSiape());
        values.put("USUARIO", funcionario.getUsuarioId());

        long id = db.insert("FUNCIONARIO", null, values);
        funcionario.setId((int) id);

    }

}
