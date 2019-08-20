/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apihit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

/**
 *
 * @author rock and roll
 */
public class PostMethodController implements Initializable {
    @FXML
    private Label nameLabel;

    @FXML
    private Label designationLabel;

    @FXML
    private Label expertiseLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField designationField;

    @FXML
    private TextField expertiseField;

    @FXML
    private Button postButton;

    @FXML
    String postEmployee(ActionEvent event) throws Exception {
           JSONObject json = new JSONObject();
        json.put("name", nameField.getText());
       json.put("designation",designationField.getText());
       json.put("expertise",expertiseField.getText());
       
           //HttpClient
           HttpClient httpClient = HttpClientBuilder.create().build();
           
           //post the JSONObject as a string message
           String msg =json.toString();
           
           //post
           HttpPost httpPost=new HttpPost("http://localhost:8080/company/employees/");
           
           
               HttpEntity entity=new StringEntity(msg);
               
               //set the body of the post,set entity
               httpPost.setEntity(entity);
               
               //post the message using httpClient
               
               HttpResponse response=httpClient.execute(httpPost);
               
               
               //Get the status line
               StatusLine statusLine = response.getStatusLine();
               
               //check the status
               int statusCode=statusLine.getStatusCode();
               
//               if(statusCode==200){
////                   BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
//                   InputStream is=response.getEntity().getContent();
//                   return is;
//               }

 if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed:Http error code:" + response.getStatusLine().getStatusCode());
        }
               InputStream inputStream = null;
               
               
               
               
               //read the String content(JSON msg string from the response)
               Reader reader = new InputStreamReader(inputStream);
              
               //read line
               BufferedReader br=new BufferedReader(reader);
               
               //keep reading for lines and append them to a string builder
               StringBuilder builder=new StringBuilder();
               
               while(true){
                   String line=br.readLine();
                   if(line!=null){
                       builder.append(line);
                   }else{
                       break;
                   }
               }
               
               
       
       
       return builder.toString();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
