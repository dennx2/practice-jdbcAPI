# Practice JDBC API using Oracle SQL
In this project, we practiced JDBC API by running DDL and DML commands using static SQL, prepared statement, or updatable result set. The commands include INSERT, UPDATE, DELETE, CREATE, DROP, and etc.

query package:

- Get connection to the Oracle database, querying the Country table to show the results which life expectancy is in a range.
Use prepareStatement to ask user to input two values, and static statement for the two fixed values.

unknown package: 

- My code allows user to input and run sql syntax (select, update, delete, insert) freely in the console.
The code will first acquire an input/query from the user, and then determine what type of query it is by trying to run it.
If the query is valid and is either update, delete, insert, then the program will run the syntax and print a successful message.
If the query is a select statement, then the program will grab the returned column names and use the names to get row data and print the result row by row.

update package:

- Connection to the database is made successfully and then the country table is dropped and then created.
UpdateTablePreparedStm, UpdateTableStaticSQL and UpdateTableUpdateResultSet classes are used to perform insert, update and delete SQL operations on the country table records by implementing prepared statement, static SQL and result set respectively.
