package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.google.gson.Gson;

import controller.CommandeStockDao;
import controller.FournisseurDao;
import controller.PanelsManager;

import model.CommandeStock;
import model.Fournisseur;
import model.Produit;


public class NewCommandeStock extends JPanel {
	static public boolean modify = false;
	static Fournisseur currentFournisseur = new Fournisseur();
	static CommandeStock currentCmdStock = new CommandeStock();
	private JTable cmd;
	private JLabel prixTotal_label;
	ArrayList<Integer> idList = new ArrayList<Integer>();
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
		lblFournisseur.setBounds(10, 11, 80, 22);
		corps.add(lblFournisseur);
		lblFournisseur.setBackground(new Color(254, 245, 232));
		lblFournisseur.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel infosFournisseur = new JPanel();
		infosFournisseur.setBackground(new Color(255, 255, 255));
		infosFournisseur.setBounds(10, 11, 553, 165);
		corps.add(infosFournisseur);
		infosFournisseur.setLayout(null);
		
		JLabel lblSociete = new JLabel("Soci�t� :");
		lblSociete.setBounds(11, 26, 57, 14);
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
		infoSociete.setBounds(90, 22, 290, 22);
		
		JLabel adresseLabel = new JLabel("");
		adresseLabel.setBounds(90, 55, 416, 33);
		infosFournisseur.add(adresseLabel);

		JLabel phoneLabel = new JLabel("");
		phoneLabel.setBounds(90, 99, 163, 21);
		infosFournisseur.add(phoneLabel);

		JLabel mailLabel = new JLabel("");
		mailLabel.setBounds(90, 133, 312, 21);
		infosFournisseur.add(mailLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 188, 1014, 436);
		corps.add(scrollPane);

		infoSociete.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) { // Check if the value got selected, ignore if it has been deselected
					for (Fournisseur fournisseur: listSociete) {
						if (fournisseur.getSociete().equals(e.getItem().toString())) {
							currentFournisseur.setId(fournisseur.getId());
							currentFournisseur.setSociete(fournisseur.getSociete());
							currentFournisseur.setCorrespondant(fournisseur.getCorrespondant());
							currentFournisseur.setAdresse(fournisseur.getAdresse());
							currentFournisseur.setCodePostal(fournisseur.getCodePostal());
							currentFournisseur.setVille(fournisseur.getVille());
							currentFournisseur.setTel(fournisseur.getTel());
							currentFournisseur.setEmail(fournisseur.getEmail());
							adresseLabel.setText(fournisseur.getAdresse()+" "+fournisseur.getCodePostal()+" "+fournisseur.getVille());
							phoneLabel.setText(fournisseur.getTel());
							mailLabel.setText(fournisseur.getEmail());
						}
					}
				}
			}
		});
		infosFournisseur.add(infoSociete);
		
		JPanel datePanel = new JPanel();
		datePanel.setBackground(new Color(255, 255, 255));
		datePanel.setBounds(723, 11, 693, 165);
		corps.add(datePanel);
		datePanel.setLayout(null);

		JLabel lblDateReception = new JLabel("Date de r�ception :");
		lblDateReception.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDateReception.setBounds(10, 11, 189, 37);
		datePanel.add(lblDateReception);
		
		JLabel lblNumCmd = new JLabel("Commande n � :");
		lblNumCmd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNumCmd.setBounds(178, 117, 189, 37);
		datePanel.add(lblNumCmd);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		String created_at = dtf.format(now);
		JLabel created_at_label = new JLabel(created_at);
		created_at_label.setHorizontalAlignment(SwingConstants.CENTER);
		created_at_label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		created_at_label.setBounds(271, 11, 189, 37);
		datePanel.add(created_at_label);
		
		JLabel id_commande_label = new JLabel("");
		id_commande_label.setHorizontalAlignment(SwingConstants.CENTER);
		id_commande_label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		id_commande_label.setBounds(349, 117, 99, 37);
		datePanel.add(id_commande_label);
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotal.setBounds(1199, 196, 116, 35);
		corps.add(lblTotal);
		
		JPanel totalPanel = new JPanel();
		totalPanel.setBackground(new Color(255, 255, 255));
		totalPanel.setBounds(1128, 238, 208, 99);
		corps.add(totalPanel);
		totalPanel.setLayout(null);
		
		prixTotal_label = new JLabel("");
		prixTotal_label.setHorizontalAlignment(SwingConstants.CENTER);
		prixTotal_label.setFont(new Font("Tahoma", Font.PLAIN, 30));
		prixTotal_label.setBounds(38, 11, 125, 77);
		totalPanel.add(prixTotal_label);
		float prixTotal = 0;
		for (int i = 0; i < cmd.getRowCount(); i++) {
			prixTotal += Float.parseFloat(cmd.getValueAt(i, 4).toString());
			System.out.println(cmd.getValueAt(i, 4));
		}
		prixTotal_label.setText(String.valueOf(prixTotal));
		
		cmd = new JTable();
		cmd.setBackground(new Color(255, 255, 255));
		cmd.setRowSelectionAllowed(false);
		scrollPane.setViewportView(cmd);
		cmd.setRowHeight(100);
		cmd.setModel(liste());
		
		JButton btnAddProduit = new JButton("Ajouter un produit");
		btnAddProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relisting();
			}
		});
		btnAddProduit.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAddProduit.setBounds(106, 675, 760, 77);
		corps.add(btnAddProduit);
		
		JButton btnValider = new JButton();
		if (modify) {
			btnValider.setText("Modifier");
		} else {
			btnValider.setText("Valider");
		}
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommandeStockDao commandeStockDao = new CommandeStockDao();

				DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
				DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
				int m = cmd.getRowCount(), n = cmd.getColumnCount();
				ArrayList < ArrayList < Produit >> matrix = new ArrayList < ArrayList < Produit >> ();

				for (int i = 0; i < m; i++) {
					ArrayList < Produit > row = new ArrayList < Produit > ();
					Produit produit = new Produit();
					produit.setId(idList.get(i));
					produit.setQuantite(Integer.parseInt(cmd.getValueAt(i, 0).toString()));
					produit.setLibelle((String) cmd.getValueAt(i, 1));
					produit.setPrixHT(Float.parseFloat(cmd.getValueAt(i, 2).toString()));
					produit.setPrixTTC(Float.parseFloat(cmd.getValueAt(i, 4).toString()));
					
					row.add(produit);
					matrix.add(row);
				}

				String json = new Gson().toJson(matrix);
				System.out.println(json);
				Date newDate = Date.valueOf(LocalDateTime.now());
				if (modify)
					commandeStockDao.update(new CommandeStock(currentCmdStock.getId(),now, currentFournisseur.getId(), cmd.getRowCount(), Float.parseFloat(prixTotal_label.getText()), json), currentCmdStock.getId());
				else
					commandeStockDao.create(new CommandeStock(currentCmdStock.getId(),now, currentFournisseur.getId(), cmd.getRowCount(), Float.parseFloat(prixTotal_label.getText()), json));
			}
		});
		btnValider.setBackground(Color.ORANGE);
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnValider.setBounds(1102, 557, 249, 56);
		corps.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnAnnuler.setBounds(1102, 630, 249, 56);
		corps.add(btnAnnuler);
	}
}
