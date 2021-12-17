package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controller.ClientDao;
import controller.FournisseurDao;
import controller.PanelsManager;
import model.Client;
import model.CommandeStock;
import model.Fournisseur;

public class NewCommandeStock extends JPanel {
	static public boolean modify = false;
	static Fournisseur currentFournisseur = new Fournisseur();
	static CommandeStock currentCmdStock = new CommandeStock();
	/**
	 * Create the panel.
	 */
	public NewCommandeStock() {
		setBounds(0, 0, 1440, 900);
		setBackground(new Color(254, 245, 232));
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
				PanelsManager.contentPane.add(PanelsManager.switchtoCommandeStockView());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
				//modify = false;
			}
		});
		btnRetour.setIcon(new ImageIcon("C:\\Users\\fredb\\AFPA\\workspace-java\\Boulangestion\\projetBoulang\\arrow_left.png"));
		btnRetour.setBounds(22, 11, 40, 40);
		menu.add(btnRetour);
		
		JLabel lblRetour = new JLabel("Retour");
		lblRetour.setHorizontalAlignment(SwingConstants.CENTER);
		lblRetour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRetour.setBounds(10, 62, 63, 18);
		menu.add(lblRetour);
		
		JButton btnAccueil = new JButton("");
		btnAccueil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToAccueilMenu());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnAccueil.setIcon(new ImageIcon("C:\\Users\\fredb\\AFPA\\workspace-java\\Boulangestion\\projetBoulang\\exit.png"));
		btnAccueil.setBounds(1370, 11, 40, 40);
		menu.add(btnAccueil);
		
		JLabel lblAccueil = new JLabel("Accueil");
		lblAccueil.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAccueil.setBounds(1355, 62, 63, 18);
		menu.add(lblAccueil);
		
		JPanel corps = new JPanel();
		corps.setBackground(new Color(254, 245, 232));
		corps.setBounds(0, 90, 1440, 810);
		add(corps);
		corps.setLayout(null);
		
		JLabel lblFournisseur = new JLabel("Fournisseur");
		lblFournisseur.setForeground(Color.BLACK);
		lblFournisseur.setBounds(10, 11, 66, 22);
		corps.add(lblFournisseur);
		lblFournisseur.setBackground(new Color(254, 245, 232));
		lblFournisseur.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel infosFournisseur = new JPanel();
		infosFournisseur.setBackground(new Color(255, 255, 255));
		infosFournisseur.setBounds(10, 11, 553, 165);
		corps.add(infosFournisseur);
		infosFournisseur.setLayout(null);
		
		JLabel lblSociete = new JLabel("Société :");
		lblSociete.setBounds(11, 26, 46, 14);
		infosFournisseur.add(lblSociete);

		JLabel lblAdress = new JLabel("Adresse :");
		lblAdress.setBounds(11, 63, 57, 14);
		infosFournisseur.add(lblAdress);

		JLabel lblTel = new JLabel("T\u00E9l\u00E9phone :");
		lblTel.setBounds(11, 102, 72, 14);
		infosFournisseur.add(lblTel);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(11, 140, 72, 14);
		infosFournisseur.add(lblEmail);
		
		JComboBox infoSociete = new JComboBox();
		FournisseurDao fournisseurDao = new FournisseurDao();
		List <Fournisseur> listSociete = new ArrayList < > ();
		listSociete.addAll(fournisseurDao.read());
		if (modify) {
			infoSociete.addItem(currentFournisseur.getSociete());
			for (Fournisseur fournisseur: listSociete) {
				if (fournisseur.getId() != currentFournisseur.getId())
					infoSociete.addItem(fournisseur.getSociete());
			}
		} else {
			infoSociete.addItem(null);
			for (Fournisseur fournisseur: listSociete) {
				infoSociete.addItem(fournisseur.getSociete());
			}
		}
		infoSociete.setBounds(56, 22, 118, 22);
		
		JLabel adresseLabel = new JLabel("");
		adresseLabel.setBounds(90, 55, 416, 33);
		infosFournisseur.add(adresseLabel);

		JLabel phoneLabel = new JLabel("");
		phoneLabel.setBounds(90, 95, 163, 21);
		infosFournisseur.add(phoneLabel);

		JLabel mailLabel = new JLabel("");
		mailLabel.setBounds(90, 133, 312, 21);
		infosFournisseur.add(mailLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 1014, 452);
		corps.add(scrollPane);

		infoSociete.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) { // Check if the value got selected, ignore if it has been deselected
					for (Fournisseur fournisseur: listSociete) {
						if (fournisseur.getSociete().equals(e.getItem().toString())) {
							currentFournisseur.setName(client.getName());
							currentFournisseur.setEmail(client.getEmail());
							currentFournisseur.setFirstName(client.getFirstName());
							currentFournisseur.setTel(client.getTel());
							currentFournisseur.setId(client.getId());
							currentFournisseur.setTel(client.getTel());
							currentFournisseur.setAdress(client.getAdress());
							currentFournisseur.setCity(client.getCity());
							currentFournisseur.setZip(client.getZip());
							adresseLabel.setText(fournisseur.getAdress());
							phoneLabel.setText(fournisseur.getTel());
							mailLabel.setText(fournisseur.getFirstName());
						}
					}
				}
			}
		});
		infosFournisseur.add(infoSociete);
	}
}
