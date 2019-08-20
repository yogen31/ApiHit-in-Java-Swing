/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apihit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.json.simple.JSONObject;

/**
 *
 * @author rock and roll
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    
    @FXML
    private Label designationLabel;

    @FXML
    private Label expertiseLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField designationField;

    @FXML
    private TextField expertiseField;

    @FXML
    private Button submitButoon;
       @FXML
    private Button showButton;
       
//    @FXML
//    private Button gotopostButton;
       
       
//    @FXML
//    void gotoPost(ActionEvent event) {
//        try{
//        Parent switchSceneParent = FXMLLoader.load(getClass().getResource("Post.fxml"));
//       
//                 
//             
//             Scene switchSceneScene=new Scene(switchSceneParent);
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(switchSceneScene);
//        window.show();}
//        catch(Exception o){
//            System.out.println(o);
//        }
//    }
    

    @FXML
    void showTable(ActionEvent event) throws IOException {
            
                   Parent switchSceneParent = FXMLLoader.load(getClass().getResource("HttpClientTable.fxml"));
       
                 
             
             Scene switchSceneScene=new Scene(switchSceneParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(switchSceneScene);
        window.show();
    }

    @FXML
    void saveEmployee(ActionEvent event)  {
        JSONObject json = new JSONObject();
        json.put("name", nameField.getText());
       json.put("designation",designationField.getText());
       json.put("expertise",expertiseField.getText());
  
       
       try{
           URL url=new URL("http://localhost:8080/company/employees/");
           HttpURLConnection conn=(HttpURLConnection)url.openConnection();
           conn.setUseCaches(false);
           conn.setDoInput(true);
           conn.setRequestProperty("Content-Type","application/json");
           conn.setDoOutput(true);
           conn.setRequestMethod("POST");
           OutputStreamWriter wr=new OutputStreamWriter(conn.getOutputStream());
           wr.write(json.toString());
           wr.flush();
           wr.flush();
           wr.close();
           conn.getInputStream();
           BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           
             
              
                   Parent switchSceneParent = FXMLLoader.load(getClass().getResource("AllEmployee.fxml"));
       
                 
             
             Scene switchSceneScene=new Scene(switchSceneParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(switchSceneScene);
        window.show();  
           
       } catch(Exception e){
           System.out.println(e);
       }
    }

    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
