package connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import tableBooks.*;
/**
 * Read-write-delete data in a database table in the book.
 * @author Kirash
 */
public class BooksDBDAO implements BooksDAO{

	private ConnectDB con;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
/**
 * Create a connection with the connection class.	
 * @param parent
 */
	public BooksDBDAO(ConnectDB parent){
		con = parent;
	}
/**
 * {@inheritDoc}
 */    
     public void write(Books books) throws SQLException{

				preparedStatement = con.getDBConnection()
						.prepareStatement("insert into  systemLibrary.books values (?, ?, ?, ?, ?, ?)");
				preparedStatement.setInt(1, books.getNamber());
				preparedStatement.setString(2, books.getName());
				preparedStatement.setInt(3, books.getYear());
				preparedStatement.setString(4, books.getAuthor());
				preparedStatement.setString(5, books.getCategory());
				preparedStatement.setInt(6, books.getQuantity());
				preparedStatement.executeUpdate();
				preparedStatement.close();
				
    }
/**
 * {@inheritDoc}
 */
	public ArrayList<Books> showAll(String category) throws SQLException{
		
		ArrayList<Books> booksResult = new ArrayList<Books>();
		
		statement = con.getDBConnection().createStatement();
		ResultSet qresult1 = statement
				.executeQuery("SELECT * FROM systemLibrary.books where category=" + "'"+ category + "'");
		while (qresult1.next())
		booksResult.add(new Books(qresult1.getInt("number"), qresult1.getString("name"), 
				qresult1.getInt("year"), qresult1.getString("author"), qresult1.getString("category"), 
				qresult1.getInt("quantity")));

		statement.close();
		
	return booksResult;
	}
/**
 * Method for close connect with database in ConnectDB.
 * @throws SQLException
 */
	public void closeConnection() throws SQLException{
		con.closeDBConnection();
	}
	 
}