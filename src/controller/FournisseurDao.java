package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Fournisseur;


public class FournisseurDao implements IDao<Fournisseur>{
	
	Connection connect = GetConnection.getConnection();
	ResultSet rs = null;
	List<Fournisseur> listeFournisseurs = new ArrayList<>();

	@Override
	public void create(Fournisseur fournisseur) {
		try {
			PreparedStatement sql = connect.prepareStatement("INSERT INTO fournisseur (societe, correspondant, adresse,"
		+" cp, ville, tel, email) VALUES (?, ?, ?, ?, ?, ?, ?)");
			sql.setString(1, fournisseur.getSociete());
			sql.setString(2, fournisseur.getCorrespondant());
			sql.setString(3, fournisseur.getAdresse());
			sql.setInt(4, fournisseur.getCodePostal());
			sql.setString(5, fournisseur.getVille());
			sql.setString(6, fournisseur.getTel());
			sql.setString(7, fournisseur.getEmail());
			sql.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public List<Fournisseur> read() {
		PreparedStatement sql2;
		try {
			sql2 = connect.prepareStatement("SELECT * FROM fournisseur");
			rs = sql2.executeQuery();
			
			while(rs.next()) {
				Fournisseur fournisseur = new Fournisseur(rs.getInt("id"),rs.getString("societe"),rs.getString("adresse"), rs.getInt("cp"), rs.getString("ville"),rs.getString("tel"), rs.getString("email"));
				listeFournisseurs.add(fournisseur);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeFournisseurs;
	}

	@Override
	public void update(Fournisseur fournisseur, int idFournisseur) {
		try {
			PreparedStatement sql = connect.prepareStatement("UPDATE fournisseur SET societe=?, correspondant=?, adresse=?, cp=?, ville=?, tel=?, email=? WHERE fournisseur.id=?");
			sql.setString(1, fournisseur.getSociete());
			sql.setString(2, fournisseur.getCorrespondant());
			sql.setString(3, fournisseur.getAdresse());
			sql.setInt(4, fournisseur.getCodePostal());
			sql.setString(5, fournisseur.getVille());
			sql.setString(6, fournisseur.getTel());
			sql.setString(7, fournisseur.getEmail());
			sql.setInt(8, idFournisseur);
			
			sql.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(int idToDelete) {
		try {
			PreparedStatement sql = connect.prepareStatement("DELETE FROM fournisseur WHERE id=?");
			sql.setInt(1, idToDelete);
			sql.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Fournisseur findById(int id) {
		
		try {
			PreparedStatement req = connect.prepareStatement("SELECT * FROM fournisseur WHERE id=?");
			req.setInt(1, id);
			
			ResultSet rs = req.executeQuery();
			
			while(rs.next()) {
				Fournisseur fournisseur = new Fournisseur(rs.getInt("id"),rs.getString("societe"),rs.getString("correspondant"),rs.getString("adresse"),rs.getInt("cp"),rs.getString("ville"),rs.getString("tel"),rs.getString("email"));
				return fournisseur;
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}

	
	
}
