package model;

public class Produit {
    private int id;
    private String libelle;
    private float prixUnitaire;

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
