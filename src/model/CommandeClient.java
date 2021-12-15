package model;
import java.util.Date;

public class CommandeClient
{
    private int id;
    private Date created_at;
    private Date withdrawal_at;
    private int fk_client;
    private int nbrArticles;
    private float prixTotal;
    private boolean accompte;
    private String status;
    private String typePaiment;
    private String produits;

        public CommandeClient() {
            super();
        }

        public CommandeClient(Date created_at, Date withdrawal_at, int fk_client, int nbrArticles, float prixTotal, boolean accompte, String status, String typePaiment, String produits) {
            super();
            this.created_at = created_at;
            this.withdrawal_at = withdrawal_at;
            this.fk_client = fk_client;
            this.nbrArticles = nbrArticles;
            this.prixTotal = prixTotal;
            this.accompte = accompte;
            this.status = status;
            this.typePaiment = typePaiment;
            this.produits = produits;
        }

    public int getId() {
        return id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getWithdrawal_at() {
        return withdrawal_at;
    }

    public int getFk_client() {
        return fk_client;
    }

    public int getNbrArticles() {
        return nbrArticles;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public boolean isAccompte() {
        return accompte;
    }

    public String getStatus() {
        return status;
    }

    public String getTypePaiment() {
        return typePaiment;
    }

    public String getProduits() {
        return produits;
    }


    public void setTypePaiment(String typePaiment) {
        this.typePaiment = typePaiment;
    }

    public void setProduits(String produits) {
        this.produits = produits;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setWithdrawal_at(Date withdrawal_at) {
        this.withdrawal_at = withdrawal_at;
    }

    public void setFk_client(int fk_client) {
        this.fk_client = fk_client;
    }

    public void setNbrArticles(int nbrArticles) {
        this.nbrArticles = nbrArticles;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public void setAccompte(boolean accompte) {
        this.accompte = accompte;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
