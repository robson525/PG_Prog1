/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.robmah.sigosapp.modelos;

/**
 *
 * @author Marina
 */
public class Setor {

    private int _id;
    private String nome;
    private Unidade unidade;
    
    public Setor (){
        
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String toString(){
        return this.nome;
    }
}

    