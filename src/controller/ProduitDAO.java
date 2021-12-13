package controller;

import model.CommandeClient;
import model.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {
    //Appel de la connection
    Connection connect = GetConnection.getConnection();

    public boolean add(Produit object) {
        boolean message = false;
        try {

            PreparedStatement req = connect.prepareStatement("INSERT INTO produits"
                    + "(libelle, prixUnitaire) VALUES (?,?) ");

            req.setString(1, object.getLibelle());
            req.setFloat(2, object.getPrixUnitaire());

            req.executeUpdate();
            message = true;
            System.out.println("Insertion OK");

        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Insertion KO - KO - KO");

        }
        return message;

    }

    public List<Produit> read() {
        List<Produit> produits = new ArrayList<>();

        try {
            PreparedStatement req = connect.prepareStatement("SELECT * FROM produits");

            ResultSet rs = req.executeQuery();
            //System.out.println(req);

            while(rs.next()) {
                Produit article = new Produit();
                article.setId(rs.getInt("id"));
                article.setLibelle(rs.getString("libelle"));
                article.setPrixUnitaire(rs.getFloat("prixUnitaire"));

                produits.add(article);
            }
            //System.out.println("-------------------"+listeArticle);
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Insertion KO - KO - KO");
        }
        return produits;
    }
}
