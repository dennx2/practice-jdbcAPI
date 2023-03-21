package myjava.query;

import myjava.OracleInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//Kaiyan

public class QueryTablePreparedStm2 {

    public static void main(String[] args) {

        Connection conn = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            // load the driver
            Class.forName(OracleInfo.DRIVER_CLASS_ORACLE);
            System.out.println("Driver is loaded successfully...");

            //create connection
            conn = DriverManager.getConnection(OracleInfo.THIN_URL, OracleInfo.USERNAME, OracleInfo.PWD);
            System.out.println("Connected database successfully...");

            //create statement
            statement = conn.createStatement();

            //drop old table
            String sql = "DROP TABLE COUNTRY";
            statement.executeUpdate(sql);
            System.out.println("Dropped the table successfully...");

            //create new table
            sql = "CREATE TABLE COUNTRY" + "(" +
                    "COUNTRY_NAME varchar(20)," +
                    "REGION_ID int," +
                    "LIFE_EXPECTANCY float" + ")";
            statement.executeUpdate(sql);
            System.out.println("Created the table successfully...");

            //insert values to the table
            sql = "INSERT INTO COUNTRY VALUES ('Canada', 1, 83)";
            statement.executeUpdate(sql);

            sql = "INSERT INTO COUNTRY VALUES ('US', 2, 76)";
            statement.executeUpdate(sql);

            System.out.println("Inserted the rows successfully...");


            //create a prepared statement
            System.out.println("Creating a prepared statement...");
            sql = "SELECT COUNTRY_NAME, REGION_ID, LIFE_EXPECTANCY FROM COUNTRY "
                    + "WHERE LIFE_EXPECTANCY BETWEEN ? AND ?";
            pstmt = conn.prepareStatement(sql);

            //get user input
            Scanner inputReader = new Scanner(System.in);
            System.out.print("Please enter age 1: ");
            float age1 = inputReader.nextFloat();
            System.out.print("Please enter age 2: ");
            float age2 = inputReader.nextFloat();
            inputReader.close();

            //set up query parameters
            pstmt.setFloat(1, age1);
            pstmt.setFloat(2, age2);

            //execute a query
            rset = pstmt.executeQuery();

            //process ResultSet
            System.out.println();
            while (rset.next()) {
//				int i;
//				for (i=1; i<3; ++i) {
//					System.out.print(rset.getString(i) + ",");
//				}
//				System.out.println(rset.getString(i));
                System.out.println("County: " + rset.getString(1) + "\n"
                        + "RegionID: " + rset.getString(2) + "\n"
                        + "LifeExpectancy: " + rset.getString(3) + "\n");
            }
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
                if (pstmt != null)
                    pstmt.close();
                if (statement != null)
                    statement.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
            }
        }

    }

}
