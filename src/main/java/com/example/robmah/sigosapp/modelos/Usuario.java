/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.robmah.sigosapp.modelos;

/**
 *
 * @author Marina
 */
public class Usuario {  

    private int _id;
    private String nome;
    private String email;
    private String telefone;
    private int tipo;
    private String outroTipo;
    private String identificacao;
    private Setor setor;
    
    public Usuario (){
        
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getOutroTipo() {
        return outroTipo;
    }

    public void setOutroTipo(String outroTipo) {
        this.outroTipo = outroTipo;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
