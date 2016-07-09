package tableUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * Stores information about the users for the administrator.
 * @author Kirash
 *
 */

public class UserTookBook {
	
	private int id;
	private String login;
	private String nameBook;
	private String date;
/** 
* Default constructor.
*/	
	public UserTookBook (){		
	}
/**
* Constructor to initialize the data on users.
* @param id
* @param login
* @param nameBook
* @param date
*/	
	public UserTookBook(int id, String login, String nameBook,String dateToday){
		this.id = id;
		this.login = login;
		this.nameBook =nameBook;
		this.date = dateToday;
	}
	
	public void setId(int id){
		this.id = id;
	}
	public void setLogin(String login){
		this.login = login;
	}
	public void setNameBook(String nameBook){
		this.nameBook = nameBook;
	}
	public void setData(String dateToday){
		this.date = dateToday;
		}
	
	
	public int getId(){
		return id;
	}
	public String getLogin(){
			return login;
	}
	public String getNameBook(){
			return nameBook;
	}
	public String getDate(){
			return date;		
	}
		
		public static boolean isValidDate(String value, String dateModel) {

		    if (value == null || dateModel == null || dateModel.length() <= 0 || value.length() > 10) {
		      return false;
		    }

		    SimpleDateFormat formatter = new SimpleDateFormat(dateModel);
		    formatter.setLenient(false);

		    try {
		      formatter.parse(value);
		    } catch (ParseException e) {
		      return false;
		    }
		    return true;
		  }
	
		@Override
		public String toString() {
			return "Calendar [Id=" + id + ", Login=" + login
					+ ", NameBook=" + nameBook + ", Date=" + date + "]";	
}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime* result + id;
			result = prime* result	+ ((login == null) ? 0 : login.hashCode());
			result = prime* result	+ ((nameBook == null) ? 0 : nameBook.hashCode());
			result = prime* result	+ ((date == null) ? 0 : date.hashCode());

			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UserTookBook other = (UserTookBook) obj;
			if (login == null) {
				if (other.login != null)
					return false;
			} else if (!login.equals(other.login))
				return false;
			if (nameBook == null) {
					if (other.nameBook != null)
						return false;
			} else if (!nameBook.equals(other.nameBook))
				return false;
			if (date == null) {
				if (other.date != null)
					return false;
			} else if (!date.equals(other.date))
				return false;
			if (id != other.id)
				return false;			
			return true;
		}
}