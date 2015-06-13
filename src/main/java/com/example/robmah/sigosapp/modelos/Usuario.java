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
    
    private String nome;
    private String telefone;
    private String email;
    private String estado;
    private String minicipio;
    private String unidade;
    private int tipo;
    
    public Usuario (){
        
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getTelefone(){
        return this.telefone;        
    }
    
    public void setTelefone (String telefone){
        this.telefone = telefone;
    }
    
    public String getEmail (){
        return this.email;
    }
    
    public void setEmail (String email){
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMinicipio() {
        return minicipio;
    }

    public void setMinicipio(String minicipio) {
        this.minicipio = minicipio;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
    
}
