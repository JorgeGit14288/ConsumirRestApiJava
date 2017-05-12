/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.getHelp;

/**
 *
 * @author intercitydev
 */
public class mainHelp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        httpHelp helpHelper =  new httpHelp();
        helpHelper.getHelp("es");
    }
    
}
