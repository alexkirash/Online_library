package connect;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tableUser.*;
/**
 *  Read-write-delete data in a database table in the users who have a book on loan.
 * @author Kirash
 */
public class UserTableDBDAO implements UserTookBookDAO{

	private ConnectDB con;
	private Statement statement = null;
/**
 * Create a connection with the connection class.
 * @param parent
 */
	public UserTableDBDAO(ConnectDB parent){
		con = parent;
	}
/**
 * {@inheritDoc}
 */     
     public void write(UserTookBook date) throws SQLException{

    	 statement = con.getDBConnection().createStatement();
			statement.executeUpdate("insert into  systemLibrary.userTable "
							+ "(login, nameBook, date) "
							+"values ('"+ date.getLogin() + "', '" + date.getNameBook() + "', '"
							+ date.getDate()+"')");
				
				statement.close();
		
    }
/**
  * {@inheritDoc}
  */
     public void writeInBlackList(UserTookBook date) throws SQLException{

    	 statement = con.getDBConnection().createStatement();
			statement.executeUpdate("insert into  systemLibrary.blacklisttable "
							+ "(login, nameBook, date) "
							+"values ('"+ date.getLogin() + "', '" + date.getNameBook() + "', '"
							+ date.getDate()+"')");
				
				statement.close();
				
			
 }
/**
 * {@inheritDoc}
 */  
	public ArrayList<UserTookBook> showAll() throws SQLException{
		
		ArrayList<UserTookBook> userResult = new ArrayList<UserTookBook>();
		
		statement = con.getDBConnection().createStatement();
		ResultSet qresult1 = statement
				.executeQuery("SELECT * FROM systemLibrary.userTable ");
		while (qresult1.next())
		userResult.add(new UserTookBook( qresult1.getInt("iduserTable"), 
				qresult1.getString("login"), 
				qresult1.getString("nameBook"), qresult1.getString("date")));

		statement.close();
		
	return userResult;
	}
/**
 * {@inheritDoc}
 */	
public ArrayList<UserTookBook> showAllBlackList() throws SQLException{
		
		ArrayList<UserTookBook> userResult = new ArrayList<UserTookBook>();
		
		statement = con.getDBConnection().createStatement();
		ResultSet qresult1 = statement
				.executeQuery("SELECT * FROM systemLibrary.blacklisttable ");
		while (qresult1.next())
		userResult.add(new UserTookBook( qresult1.getInt("idblacklist"), 
				qresult1.getString("login"), 
				qresult1.getString("nameBook"), qresult1.getString("date")));

		statement.close();
		
	return userResult;
	}
/**
 * {@inheritDoc}
 */
public void delete(UserTookBook utb) throws SQLException{
	
	statement = con.getDBConnection().createStatement();
	statement
			.executeUpdate("DELETE FROM systemLibrary.userTable WHERE nameBook = " + "'" 
	+ utb.getNameBook() + "'" );
	
	statement.close();

}
/**
 * {@inheritDoc}
 */
public void deleteBlacklist(UserTookBook utb) throws SQLException{
	
	statement = con.getDBConnection().createStatement();
	statement
			.executeUpdate("DELETE FROM systemLibrary.blacklisttable WHERE nameBook = " + "'" 
					+ utb.getNameBook() + "'" );
	
	statement.close();

}
/**
 * Method for close connect with database in ConnectDBMysql.
 * @throws SQLException
 */
	public void closeConnection() throws SQLException{
		con.closeDBConnection();
	}
	 
}