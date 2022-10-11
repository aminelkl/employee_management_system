package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.cell.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Board implements Initializable {

	@FXML
	private Button deleteButton;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private AnchorPane pane2;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button EditButton;
	
    @FXML
    private TextField searchBar;
	
    @FXML
    private TableView<employeeList> employee;

    @FXML
    private TableColumn<employeeList, String> firstColumn;

    @FXML
    private TableColumn<employeeList, String> genderColumn;

    @FXML
    private TableColumn<employeeList, String> idColumn;

    @FXML
    private TableColumn<employeeList, String> lastColumn;
    
    @FXML
    private TableColumn<employeeList, String> yoeColumn;
    
    @FXML
    private Button refresh;
    
	@FXML
	private AnchorPane pane1;

    
    // Exit button
	public void exit(ActionEvent event) throws IOException {
		switchLogIn();
		Stage stage;
		stage = (Stage) pane2.getScene().getWindow();
		stage.close();	
	}
	
	public void switchLogIn() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/SignIn.fxml"));
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);		
		Stage primaryStage = new Stage();
		
		//primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("/application/e__book__droid.png"));
		primaryStage.show();		
	}
	
	// Add employee button
	public void addButton(ActionEvent event) throws IOException {
		switchAdd();
	}
	
	//switch to add employe scene
	public void switchAdd() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Add.fxml"));
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Stage primaryStage = new Stage();
		
	//	primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("/application/e__book__droid.png"));
		primaryStage.show();
	}
	
	// switch to delete employee scene
	public void switchDelete() throws IOException  {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Delete.fxml"));
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Stage primaryStage = new Stage();
		
		//primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("/application/e__book__droid.png"));
		primaryStage.show();
	}
	
	// switch to delete employee button
	public void switchD(ActionEvent e) throws IOException {
		switchDelete();
	}
	

	
//TABLEVIEW

    public ObservableList<employeeList> data = FXCollections.observableArrayList();
	
	public void initialize(URL url, ResourceBundle resource) {
		//employee.getItems().clear();
		
		try {
    		String query = "select * from employee";
    		
    		DataBase connectLive = new DataBase();
    		Connection connectDb = connectLive.getConnection();
    		
    		Statement st;
    		ResultSet rs;
    		
    		st = connectDb.createStatement();
    		rs = st.executeQuery(query);
    		employeeList emp;
    		
    		while (rs.next()) {
    			emp = new employeeList(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("gender"), rs.getString("yoe"));
    			data.add(emp);
    		}
    		connectDb.close();
    		
    		idColumn.setCellValueFactory(new PropertyValueFactory<employeeList, String>("id"));
        	firstColumn.setCellValueFactory(new PropertyValueFactory<employeeList, String>("firstname"));
        	lastColumn.setCellValueFactory(new PropertyValueFactory<employeeList, String>("lastname"));
        	genderColumn.setCellValueFactory(new PropertyValueFactory<employeeList, String>("gender"));
        	yoeColumn.setCellValueFactory(new PropertyValueFactory<employeeList, String>("yoe"));
        	employee.setItems(data);
    		
        	// 	Initialize filtered list of employee
        	FilteredList<employeeList> filtereddata = new FilteredList<>(data, b -> true);
        	
        	searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
        		filtereddata.setPredicate(employeeList -> {
        			
        			if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
        				return true;
        			}
        			
        			String searchKeyword = newValue.toLowerCase();
        			
        			if (Integer.toString(employeeList.getId()) == searchKeyword) {
        				return true;
        			} 
        			
        			else if(employeeList.getFirstname().toLowerCase().indexOf(searchKeyword) > -1) {
        				return true;
        			}
        			
        			else if(employeeList.getLastname().toLowerCase().indexOf(searchKeyword) > -1) {
        				return true;
        			}
        			
        			else if(employeeList.getGender().toLowerCase().indexOf(searchKeyword) > -1) {
        				return true;
        			}
        			
        			else if(employeeList.getYoe().indexOf(searchKeyword) > -1) {
        				return true;
        			}
        			
        			else {
        				return false;
        			}
        			
        		} );
        	} );
        
        	SortedList<employeeList> sorteddata = new SortedList<> (filtereddata);
        	sorteddata.comparatorProperty().bind(employee.comparatorProperty());
        	employee.setItems(sorteddata);
    	 
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		
	}
	
	public void refresh() {
		data.clear();
		try {
    		String query = "select * from employee";
    		
    		DataBase connectLive = new DataBase();
    		Connection connectDb = connectLive.getConnection();
    		
    		Statement st;
    		ResultSet rs;
    		
    		st = connectDb.createStatement();
    		rs = st.executeQuery(query);
    		employeeList emp;
    		
    		while (rs.next()) {
    			emp = new employeeList(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("gender"), rs.getString("yoe"));
    			data.add(emp);
    		}
    		connectDb.close();
    		
    		idColumn.setCellValueFactory(new PropertyValueFactory<employeeList, String>("id"));
        	firstColumn.setCellValueFactory(new PropertyValueFactory<employeeList, String>("firstname"));
        	lastColumn.setCellValueFactory(new PropertyValueFactory<employeeList, String>("lastname"));
        	genderColumn.setCellValueFactory(new PropertyValueFactory<employeeList, String>("gender"));
        	yoeColumn.setCellValueFactory(new PropertyValueFactory<employeeList, String>("yoe"));
        	employee.setItems(data);
    		
        	// 	Initialize filtered list of employee
        	FilteredList<employeeList> filtereddata = new FilteredList<>(data, b -> true);
        	
        	searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
        		filtereddata.setPredicate(employeeList -> {
        			
        			if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
        				return true;
        			}
        			
        			String searchKeyword = newValue.toLowerCase();
        			
        			if (Integer.toString(employeeList.getId()) == searchKeyword) {
        				return true;
        			} 
        			
        			else if(employeeList.getFirstname().indexOf(searchKeyword) > -1) {
        				return true;
        			}
        			
        			else if(employeeList.getLastname().indexOf(searchKeyword) > -1) {
        				return true;
        			}
        			
        			else if(employeeList.getGender().indexOf(searchKeyword) > -1) {
        				return true;
        			}
        			
        			else if(employeeList.getYoe().indexOf(searchKeyword) > -1) {
        				return true;
        			}
        			
        			else {
        				return false;
        			}
        			
        		} );
        	} );
        
        	SortedList<employeeList> sorteddata = new SortedList<> (filtereddata);
        	sorteddata.comparatorProperty().bind(employee.comparatorProperty());
        	employee.setItems(sorteddata);
    	 
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	
	}

	 public void displaySelected(MouseEvent event) throws IOException {
		 if (event.getClickCount() > 1) {
		 
		 employeeList emp = employee.getSelectionModel().getSelectedItem();
		 if (emp ==null) {
			 System.out.println("Ya R");
		 }
		 else {
			 String f = emp.getFirstname(); 
			 String l = emp.getLastname();
			 String i = String.valueOf(emp.getId());
			 String g = emp.getGender();
			 String y = emp.getYoe();

			 FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Edit.fxml"));
			 Parent root = (Parent) loader.load();
			 Edit secondController = loader.getController();
			 secondController.myFunction(i, f, l, g, y);
			 Stage stage = new Stage();
			 Scene scene = new Scene(root);
			// scene.setFill(Color.TRANSPARENT);
			 stage.setScene(scene);
			// stage.initStyle(StageStyle.TRANSPARENT);
			 stage.show();
			 
		 	} 
		 }
	 }
	 	 
}
