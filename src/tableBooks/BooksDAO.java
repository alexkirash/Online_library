package tableBooks;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Interfaces to provide data on books, regardless of output: a file or database.
 * @author Kirash
 *
 */
public interface BooksDAO {
/**
* Record books in source storage.
* @param books
* @throws SQLException
* @throws ClassNotFoundException
*/
	void write(Books books) throws SQLException, ClassNotFoundException;
/**
* Show all books that contains in some source storage.
* @return
* @throws SQLException
*/	
	ArrayList<Books> showAll(String category) throws SQLException;
}
