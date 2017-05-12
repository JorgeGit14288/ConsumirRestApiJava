/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.getHelp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 *
 * @author intercitydev
 */
public class httpHelp {

    public String getHelp(String lenguaje) {
        String resultado = null;

        
        System.out.println("OBTENER SOLO UN ARRAY DE CADENA JSON");
        //String myURL = "http://192.168.5.44/app_dev.php/cus/getaccount/50241109321.json";
        String myURL = "http://192.168.5.44/app_dev.php/public/howto/" +lenguaje;
        System.out.println("Requested URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;

        try {

            URL url = new URL(myURL);
            urlConn = url.openConnection();

            if (urlConn != null) {

                urlConn.setReadTimeout(5 * 1000);
                if (urlConn.getInputStream() != null) {

                    in = new InputStreamReader(urlConn.getInputStream(),
                            Charset.defaultCharset());

                    BufferedReader bufferedReader = new BufferedReader(in);
                    if (bufferedReader != null) {
                        int cp;
                        while ((cp = bufferedReader.read()) != -1) {
                            sb.append((char) cp);
                        }
                        bufferedReader.close();
                    }
                } else {
                    System.out.print("no se logor leer en inpustream");
                }
                resultado = sb.toString();

            } else {
                System.out.print("no se pudo conectar con el servidor");

            }

        } catch (Exception e) {
            // e.printStackTrace();
            //System.out.println("SERVIDOR NO RESPONDE");

        }
        System.out.println("se ha devuelto algo del servidor" + resultado);
        return resultado;

    }

}
