package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {

	public Connection databaseLink;
	
	public Connection getConnection() {
		String DRIVER = "com.mysql.cj.jdbc.Driver", URL = "jdbc:mysql://localhost:3306/employeemanagement?serverTimezone=UTC",
                USER = "root", PASSWORD = "password";

        Connection cnx = null;

        try {
            if (cnx == null || cnx.isClosed()) {
                Class.forName(DRIVER);
                cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ex.getClass().getName()).log(Level.SEVERE, null, ex);
        }		
       
        return cnx;
	}
	
}
