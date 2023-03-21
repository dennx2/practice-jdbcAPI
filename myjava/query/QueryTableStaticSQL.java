package myjava.query;

import myjava.OracleInfo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//Kaiyan

public class QueryTableStaticSQL {

    public static void main(String[] args) {

        Connection conn = null;
        Statement statement = null;
        ResultSet rset = null;

        try {
            //load the driver

            Class.forName(OracleInfo.DRIVER_CLASS_ORACLE);

            System.out.println("Driver is loaded successfully...");

            //create the connection

            conn = DriverManager.getConnection(OracleInfo.THIN_URL, OracleInfo.USERNAME, OracleInfo.PWD);

            System.out.println("Database is connected successfully...\n");

            //create the statement
            statement = conn.createStatement();

            //execute a query
            rset = statement.executeQuery("SELECT COUNTRY_NAME, REGION_ID, LIFE_EXPECTANCY FROM COUNTRY"
                    + " WHERE LIFE_EXPECTANCY BETWEEN 60 AND 90");

            System.out.println("Countries which have life expectancy between 60 and 90:\n ");
            while (rset.next())

                System.out.println("County: " + rset.getString(1) + "\n"
                        + "RegionID: " + rset.getString(2) + "\n"
                        + "LifeExpectancy: " + rset.getString(3) + "\n");

        } catch (SQLException se) {
            //handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //handle errors for Class.forName
            e.printStackTrace();
        }

        //close the connection in finally block
        finally {
            try {
                if (rset != null)
                    rset.close();
                if (statement != null)
                    statement.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
            }
        }
    }

}
