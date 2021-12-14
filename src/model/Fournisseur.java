package model;

public class Fournisseur {
	private int id;
	private String societe;
	private String correspondant;
	private String adresse;
	private int codePostal;
	private String ville;
	private String tel;
	private String email;
	
	public Fournisseur(String societe, String correspondant, String adresse, int codePostal, String ville, String tel,
			String email) {
		super();
		this.societe = societe;
		this.correspondant = correspondant;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.tel = tel;
		this.email = email;
	}

	public Fournisseur(int id, String societe, String adresse, int codePostal, String ville,
			String tel, String email) {
		super();
		this.id = id;
		this.societe = societe;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.tel = tel;
		this.email = email;
	}
	
	public Fournisseur(int id, String societe, String correspondant, String adresse, int codePostal, String ville,
			String tel, String email) {
		super();
		this.id = id;
		this.societe = societe;
		this.correspondant = correspondant;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.tel = tel;
		this.email = email;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public String getCorrespondant() {
		return correspondant;
	}

	public void setCorrespondant(String correspondant) {
		this.correspondant = correspondant;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
