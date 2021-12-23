package controller;

import model.Client;
import model.CommandeClient;
import model.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {
    //Appel de la connection
    Connection connect = GetConnection.getConnection();

    public boolean add(Produit produit) {
        boolean message = false;
        try {

            PreparedStatement req = connect.prepareStatement("INSERT INTO produits"
                    + "(libelle, fabricant, poids, quantite, prixHT, prixTTC) VALUES (?,?,?,?,?,?) ");

            req.setString(1, produit.getLibelle());
            req.setString(2, produit.getFabricant());
            req.setFloat(3, produit.getPoids());
            req.setInt(4, produit.getQuantite());
            req.setFloat(5, produit.getPrixHT());
            req.setFloat(6, produit.getPrixTTC());

            req.executeUpdate();
            message = true;
            System.out.println("Insertion OK");

        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Insertion KO - KO - KO");

        }
        return message;

    }

    public void update(Produit produit, int idProduit) {
        try {
            PreparedStatement sql = connect.prepareStatement("UPDATE produits SET libelle=?, fabricant=?, poids=?, quantite=?, prixHT=?, prixTTC=? WHERE produits.id=?");
            sql.setString(1, produit.getLibelle());
            sql.setString(2, produit.getFabricant());
            sql.setFloat(3, produit.getPoids());
            sql.setInt(4, produit.getQuantite());
            sql.setFloat(5, produit.getPrixHT());
            sql.setFloat(6, produit.getPrixTTC());
            sql.setInt(7, idProduit);
            sql.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public List<Produit> read() {
        List<Produit> produits = new ArrayList<>();

        try {
            PreparedStatement req = connect.prepareStatement("SELECT * FROM produits ORDER BY libelle");

            ResultSet rs = req.executeQuery();
            //System.out.println(req);

            while(rs.next()) {
                Produit article = new Produit();
                article.setId(rs.getInt("id"));
                article.setLibelle(rs.getString("libelle"));
                article.setFabricant(rs.getString("fabricant"));
                article.setPoids(rs.getFloat("poids"));
                article.setQuantite(rs.getInt("quantite"));
                article.setPrixHT(rs.getFloat("prixHT"));
                article.setPrixTTC(rs.getFloat("prixTTC"));

                produits.add(article);
            }
            //System.out.println("-------------------"+listeArticle);
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Insertion KO - KO - KO");
        }
        return produits;
    }

    public void delete(int idToDelete) {
        try {
            PreparedStatement sql = connect.prepareStatement("DELETE FROM produits WHERE id=?");
            sql.setInt(1, idToDelete);
            sql.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
