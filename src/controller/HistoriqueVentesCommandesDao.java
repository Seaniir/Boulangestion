package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import model.CommandeClient;

public class HistoriqueVentesCommandesDao implements IDao {
	Connection connect = GetConnection.getConnection();
	ResultSet rs = null;
	List<CommandeClient> listeCommandeClient = new ArrayList<>();
	@Override
	public void create(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CommandeClient> read() {
		PreparedStatement sql;
        try {
            sql = connect.prepareStatement("SELECT id, withdrawal_a, prenom, nom, nbrArticles, prixTotal, FROM commandesclients INNER JOIN clients ON commandesclients.fk_client = clients.id");
            sql.setInt(1, 1);
            rs = sql.executeQuery();
            while(rs.next()) {
                CommandeClient commandeClient = new CommandeClient();

                listeCommandeClient.add(commandeClient);
            }
            
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Insertion KO - KO - KO");
        }
        return listeCommandeClient;
	}

	@Override
	public void update(Object object, int idObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int idToDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
