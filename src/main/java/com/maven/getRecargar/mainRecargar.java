/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.getRecargar;

/**
 *
 * @author intercitydev
 */
public class mainRecargar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        
        
        httpRecargar recargarHelper = new httpRecargar();
        String amount ="25";
        String returnUrl ="recargas.htm";
        String details="Se ha realizado la recarga de saldo";
        String accountid = "29";
        
      // String resultado  = recargarHelper.getRecargas(accountid, amount, returnUrl,details);  
      ResultadoRecarga res = new ResultadoRecarga();
      res =recargarHelper.getResultadoRecarga("721ed4462ca418791f635e90b67f4967");
      System.out.println("Los datos de la recarga son "+ res.getAmount() + " "+ res.getDetails());
        
    }
    
}
