/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apihit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * FXML Controller class
 *
 * @author rock and roll
 */
public class AllEmployeeController implements Initializable {
       @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee,String> nameColumn;
  
    

    @FXML
    private TableColumn<Employee,String> designationColumn;

    @FXML
    private TableColumn<Employee,String> expertiseColumn;

    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        try{
           URL ur=new URL("http://localhost:8080/company/employees/");
           HttpURLConnection conn=(HttpURLConnection)ur.openConnection();
           conn.setUseCaches(false);
           conn.setDoInput(true);
         
           conn.setDoOutput(true);
           conn.setRequestMethod("GET");
          
         
           conn.getInputStream();
           BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           
           JSONParser parser = new JSONParser();
           JSONArray jsonArray=(JSONArray)parser.parse(in.readLine());
           ObservableList<Employee>oblist=FXCollections.observableArrayList();
          
          for(Object object:jsonArray){
            JSONObject jsonObject=(JSONObject)parser.parse(object.toString());
//            System.out.println("jsonObject:"+);
            oblist.add(new Employee(jsonObject.get("name").toString(),jsonObject.get("designation").toString(),jsonObject.get("expertise").toString()));
         
         nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
         designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
         expertiseColumn.setCellValueFactory(new PropertyValueFactory<>("expertise"));  
         
          }
           
          
         
         
         employeeTable.setItems(oblist);
           
            
    }    catch(Exception e){
            System.out.println(e);
    }
    
}
}
