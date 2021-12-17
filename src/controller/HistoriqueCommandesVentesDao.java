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
	
	
	
	
	public ConnectionUrlParser.Pair<ArrayList<CommandeClient>, ArrayList<Client>> read() {
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
	
}
