package myjava.update;

import myjava.OracleInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Simul

public class InitializeDB {
	private Connection conn = null;
	private Statement statement = null;
	
    // constructor
    public InitializeDB() {
        try {
            // load driver
            Class.forName(OracleInfo.DRIVER_CLASS_ORACLE);
            System.out.println("Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver loading failed!");
        }

        try {
            // establish db connection
            conn = DriverManager.getConnection(OracleInfo.THIN_URL, OracleInfo.USERNAME, OracleInfo.PWD);
            System.out.println("Connection to DB was established successfully!");
            statement = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection to DB failed!");
        }
    }
    
    public void recreateTable() {

        String sqlDropCountryTable = "DROP TABLE COUNTRY";
        String sqlCreateCountryTable = "CREATE TABLE COUNTRY" + "(" +
                "  name VARCHAR(15)," +
                "  lifeexpectancy NUMBER(4,1)," +
                "  primary key (name)" +
                ")";
        try {
            statement.executeUpdate(sqlDropCountryTable);
            System.out.println("Country table is dropped...");

            statement.executeUpdate(sqlCreateCountryTable);
            System.out.println("Country table created successfully!");
        }
        catch (SQLException es) {
            try {
                statement.executeUpdate(sqlCreateCountryTable);
                System.out.println("Staff table created successfully!");
            }
            catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
    
    public Connection getConnection() {
    	return conn;
    }

}
