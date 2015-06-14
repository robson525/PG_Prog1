/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.robmah.sigosapp.modelos;

/**
 *
 * @author Robson
 */
public class Unidade {
    private int _id;
    private String nome;

    public Unidade() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    /*  */
    public String toString(){
        return this.nome;
    }

}
