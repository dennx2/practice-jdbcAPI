package myjava.update;

import java.sql.Connection;
import java.sql.PreparedStatement;

// Simul

//delete records from Country table (using resultset)
public class UpdateTableUpdateResultSet {
	public boolean deleteCountry(String name) {
//		delete flag
		boolean delete = true;
		
		PreparedStatement pstmt = null;
		InitializeDB myDB = new InitializeDB();
    	Connection conn = myDB.getConnection();
    	
		try {
        	//insert sql query
        	String sql = "DELETE FROM COUNTRY WHERE NAME = ?";
        	//prepared statement
        	pstmt = conn.prepareStatement(sql);
        	
        	//setting the sql parameters dynamically
        	pstmt.setString(1,name);

        	//executing the prepared statement
            pstmt.executeUpdate();
        }
        catch (Exception e){
//            e.printStackTrace();
        	System.out.println(e.getMessage());
        	delete = false;
        }
		return delete;
	}

}
