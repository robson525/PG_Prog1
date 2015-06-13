/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.robmah.sigosapp.modelos;

import java.util.Date;

/**
 *
 * @author Marina
 */
public class Envio {
    
    private Date data;
    private Ocorrencia ocorrencia;
    
    public Envio(){
        
    }
    
    public Date getData(){
        return this.data;
    }
    
    public void setData(Date data){
        this.data = data;
    }
    
    public Ocorrencia getOcorrencia(){
        return this.ocorrencia;
    }
    
    public void setOcorrencia(Ocorrencia ocorrencia){
        this.ocorrencia = ocorrencia;
    }
}

