package com.example.robmah.sigosapp.controle;

import android.database.sqlite.SQLiteDatabase;

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



}
