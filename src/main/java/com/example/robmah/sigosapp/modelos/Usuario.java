/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.robmah.sigosapp.modelos;

import android.database.sqlite.SQLiteDatabase;

/**
 *
 * @author Marina
 */
public abstract class Usuario {

    private int _id;
    private String nome;
    private String email;
    private String telefone;
    private Setor setor;
    
    public Usuario (){
        
    }

    public int getUsuarioId() {
        return _id;
    }

    public void setUsuarioId(int _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public abstract void setIdentificacao(String identificacao);

    public abstract void Salvar(SQLiteDatabase db);

}
