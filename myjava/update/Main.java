package myjava.update;

// Simul
public class Main {
    public static void main(String[] args) {
    	
    	//create table
    	InitializeDB myDB = new InitializeDB();
    	myDB.recreateTable();

    	//insert records in the countries table

    	boolean resultInsert = false;
    	UpdateTablePreparedStm utps = new UpdateTablePreparedStm();
    	resultInsert = utps.insertCountry("Nepal",60);

    	if(resultInsert) {
    		System.out.println("Record was successfully inserted in the 'Country' table!");
    	}
    	
    	//update a record in the countries table
    	
    	boolean resultIUpdate = false;
    	UpdateTableStaticSQL utss = new UpdateTableStaticSQL();
    	resultIUpdate = utss.updateCountry("Nepal",60);

    	if(resultIUpdate) {
    		System.out.println("Record was successfully updated in the 'Country' table!");
    	}
    	
    	//delete a record from the countries table
    	boolean resultDelete = false;
    	UpdateTableUpdateResultSet uturs = new UpdateTableUpdateResultSet();
    	resultDelete = uturs.deleteCountry("Nepal");

    	if(resultDelete) {
    		System.out.println("Record was successfully deleted from the 'Country' table!");
    	}
    	
    }
}
