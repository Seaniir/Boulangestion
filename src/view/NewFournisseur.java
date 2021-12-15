package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.FournisseurDao;
import controller.PanelsManager;
import model.Fournisseur;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class NewFournisseur extends JPanel {
	private JTextField societeValue;
	private JTextField correspValue;
	private JTextField adressValue;
	private JTextField cpValue;
	private JTextField villeValue;
	private JTextField telValue;
	private JTextField emailValue;
	private JButton btnValider;
	public static boolean modify = false;
	
	/**
	 * Create the panel.
	 */
	public NewFournisseur() {
		setBackground(new Color(254, 245, 232));

		setBounds(0, 0, 1440, 900);
		setLayout(null);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(255, 255, 255));
		menu.setBounds(0, 0, 1440, 90);
		add(menu);
		menu.setLayout(null);
		
		JButton btnRetour = new JButton("");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToListeFournisseurs());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
				modify = false;
			}
		});
		btnRetour.setIcon(new ImageIcon("C:\\Users\\fredb\\AFPA\\workspace-java\\Boulangestion\\projetBoulang\\arrow_left.png"));
		btnRetour.setBounds(22, 11, 40, 40);
		menu.add(btnRetour);
		
		JLabel lblNewLabel = new JLabel("Retour");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 62, 63, 18);
		menu.add(lblNewLabel);
		
		JButton btnAccueil = new JButton("");
		btnAccueil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToAccueilMenu());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
				modify = false;
			}
		});
		btnAccueil.setIcon(new ImageIcon("C:\\Users\\fredb\\AFPA\\workspace-java\\Boulangestion\\projetBoulang\\exit.png"));
		btnAccueil.setBounds(1370, 11, 40, 40);
		menu.add(btnAccueil);
		
		JLabel lblNewLabel_1 = new JLabel("Accueil");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(1355, 62, 63, 18);
		menu.add(lblNewLabel_1);
		
		JPanel formulaire = new JPanel();
		formulaire.setBackground(new Color(255, 255, 255));
		formulaire.setBounds(264, 155, 912, 706);
		add(formulaire);
		formulaire.setLayout(null);
		
		JLabel lblSociete = new JLabel("Soci\u00E9t\u00E9 :");
		lblSociete.setBackground(new Color(255, 255, 255));
		lblSociete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSociete.setBounds(40, 33, 77, 23);
		formulaire.add(lblSociete);
		
		societeValue = new JTextField();
		societeValue.setBounds(277, 24, 540, 32);
		formulaire.add(societeValue);
		societeValue.setColumns(10);
		
		JLabel lblCorresp = new JLabel("Correspondant :");
		lblCorresp.setBackground(new Color(255, 255, 255));
		lblCorresp.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorresp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCorresp.setBounds(40, 98, 129, 23);
		formulaire.add(lblCorresp);
		
		correspValue = new JTextField();
		correspValue.setColumns(10);
		correspValue.setBounds(277, 85, 540, 32);
		formulaire.add(correspValue);
		
		JLabel lblAdresse = new JLabel("Adresse de facturation :");
		lblAdresse.setBackground(new Color(255, 255, 255));
		lblAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAdresse.setBounds(35, 166, 198, 23);
		formulaire.add(lblAdresse);
		
		adressValue = new JTextField();
		adressValue.setColumns(10);
		adressValue.setBounds(277, 157, 540, 149);
		formulaire.add(adressValue);
		
		JLabel lblCp = new JLabel("Code postal :");
		lblCp.setHorizontalAlignment(SwingConstants.CENTER);
		lblCp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCp.setBackground(Color.WHITE);
		lblCp.setBounds(40, 345, 112, 23);
		formulaire.add(lblCp);
		
		cpValue = new JTextField();
		cpValue.setColumns(10);
		cpValue.setBounds(197, 336, 136, 32);
		formulaire.add(cpValue);
		
		JLabel lblVille = new JLabel("Ville :");
		lblVille.setHorizontalAlignment(SwingConstants.CENTER);
		lblVille.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVille.setBackground(Color.WHITE);
		lblVille.setBounds(380, 345, 67, 23);
		formulaire.add(lblVille);
		
		villeValue = new JTextField();
		villeValue.setColumns(10);
		villeValue.setBounds(480, 336, 337, 32);
		formulaire.add(villeValue);
		
		JLabel lblTel = new JLabel("T\u00E9l\u00E9phone :");
		lblTel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTel.setBackground(Color.WHITE);
		lblTel.setBounds(40, 429, 104, 23);
		formulaire.add(lblTel);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBackground(Color.WHITE);
		lblEmail.setBounds(40, 507, 67, 23);
		formulaire.add(lblEmail);
		
		telValue = new JTextField();
		telValue.setInputVerifier(new InputVerifier() {
			//verifie le tel à la saisie
			@Override
			public boolean verify(JComponent input) {
				String tel = ((JTextField) input).getText().trim();
				if(tel.matches("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$") || tel.isEmpty()) {
					return true;
				}else {
					JOptionPane.showMessageDialog(null, "Téléphone invalide", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		});
		telValue.setColumns(10);
		telValue.setBounds(277, 420, 540, 32);
		formulaire.add(telValue);
		
		emailValue = new JTextField();
		//vérifie le mail à la saisie
		emailValue.setInputVerifier(new InputVerifier() {
			
			@Override
			public boolean verify(JComponent input) {
				String mail = ((JTextField) input).getText().trim();
				if(mail.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+[.]+[a-zA-Z0-9]{2,6}+$") || mail.isEmpty()) {
					return true;
				}else {
					JOptionPane.showMessageDialog(null, "Email invalide", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		});
		emailValue.setColumns(10);
		emailValue.setBounds(277, 498, 540, 32);
		formulaire.add(emailValue);
		
		
		btnValider = new JButton("Valider");
		if(modify = false) {
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String societeSaisie = societeValue.getText();
				String correspSaisie = correspValue.getText();
				String adresseSaisie = adressValue.getText();
				int cpSaisie = Integer.valueOf(cpValue.getText());
				String villeSaisie = villeValue.getText();
				String telSaisie = telValue.getText();
				String emailSaisie = emailValue.getText();
				
				Fournisseur nouveau = new Fournisseur(societeSaisie, correspSaisie, adresseSaisie, cpSaisie, villeSaisie, telSaisie, emailSaisie);
				
				FournisseurDao fournisseurDao = new FournisseurDao();
				//ajoute le fournisseur à la bdd
				fournisseurDao.create(nouveau);
				clearFields();
				}
			});
		}else {
			btnValider.setText("Modifier");
			FournisseurDao fournisseurDaoModified = new FournisseurDao();
			fillFields(FournisseurDao.currentFournisseur);
			String societeSaisie = societeValue.getText();
			String correspSaisie = correspValue.getText();
			String adresseSaisie = adressValue.getText();
			int cpSaisie = Integer.valueOf(cpValue.getText());
			String villeSaisie = villeValue.getText();
			String telSaisie = telValue.getText();
			String emailSaisie = emailValue.getText();
			Fournisseur nouveau = new Fournisseur(societeSaisie, correspSaisie, adresseSaisie, cpSaisie, villeSaisie, telSaisie, emailSaisie);
			fournisseurDaoModified.update(nouveau, FournisseurDao.currentFournisseur.getId());		
		}
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnValider.setBackground(new Color(242, 193, 102));
		btnValider.setBounds(197, 593, 191, 50);
		formulaire.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//pop-up de confirmation: si oui vide les champs, si non, ne fait rien
				if (JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir annuler?", "Annulation",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					clearFields();
					modify = false;
				}
			}
		});
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAnnuler.setBounds(590, 593, 191, 50);
		formulaire.add(btnAnnuler);
		
	}
	//méthode pour vider les champs
	public void clearFields() {
		societeValue.setText(null);
		correspValue.setText(null);
		adressValue.setText(null);
		cpValue.setText(null);
		villeValue.setText(null);
		telValue.setText(null);
		emailValue.setText(null);
		btnValider.setText("Valider");
	}
	
	public void fillFields(Fournisseur fournisseur) {
		societeValue.setText(fournisseur.getSociete());
		correspValue.setText(fournisseur.getCorrespondant());
		adressValue.setText(fournisseur.getAdresse());
		cpValue.setText(String.valueOf(fournisseur.getCodePostal()));
		villeValue.setText(fournisseur.getVille());
		telValue.setText(fournisseur.getTel());
		emailValue.setText(fournisseur.getEmail());	
	}
}
