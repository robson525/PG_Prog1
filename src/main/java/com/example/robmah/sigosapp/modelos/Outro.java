package com.example.robmah.sigosapp.modelos;

/**
 * Created by Robson on 24/06/2015.
 */
public class Outro extends Usuario {

    private String cpf;

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
