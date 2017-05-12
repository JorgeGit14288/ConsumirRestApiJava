/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.sendMsg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import org.json.JSONObject;
import com.maven.getRecargar.ResultadoRecarga;

/**
 *
 * @author intercitydev
 */
public class httpSendMsg {

    private String getHttpString(String destination, String message) {
        String resultado = null;

        String myURL = "http://192.168.5.44/app_dev.php/fin/send/sms.json";

        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                System.out.println("Se Abrio la conexion");
                urlConn.setReadTimeout(60 * 1000);
                urlConn.setDoOutput(true);
                String data = URLEncoder.encode("destination", "UTF-8") + "=" + URLEncoder.encode(destination, "UTF-8");
                data += "&" + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
                System.out.println("los Datos a enviar por POST son " + data);

                try ( //obtenemos el flujo de escritura
                        OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream())) {
                    //escribimos
                    wr.write(data);
                    wr.flush();
//cerramos la conexiÃ³n
                }
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
            resultado = sb.toString();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }
        System.out.println("DATOS ENVIADOS DEL SERVIDOR " + sb.toString());

        return resultado;
    }

    public String sendMsg(String destination, String message)  {

        String resultado = this.getHttpString(destination, message) ;
        String Respuesta=null;
        String code=null;
        try {

            if (resultado != null) {

                String jsonResult = resultado;
                

                // System.out.println("\n\n--------------------OBTENEMOS OBJETO JSON NATIVO DE LA PAGINA, USAMOS EL ARRAY DATA---------------------------\n\n");
                JSONObject objJason = new JSONObject(jsonResult);
                // JSONArray dataJson = new JSONArray();
                //  dataJson = objJason.getJSONArray("data");
                String data = objJason.optString("data");
                String mensaje = objJason.optString("message");
                code = objJason.optString("code");
                System.out.println("\n MENSAJE DEL SERVIDOR " + mensaje);
                System.out.println("\n CODIGO DE RESPUESTA " + code);
                
                if (code.compareTo("200")==0)
                {
                    Respuesta = mensaje;
                    
                }
                else
                {
                    Respuesta = "Ha ocurrido un error, no se envio el mensaje, y el servidor respondio "+code;
                }

            } else {
                System.out.println("El servidor de las recargas no respondio correctamenteo");
            }
        } catch (Exception e) {
            System.out.println("No se pudo traducir la respuesta del servidor");
            e.printStackTrace();
        }
        System.out.println("La Respuesta final del servidor es "+ Respuesta);
        return Respuesta;
    }

    // para confirmar el resultado de la recarga
    private String getHttpStringResultado(String token) {
        String resultado = null;

        String myURL = "http://192.168.5.44/app_dev.php/fin/payment/get";

        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                System.out.println("Se Abrio la conexion");
                urlConn.setReadTimeout(60 * 1000);
                urlConn.setDoOutput(true);
                String data = URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8");

                System.out.println("los Datos a enviar por POST son " + data);

                try ( //obtenemos el flujo de escritura
                        OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream())) {
                    //escribimos
                    wr.write(data);
                    wr.flush();
//cerramos la conexiÃ³n
                }
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
            resultado = sb.toString();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }
        System.out.println("DATOS ENVIADOS DEL SERVIDOR " + sb.toString());

        return resultado;
    }

    public ResultadoRecarga getResultadoRecarga(String token) {
        
        ResultadoRecarga objResultado = new ResultadoRecarga();

        String resultado = this.getHttpStringResultado(token);
       String resultado2 = "{\n"
                + "    \"code\": 200,\n"
                + "    \"message\": \"El pago existe.\",\n"
                + "    \"data\": {\n"
                + "        \"token\": \"721ed4462ca418791f635e90b67f4967\",\n"
                + "        \"code\": 317675,\n"
                + "        \"amount\": 10,\n"
                + "        \"rs_code\": \"PG1003\",\n"
                + "        \"rs_datetime\": \"2016-11-25T01:56:11-0600\",\n"
                + "        \"rs_date_transf\": \"2016-11-25T01:54:00-0600\",\n"
                + "        \"rs_message\": \"Transaction status.\",\n"
                + "        \"rs_reference\": \"F19B2608\",\n"
                + "        \"rs_status\": \"COMPLETED\",\n"
                + "        \"creation_date\": \"2016-11-25T00:54:02-0600\",\n"
                + "        \"processed_date\": \"2016-11-25T00:57:33-0600\",\n"
                + "        \"return_url\": \"http://reseller.intercitytm.com/app_dev.php/payments/complete\",\n"
                + "        \"account_id\": 22,\n"
                + "        \"details\": \"Compra de credito, tienda.\",\n"
                + "        \"id\": 11\n"
                + "    }\n"
                + "}";
        String url = null;

        try {

            if (resultado != null) {

                String jsonResult = resultado;

                // System.out.println("\n\n--------------------OBTENEMOS OBJETO JSON NATIVO DE LA PAGINA, USAMOS EL ARRAY DATA---------------------------\n\n");
                JSONObject objJason = new JSONObject(jsonResult);
                // JSONArray dataJson = new JSONArray();
                //  dataJson = objJason.getJSONArray("data");
                System.out.println("El resultado del servidor es " + objJason);
                String data = objJason.optString("data");
                String mensaje = objJason.optString("message");
                String code = objJason.optString("code");
                System.out.println("\n MENSAJE DEL SERVIDOR " + mensaje);
                System.out.println("\n CODIGO DE RESPUESTA " + code);
                System.out.println(" el objeto jdata es " + data);
                
                 objResultado.setMessageServer(mensaje);
                 objResultado.setCodeServer(code);

                objJason = new JSONObject(data);
                System.out.println("el nuevo objeto json es " + objJason);

                
                objResultado.setAccount_id(objJason.optString("account_id"));
                objResultado.setToken(objJason.optString("token"));
                objResultado.setCode(objJason.optString("code"));
                objResultado.setAmount(objJason.optString("amount"));
                objResultado.setRs_code(objJason.optString("rs_code"));
                objResultado.setRs_datetime(objJason.optString("rs_datetime"));
                objResultado.setRs_datetransf(objJason.optString("rs_date_transf"));
                objResultado.setRs_message(objJason.optString("rs_message"));
                objResultado.setRs_reference(objJason.optString("rs_reference"));
                objResultado.setRs_status(objJason.optString("rs_status"));
                objResultado.setCreation_date(objJason.optString("creation_date"));
                objResultado.setProcessed_date(objJason.optString("processed_date"));
                objResultado.setReturn_url(objJason.optString("return_url"));
                objResultado.setId(objJason.optString("id"));
                objResultado.setDetails(objJason.optString("details"));
                
                //  url = data;
            } else {
                System.out.println("El servidor de las recargas no respondio correctamenteo");
            }
        } catch (Exception e) {
            System.out.println("No se pudo traducir la respuesta del servidor");
            e.printStackTrace();
        }
        System.out.println("La Respuesta final del servidor es " + url);
        return objResultado;
    }
}
