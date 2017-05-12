/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.dids;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author intercitydev
 */
public class httpDids {
    
    public String getHttpString(String state, String area) {
        String resultado = null;
        System.out.println("OBTENER SOLO UN ARRAY DE CADENA JSON");
        String myURL = "http://192.168.5.44/app_dev.php/cus/dids/find";
        System.out.println("Requested URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null) {
                urlConn.setReadTimeout(10 * 1000);
                urlConn.setDoOutput(true);
                String data = URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode(state, "UTF-8");
                data += "&" + URLEncoder.encode("area", "UTF-8") + "=" + URLEncoder.encode(area, "UTF-8");                
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
                resultado = sb.toString();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }
        System.out.println("DATOS ENVIADOS DEL SERVIDOR " + sb.toString());
        return resultado;

    }

    public List<Did> getDids(String state, String area) {

        String resultado = this.getHttpString(state, area);
       
          List<Did> dids = new ArrayList<Did>();

        try {

            if (resultado.compareTo("") == 0) {
                System.out.println("el servidor no responde");

            } else {

                String jsonResult = resultado;

                System.out.println("\n\n--------------------OBTENEMOS OBJETO JSON NATIVO DE LA PAGINA, USAMOS EL ARRAY DATA---------------------------\n\n");
                JSONObject objJason = new JSONObject(jsonResult);
                // JSONArray dataJson = new JSONArray();
                //  dataJson = objJason.getJSONArray("data");

                String mensaje = objJason.optString("message");
                String code = objJason.optString("code");

                if (code.compareTo("200") == 0) {
                    
                    
                   
                    String jdata = objJason.optString("data");
                    System.out.println("\n\n MENSAJE DEL SERVIDOR " + mensaje);
                    System.out.println(" el objeto jdata es "+jdata);
                   // objJason = new JSONObject(jdata);
                   System.out.println("objeto normal 1 " + objJason.toString());
                    //
                   
                    JSONArray jsonArray = new JSONArray();
                    Gson gson = new Gson();
                    //objJason = gson.t
                  jsonArray = objJason.getJSONArray("data");
                    
                    System.out.println("\n\nEL ARRAY FINAL ES " + jsonArray.toString());

                  

                    for (int i = 0; i < jsonArray.length(); i++) {
                         Did did = new Did();
                        did.setNo(i + 1);
                        did.setId(( jsonArray.getJSONObject(i).getInt("id")));
                       did.setPhone( jsonArray.getJSONObject(i).getInt("phone"));
                        did.setCountry(jsonArray.getJSONObject(i).getString("country"));
                        did.setState(jsonArray.getJSONObject(i).getString("state"));
                        did.setAreas(jsonArray.getJSONObject(i).getString("areas"));
                        did.setStatus(jsonArray.getJSONObject(i).getInt("status"));

                        

                        dids.add(did);
                    }
                    for (int i = 0; i < dids.size(); i++) {
                        System.out.print("\n\nNo" + dids.get(i).getNo());
                        System.out.print("  id " + dids.get(i).getId());
                        System.out.print("  Phone " + dids.get(i).getPhone());
                        System.out.print("  country " + dids.get(i).getCountry());
                        System.out.print("  State " + dids.get(i).getState());
                        System.out.print("  Areas " + dids.get(i).getAreas());
                        System.out.print("  Status" + dids.get(i).getStatus());
                        
                    }
                   

                } else {
                     System.out.print("El servidor ha respondido "+mensaje+code);
                }

            }
        } catch (Exception e) {
             System.out.println("El servidor para obtener el historial de dids no ha respondido");
            e.printStackTrace();
           
        }
        return dids;
    }
    
}
