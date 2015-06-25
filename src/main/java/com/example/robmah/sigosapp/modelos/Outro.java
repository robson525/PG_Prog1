package com.example.robmah.sigosapp.modelos;

/**
 * Created by Robson on 24/06/2015.
 */
public class Outro extends Usuario {

    private int _id;
    private String cpf;

    public int getId(){
        return this._id;
    }

    public void setId(int id){
        this._id = id;
    }

    public String getCpf (){
        return this.cpf;
    }

    public void setCpf (String cpf){
        this.cpf = cpf;
    }

    @Override
    public void setIdentificacao(String cpf) {
        this.cpf = cpf;
    }

    public String getIdentificacao(){
        return this.cpf;
    }


}
