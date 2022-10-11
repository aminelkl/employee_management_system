package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;

public class Connect {

	@FXML
	private Button connectButton;

	@FXML
	private Label errorLabel;

	@FXML
	private TextField userField;

	@FXML
	private TextField passField;

	@FXML
	private Button exitButton;

	@FXML
	private AnchorPane pane1;

	@FXML

	// Connect Button Action 
	public void connectButtonOnAction(ActionEvent event) {
		
		if (!userField.getText().isEmpty() && !passField.getText().isEmpty()) {
			validateLogin();
		}

		else {
			errorLabel.setText("Please enter username and password");
			userField.setText("");
			passField.setText("");
		}

	}

	// Exit stage without button action
	public void exit() {
		Stage stage;
		stage = (Stage) pane1.getScene().getWindow();
		stage.close();
	}

	// Exit stage button action
	public void exit(ActionEvent event) {
		Stage stage;
		stage = (Stage) pane1.getScene().getWindow();
		stage.close();
	}

		
	public void validateLogin() {
		DataBase connectLive = new DataBase();
		Connection connectDb = connectLive.getConnection();

		String verifyLog = "SELECT count(*) FROM user WHERE userName = '" + userField.getText() + "' AND password = '"
				+ passField.getText() + "'";

		try {
			
			Statement statement = connectDb.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLog);

			while (queryResult.next()) {
				if (queryResult.getInt(1) == 1) {
					exit();
					switchToBoard();

				} 
				else {
					errorLabel.setText("Invalid Login, Please try again");
					userField.setText("");
					passField.setText("");
				}
			}

		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	// Switch to the dashBoard
	public void switchToBoard() throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("/application/DashBoard.fxml"));
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		Stage primaryStage = new Stage();

		// primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("/application/e__book__droid.png"));
		primaryStage.show();

	}

}
