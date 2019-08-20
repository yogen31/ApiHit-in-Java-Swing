/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apihit;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import static java.lang.System.in;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author rock and roll
 */
public class HttpCLient {
    public static void main(String[] args) throws Exception{
        
          HttpClient client=HttpClientBuilder.create().build();
          HttpGet request=new HttpGet("http://localhost:8080/company/employees/");
//          request.addHeader("id", "1");
         
          HttpResponse response = client.execute(request);
          if(response.getStatusLine().getStatusCode()!=200){
              throw new RuntimeException("Failed:Http error code:"+response.getStatusLine().getStatusCode());
              
          }
          BufferedReader br=new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
          StringBuffer result=new StringBuffer();
          String line="";
          while((line=br.readLine())!=null){
                        result.append(line);
       
          }
          System.out.println("Response:"+result);
         try{
          FileWriter file = new FileWriter("C:\\json\\ram\\shyam3.txt");
          file.write(result.toString());
          file.close();
      }catch(Exception ex){
          System.out.println(ex);
      } 
    
      
         JSONParser parser = new JSONParser();
           JSONArray jsonArray=(JSONArray)parser.parse(br.readLine());
           ObservableList<Employee>oblist=FXCollections.observableArrayList();
          
          for(Object object:jsonArray){
            JSONObject jsonObject=(JSONObject)parser.parse(object.toString());
//            System.out.println("jsonObject:"+);
            
         
         
         
          }
           
         
          
    }
}
