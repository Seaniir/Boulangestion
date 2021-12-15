package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.conf.ConnectionUrlParser;
import model.Client;
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
            System.out.println("Insertion OK");

        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Insertion KO - KO - KO");

        }
        return message;

    }


    //Affichage des articles
    public List<CommandeClient> read() {
        List<CommandeClient> commandeClient = new ArrayList<>();

        try {
            PreparedStatement req = connect.prepareStatement("SELECT * FROM commandesclients");

            ResultSet rs = req.executeQuery();
            //System.out.println(req);

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
            //System.out.println("-------------------"+listeArticle);
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Insertion KO - KO - KO");
        }
        return commandeClient;
    }


    public ConnectionUrlParser.Pair<CommandeClient, Client> findById(int id) {
        ConnectionUrlParser.Pair<CommandeClient, Client> pair = null;
        try {
            PreparedStatement req = connect.prepareStatement("SELECT * FROM commandesclients INNER JOIN clients ON clients.id = commandesclients.fk_client AND commandesclients.id = ?");
            req.setInt(1, id);

            ResultSet rs = req.executeQuery();
            while(rs.next()) {
                CommandeClient article = new CommandeClient();
                Client client = new Client();
                article.setId(rs.getInt("id"));
                article.setCreated_at(rs.getDate("created_at"));
                article.setWithdrawal_at(rs.getDate("withdrawal_at"));
                article.setFk_client(rs.getInt("fk_client"));
                article.setNbrArticles(rs.getInt("nbrArticles"));
                article.setPrixTotal(rs.getFloat("prixTotal"));
                article.setAccompte(rs.getBoolean("accompte"));
                article.setStatus(rs.getString("status"));
                article.setTypePaiment(rs.getString("typePaiment"));
                article.setProduits(rs.getString("produits"));
                client.setId(rs.getInt("clients.id"));
                client.setName(rs.getString("nom"));
                client.setFirstName(rs.getString("prenom"));
                client.setAdress(rs.getString("adresse"));
                client.setZip(rs.getInt("zip"));
                client.setCity(rs.getString("ville"));
                client.setTel(rs.getString("telephone"));
                client.setEmail(rs.getString("email"));

                pair = new ConnectionUrlParser.Pair<>(article, client);
            }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Insertion KO - KO - KO");
        }
        return pair;
    }

    public static ConnectionUrlParser.Pair<Integer, String> getTwo()
    {
        return new ConnectionUrlParser.Pair<Integer, String>(10, "GeeksforGeeks");
    }

}