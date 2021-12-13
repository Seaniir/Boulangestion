package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class NouveauClient extends JPanel {
	private JTextField nameValue;
	private JTextField firstNameValue;
	private JTextField telValue;
	private JTextField emailValue;
	private JTextField zipValue;
	private JTextField cityValue;
	private JTextField adressValue;

	/**
	 * Create the panel.
	 */
	public NouveauClient() {
		setBackground(new Color(255, 239, 213));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1440, 131);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblBack = new JLabel("");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblBack.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setIcon(new ImageIcon("C:\\Users\\Julien\\Desktop\\projetBoulang\\arrow_left.png"));
		lblBack.setBounds(10, 10, 160, 72);
		panel.add(lblBack);
		
		JLabel lblNewLabel = new JLabel("Retour");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 102, 160, 19);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Julien\\Desktop\\projetBoulang\\exit.png"));
		lblNewLabel_1.setBounds(1331, 10, 99, 89);
		panel.add(lblNewLabel_1);
		
		JLabel lblAccueil = new JLabel("Accueil");
		lblAccueil.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAccueil.setBounds(1270, 102, 160, 19);
		panel.add(lblAccueil);
		
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
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(255, 140, 0));
		btnNewButton.setBounds(168, 571, 155, 30);
		panel_1.add(btnNewButton);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAnnuler.setForeground(new Color(0, 0, 0));
		btnAnnuler.setBackground(Color.GRAY);
		btnAnnuler.setBounds(584, 576, 155, 30);
		panel_1.add(btnAnnuler);
		
		adressValue = new JTextField();
		adressValue.setBounds(168, 175, 571, 70);
		panel_1.add(adressValue);
		adressValue.setColumns(10);
		
	}
}
