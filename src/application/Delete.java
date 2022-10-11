package application;

import java.sql.Connection;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Delete {


    @FXML
    private Label errorLabel;
	
	@FXML
	private Button deleteB;
	
	@FXML 
	private TextField idlabel;
	
	@FXML
	private AnchorPane pane4;
	
	@FXML
	private Button exitButton;
	
	// exit button
	public void exit(ActionEvent e)  {
		Stage stage;
		stage = (Stage) pane4.getScene().getWindow();
		stage.close();
	}
	
	// exit without button
	public void exit()  {
		Stage stage;
		stage = (Stage) pane4.getScene().getWindow();
		stage.close();
	}
	
	// delete employee button
	public void del(ActionEvent e) {
		
		String id = idlabel.getText();
		if (!id.isEmpty()) {
		
			String delDb = "delete from employee where id=" + id;
	         
			DataBase connectLive = new DataBase();
			Connection connectDb = connectLive.getConnection();
			
			try {
			Statement mySt = connectDb.createStatement();
			mySt.executeUpdate(delDb);
		    exit();
		        
			} catch (Exception E) {
	          E.printStackTrace();
			}
		}
		else {
			errorLabel.setText("Please enter a valid ID");
		}
	}
	
	
}
