package view;

import controller.PanelsManager;
import controller.UserDao;
import model.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Hub extends JFrame {

	private JPanel contentPane;
	private JTextField loginValue;
	private JPasswordField pwdValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hub frame = new Hub();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Hub() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 10));
		getContentPane().setEnabled(false);
		getContentPane().setBackground(Color.PINK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1440, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		PanelsManager.contentPane = contentPane;
		PanelsManager.contentPane.add(PanelsManager.switchToLambdaPanel());
		setContentPane(PanelsManager.contentPane);
		getContentPane().setLayout(null);
		
		JLabel lblBoulangestion = new JLabel("Boulangestion");
		lblBoulangestion.setBounds(0, 111, 1426, 113);
		lblBoulangestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoulangestion.setForeground(new Color(128, 0, 0));
		lblBoulangestion.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 80));
		getContentPane().add(lblBoulangestion);
		
		JPanel panel = new JPanel();
		panel.setBounds(516, 327, 393, 281);
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblConnexion = new JLabel("Connexion");
		lblConnexion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnexion.setBounds(0, 0, 393, 57);
		panel.add(lblConnexion);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLogin.setBounds(10, 79, 80, 34);
		panel.add(lblLogin);
		
		JLabel lblPwd = new JLabel("Mot de passe");
		lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPwd.setBounds(10, 140, 100, 34);
		panel.add(lblPwd);
		
		loginValue = new JTextField();
		loginValue.setBounds(158, 89, 225, 19);
		panel.add(loginValue);
		loginValue.setColumns(10);
		
		pwdValue = new JPasswordField();
		pwdValue.setBounds(158, 150, 225, 19);
		panel.add(pwdValue);
		
		JButton btnConnexion = new JButton("Se connecter");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login_saisie = loginValue.getText();
				String pwd_saisie = String.valueOf(pwdValue.getPassword()); 
				User nouveau = new User();
				
				nouveau.setLogin(login_saisie);
				nouveau.setPwd(pwd_saisie);
				
				UserDao usDao = new UserDao();
				
				usDao.login(login_saisie, pwd_saisie);
				
				if(usDao.login(login_saisie, pwd_saisie)) {
					
					JOptionPane.showMessageDialog(null, "Félicitation");			
					contentPane.removeAll();
					AccueilMenu accueilMenu = new AccueilMenu();
					contentPane.add(accueilMenu);
					contentPane.repaint();
					contentPane.revalidate();
					
				}else {
					JOptionPane.showMessageDialog(null, "Impossible de se connecter");
				}		
			}
		});
		btnConnexion.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConnexion.setBounds(10, 199, 373, 61);
		panel.add(btnConnexion);
		
		JLabel lblCopyright = new JLabel("\u00A9 2021 Boulangestion");
		lblCopyright.setBounds(0, 840, 1426, 13);
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCopyright);
		
		JLabel lblimg = new JLabel("New label");
		lblimg.setBounds(0, 0, 1426, 873);
		getContentPane().add(lblimg);
		lblimg.setIcon(new ImageIcon("C:\\Users\\Julien\\Desktop\\projetBoulang\\croissants-french-pastries-croissants-background-bakery-products-breakfast-besthqwallpapers.com-1440x900.jpg"));
	}
}
