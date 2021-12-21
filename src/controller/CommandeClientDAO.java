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

        }catch(Exception e) {
            e.printStackTrace();
        }
        return message;

    }

    public void update(CommandeClient commandeClient, int idCommandeClient) {
        java.util.Date utilDate = commandeClient.getWithdrawal_at();
        java.sql.Date sqlDate = new java.sql.Date(commandeClient.getWithdrawal_at().getTime());
        try {
            PreparedStatement sql = connect.prepareStatement("UPDATE commandesclients SET withdrawal_at=?, fk_client=?, nbrArticles=?, prixTotal=?, accompte=?, status=?, typePaiment=?, produits=?, cloturee=? WHERE commandesclients.id=?");
            sql.setDate(1, sqlDate);
            sql.setInt(2, commandeClient.getFk_client());
            sql.setInt(3, commandeClient.getNbrArticles());
            sql.setFloat(4, commandeClient.getPrixTotal());
            sql.setBoolean(5, commandeClient.isAccompte());
            sql.setString(6, commandeClient.getStatus());
            sql.setString(7, commandeClient.getTypePaiment());
            sql.setString(8, commandeClient.getProduits());
            sql.setBoolean(9, commandeClient.isCloturee());
            sql.setInt(10, idCommandeClient);
            sql.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void archive(int idCommandeClient) {
        try {
            PreparedStatement sql = connect.prepareStatement("UPDATE commandesclients SET status=? WHERE commandesclients.id=?");
            sql.setString(1, "Terminé");
            sql.setInt(2, idCommandeClient);
            sql.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    //Affichage des articles
    public List<CommandeClient> read() {
        List<CommandeClient> commandeClient = new ArrayList<>();

        try {
            PreparedStatement req = connect.prepareStatement("SELECT * FROM commandesclients WHERE commandesclients.status='En cours'");

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
                article.setTypePaiment(rs.getString("typePaiment"));
                article.setPrixTotal(rs.getFloat("prixTotal"));
                article.setAccompte(rs.getBoolean("accompte"));
                article.setStatus(rs.getString("status"));
                article.setCloturee(rs.getBoolean("cloturee"));

                commandeClient.add(article);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return commandeClient;
    }

    public List<CommandeClient> readIsClosed() {
        List<CommandeClient> commandeClient = new ArrayList<>();

        try {
            PreparedStatement req = connect.prepareStatement("SELECT * FROM commandesclients WHERE commandesclients.cloturee='0'");

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
                article.setTypePaiment(rs.getString("typePaiment"));
                article.setPrixTotal(rs.getFloat("prixTotal"));
                article.setAccompte(rs.getBoolean("accompte"));
                article.setStatus(rs.getString("status"));
                article.setProduits(rs.getString("produits"));
                article.setCloturee(rs.getBoolean("cloturee"));

                commandeClient.add(article);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return commandeClient;
    }
    // Affichage des Devis
    public ConnectionUrlParser.Pair<ArrayList<CommandeClient>, ArrayList<Client>> readPairDevis() {
    	ConnectionUrlParser.Pair<ArrayList<CommandeClient>, ArrayList<Client>> pair = new ConnectionUrlParser.Pair<ArrayList<CommandeClient>, ArrayList<Client>>(new ArrayList<CommandeClient>(), new ArrayList<Client>() );
    	try {
    		PreparedStatement sql = connect.prepareStatement("SELECT * FROM commandesclients INNER JOIN clients ON commandesclients.fk_client = clients.id AND commandesclients.status = 'Devis' ");
    		ResultSet rs = sql.executeQuery();
    		while(rs.next()) {
    			CommandeClient cC = new CommandeClient();
    			Client c = new Client();
    			cC.setId(rs.getInt("id"));
    			cC.setWithdrawal_at(rs.getDate("withdrawal_at")); 
    			cC.setNbrArticles(rs.getInt("nbrArticles"));
    			cC.setPrixTotal(rs.getFloat("prixTotal"));
    			cC.setTypePaiment(rs.getString("typePaiment")); 
    			c.setName(rs.getString("nom"));
    			c.setFirstName(rs.getString("prenom"));
    			pair.left.add(cC);
    			pair.right.add(c);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return pair;
    }
    // Affichage de l'historique des commandes. 
    public ConnectionUrlParser.Pair<ArrayList<CommandeClient>, ArrayList<Client>> readPairHisto() {
    	ConnectionUrlParser.Pair<ArrayList<CommandeClient>, ArrayList<Client>> pair = new ConnectionUrlParser.Pair<ArrayList<CommandeClient>, ArrayList<Client>>(new ArrayList<CommandeClient>(), new ArrayList<Client>() );
    	try {
    		PreparedStatement sql = connect.prepareStatement("SELECT * FROM commandesclients INNER JOIN clients ON commandesclients.fk_client = clients.id AND commandesclients.status = 'Terminé' ");
    		ResultSet rs = sql.executeQuery();
    		while(rs.next()) {
    			CommandeClient cC = new CommandeClient();
    			Client c = new Client();
    			cC.setId(rs.getInt("id"));
    			cC.setWithdrawal_at(rs.getDate("withdrawal_at")); 
    			cC.setNbrArticles(rs.getInt("nbrArticles"));
    			cC.setPrixTotal(rs.getFloat("prixTotal"));
    			cC.setTypePaiment(rs.getString("typePaiment")); 
    			c.setName(rs.getString("nom"));
    			c.setFirstName(rs.getString("prenom"));
    			pair.left.add(cC);
    			pair.right.add(c);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return pair;
    }
    
    public void delete(int idToDelete) {
        try {
            PreparedStatement sql = connect.prepareStatement("DELETE FROM commandesclients WHERE id=?");
            sql.setInt(1, idToDelete);
            sql.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        }
        return pair;
    }
}
    
    
    
