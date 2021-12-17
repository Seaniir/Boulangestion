package view;

import com.google.gson.Gson;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.util.*;
import java.util.List;

public class NouvelleCommandeClientView extends JPanel {
	static public boolean modify = false;
	static Client currentClient = new Client();
	static CommandeClient currentCommande = new CommandeClient();
	private JTable table;
	JButton button = new JButton();
	JComboBox comboBox = new JComboBox();
	JLabel prixTotal_label = new JLabel();
	ArrayList<Integer> idList = new ArrayList<Integer>();
	ArrayList<Float> poidsList = new ArrayList<Float>();

	/**
	 * Create the panel.
	 */
	public NouvelleCommandeClientView() {
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
				currentClient = new Client();
				currentCommande = new CommandeClient();
				modify = false;
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
				currentClient = new Client();
				currentCommande = new CommandeClient();
				modify = false;
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

		JComboBox comboBox_1 = new JComboBox();
		ClientDao clientDao = new ClientDao();
		List < Client > listClients = new ArrayList < > ();
		listClients.addAll(clientDao.read());
		if (modify) {
			comboBox_1.addItem(currentClient.getName());
			for (Client clients: listClients) {
				if (clients.getId() != currentClient.getId())
					comboBox_1.addItem(clients.getName());
			}
		} else {
			comboBox_1.addItem(null);
			for (Client clients: listClients) {
				comboBox_1.addItem(clients.getName());
			}
		}
		comboBox_1.setBounds(56, 22, 118, 22);

		JLabel adresseLabel = new JLabel("");
		adresseLabel.setBounds(80, 55, 229, 33);
		panel_2.add(adresseLabel);

		JLabel phoneLabel = new JLabel("");
		phoneLabel.setBounds(90, 95, 100, 44);
		panel_2.add(phoneLabel);

		JLabel prenomLabel = new JLabel("");
		prenomLabel.setBounds(300, 8, 118, 50);
		panel_2.add(prenomLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 1014, 452);
		panel.add(scrollPane);

		comboBox_1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) { // Check if the value got selected, ignore if it has been deselected
					for (Client client: listClients) {
						if (client.getName().equals(e.getItem().toString())) {
							currentClient.setName(client.getName());
							currentClient.setEmail(client.getEmail());
							currentClient.setFirstName(client.getFirstName());
							currentClient.setId(client.getId());
							currentClient.setAdress(client.getAdress());
							currentClient.setCity(client.getCity());
							currentClient.setZip(client.getZip());
							adresseLabel.setText(client.getAdress());
							prenomLabel.setText(client.getFirstName());
							phoneLabel.setText(client.getTel());
						}
					}
				}
			}
		});
		panel_2.add(comboBox_1);

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

		JLabel lblNewLabel_7 = new JLabel("Total :");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(1199, 196, 116, 35);
		panel.add(lblNewLabel_7);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(1128, 238, 208, 99);
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
		lblNewLabel_8.setBounds(1128, 381, 208, 25);
		panel.add(lblNewLabel_8);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(1159, 417, 156, 90);
		panel.add(panel_5);
		panel_5.setLayout(null);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Esp\u00E8ces");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNewRadioButton.setBounds(18, 22, 109, 23);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Carte banquaire");
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

		table = new JTable();
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		table.setRowHeight(100);
		table.setModel(liste());

		JButton btnNewButton = new JButton("Ajouter un produit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relisting();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBounds(105, 630, 760, 77);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton();
		if (modify) {
			btnNewButton_1.setText("Modifier");
		} else {
			btnNewButton_1.setText("Valider");
		}

		String strDate = null;
		if (modify) {
			if (currentCommande.getTypePaiment().equals("Espèces")) {
				rdbtnNewRadioButton.setSelected(true);
			} else if (currentCommande.getTypePaiment().equals("Carte banquaire")) {
				rdbtnNewRadioButton_1.setSelected(true);
			}
			prenomLabel.setText(currentClient.getFirstName());
			adresseLabel.setText(currentClient.getAdress());
			phoneLabel.setText(currentClient.getTel());
			Date date = currentCommande.getWithdrawal_at();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			strDate = dateFormat.format(date);
		}

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(840, 200, 150, 30);
		datePicker.getJFormattedTextField().setText(strDate);
		add(datePicker);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommandeClientDAO commandeClientDAO = new CommandeClientDAO();
				String typePaiment = "";
				if (rdbtnNewRadioButton.isSelected()) {
					typePaiment = "Especes";
				} else if (rdbtnNewRadioButton_1.isSelected()) {
					typePaiment = "Carte banquaire";
				}
				DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

				DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
				System.out.println(datePicker.getJFormattedTextField().getText());
				int m = table.getRowCount(), n = table.getColumnCount();
				ArrayList < ArrayList < Produit >> matrix = new ArrayList < ArrayList < Produit >> ();

				for (int i = 0; i < m; i++) {
					ArrayList < Produit > row = new ArrayList < Produit > ();
					Produit produit = new Produit();
					produit.setId(idList.get(i));
					produit.setPoids(poidsList.get(i));
					produit.setPrixHT(Float.parseFloat(table.getValueAt(i, 2).toString()));
					produit.setPrixTTC(Float.parseFloat(table.getValueAt(i, 4).toString()));
					produit.setQuantite(Integer.parseInt(table.getValueAt(i, 0).toString()));
					produit.setLibelle((String) table.getValueAt(i, 1));
					//produit.setPrixUnitaire((Float) table.getValueAt(i, 2));
					row.add(produit);
					matrix.add(row);
				}

				System.out.println(idList);
				String json = new Gson().toJson(matrix);
				System.out.println(json);
				Date date = null;
				try {
					date = inputFormat.parse(datePicker.getJFormattedTextField().getText());
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				if (modify)
					commandeClientDAO.update(new CommandeClient(date, date, currentClient.getId(), table.getRowCount(), Float.parseFloat(prixTotal_label.getText()), true, "En cours", typePaiment, json), currentCommande.getId());
				else
					commandeClientDAO.add(new CommandeClient(date, date, currentClient.getId(), table.getRowCount(), Float.parseFloat(prixTotal_label.getText()), true, "En cours", typePaiment, json));
			}
		});
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1.setBounds(1102, 557, 249, 56);
		panel.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Annuler");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1.setBounds(1102, 630, 249, 56);
		panel.add(btnNewButton_1_1);
		float prixTotal = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			prixTotal += Float.parseFloat(table.getValueAt(i, 5).toString());
			System.out.println(table.getValueAt(i, 5));
		}
		prixTotal_label.setText(String.valueOf(prixTotal));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.addPropertyChangeListener("tableCellEditor", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getNewValue() == null) {
					int row = table.getSelectedRow();
					int column = table.getSelectedColumn();
					System.out.println(table.getColumnName(column));
					if (table.getColumnName(column).equals("Quantité")) {
						table.setValueAt(Float.parseFloat(table.getValueAt(row, 0).toString()) * (Float.parseFloat(table.getValueAt(row, 2).toString())), row, 3);
						table.setValueAt(Float.parseFloat(table.getValueAt(row, 0).toString()) * (Float.parseFloat(table.getValueAt(row, 4).toString())), row, 5);
						float prixTotal = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							prixTotal += Float.parseFloat(table.getValueAt(i, 5).toString());
						}
						prixTotal_label.setText(Float.toString(prixTotal));
					}
				} else {
					// editing started
				}
			}
		});
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) { // Check if the value got selected, ignore if it has been deselected
					ProduitDAO produitDAO = new ProduitDAO();
					List < Produit > listProduits = new ArrayList < > ();
					listProduits.addAll(produitDAO.read());
					int row = table.getSelectedRow();
					for (Produit article: listProduits) {
						if (article.getLibelle().equals(e.getItem())) {
							idList.remove(table.getSelectedRow());
							idList.add(table.getSelectedRow(), article.getId());
							poidsList.remove(table.getSelectedRow());
							poidsList.add(table.getSelectedRow(), article.getPoids());
							table.setValueAt(article.getPrixHT(),row, 2);
							table.setValueAt(article.getPrixTTC(),row, 4);
							float prixTotal = 0;
							for (int i = 0; i < table.getRowCount(); i++) {
								prixTotal += Float.parseFloat(table.getValueAt(i, 5).toString());
							}
							prixTotal_label.setText(Float.toString(prixTotal));
						}
					}
				}
			}
		});
	}

	public DefaultTableModel liste() {
		String[] col = {
				"Quantité",
				"Libellé",
				"Prix Unitaire HT",
				"Prix total HT",
				"Prix Unitaire TTC",
				"Prix total TTC"
		};
		DefaultTableModel tab = new DefaultTableModel(null, col);

		ProduitDAO produitDAO = new ProduitDAO();
		List < Produit > listProduits = new ArrayList < > ();
		listProduits.addAll(produitDAO.read());
		for (Produit article: listProduits) {
			comboBox.addItem(article.getLibelle());
		}
		if (modify) {
			Gson gson = new Gson();
			Type type = new TypeToken < ArrayList < ArrayList < Produit >>> () {}.getType();
			ArrayList < ArrayList < Produit >> contactList = gson.fromJson(currentCommande.getProduits(), type);
			for (ArrayList < Produit > produit: contactList) {
				Vector vect = new Vector();
				poidsList.add(produit.get(0).getPoids());
				idList.add(produit.get(0).getId());
				vect.add(produit.get(0).getQuantite());
				vect.add(produit.get(0).getLibelle());
				vect.add(produit.get(0).getPrixHT());
				vect.add(produit.get(0).getPrixTTC() * produit.get(0).getQuantite());
				vect.add(produit.get(0).getPrixTTC());
				vect.add(produit.get(0).getPrixTTC() * produit.get(0).getQuantite());
				tab.addRow(vect);
			}
		} else {
			Vector vect = new Vector();
			idList.add(listProduits.get(0).getId());
			poidsList.add(listProduits.get(0).getPoids());
			vect.add(0);
			vect.add(listProduits.get(0).getLibelle());
			vect.add(listProduits.get(0).getPrixHT());
			vect.add(0);
			vect.add(listProduits.get(0).getPrixTTC());
			vect.add(0);
			tab.addRow(vect);
			prixTotal_label.setText(Float.toString(0));
		}
		return tab;
	}

	public void relisting() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ProduitDAO produitDAO = new ProduitDAO();
		List < Produit > listProduits = new ArrayList < > ();
		listProduits.addAll(produitDAO.read());
		Vector vect = new Vector();
		vect.add(0);
		idList.add(listProduits.get(0).getId());
		poidsList.add(listProduits.get(0).getPoids());
		vect.add(listProduits.get(0).getLibelle());
		vect.add(listProduits.get(0).getPrixHT());
		vect.add(0);
		vect.add(listProduits.get(0).getPrixTTC());
		vect.add(0);
		model.addRow(vect);
		table.setModel(model);
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