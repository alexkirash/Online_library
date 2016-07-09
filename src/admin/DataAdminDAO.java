package admin;
/**
 * Interfaces to provide data on admins, regardless of output: a file or database, or anywhere else.
 * @author Kirash
 *
 */
import java.sql.SQLException;
/**
 * Record administrator in source storage.
 * @param admin
 * @throws SQLException
 * @throws ClassNotFoundException
 */
public interface DataAdminDAO {

	void write(DataAdmin admin) throws SQLException, ClassNotFoundException;
/**
* Verification of the presence of such administrator in some source storage.
* @param admin
* @return
* @throws SQLException
*/
	boolean checkAdmin(DataAdmin admin) throws SQLException;
	
}