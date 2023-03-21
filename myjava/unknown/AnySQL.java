package myjava.unknown;

import myjava.JDBCUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

// Jaydenn

public class AnySQL {

    // database connection elements
    private Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;

    // constructor
    public AnySQL(Connection conn) {
        this.connection = conn;
    }

    // method to drop and create Country table
    public void reCreateTable() {

        String sqlDropCountryTable = "DROP TABLE country";
        String sqlCreateCountryTable =
                "CREATE TABLE country" + "(" +
                        "  name VARCHAR(15)," +
                        "  lifeExpectancy NUMBER(4, 1)," +
                        "  primary key (name)" +
                        ")";

        try {
            // creating statement
            statement = connection.createStatement();

            // drop table
            statement.executeUpdate(sqlDropCountryTable);
            System.out.println("Country table dropped...");

            // create table
            statement.executeUpdate(sqlCreateCountryTable);
            System.out.println("Country table created successfully!");
        }
        // if dropping failed, simply create the table
        catch (SQLException e) {
            try {
                statement.executeUpdate(sqlCreateCountryTable);
                System.out.println("Country table created successfully!");
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }
    }


    // method to get user input and determine which type of statement execution to use
    public void runQuery(String input) {

        try {
            // creating statement
            statement = connection.createStatement();

            // check user query type
            boolean check = statement.execute(input);

            // if it's a select query
            if (check) {
                // get the resultSet from the statement
                resultSet = statement.getResultSet();

                // print the query result
                displayDMLResult(resultSet);
            }
            // if it's an update, delete, or insert query
            else {
                // get row count from the statement
                int rowCount = statement.getUpdateCount();

                // if successful, print a message
                String actionType = input.split(" ")[0].toLowerCase();
                System.out.println(rowCount + " row has been successfully " + actionType + "d.");
            }
        } catch (SQLException e) {
            System.out.println("Error! Please check your sql syntax.");
            System.out.println(e.getMessage());
        } finally {
            // close the resultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // close the statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
//            try {
//                statement = conn.createStatement();
//                String queryType = input.trim().split(" ")[0];
//                switch (queryType.toLowerCase()){
//                    case "select":
//                        // execute the select query
//                        resultSet = statement.executeQuery(input);
//                        displayDMLResult(resultSet);
//                        break;
//                    case "update":
//                    case "insert":
//                    case "delete":
//                        int rows = statement.executeUpdate(input);
//                        break;
//                    default:
//                        System.out.println("Please enter a valid SQL query starting with one of these: " +
//                                "SELECT, UPDATE, INSET, DELETE");
//                }
//            }
//            catch (SQLException e) {
//                System.out.println("Something's wrong with your query, please try again.");
//                e.printStackTrace();
//            }
//            finally {
//                if (statement != null) statement.close();
//            }
    }


    // method to display DML query result to the console
    public void displayDMLResult(ResultSet resultSet) {

        // initialize a list for column names
        ArrayList<String> columnNameLst = new ArrayList<>();

        // to create a list of column names
        try {
            // get the metadata from the query result
            ResultSetMetaData metaData = resultSet.getMetaData();

            // add column names into the list
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNameLst.add(metaData.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // initialize a list for row data
        ArrayList<String> rowData = new ArrayList<>();

        // to create a list for each row in the result table, and print row by row
        try {
            // print table columns
            System.out.println(String.join(", ", columnNameLst));
            // while there is still next row in the result table
            while (resultSet.next()) {
                // for every column of the row, add the data to the list of row data
                for (String columnName : columnNameLst) {
                    switch (columnName) {
                        case "NAME":
                            String countryName = resultSet.getString("name");
                            rowData.add(countryName);
                            continue;
                        case "LIFEEXPECTANCY":
                            double lifeExp = resultSet.getFloat("lifeExpectancy");
                            String lifeExpString = Double.toString(lifeExp);
                            rowData.add(lifeExpString);
                            continue;
                        default:
                            rowData.add("null");
                            continue;
                    }
                }

                // print the data of the row
                System.out.println(String.join(", ", rowData));

                // clear the data in the row list, so we can print the next row starting with empty row list
                rowData.clear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        // use JDBCUtilities to create database connection
        JDBCUtilities jdbcUtilities = new JDBCUtilities();
        Connection conn = jdbcUtilities.initializeDB();

        // use the created connection to create a database instance
        AnySQL db = new AnySQL(conn);
        db.reCreateTable();

        // create a scanner to accept user input
        Scanner keyboard = new Scanner(System.in);

        // test use
        db.runQuery("insert into country values ('taiwan', 80)");
        db.runQuery("insert into country values ('canada', 75)");
        db.runQuery("insert into country values ('japan', 90)");
        db.runQuery("insert into country values ('italy', 65)");
        db.runQuery("insert into country values ('korea', 87)");
//            db.runQuery("select * from country");
//            db.runQuery("update country set lifeExpectancy = 80 where name = 'japan'");
//            db.runQuery("select * from country");
//            db.runQuery("select lifeExpectancy from country");
//            db.runQuery("delete from country where name = 'taiwan'");
//            db.runQuery("select * from country");

        // keep asking user for query input until user hit q
        while (true) {
            // ask user for query input
            System.out.println("Enter your query: (enter \"q\" to quit)");
            String userInput = keyboard.nextLine();

            // quit the loop if user opt to quit
            if (userInput.trim().equals("q")) {
                break;
            }

            // if not quitting, run the user's query
            db.runQuery(userInput);
        }

        // close scanner
        keyboard.close();

    }


}
