/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.restjson;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import org.json.JSONObject;

/**
 *
 * @author jorge
 */
public class consumir {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Gson gson = new Gson();
        cData data = new cData();
        Account acount = new Account();
        acount.setAddress1("Ciudad");
        acount.setCity("Guatemala");
        acount.setEmail("jorge@mail");
        acount.setLanguaje_id("Spanish");
        acount.setFirst_name("Jorge");
        acount.setLast_name("Fuentes");
        acount.setNotify_flag("today");
        acount.setNotify_email("to mail");
        acount.setPostal_code("");
        data.account = acount;

        String toJson = gson.toJson(data);
        
        System.out.println("\n\n ---------------------CONVERTIR OBJETO A JSON --------------");
        System.out.println(toJson);
        //METODO PARA ATRAPAR UN OBJETO JSON Y CONVERTIRLO A UN OBJETO
        cData data2 = new cData();
        data2 = gson.fromJson(toJson, cData.class);

        System.out.println("De Json a Objeto");
        System.out.println(data.account.getFirst_name() + "  " + data.account.getLast_name() + " " +data.account.getCompany_name());

        System.out.println("\n\n--------------------- EL OBJETO JSON  ARRAY---------------------------\n\n");
        String myURL = "http://192.168.5.44/app_dev.php/cus/getaccount/50241109321.json";
        System.out.println("Requested URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;

        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                urlConn.setReadTimeout(60 * 1000);
            }
            if (urlConn != null && urlConn.getInputStream() != null) {
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
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }

        System.out.println(sb.toString());
        JSONObject jsonObject = new JSONObject(sb.toString());

    }

}
