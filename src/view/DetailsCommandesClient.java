package view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.PanelsManager;
import controller.ProduitDAO;
import model.Client;
import model.CommandeClient;
import model.Produit;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetailsCommandesClient extends JPanel {
	static CommandeClient currentCommande = new CommandeClient();
	static Client currentClient = new Client();
	JButton button = new JButton();
	JComboBox<String> comboBox = new JComboBox<>();
	JLabel prixTotal_label;

	/**
	 * Create the panel.
	 */
	public DetailsCommandesClient() {
		setBounds(0, 0, 1440, 900);
		setLayout(null);
		URL exitURL = ClassLoader.getSystemResource("exit.png");
		ImageIcon accueilImage = new ImageIcon(exitURL);
		Image accueilImageImage = accueilImage.getImage();
		Image newimg4 = accueilImageImage.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
		accueilImage = new ImageIcon(newimg4);
		URL historyURL = ClassLoader.getSystemResource("history.png");
		ImageIcon imageIcon = new ImageIcon(historyURL);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		button.addActionListener(
				event -> JOptionPane.showMessageDialog(null, "Do you want to modify this line?")
		);
		URL returnURL = ClassLoader.getSystemResource("arrow_left.png");
		ImageIcon imageReturn = new ImageIcon(returnURL);
		Image imageReturnImage = imageReturn.getImage();
		Image newimg3 = imageReturnImage.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
		imageReturn = new ImageIcon(newimg3);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(254, 245, 232));
		panel.setBounds(0, 89, 1440, 811);
		add(panel);

		JLabel lblNewLabel_1 = new JLabel("Client");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(10, 11, 44, 22);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setBackground(Color.ORANGE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(10, 11, 553, 148);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Nom :");
		lblNewLabel_2.setBounds(11, 26, 46, 14);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Adresse :");
		lblNewLabel_3.setBounds(10, 64, 57, 14);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("T\u00E9l\u00E9phone :");
		lblNewLabel_4.setBounds(10, 110, 72, 14);
		panel_2.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Pr\u00E9nom :");
		lblNewLabel_5.setBounds(237, 26, 78, 14);
		panel_2.add(lblNewLabel_5);

		JLabel adresseLabel = new JLabel("");
		adresseLabel.setBounds(80, 55, 229, 33);
		panel_2.add(adresseLabel);

		JLabel phoneLabel = new JLabel("");
		phoneLabel.setBounds(90, 95, 100, 44);
		panel_2.add(phoneLabel);

		JLabel prenomLabel = new JLabel("");
		prenomLabel.setBounds(300, 8, 118, 50);
		panel_2.add(prenomLabel);

		JLabel nameLabel = new JLabel("");
		nameLabel.setBounds(60, 20, 229, 25);
		panel_2.add(nameLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 1014, 526);
		panel.add(scrollPane);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(573, 11, 857, 148);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Date du jour :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_6.setBounds(10, 11, 189, 37);
		panel_3.add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("Date de retrait :");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_6_1.setBounds(10, 79, 189, 37);
		panel_3.add(lblNewLabel_6_1);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		String created_at = dtf.format(now);
		JLabel created_at_label = new JLabel(created_at);
		created_at_label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		created_at_label.setBounds(271, 11, 189, 37);
		panel_3.add(created_at_label);

		JLabel id_commande_label = new JLabel("");
		id_commande_label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		id_commande_label.setBounds(350, 100, 189, 37);
		panel_3.add(id_commande_label);

		JLabel withdrawal_at_label = new JLabel("");
		withdrawal_at_label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		withdrawal_at_label.setBounds(271, 79, 166, 45);
		panel_3.add(withdrawal_at_label);

		JLabel lblNewLabel_7 = new JLabel("Total :");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(1199, 228, 116, 35);
		panel.add(lblNewLabel_7);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(1128, 274, 208, 99);
		panel.add(panel_4);
		panel_4.setLayout(null);

		prixTotal_label = new JLabel("");
		prixTotal_label.setHorizontalAlignment(SwingConstants.CENTER);
		prixTotal_label.setFont(new Font("Tahoma", Font.PLAIN, 30));
		prixTotal_label.setBounds(38, 11, 125, 77);
		panel_4.add(prixTotal_label);

		JLabel lblNewLabel_8 = new JLabel("M\u00E9thode de paiment");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(1128, 438, 208, 25);
		panel.add(lblNewLabel_8);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(1159, 488, 156, 90);
		panel.add(panel_5);
		panel_5.setLayout(null);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Esp\u00E8ces");
		rdbtnNewRadioButton.setEnabled(false);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNewRadioButton.setBounds(18, 22, 109, 23);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Carte banquaire");
		rdbtnNewRadioButton_1.setEnabled(false);
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNewRadioButton_1.setBounds(18, 48, 132, 23);

		rdbtnNewRadioButton.addActionListener(e -> rdbtnNewRadioButton_1.setSelected(false));

		rdbtnNewRadioButton_1.addActionListener(e -> rdbtnNewRadioButton.setSelected(false));

		panel_5.add(rdbtnNewRadioButton);
		panel_5.add(rdbtnNewRadioButton_1);

		JTable table_1 = new JTable();
		table_1.setEnabled(false);
		table_1.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table_1);
		table_1.setRowHeight(100);
		table_1.setModel(liste());
		float prixTotal = 0;
		for (int i = 0; i < table_1.getRowCount(); i++) {
			System.out.println(table_1.getValueAt(i, 5));
			prixTotal += Float.parseFloat(table_1.getValueAt(i, 5).toString());
		}
		prixTotal_label.setText(String.valueOf(prixTotal));
		if (currentCommande.getTypePaiment().equals("Especes")) {
			rdbtnNewRadioButton.setSelected(true);
		} else if (currentCommande.getTypePaiment().equals("Carte banquaire")) {
			rdbtnNewRadioButton_1.setSelected(true);
		}
		nameLabel.setText(currentClient.getName());
		prenomLabel.setText(currentClient.getFirstName());
		adresseLabel.setText(currentClient.getAdress());
		phoneLabel.setText(currentClient.getTel());
		Date date = currentCommande.getWithdrawal_at();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = dateFormat.format(date);
		withdrawal_at_label.setText(strDate);
		
		JLabel lblNewLabel = new JLabel("Commande N\u00B0");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(470, 47, 141, 31);
		panel_3.add(lblNewLabel);
		
		JLabel idCommandeLabel = new JLabel("");
		idCommandeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idCommandeLabel.setBounds(605, 46, 46, 32);
		panel_3.add(idCommandeLabel);
		
		JPanel menu = new JPanel();
		menu.setLayout(null);
		menu.setBackground(Color.WHITE);
		menu.setBounds(0, 0, 1440, 90);
		add(menu);
		
		JButton btnRetour = new JButton("");
		btnRetour.setIcon(new ImageIcon(returnURL));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToCommandesClientPanel());
				PanelsManager.contentPane.revalidate();
				PanelsManager.contentPane.repaint();
			}
		});
		btnRetour.setBounds(22, 11, 40, 40);
		menu.add(btnRetour);
		
		JLabel lblRetour = new JLabel("Retour");
		lblRetour.setHorizontalAlignment(SwingConstants.CENTER);
		lblRetour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRetour.setBounds(10, 62, 63, 18);
		menu.add(lblRetour);
		
		JButton btnAccueil = new JButton("");
		btnAccueil.setIcon(new ImageIcon(exitURL));
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToAccueilMenu());
				PanelsManager.contentPane.revalidate();
				PanelsManager.contentPane.repaint();
			}
		});
		btnAccueil.setBounds(1370, 11, 40, 40);
		menu.add(btnAccueil);
		idCommandeLabel.setText(String.valueOf(currentCommande.getId()));
		JLabel lblAccueil = new JLabel("Accueil");
		lblAccueil.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAccueil.setBounds(1355, 62, 63, 18);
		menu.add(lblAccueil);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table_1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table_1.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
		table_1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table_1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table_1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table_1.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
	}

	public DefaultTableModel liste() {
		String[] col = {
				"Quantité",
				"Libellé",
				"Prix Unitaire",
				"Prix total HT",
				"Prix total TTC",
				"Montant réglé"
		};
		DefaultTableModel tab = new DefaultTableModel(null, col);

		ProduitDAO produitDAO = new ProduitDAO();
		List<Produit> listProduits = new ArrayList<>(produitDAO.read());
		for (Produit article: listProduits) {
			comboBox.addItem(article.getLibelle());
		}
		Gson gson = new Gson();
		Type type = new TypeToken < ArrayList < ArrayList < Produit >>> () {}.getType();
		ArrayList < ArrayList < Produit >> productsList = gson.fromJson(currentCommande.getProduits(), type);
		for (ArrayList < Produit > produit: productsList) {
			Vector<java.io.Serializable> vect = new Vector<>();
			vect.add(produit.get(0).getQuantite());
			vect.add(produit.get(0).getLibelle());
			vect.add(produit.get(0).getPrixHT());
			vect.add(produit.get(0).getPrixHT() * produit.get(0).getQuantite());
			vect.add(produit.get(0).getPrixTTC());
			vect.add(produit.get(0).getPrixTTC() * produit.get(0).getQuantite());
			tab.addRow(vect);
		}

		return tab;

	}
}