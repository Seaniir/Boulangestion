package model;

public class User {
	// Attributes
	private String login;
	private String pwd;
	// Constructors
	public User() {
		super();
	}
	public User(String login, String pwd) {
		super();
		this.login = login;
		this.pwd = pwd;
	}
	//Getter Setter
	public String getLogin() {
		return login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
