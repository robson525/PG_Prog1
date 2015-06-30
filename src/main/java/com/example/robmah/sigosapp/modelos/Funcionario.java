package com.example.robmah.sigosapp.modelos;

import android.database.sqlite.SQLiteDatabase;

import com.example.robmah.sigosapp.controle.FuncionarioDAO;

/**
 * Created by Robson on 24/06/2015.
 */
public class Funcionario extends Usuario {

    private int _id;
    private String siape;

    public int getId(){
        return this._id;
    }

    public void setId(int id){
        this._id = id;
    }

    public String getSiape(){
        return this.siape;
    }

    public void setSiape(String siape){
        this.siape = siape;
    }

    @Override
    public void setIdentificacao(String ciap){
        this.siape = ciap;
    }

    @Override
    public String getIdentificacao(){
        return this.siape;
    }

    @Override
    public void Salvar(SQLiteDatabase db) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(db);
        funcionarioDAO.saveFuncionario(this);

    }
}

