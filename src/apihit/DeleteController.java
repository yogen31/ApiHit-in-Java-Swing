/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apihit;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author rock and roll
 */
public class DeleteController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML
    private Button deleteButton;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idField;

  

    @FXML
    void employeeDelete(ActionEvent event) throws Exception {
         JSONObject json = new JSONObject();
        json.put("id",idField.getText());
       
        
        HttpClient client=HttpClientBuilder.create().build();
        HttpDelete delete= new HttpDelete("http://localhost:8080/company/employees/"+idField.getText());
        delete.setHeader("Accept","application/json");
        delete.setHeader("Content-type","application/json");
        
        
         System.out.println("Executing request:"+delete.getRequestLine());
         ResponseHandler<String> responseHandler=(ResponseHandler<String>) new MyStringResponseHandler();
         String responseBody= client.execute(delete, responseHandler);
         System.out.println(".......");
         System.out.println(responseBody);
          Parent switchSceneParent = FXMLLoader.load(getClass().getResource("HttpClientTable.fxml"));
       
                 
             
             Scene switchSceneScene=new Scene(switchSceneParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(switchSceneScene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
