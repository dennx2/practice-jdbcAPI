package myjava;

import java.sql.*;

// Jaydenn
public class JDBCUtilities {

    private Connection conn = null;

    public Connection initializeDB() {

        try {
            // load driver
            Class.forName(OracleInfo.DRIVER_CLASS_ORACLE);
            System.out.println("Driver loaded successfully!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Driver loading failed!");
            System.exit(1);
        }

        try {
            // establish db connection
            conn = DriverManager.getConnection(OracleInfo.THIN_URL, OracleInfo.USERNAME, OracleInfo.PWD);
            System.out.println("Connection to DB is established successfully!");
        }
        catch (SQLException e) {
            System.out.println("Connection to DB failed!");
            System.exit(2);
        }

        return conn;
    }

}
