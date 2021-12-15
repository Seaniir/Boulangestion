package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CommandeClient;

public class CommandeClientDAO {

    //Appel de la connection
    Connection connect = GetConnection.getConnection();

    public boolean add(CommandeClient object) {
        java.util.Date utilDate = object.getWithdrawal_at();
        java.sql.Date sqlDate = new java.sql.Date(object.getWithdrawal_at().getTime());
        boolean message = false;
        try {

            PreparedStatement req = connect.prepareStatement("INSERT INTO commandesclients"
                    + "(created_at, withdrawal_at, fk_client, nbrArticles, prixTotal, accompte, status, typePaiment, produits) VALUES (NOW(),?,?,?,?,?,?,?,?) ");

            req.setDate(1, sqlDate);
            req.setInt(2, object.getFk_client());
            req.setInt(3, object.getNbrArticles());
            req.setFloat(4, object.getPrixTotal());
            req.setBoolean(5, object.isAccompte());
            req.setString(6, object.getStatus());
            req.setString(7, object.getTypePaiment());
            req.setString(8, object.getProduits());

            req.executeUpdate();
            message = true;

        }catch(Exception e) {
            e.printStackTrace();
        }
        return message;

    }


    //Affichage des articles
    public List<CommandeClient> read() {
        List<CommandeClient> commandeClient = new ArrayList<>();

        try {
            PreparedStatement req = connect.prepareStatement("SELECT * FROM commandesclients");

            ResultSet rs = req.executeQuery();

            while(rs.next()) {
                java.util.Date utilDate = new java.util.Date(rs.getDate("created_at").getTime());
                java.util.Date withDate = new java.util.Date(rs.getDate("withdrawal_at").getTime());
                CommandeClient article = new CommandeClient();
                article.setId(rs.getInt("id"));
                article.setCreated_at(utilDate);
                article.setWithdrawal_at(withDate);
                article.setFk_client(rs.getInt("fk_client"));
                article.setNbrArticles(rs.getInt("nbrArticles"));
                article.setPrixTotal(rs.getFloat("prixTotal"));
                article.setAccompte(rs.getBoolean("accompte"));
                article.setStatus(rs.getString("status"));

                commandeClient.add(article);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return commandeClient;
    }


    /*public List<Article> findById(int id) {
        List<Article> listearticle = new ArrayList<>();
        try {
            PreparedStatement req = connect.prepareStatement("SELECT * FROM article WHERE id=?");
            req.setInt(1, id);

            ResultSet rs = req.executeQuery();

            while(rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitre(rs.getString("titre"));
                article.setResume(rs.getString("resume"));
                article.setContenu(rs.getString("contenu"));
                article.setCreated_at(rs.getDate("created_at"));
                article.setAuteur(rs.getString("auteur"));

                listearticle.add(article);
            }
            System.out.println(listearticle);
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Insertion KO - KO - KO");
        }
        return listearticle;
    }*/

}