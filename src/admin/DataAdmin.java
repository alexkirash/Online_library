package admin;
/**
 * Stores information about the Administrator.
 * @author Kirash
 *
 */
public class DataAdmin {

	private int id;
	private String login;
	private String password; 
/**
* Default constructor.
*/	
	public DataAdmin(){
		
	}
	
/**
* Constructor to initialize the data on Admin.
* @param id
* @param login
* @param password
*/
	public DataAdmin(int id, String login, String password){
		this.id = id;
		this.login = login;
		this.password = password;
	}
	
	public void setId(int id){
		this.id = id;
	}
	public void setLogin(String login){
		this.login = login;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public int getId(){
		return id;
	}
	public String getLogin(){
		return login;
	}
	public String getPassword(){
		return password;
	}
}
