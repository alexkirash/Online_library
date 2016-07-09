package connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import admin.*;
/**
 * Write-check data in a database table in the administrator.
 * @author Kirash
 *
 */
public class AdminDAODB implements DataAdminDAO {

	private ConnectDB con;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
/**
* Create a connection with the connection class.
* @param parent
*/	
	public AdminDAODB(ConnectDB parent){
		con = parent;
	}
     
     public void write(DataAdmin admin) throws SQLException{

				preparedStatement = con.getDBConnection()
						.prepareStatement("insert into  systemLibrary.admin values (?, ?, ?)");
				preparedStatement.setLong(1, admin.getId());
				preparedStatement.setString(2, admin.getLogin());
				preparedStatement.setString(3, admin.getPassword());
				preparedStatement.executeUpdate();

				preparedStatement.close();
			
    }
    public boolean checkAdmin(DataAdmin admin) throws SQLException{
    	 
    	boolean result = false, isLogin = false, isPassword = false;
    	
    	 statement = con.getDBConnection().createStatement();
			ResultSet qresult = statement
					.executeQuery("SELECT * FROM systemLibrary.admin");
			ArrayList<String> logins = new ArrayList<String>();
			while (qresult.next())
				logins.add(qresult.getString("login"));
				isLogin = logins.contains(admin.getLogin());
			
			ResultSet qresult2 = statement
						.executeQuery("SELECT * FROM systemLibrary.admin");
			ArrayList<String> passwords = new ArrayList<String>();
			while(qresult2.next())
				passwords.add(qresult2.getString("password"));
				isPassword = passwords.contains(admin.getPassword());
			
			statement.close();
			
			if(isLogin && isPassword){
				result = true;
			}
			else
				result = false;
			
			return result;	 
    }
 /**
 * Method for close connect with database in ConnectDBMysql.
 * @throws SQLException
 */
    public void closeConnection() throws SQLException{
		con.closeDBConnection();
	}
}
