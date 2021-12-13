package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	Connection connect = controller.GetConnection.getConnection();	
	
	public boolean login(String login, String pwd) {
		Boolean msg = false;
		try {
			PreparedStatement sql = connect.prepareStatement("SELECT * FROM users WHERE login=? AND password=PASSWORD(?)");
			sql.setString(1, login);
			sql.setString(2, pwd);
			
			ResultSet rs = sql.executeQuery();
			if(rs.next()) {
				msg = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
}
