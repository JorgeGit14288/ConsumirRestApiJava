/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.sendMsg;

/**
 *
 * @author intercitydev
 */
public class sendMsgMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.print("Enviare un mesnaje");
       httpSendMsg sendMsg = new httpSendMsg();
       String telefono ="50241109321";
       String mensaje = "Mensaje de prueba consola2 ";
       sendMsg.sendMsg(telefono,mensaje);
       
    }
    
}
