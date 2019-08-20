/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apihit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * FXML Controller class
 *
 * @author rock and roll
 */
public class HttpClientTableController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Employee> employeeTable;
    
     @FXML
    private TableColumn<Employee, String> idColumn;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, String> designationColumn;

    @FXML
    private TableColumn<Employee, String> expertiseColumn;
    
     @FXML
    private TableColumn<Employee, String> createdatColumn;
    ObservableList<Employee> oblist = FXCollections.observableArrayList();
 @FXML
    private Button deleteButton;
 
 
    @FXML
    void deleteEmployee(ActionEvent event) throws IOException {
         Parent switchSceneParent = FXMLLoader.load(getClass().getResource("Delete.fxml"));
       
                 
             
             Scene switchSceneScene=new Scene(switchSceneParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(switchSceneScene);
        window.show();
    }
    
    @FXML
    private Button putButton;

    @FXML
    void putEmployee(ActionEvent event) throws Exception {
          Parent switchSceneParent = FXMLLoader.load(getClass().getResource("Put.fxml"));
       
                 
             
             Scene switchSceneScene=new Scene(switchSceneParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(switchSceneScene);
        window.show();
        
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:8080/company/employees/");
//          request.addHeader("id", "1");

        
        
           HttpResponse response = client.execute(request);
       
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed:Http error code:" + response.getStatusLine().getStatusCode());
        }
       
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
//             StringBuffer result = new StringBuffer();
//        String line = "";
//            while ((line = br.readLine()) != null) {
//                result.append(line);
//                
//            }
//       
//        System.out.println("Response:" + result);
//       
//            FileWriter file = new FileWriter("C:\\json\\ram\\shyam3.txt");
//            file.write(result.toString());
//            file.close();
       

        JSONParser parser = new JSONParser();
 
       
                 JSONArray jsonArray = (JSONArray) parser.parse(br.readLine());

            for (Object object : jsonArray) {
                

                JSONObject jsonObject = (JSONObject) parser.parse(object.toString());

                oblist.add(new Employee(jsonObject.get("id").toString(),jsonObject.get("name").toString(), jsonObject.get("designation").toString(), jsonObject.get("expertise").toString(),jsonObject.get("createdAt").toString()));
                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
                expertiseColumn.setCellValueFactory(new PropertyValueFactory<>("expertise"));
                createdatColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

            }
            employeeTable.setItems(oblist);

        }catch(Exception e){
            
            System.out.println(e);
        }
    }
}
