package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;

import controller.PanelsManager;
import controller.UserDao;
import model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Login extends JPanel {
	private JTextField loginValue;
	private JPasswordField pwdValue;

	/**
	 * Create the panel.
	 */
	public Login() {
		setBackground(new Color(255, 239, 213));
		setBounds(0,0,1440,900);
		setLayout(null);
		URL imageUrl = ClassLoader.getSystemResource("res/bg.png");
		BufferedImage img = null;
		try {
			img = ImageIO.read(imageUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(1440, 900,
				Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);

		JLabel lblBoulangestion = new JLabel("Boulangestion");
		lblBoulangestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoulangestion.setForeground(new Color(157, 70, 6));
		lblBoulangestion.setFont(new Font("Gentium Book Basic", Font.BOLD | Font.ITALIC, 80));
		lblBoulangestion.setBounds(10, 38, 1420, 98);
		add(lblBoulangestion);
		
		JLabel lblCopyright = new JLabel("\u00A9 2021 Boulangestion");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setBounds(10, 877, 1420, 13);
		add(lblCopyright);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(457, 317, 546, 375);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblConnexion = new JLabel("Connexion");
		lblConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnexion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblConnexion.setBounds(10, 5, 526, 35);
		panel.add(lblConnexion);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLogin.setBounds(10, 93, 126, 40);
		panel.add(lblLogin);
		
		JLabel lblPwd = new JLabel("Mot de passe");
		lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPwd.setBounds(10, 191, 126, 40);
		panel.add(lblPwd);
		
		loginValue = new JTextField();
		loginValue.setBounds(211, 93, 325, 40);
		panel.add(loginValue);
		loginValue.setColumns(10);
		
		pwdValue = new JPasswordField();
		pwdValue.setBounds(211, 191, 325, 40);
		panel.add(pwdValue);
		
		JButton btnNewButton = new JButton("Se Connecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String login_saisie = loginValue.getText();
			String pwd_saisie = String.valueOf(pwdValue.getPassword()); 
			User nouveau = new User();
			
			nouveau.setLogin(login_saisie);
			nouveau.setPwd(pwd_saisie);
			
			UserDao usDao = new UserDao();
			
			usDao.login(login_saisie, pwd_saisie);
			
			if(usDao.login(login_saisie, pwd_saisie)) {
				
				JOptionPane.showMessageDialog(null, "Felicitation");			
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToAccueilMenu());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
				
			}else {
				JOptionPane.showMessageDialog(null, "Impossible de se connecter");
			}		
		}
		});
		btnNewButton.setBounds(10, 296, 526, 42);
		panel.add(btnNewButton);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(imageIcon);
		lblNewLabel.setBounds(0, 0, 1440, 900);
		add(lblNewLabel);
		
	}
}
