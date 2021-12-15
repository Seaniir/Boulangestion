package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Client;

public class ClientDao implements IDao<Client> {
	Connection connect = controller.GetConnection.getConnection();	
	ResultSet rs = null;
	List<Client> listeClient = new ArrayList<>();
	public static Client currentclient;
	public void inscription(Client client) {
		
		try {
			PreparedStatement sql = connect.prepareStatement("INSERT INTO clients (nom,prenom,adresse,zip,ville,telephone,email) VALUES"
					+"(?,?,?,?,?,?,?)");
			
			sql.setString(1, client.getName());
			sql.setString(2, client.getFirstName());
			sql.setString(3, client.getAdress());
			sql.setInt(4, client.getZip());
			sql.setString(5, client.getCity());
			sql.setString(6, client.getTel());
			sql.setString(7, client.getEmail());
			sql.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public boolean mailAlreadyExists(String mail) {
		Boolean msg = false;
		try {
			PreparedStatement sql = connect.prepareStatement("SELECT * FROM clients WHERE email=?");
			sql.setString(1, mail);
			ResultSet rs = sql.executeQuery();
			if(!rs.next()) {
				
				msg = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public void create(Client client) {
		
	}

	@Override
	public List<Client> read() {
		
		PreparedStatement sql;
        try {
            sql = connect.prepareStatement("SELECT * FROM clients");
            rs = sql.executeQuery();
            while(rs.next()) {
                Client client = new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("adresse"),rs.getInt("zip"),rs.getString("ville"),
          rs.getString("telephone"),rs.getString("email"));

                listeClient.add(client);
            }
            
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Insertion KO - KO - KO");
        }
        return listeClient;
    }
	

	@Override
	public void update(Client client, int idClient) {
		try {
			PreparedStatement sql = connect.prepareStatement("UPDATE clients SET nom=?, prenom=?, adresse=?, zip=?, ville=?, telephone=?, email=? WHERE clients.id=?");
			sql.setString(1, client.getName());
			sql.setString(2, client.getFirstName());
			sql.setString(3, client.getAdress());
			sql.setInt(4, client.getZip());
			sql.setString(5, client.getCity());
			sql.setString(6, client.getTel());
			sql.setString(7, client.getEmail());
			sql.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int idToDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Client> findById(int id) {
		List<Client> listeClient = new ArrayList<>();
		try {
			PreparedStatement req = connect.prepareStatement("SELECT * FROM clients WHERE id=?");
			req.setInt(1, id);
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("titre"));
				client.setFirstName(rs.getString("resume"));
				client.setAdress(rs.getString("contenu"));
				client.setZip(rs.getInt("created_at"));
				client.setCity(rs.getString("auteur"));
				client.setTel(rs.getString("auteur"));
				client.setEmail(rs.getString("auteur"));
				
				listeClient.add(client);
			}
			System.out.println(listeClient);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Insertion KO - KO - KO");
		}
		return listeClient;
	}
	

}
