package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import controller.ClientDao;
import controller.PanelsManager;
import model.Client;

import java.awt.Font;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NouveauClient extends JPanel {
	private JTextField nameValue;
	private JTextField firstNameValue;
	private JTextField telValue;
	private JTextField emailValue;
	private JTextField zipValue;
	private JTextField cityValue;
	private JTextField adressValue;
	
	public static boolean modify = false;
	
	public NouveauClient() {
		setBounds(0, 0, 1440, 900);
		setBackground(new Color(254, 245, 232));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1440, 90);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Retour");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 62, 63, 18);
		panel.add(lblNewLabel);
		
		JLabel lblAccueil = new JLabel("Accueil");
		lblAccueil.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAccueil.setBounds(1355, 62, 63, 18);
		panel.add(lblAccueil);
		
		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToListeClientsPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
				modify = false;
			}
		});
		btnBack.setBackground(Color.WHITE);
		btnBack.setIcon(new ImageIcon("C:\\Users\\Julien\\Desktop\\projetBoulang\\arrow_left.png"));
		btnBack.setBounds(10, 10, 40, 40);
		panel.add(btnBack);
		
		JButton btnAccueil = new JButton("");
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToAccueilMenu());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
				modify = false;
			}
		});
		btnAccueil.setBackground(Color.WHITE);
		btnAccueil.setIcon(new ImageIcon("C:\\Users\\Julien\\Desktop\\projetBoulang\\exit.png"));
		btnAccueil.setBounds(1370, 11, 40, 40);
		panel.add(btnAccueil);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(287, 166, 815, 676);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName = new JLabel("Nom:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(28, 39, 130, 35);
		panel_1.add(lblName);
		
		JLabel lblFirstName = new JLabel("Prenom:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFirstName.setBounds(28, 93, 130, 35);
		panel_1.add(lblFirstName);
		
		JLabel lblNewLabel_2_2 = new JLabel("Adresse:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(28, 175, 130, 35);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblZip = new JLabel("Code Postal:");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblZip.setBounds(28, 287, 130, 35);
		panel_1.add(lblZip);
		
		JLabel lblTel = new JLabel("Telephone:");
		lblTel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTel.setBounds(28, 386, 130, 35);
		panel_1.add(lblTel);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(28, 472, 130, 35);
		panel_1.add(lblEmail);
		
		JLabel lblCity = new JLabel("Ville:");
		lblCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCity.setBounds(405, 287, 93, 35);
		panel_1.add(lblCity);
		
		nameValue = new JTextField();
		nameValue.setBounds(168, 39, 571, 31);
		panel_1.add(nameValue);
		nameValue.setColumns(10);
		
		firstNameValue = new JTextField();
		firstNameValue.setColumns(10);
		firstNameValue.setBounds(168, 93, 571, 31);
		panel_1.add(firstNameValue);
		
		telValue = new JTextField();
		telValue.setColumns(10);
		telValue.setBounds(168, 392, 571, 31);
		panel_1.add(telValue);
		
		emailValue = new JTextField();
		emailValue.setColumns(10);
		emailValue.setBounds(168, 476, 571, 31);
		panel_1.add(emailValue);
		
		zipValue = new JTextField();
		zipValue.setColumns(10);
		zipValue.setBounds(168, 293, 227, 31);
		panel_1.add(zipValue);
		
		cityValue = new JTextField();
		cityValue.setColumns(10);
		cityValue.setBounds(512, 293, 227, 31);
		panel_1.add(cityValue);
		
		adressValue = new JTextField();
		adressValue.setBounds(168, 175, 571, 70);
		panel_1.add(adressValue);
		adressValue.setColumns(10);
		panel_1.add(adressValue);
		
		if (modify == true) {
			// Pre-remplis les champs.
			fillFields(ClientDao.currentClient);
			
			// Form to update a client.
			JButton btnNewButton = new JButton("Modifier");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ClientDao clientDaoModified = new ClientDao();
					String name_saisie = nameValue.getText();
					String firstName_saisie = firstNameValue.getText();
					String adress_saisie = adressValue.getText();
					int zip_saisie = zipValue.getX();
					String city_saisie = cityValue.getText();
					String tel_saisie = telValue.getText();
					String email_saisie = emailValue.getText();
					
					Client nouveau = new Client(
							name_saisie,
							firstName_saisie,
							adress_saisie,
							zip_saisie,
							city_saisie,
							tel_saisie,
							email_saisie);
					clientDaoModified.update(nouveau, ClientDao.currentClient.getId());
					btnNewButton.setText("Valider");
					nameValue.setText("");
					firstNameValue.setText("");
					adressValue.setText("");
					zipValue.setText("");
					cityValue.setText("");
					telValue.setText("");
					emailValue.setText("");
					modify = false;
					
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchToListeClientsPanel());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
					
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnNewButton.setBackground(new Color(242, 193, 102));
			btnNewButton.setBounds(168, 571, 155, 30);
			panel_1.add(btnNewButton);	
		} else {
			// Form to create a. 
			JButton btnNewButton = new JButton("Valider");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name_saisie = nameValue.getText();
					String firstName_saisie = firstNameValue.getText();
					String adress_saisie = adressValue.getText();
					int zip_saisie = zipValue.getX();
					String city_saisie = cityValue.getText();
					String tel_saisie = telValue.getText();
					String email_saisie = emailValue.getText();
					Client nouveau = new Client(
							name_saisie,
							firstName_saisie,
							adress_saisie,
							zip_saisie,
							city_saisie,
							tel_saisie,
							email_saisie
							);
					if(!(Pattern.matches("^[a-zA-Z0-9_.-]+[@][a-zA-Z0-9-]+[.]+[a-zA-Z0-9]+$",email_saisie))) {
						JOptionPane.showMessageDialog(null, "Mail invalide","Error",JOptionPane.ERROR_MESSAGE);
					}else {
						
						ClientDao clientDao = new ClientDao();
						
						if (clientDao.mailAlreadyExists(email_saisie)) {
							nameValue.setText("");
							firstNameValue.setText("");
							adressValue.setText("");
							zipValue.setText("");
							cityValue.setText("");
							telValue.setText("");
							emailValue.setText("");
							clientDao.create(nouveau);
						}else {
							JOptionPane.showMessageDialog(null, "Mail existe deja","Error",JOptionPane.ERROR_MESSAGE);
							
						}
					}
			}
			});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(242, 193, 102));
		btnNewButton.setBounds(168, 571, 155, 30);
		panel_1.add(btnNewButton);
		}
		
		
		
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir annuler","Annuler",JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					nameValue.setText("");
					firstNameValue.setText("");
					adressValue.setText("");
					zipValue.setText("");
					cityValue.setText("");
					telValue.setText("");
					emailValue.setText("");
				} else if (n == JOptionPane.NO_OPTION) {
					
				} else {

				}
			}
		});
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAnnuler.setForeground(new Color(0, 0, 0));
		btnAnnuler.setBackground(Color.GRAY);
		btnAnnuler.setBounds(584, 576, 155, 30);
		panel_1.add(btnAnnuler);
	}
	// Method pour remplir les champs
	public void fillFields(Client client) {
		nameValue.setText(client.getName());
		firstNameValue.setText(client.getFirstName());
		adressValue.setText(client.getAdress());
		zipValue.setText(String.valueOf(client.getZip()));
		cityValue.setText(client.getCity());
		telValue.setText(client.getTel());
		emailValue.setText(client.getEmail());	
	}
}

