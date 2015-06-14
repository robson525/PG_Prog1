/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.robmah.sigosapp.modelos;

/**
 *
 * @author Marina
 */
public class Ocorrencia {

    private int _id;
    private String descricao;
    private String local;
    private int papel;// Vitima, Testemunha
    private Setor setor;
    private TipoOcorrencia tipo;
    private Usuario usuario;
    
    public Ocorrencia(){
        
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getDescricao(){
        return this.descricao;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public String getLocal(){
        return this.local;
    }
    
    public void setLocal(String local){
        this.local = local;
    }

    public int getPapel() {
        return papel;
    }

    public void setPapel(int papel) {
        this.papel = papel;
    }
    
    public Setor getSetor(){
        return this.setor;
    }
    
    public void setSetor(Setor setor){
        this.setor = setor;
    }
    
    public TipoOcorrencia getTipo(){
        return this.tipo;
    }
    
    public void setTipo(TipoOcorrencia tipo){
        this.tipo = tipo;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }


    public String toString(){
        return this.tipo.getNome();
    }

}
