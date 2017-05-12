/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.getHistorial;

import com.maven.getHistorial.HistorialAccount;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author jorge
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        String idAccount = "29";
        String page = "1";
        String max = "10";
        String StartDate = "2016-09-20 00:00:00";
        String endDate = "2016-10-30 23:59:59";
        String destination = "";

        HistorialAccount historialHelper = new HistorialAccount();
        historialHelper.getHistorial(idAccount, page, max, StartDate, endDate, destination);

    }

}
