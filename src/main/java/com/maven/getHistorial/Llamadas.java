/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.getHistorial;

/**
 *
 * @author jorge
 */
public class Llamadas {

    public Llamadas() {
    }

    private int no;
    private String callstart;
    private String callednum;
    private String notes;
    private String billseconds;
    private String minutes;
    private String cost;
    private String rate_cost;

    public String getRate_cost() {
        return rate_cost;
    }

    public void setRate_cost(String rate_cost) {
        this.rate_cost = rate_cost;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getCallstart() {
        return callstart;
    }

    public void setCallstart(String callstart) {
        this.callstart = callstart;
    }

    public String getCallednum() {
        return callednum;
    }

    public void setCallednum(String callednum) {
        this.callednum = callednum;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBillseconds() {
        return billseconds;
    }

    public void setBillseconds(String billseconds) {
        this.billseconds = billseconds;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

}
