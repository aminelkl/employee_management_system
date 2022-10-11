package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddControl {


	
	@FXML
	private Button exit;
	
	@FXML 
	private TextField firstName;
	
	@FXML 
	private TextField gender;
	
	@FXML 
	private TextField yearOfExperience;
	
	@FXML
	private Button done;
	
	@FXML 
	private TextField lastName;

	@FXML
	private AnchorPane pane3;
	
    @FXML
    private CheckBox check;
    
    @FXML
    private Label checkLabel;

	// exit button
	public void exit(ActionEvent e)  {
		Stage stage;
		stage = (Stage) pane3.getScene().getWindow();
		stage.close();
	}
	
	// exit without button
	public void exit()  {
		Stage stage;
		stage = (Stage) pane3.getScene().getWindow();
		stage.close();
	}
	
	
	
	public boolean check() {
		if (check.isIndeterminate()) {
			return false;
		}
		else {
			return true;
		}
	}
		
	// finish button
	public void finish(ActionEvent event) {

		String ln = lastName.getText();
		String fn = firstName.getText();
		String gen = gender.getText();
		String yoe = yearOfExperience.getText();
		
		String addDb = "insert into employee (firstname, lastname, gender, yoe) values ('" + fn + "'," + "'" + ln + "','" + gen + "', '" + yoe + "')"; 
 		                                                                        
		DataBase connectLive = new DataBase();
		Connection connectDb = connectLive.getConnection();
		
		try {
		Statement mySt = connectDb.createStatement();
		mySt.executeUpdate(addDb);
	    exit();
	
	        
		} catch (Exception E) {
          E.printStackTrace();
		}
	}	 

}
