package com.example.robmah.sigosapp.controle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.robmah.sigosapp.modelos.Usuario;

/**
 * Created by Robson on 13/06/2015.
 */
public class UsuarioDAO {


    private SQLiteDatabase db;

    public UsuarioDAO(SQLiteDatabase db){
        this.db = db;
    }


    public void getUsuario(Usuario usuario){


        SetorDAO setorDAO = new SetorDAO(db);
        Cursor cursor = db.query("USUARIO", null, null, null, null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            usuario.setUsuarioId(cursor.getInt(cursor.getColumnIndex("_id")));
            usuario.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
            usuario.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));
            usuario.setSetor( setorDAO.getSetorbyId( cursor.getInt(cursor.getColumnIndex("SETOR") ) ));

        }

    }

    public boolean UsuarioRegistrado(){
        Cursor cursor = db.query("USUARIO", null, null, null, null, null, null, null);
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void saveUsuario(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("NOME", usuario.getNome());
        values.put("TELEFONE", usuario.getTelefone());
        values.put("EMAIL", usuario.getEmail());
        values.put("SETOR", usuario.getSetor().getId() );

        long id = db.insert("USUARIO", null, values);
        usuario.setUsuarioId((int) id);

    }


    public static int getTipoByString(String tipo){
        if(TextUtils.equals(tipo, "Estudante")){
            return 1;
        }else if(TextUtils.equals(tipo, "Funcionario")){
            return 2;
        }else if(TextUtils.equals(tipo, "Outro")){
            return 3;
        }else{
            return 1;
        }
    }

}
