package model;

public class Client {
	// Attributes
	private int id;
	private String name;
	private String firstName;
	private String adress;
	private int zip;
	private String city;
	private String tel;
	private String email;
	// Constructors

	public Client(){};
	public Client(String name, String firstName, String adress, int zip, String city, String tel, String email) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.adress = adress;
		this.zip = zip;
		this.city = city;
		this.tel = tel;
		this.email = email;
	}

	public Client(int id, String name, String firstName, String adress, int zip, String city, String tel,
				  String email) {
		super();
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.adress = adress;
		this.zip = zip;
		this.city = city;
		this.tel = tel;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// Getter Setter
	public String getName() {
		return name;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getAdress() {
		return adress;
	}
	public int getZip() {
		return zip;
	}
	public String getCity() {
		return city;
	}
	public String getTel() {
		return tel;
	}
	public String getEmail() {
		return email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
