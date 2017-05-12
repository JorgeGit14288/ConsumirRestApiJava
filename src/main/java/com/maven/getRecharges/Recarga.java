/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.getRecharges;

/**
 *
 * @author intercitydev
 */
public class Recarga {
    
    
    
    
    
    private String monto;  //credit
    private String saldoAnterior; //after_balance
    private String saldoPosterior; //before_balance
    private String descripcion;  //descripcion
    private String credito;      //credit
    private String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    

    public Recarga() {
    }
    private int No;

    public int getNo() {
        return No;
    }

    public void setNo(int No) {
        this.No = No;
    }
    
    
    

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(String saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public String getSaldoPosterior() {
        return saldoPosterior;
    }

    public void setSaldoPosterior(String saldoPosterior) {
        this.saldoPosterior = saldoPosterior;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }
    
  
}
