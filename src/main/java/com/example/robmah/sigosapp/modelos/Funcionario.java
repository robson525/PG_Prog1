package com.example.robmah.sigosapp.modelos;

/**
 * Created by Robson on 24/06/2015.
 */
public class Funcionario extends Usuario {

    private int _id;
    private String ciap;

    public int getId(){
        return this._id;
    }

    public void setId(int id){
        this._id = id;
    }

    public String getCiap(){
        return this.ciap;
    }

    public void setCiap (String ciap){
        this.ciap = ciap;
    }

    public void setIdentificacao(String ciap){
        this.ciap = ciap;
    }
    public String getIdentificacao(){
        return this.ciap;
    }
}

