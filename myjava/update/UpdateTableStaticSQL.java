package myjava.update;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// Simul

//update values of Country table
public class UpdateTableStaticSQL {
	public boolean updateCountry(String name, Integer lifexpectancy) {
		
				ResultSet resultSet = null;
				InitializeDB myDB = new InitializeDB();
		    	Connection conn = myDB.getConnection();
		    	
		    	
		    	//return true on successful insert otherwise return false
		    	boolean update = true;
				try {
					//check to see if the record with input id already exists in the database
					Statement statement = conn.createStatement();
					String selectQry = "SELECT name FROM COUNTRY WHERE name = '" + name + "'";
					resultSet = statement.executeQuery(selectQry);
					
					String foundName=null;

					while (resultSet.next()) {
						foundName = resultSet.getString("name");
					}

					//handling null values		     
		        	if(lifexpectancy == null)
		        		lifexpectancy= -999;

					//if the input name is equal to the name in the database, the record exists
					if(foundName.equals(name)) {
						//update sql query (static sql)
						String sql = "UPDATE COUNTRY " + 
									 "SET LIFEEXPECTANCY = " + lifexpectancy +
								    "WHERE NAME = '" + name + "'";
											
						statement.executeUpdate(sql);
					}else {
						//a record with the input name does not exists in the database
						update = false;
					}		
				}catch(Exception e) {
					e.printStackTrace();
					update=false;
				}
				return update;
		    }
}
