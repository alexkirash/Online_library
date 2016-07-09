package user;

import java.sql.SQLException;

/**
 * Interfaces to provide data on users, regardless of output: a file or database.
 * @author Kirash
 *
 */
public interface DataUserDAO {
/**
* Record user in source storage.
* @param user
* @throws SQLException
* @throws ClassNotFoundException
*/
	void write(DataUser user) throws SQLException, ClassNotFoundException;
/**
* Verification of the presence of such a system login in some source storage.
* @param login
* @return
* @throws SQLException
*/
	boolean checkUser(DataUser user) throws SQLException;
/**
* Finding data about the user for their login in some source storage.
* @param login
* @return
* @throws SQLException
*/	
	boolean readCoincidence(String login) throws SQLException;

}
