package tableUser;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Interfaces to provide data on users, regardless of output: a file or database.
 * @author Kirash
 *
 */
public interface UserTookBookDAO {
/**
* Record users in source storage.
* @param users
* @throws SQLException
* @throws ClassNotFoundException
*/
	void write(UserTookBook date) throws SQLException, ClassNotFoundException;
/**
* Show all users that contains in some source storage.
* @return
* @throws SQLException
*/		
	ArrayList<UserTookBook> showAll() throws SQLException;
/**
* Show all books that contain some storage source, and entered the black list.
* @return
* @throws SQLException
*/		
	ArrayList<UserTookBook> showAllBlackList() throws SQLException;
/**
* Delete data on user's whitelist
* @return
* @throws SQLException
*/	
	void delete(UserTookBook utb) throws SQLException;
/**
* Remove user data from the database entirely.
* @return
* @throws SQLException
*/	
	void deleteBlacklist(UserTookBook utb) throws SQLException;
}
