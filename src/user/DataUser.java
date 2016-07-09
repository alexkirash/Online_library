package user;
/**
 * Stores information about the User.
 * @author Kirash
 *
 */
public class DataUser {

private int id;
private String login;
private String password; 
private String name; 
private String surName;
/**
 * Default constructor.
 */
public DataUser(){	
}
/**
 * Constructor to initialize the data on User.
 * @param id
 * @param login
 * @param password
 * @param name
 * @param surName
 */
public DataUser(int id, String login, String password, String name, String surName){
	this.id = id;
	this.password = password;
	this.login = login;
	this.name = name;
	this.surName = surName;
}

public String getLogin(){
	return login;
}
public String getPassword(){
	return password;
}
public String getName(){
	return name;
}
public String getSurName(){
	return surName;
}
public int getId(){
	return id;
}

public void setLogin(String login){
	this.login = login;
}
public void setPassword(String password){
	this.password = password;
}
public void setName(String name){
	this.name = name;
}
public void setSurName(String surName){
	this.surName = surName;
}
public void setId(int id){
	this.id = id;
}

}
