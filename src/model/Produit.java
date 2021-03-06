package model;

public class Produit {
    private int id;
    private String libelle;
    private String fabricant;
    private float poids;
    private int quantite;
    private float prixHT;
    private float prixTTC;

    public Produit(){};

    public Produit(int id, String libelle, String fabricant, float poids, int quantite, float prixHT, float prixTTC){
        this.id = id;
        this.libelle = libelle;
        this.fabricant = fabricant;
        this.poids = poids;
        this.quantite = quantite;
        this.prixHT = prixHT;
        this.prixTTC = prixTTC;
    }
    public Produit(String libelle, String fabricant, float poids, int quantite, float prixHT, float prixTTC){
        this.libelle = libelle;
        this.fabricant = fabricant;
        this.poids = poids;
        this.quantite = quantite;
        this.prixHT = prixHT;
        this.prixTTC = prixTTC;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public void setPrixHT(float prixHTKG) {
        this.prixHT = prixHTKG;
    }

    public void setPrixTTC(float prixTTCKG) {
        this.prixTTC = prixTTCKG;
    }

    public String getFabricant() {
        return fabricant;
    }

    public float getPoids() {
        return poids;
    }

    public float getPrixHT() {
        return prixHT;
    }

    public float getPrixTTC() {
        return prixTTC;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
