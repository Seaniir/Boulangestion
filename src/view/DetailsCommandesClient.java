package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import controller.ClientDao;
import controller.CommandeClientDAO;
import controller.PanelsManager;
import controller.ProduitDAO;
import model.Client;
import model.CommandeClient;
import model.Produit;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.util.*;
import java.util.List;

public class DetailsCommandesClient extends JPanel {
	static CommandeClient currentCommande = new CommandeClient();
	static Client currentClient = new Client();
	private JTable table_1;
	JButton button = new JButton();
	JComboBox comboBox = new JComboBox();
	JLabel prixTotal_label = new JLabel();

	/**
	 * Create the panel.
	 */
	public DetailsCommandesClient() {
		setBounds(0, 0, 1440, 900);
		setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 1440, 99);
		add(panel_1);

		JButton accueilBtn = new JButton("");
		ImageIcon accueilImage = new ImageIcon("C:\\Users\\Quentin\\Downloads\\sign-out.png");
		Image accueilImageImage = accueilImage.getImage(); // transform it
		Image newimg4 = accueilImageImage.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		accueilImage = new ImageIcon(newimg4); // transform it back
		accueilBtn.setIcon(accueilImage);
		accueilBtn.setBounds(1333, 0, 107, 75);
		panel_1.add(accueilBtn);
		accueilBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToAccueilMenu());
				PanelsManager.contentPane.revalidate();
				PanelsManager.contentPane.repaint();
			}
		});

		JButton historiqueBtn = new JButton("");
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\Quentin\\Downloads\\history.png");
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back
		historiqueBtn.setIcon(imageIcon);
		historiqueBtn.setBounds(0, 0, 127, 75);
		panel_1.add(historiqueBtn);
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						JOptionPane.showMessageDialog(null, "Do you want to modify this line?");
					}
				}
		);
		ImageIcon imageClient = new ImageIcon("C:\\Users\\Quentin\\Downloads\\user.png");
		Image imageClientImage = imageClient.getImage(); // transform it
		Image newimg2 = imageClientImage.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageClient = new ImageIcon(newimg2);

		JButton returnBtn = new JButton("");
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToCommandesClientPanel());
				PanelsManager.contentPane.revalidate();
				PanelsManager.contentPane.repaint();
			}
		});
		ImageIcon imageReturn = new ImageIcon("C:\\Users\\Quentin\\Downloads\\return.png");
		Image imageReturnImage = imageReturn.getImage(); // transform it
		Image newimg3 = imageReturnImage.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageReturn = new ImageIcon(newimg3); // transform it back
		returnBtn.setBounds(127, 0, 127, 75);
		returnBtn.setIcon(imageReturn);
		panel_1.add(returnBtn);

		JLabel lblNewLabel_9 = new JLabel("Historique");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(0, 74, 127, 25);
		panel_1.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Retour");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(127, 74, 127, 25);
		panel_1.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Accueil");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(1333, 74, 107, 25);
		panel_1.add(lblNewLabel_11);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 248, 220));
		panel.setBounds(0, 100, 1440, 800);
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
		ClientDao clientDao = new ClientDao();
		List < Client > listClients = new ArrayList < > ();
		listClients.addAll(clientDao.read());

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

		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton_1.setSelected(false);
			}
		});

		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton.setSelected(false);
			}
		});

		panel_5.add(rdbtnNewRadioButton);
		panel_5.add(rdbtnNewRadioButton_1);

		table_1 = new JTable();
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
		UtilDateModel model = new UtilDateModel();
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
		List < Produit > listProduits = new ArrayList < > ();
		listProduits.addAll(produitDAO.read());
		for (Produit article: listProduits) {
			comboBox.addItem(article.getLibelle());
		}
		Gson gson = new Gson();
		Type type = new TypeToken < ArrayList < ArrayList < Produit >>> () {}.getType();
		ArrayList < ArrayList < Produit >> productsList = gson.fromJson(currentCommande.getProduits(), type);
		for (ArrayList < Produit > produit: productsList) {
			Vector vect = new Vector();
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

	public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

		private String datePattern = "dd/MM/yyy";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}
}