package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.conf.ConnectionUrlParser;

import model.CommandeStock;
import model.Fournisseur;

public class CommandeStockDao implements IDao<CommandeStock>{

	Connection connect = GetConnection.getConnection();

	@Override
	public void create(CommandeStock cmdStock) {
		PreparedStatement req;
		try {
			req = connect.prepareStatement("INSERT INTO commandesstock"
			    + "(dateReception, fk_idfournisseur, nbrArticles, prixTotal,"+
				" produits) VALUES (NOW(),?,?,?,?) ");
			req.setInt(1, cmdStock.getFk_idfournisseur());
	        req.setInt(2, cmdStock.getNbrArticles());
	        req.setFloat(3, cmdStock.getPrixTotal());
	        req.setString(4, cmdStock.getProduits());
	        req.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}       
	}

	@Override
	public List<CommandeStock> read() {
		List<CommandeStock> commandeStock = new ArrayList<>();
        try {
            PreparedStatement req = connect.prepareStatement("SELECT * FROM commandesstock WHERE isVisible=?");
            req.setInt(1, 1);
            ResultSet rs = req.executeQuery();

            while(rs.next()) {
            	java.util.Date sqlDate = new java.sql.Date(rs.getDate("dateReception").getTime());
                CommandeStock cmdStock = new CommandeStock(rs.getInt("id"),
                	sqlDate,rs.getInt("fk_idfournisseur"),rs.getInt("nbrArticles"),
                	rs.getFloat("prixTotal"));
                commandeStock.add(cmdStock);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return commandeStock;
	}

	//methode pour afficher les infos des commandes avec les infos du fournisseur
	public ConnectionUrlParser.Pair<ArrayList<CommandeStock>, ArrayList<Fournisseur>> readPair() {
		ConnectionUrlParser.Pair<ArrayList<CommandeStock>, ArrayList<Fournisseur>> pair =
			new ConnectionUrlParser.Pair<ArrayList<CommandeStock>, 
			ArrayList<Fournisseur>>(new ArrayList<CommandeStock>(), new ArrayList<Fournisseur>());
        try {
            PreparedStatement req = connect.prepareStatement("SELECT * FROM commandesstock INNER"+
            	" JOIN fournisseur ON fournisseur.id=commandesstock.fk_idfournisseur"+
            	" AND commandesstock.isVisible=?");
            req.setInt(1, 1);
            ResultSet rs = req.executeQuery();
            while(rs.next()) {
                CommandeStock cmdStock = new CommandeStock(rs.getInt("id"),
                	rs.getDate("dateReception"),rs.getInt("fk_idfournisseur"),
                	rs.getInt("nbrArticles"),rs.getFloat("prixTotal"),rs.getString("produits"));
                Fournisseur fournisseur = new Fournisseur(rs.getString("societe"));
                pair.left.add(cmdStock);
                pair.right.add(fournisseur);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return pair;
	}

	@Override
	public void update(CommandeStock cmdStock, int idCmdStock) {
        try {
            PreparedStatement sql = connect.prepareStatement("UPDATE commandesstock"+
            	" SET dateReception=NOW(), fk_idfournisseur=?, nbrArticles=?, prixTotal=?,"+
            	" produits=? WHERE commandesstock.id=?");
            sql.setInt(1, cmdStock.getFk_idfournisseur());
            sql.setInt(2, cmdStock.getNbrArticles());
            sql.setFloat(3, cmdStock.getPrixTotal());
            sql.setString(4, cmdStock.getProduits());
            sql.setInt(5, idCmdStock);
            sql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void delete(int idToDelete) {
		try {
			PreparedStatement sql = connect.prepareStatement("UPDATE commandesstock"+
				" SET isVisible=? WHERE id=?");
			sql.setInt(1, 0);
			sql.setInt(2, idToDelete);
			sql.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public CommandeStock findById(int id) {
		return null;
	}
	
	//recherche d'une commande par id contenant les infos du fournisseur
	public ConnectionUrlParser.Pair<CommandeStock, Fournisseur> findByIdPair(int id) {
	        ConnectionUrlParser.Pair<CommandeStock, Fournisseur> pair = null;
	        try {
	            PreparedStatement req = connect.prepareStatement("SELECT * FROM"+
	            	" commandesstock INNER JOIN fournisseur ON fournisseur.id=commandesstock.fk_idfournisseur"+
	            	" AND commandesstock.id=?");
	            req.setInt(1, id);
	            ResultSet rs = req.executeQuery();
	            while(rs.next()) {
	                CommandeStock cmdStock = new CommandeStock(rs.getInt("id"),
	                	rs.getDate("dateReception"),rs.getInt("fk_idfournisseur"),
	                	rs.getInt("nbrArticles"),rs.getFloat("prixTotal"),rs.getString("produits"));
	                Fournisseur fournisseur = new Fournisseur(rs.getInt("fournisseur.id"),
	                		rs.getString("societe"),rs.getString("adresse"),rs.getInt("cp"),
	                		rs.getString("ville"),rs.getString("tel"),rs.getString("email"));
	                pair = new ConnectionUrlParser.Pair<>(cmdStock, fournisseur);
	            }
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	        return pair;
	 }
}
