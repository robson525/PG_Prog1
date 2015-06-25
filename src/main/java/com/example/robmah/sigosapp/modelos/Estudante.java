package com.example.robmah.sigosapp.modelos;

/**
 * Created by Robson on 24/06/2015.
 */
public class Estudante extends Usuario{

    private int _id;
    private String matricula;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getMatricula(){
        return this.matricula;
    }

    public void setMatricula (String matricula){
        this.matricula = matricula;
    }

    public void setIdentificacao(String matricula){
        this.matricula = matricula;
    }
    public String getIdentificacao(){
        return this.matricula;
    }
}
