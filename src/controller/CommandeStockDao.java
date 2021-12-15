package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CommandeStock;

public class CommandeStockDao implements IDao<CommandeStock>{

	Connection connect = GetConnection.getConnection();

	@Override
	public void create(CommandeStock cmdStock) {
		PreparedStatement req;
		try {
			req = connect.prepareStatement("INSERT INTO commandesstocks"
			        + "(dateReception, fk_idfournisseur, nbrArticles, prixTotal, produits) VALUES (NOW(),?,?,?,?) ");
			req.setInt(1, cmdStock.getFk_idfournisseur());
	        req.setInt(2, cmdStock.getNbrArticles());
	        req.setFloat(3, cmdStock.getPrixTotal());
	        req.setString(4, cmdStock.getProduits());
	
	        req.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
                java.util.Date utilDate = new java.util.Date(rs.getDate("dateReception").getTime());
                CommandeStock cmdStock = new CommandeStock(rs.getInt("id"),(Date) utilDate,rs.getInt("fk_idfournisseur"),rs.getInt("nbrArticles"),rs.getFloat("prixTotal"));
                
                commandeStock.add(cmdStock);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return commandeStock;
    
	}

	@Override
	public void update(CommandeStock object, int idObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int idToDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CommandeStock findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
