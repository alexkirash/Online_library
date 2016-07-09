package connect;

import java.sql.*;
import java.util.*;

import user.DataUser;
import user.DataUserDAO;
/**
 * Read-write-check data in a database table in the passenger.
 * @author Kirash
 *
 */
	public class UserDBDAO implements DataUserDAO {

		private ConnectDB con;
		private Statement statement = null;
/**
* Create a connection with the connection class.	
* @param parent
*/		
		public UserDBDAO(ConnectDB parent){
			con = parent;
		}
	     
	     public void write (DataUser user) throws SQLException{

	    		statement = con.getDBConnection().createStatement();
				statement
						.executeUpdate("insert into  systemLibrary.users (name, surname, login, password) "								
										+ "values ('"+ user.getName() + "', '" + user.getSurName() + "', '"
										+ user.getLogin() +"', '" + user.getPassword()+"')");
				statement.close();

	    }
/**
* {@inheritDoc}
*/
	    public boolean checkUser(DataUser user) throws SQLException{
	    	 
	    	boolean result = false, isLogin = false, isPassword = false;
	    	
	    	 	statement = con.getDBConnection().createStatement();
				ResultSet qresult = statement
						.executeQuery("SELECT * FROM systemLibrary.users");
				ArrayList<String> logins = new ArrayList<String>();
				while (qresult.next())
					logins.add(qresult.getString("login"));
					isLogin = logins.contains(user.getLogin());
				
				ResultSet qresult2 = statement
							.executeQuery("SELECT * FROM systemLibrary.users");
				ArrayList<String> passwords = new ArrayList<String>();
				while(qresult2.next())
					passwords.add(qresult2.getString("password"));
					isPassword = passwords.contains(user.getPassword());
				
				statement.close();
				
				if(isLogin && isPassword){
					result = true;
				}
				else
					result = false;
				
				return result;
	    	 
	    }
/**
* {@inheritDoc}
*/
		public boolean readCoincidence(String login) throws SQLException{
			boolean result = false;
				statement = con.getDBConnection().createStatement();
				ResultSet qresult = statement
						.executeQuery("SELECT * FROM systemLibrary.users");
				ArrayList<String> logins = new ArrayList<String>();
				while (qresult.next())
					logins.add(qresult.getString("login"));

				result = logins.contains(login);

				statement.close();
				
			return result;
		}
/**
* {@inheritDoc}
*/		
		public DataUser retUser(String login) throws SQLException{
	    	
	    	 statement = con.getDBConnection().createStatement();
				ResultSet qresult = statement
						.executeQuery("SELECT * FROM systemLibrary.users WHERE login = " + "'" 
								+ login + "'");
			ArrayList<DataUser> result = new ArrayList<DataUser>();
				while (qresult.next())
					result.add(new DataUser(qresult.getInt("idusers"), qresult.getString("login"),
							qresult.getString("password"), qresult.getString("name"), qresult.getString("surname")));
				
				statement.close();
				
			return result.get(0);
	    	 
	    }
/**
* Method for close connect with database in ConnectDBMysql.	 
* @throws SQLException
*/		
		public void closeConnection() throws SQLException{
			con.closeDBConnection();
		}
		 
	}


