/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.getRecharges;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author intercitydev
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        
        int mes = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        mes = mes+1;
        String fechaActual= year+"-"+mes+"-"+dia+" 23:59:00";
        System.out.println("Fecha acual "+fechaActual);
        
        int mesAnterior;
        int yearAnterior;
        
        if (mes==1)
        {
            mesAnterior =12;
            yearAnterior = year-1;
        }
        else
        {
           mesAnterior = mes -1; 
           yearAnterior = year;
        }
        
        String fechaAnterior = yearAnterior +"-"+mesAnterior+"-"+dia+" 00:00:00";
        System.out.println("La fecha de un mes anterior es "+fechaAnterior);
        
        
        

        
     

        String idAccount = "29";
        String page = "1";
        String max = "10";
      // String StartDate = "2016-09-11 00:00:00";
       //String endDate = "2016-11-30 23:59:59";

        String StartDate = fechaAnterior;
        String endDate = fechaActual;
        RechargeAccount recharge = new RechargeAccount();
        recharge.getRecargas(idAccount, page, max, StartDate, endDate);
    }

}
