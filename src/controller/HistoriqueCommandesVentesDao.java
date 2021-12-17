package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.conf.ConnectionUrlParser;

import model.Client;
import model.CommandeClient;

public class HistoriqueCommandesVentesDao{
	Connection connect = GetConnection.getConnection();
	ResultSet rs = null;
	List<CommandeClient> listeCC = new ArrayList<>();
	
	/*public List<CommandeClient> read() {
		//ConnectionUrlParser.Pair<CommandeClient, Client> pair = null;
		PreparedStatement sql;
        try {
            sql = connect.prepareStatement("SELECT * FROM commandesclients INNER JOIN clients ON commandesclients.fk_client = clients.id");
            
            rs = sql.executeQuery();
            while(rs.next()) {
                CommandeClient cC = new CommandeClient();
                Client c = new Client();
                cC.setId(rs.getInt("commandesclients.id"));
                cC.setWithdrawal_at(rs.getDate("withdrawal_at"));
                c.setFirstName(rs.getString("prenom"));
                c.setName(rs.getString("nom"));
                cC.setNbrArticles(rs.getInt("nbrArticles"));
                cC.setPrixTotal(rs.getInt("prixTotal"));
                System.out.println(rs.getString("prenom")+" "+rs.getString("nom"));
                listeCC.add(cC);
               
            }
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return listeCC;
	}*/
	
	// TEST PARSE 
	public ConnectionUrlParser.Pair<CommandeClient, Client> read() {
        ConnectionUrlParser.Pair<CommandeClient, Client> pair = null;
        try {
            PreparedStatement req = connect.prepareStatement("SELECT * FROM commandesclients INNER JOIN clients ON commandesclients.fk_client = clients.id");
            
            ResultSet rs = req.executeQuery();
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
                pair = new ConnectionUrlParser.Pair<>(cC, c);
                System.out.println(rs.getString("nom"));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return pair;
    }
	
}
