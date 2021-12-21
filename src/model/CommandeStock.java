package model;

import java.util.Date;

public class CommandeStock {
	private int id;
    private Date dateReception;
    private int fk_idfournisseur;
    private int nbrArticles;
    private float prixTotal;
    private String produits;
    
    public CommandeStock() {
	}
    
	public CommandeStock(int id, Date dateReception, int fk_idfournisseur,
			int nbrArticles, float prixTotal) {
		super();
		this.id = id;
		this.dateReception = dateReception;
		this.fk_idfournisseur = fk_idfournisseur;
		this.nbrArticles = nbrArticles;
		this.prixTotal = prixTotal;
	}

	public CommandeStock(int id, Date dateReception, int fk_idfournisseur,
			int nbrArticles, float prixTotal, String produits) {
		super();
		this.id = id;
		this.dateReception = dateReception;
		this.fk_idfournisseur = fk_idfournisseur;
		this.nbrArticles = nbrArticles;
		this.prixTotal = prixTotal;
		this.produits = produits;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateReception() {
		return dateReception;
	}

	public void setDateReception(Date dateReception) {
		this.dateReception = dateReception;
	}

	public int getFk_idfournisseur() {
		return fk_idfournisseur;
	}

	public void setFk_idfournisseur(int fk_idfournisseur) {
		this.fk_idfournisseur = fk_idfournisseur;
	}

	public int getNbrArticles() {
		return nbrArticles;
	}

	public void setNbrArticles(int nbrArticles) {
		this.nbrArticles = nbrArticles;
	}

	public float getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(float prixTotal) {
		this.prixTotal = prixTotal;
	}

	public String getProduits() {
		return produits;
	}

	public void setProduits(String produits) {
		this.produits = produits;
	}
    
}
