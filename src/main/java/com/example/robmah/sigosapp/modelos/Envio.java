/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.robmah.sigosapp.modelos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marina
 */
public class Envio {

    private int _id;
    private int status;// 0=criado; 1=Enviando; 2=Enviado; 3=Erro
    private Date data;
    private Ocorrencia ocorrencia;

    public static final int CRIADO = 0;
    public static final int ENVIANDO = 1;
    public static final int ENVIADO = 2;
    public static final int ERRO = 3;

    public Envio(){
        this.status = this.CRIADO;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getDataFormatada(){

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return dateFormat.format(this.data);

   }

    public String getHoraFormatada(){

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        return dateFormat.format(this.data);

    }

}

