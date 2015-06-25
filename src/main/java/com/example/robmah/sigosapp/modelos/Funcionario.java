package com.example.robmah.sigosapp.modelos;

import android.database.sqlite.SQLiteDatabase;

import com.example.robmah.sigosapp.controle.FuncionarioDAO;

/**
 * Created by Robson on 24/06/2015.
 */
public class Funcionario extends Usuario {

    private int _id;
    private String siap;

    public int getId(){
        return this._id;
    }

    public void setId(int id){
        this._id = id;
    }

    public String getSiap(){
        return this.siap;
    }

    public void setSiap(String siap){
        this.siap = siap;
    }

    public void setIdentificacao(String ciap){
        this.siap = ciap;
    }

   public String getIdentificacao(){
        return this.siap;
    }


    @Override
    public void Salvar(SQLiteDatabase db) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(db);
        funcionarioDAO.saveFuncionario(this);

    }
}

