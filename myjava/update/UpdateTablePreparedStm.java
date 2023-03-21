package myjava.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

// Simul

//insert values on Country table (using prepared statement)
public class UpdateTablePreparedStm {
	public boolean insertCountry(String name, Integer lifexpectancy) {
		
		
	    	PreparedStatement pstmt = null;
	    	InitializeDB myDB = new InitializeDB();
	    	Connection conn = myDB.getConnection();
	    	
	    	//return true on successful insert otherwise return false
	    	//false scenario = SQLIntegrityConstraintVoilationException (trying to insert non-unique id)
	    	boolean insert = true;
	    	
	    	try {
	        	//insert sql query
	        	String sql = "INSERT INTO COUNTRY VALUES (?,?)";
	        	//prepared statement
	        	pstmt = conn.prepareStatement(sql);
	        	
	        	//setting the sql parameters dynamically
	        	
	        	//dealing with null values        	
	        	if(name == null)
	        		pstmt.setNull(1,Types.VARCHAR);
	        	else
	        		pstmt.setString(1,name);
	        	
	        	if(lifexpectancy == null)
	        		pstmt.setNull(2,Types.INTEGER);
	        	else
	        		pstmt.setInt(2,lifexpectancy);
	        	
	        	//executing the prepared statement
	            pstmt.executeUpdate();
	        }
	        catch (Exception e){
//	            e.printStackTrace();
	        	System.out.println(e.getMessage());
	            insert = false;
	        }
	    	return insert;

	    }
}
