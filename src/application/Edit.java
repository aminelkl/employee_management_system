package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Edit implements Initializable{

	
	  @FXML
	    private Button exitEdit;
		
		@FXML
	    private Button buttonfinish;

	    @FXML
	    private TextField firstnameEdit;

	    @FXML
	    private TextField genderEdit;

	    @FXML
	    private TextField idEdit;

	    @FXML
	    private TextField lastnameEdit;

	    @FXML
	    private TextField yoeEdit;
	    
	    @FXML
	    private AnchorPane paneEdit;
	    
	    
	    // exit button
	    public void exitEdit(ActionEvent e)  {
			Stage stage;
			stage = (Stage) paneEdit.getScene().getWindow();
			stage.close();
		}
	    
	    // exit without scene
	    public void exit()  {
			Stage stage;
			stage = (Stage) paneEdit.getScene().getWindow();
			stage.close();
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
		}
	    
		// function to add employee data from table view to edit textfield (Called in Board.java)
	    public void myFunction(String i, String f, String l, String g, String y) {
	    	idEdit.setText(i);
	    	firstnameEdit.setText(f);
	    	lastnameEdit.setText(l);
	    	genderEdit.setText(g);
	    	yoeEdit.setText(y);
	    }
	    
	    // edit employee
	    public void finish(ActionEvent e) {
	    	int idd = Integer.valueOf(idEdit.getText());
	    	String ln = lastnameEdit.getText();
			String fn = firstnameEdit.getText();
			String gen = genderEdit.getText();
			String yoe = yoeEdit.getText();
			
			String editDb = "update employee set firstname='" + fn +"', lastname='" + ln + "', gender='" + gen + "', yoe='" + yoe + "' where id=" + idd;

			DataBase connectLive = new DataBase();
			Connection connectDb = connectLive.getConnection();
			
			try {
			Statement mySt = connectDb.createStatement();
			mySt.executeUpdate(editDb);
		    exit();
		
		        
			} catch (Exception E) {
	          E.printStackTrace();
			}
			
	    }
	    
}
