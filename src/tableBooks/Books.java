package tableBooks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * Stores information about the books.
 * @author Kirash
 *
 */
public class Books {
	private int number;
	private String name;
	private int year;
	private String author;
	private String category;
	private int quantity;
/** 
* Default constructor.
*/
	public Books(){		
	}
/**
* Constructor to initialize the data on books.
* @param namber
* @param name
* @param year
* @param author
* @param category
* @param quantity
*/	
	public Books(int namber, String name, int year, String author, String category, int quantity){
		this.number = namber;
		this.name = name;
		this.year = year;
		this.author = author;
		this.category = category;
		this.quantity = quantity;
		
	}
	
	public void setNamber(int namber){
		this.number = namber;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setYear(int year){
		this.year = year;
	}
	public void setAuthor(String author){
		this.author = author;
	}
	public void setCategory(String category){
		this.category = category;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public int getNamber(){
		return number;
	}
	public String getName(){
		return name;
	}
	public int getYear(){
		return year;
	}
	public String getAuthor(){
		return author;
	}
	public String getCategory(){
		return category;
	}
	public int getQuantity(){
		return quantity;
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
		return "Books [Number=" + number + ", Name=" + name
				+ ", Year=" + year + ", Author=" + author
				+ ", Category=" + category + ", quantity="
				+ quantity + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime* result + ((category == null) ? 0 : category .hashCode());
		result = prime* result	+ ((author == null) ? 0 : author.hashCode());
		result = prime* result + quantity;
		result = prime* result + year;
		result = prime* result	+ ((name == null) ? 0 : name.hashCode());
		result = prime* result + number;
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Books other = (Books) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (quantity != other.quantity)
			return false;
		if (year != other.year)
				return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number != other.number)
			return false;
		return true;
}
}