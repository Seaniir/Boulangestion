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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NouvelleCommandeClientView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static public boolean modify = false;
	static Client currentClient = new Client();
	static CommandeClient currentCommande = new CommandeClient();
	private final JTable table;
	JButton button = new JButton();
	JButton btnSupprimer = new JButton();
	JComboBox<String> comboBox = new JComboBox<>();
	JLabel prixTotal_label;
	ArrayList<Integer> idList = new ArrayList<>();
	ArrayList<Float> poidsList = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public NouvelleCommandeClientView() {
		setBounds(0, 0, 1440, 900);
		setLayout(null);
		ImageIcon accueilImage = new ImageIcon("C:\\Users\\Quentin\\Downloads\\sign-out.png");
		Image accueilImageImage = accueilImage.getImage();
		Image newimg4 = accueilImageImage.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
		accueilImage = new ImageIcon(newimg4);
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\Quentin\\Downloads\\history.png");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		button.addActionListener(
				event -> JOptionPane.showMessageDialog(null, "Do you want to modify this line?")
		);
		ImageIcon imageReturn = new ImageIcon("C:\\Users\\Quentin\\Downloads\\return.png");
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

		JLabel lblNewLabel_4 = new JLabel("Telephone :");
		lblNewLabel_4.setBounds(10, 110, 72, 14);
		panel_2.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Prenom :");
		lblNewLabel_5.setBounds(237, 26, 78, 14);
		panel_2.add(lblNewLabel_5);

		JComboBox<String> comboBox_1 = new JComboBox<>();
		ClientDao clientDao = new ClientDao();
		List<Client> listClients = new ArrayList<>(clientDao.read());
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

		comboBox_1.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
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

		JLabel lblNewLabel_8 = new JLabel("Methode de paiment");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(1128, 381, 208, 25);
		panel.add(lblNewLabel_8);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(1159, 417, 156, 90);
		panel.add(panel_5);
		panel_5.setLayout(null);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Especes");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNewRadioButton.setBounds(18, 22, 109, 23);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Carte banquaire");
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNewRadioButton_1.setBounds(18, 48, 132, 23);

		rdbtnNewRadioButton.addActionListener(e -> rdbtnNewRadioButton_1.setSelected(false));

		rdbtnNewRadioButton_1.addActionListener(e -> rdbtnNewRadioButton.setSelected(false));

		panel_5.add(rdbtnNewRadioButton);
		panel_5.add(rdbtnNewRadioButton_1);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		table.setRowHeight(100);
		table.setModel(liste());

		JButton btnNewButton = new JButton("Ajouter un produit");
		btnNewButton.addActionListener(e -> relisting());
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
			if (currentCommande.getTypePaiment().equals("Especes")) {
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

		btnNewButton_1.addActionListener(e -> {
			CommandeClientDAO commandeClientDAO = new CommandeClientDAO();
			String typePaiment = "";
			if (rdbtnNewRadioButton.isSelected()) {
				typePaiment = "Especes";
			} else if (rdbtnNewRadioButton_1.isSelected()) {
				typePaiment = "Carte banquaire";
			}

			DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
			System.out.println(datePicker.getJFormattedTextField().getText());
			int m = table.getRowCount();
			ArrayList < ArrayList < Produit >> matrix = new ArrayList<>();

			for (int i = 0; i < m; i++) {
				ArrayList < Produit > row = new ArrayList<>();
				Produit produit = new Produit();
				produit.setId(idList.get(i));
				produit.setPoids(poidsList.get(i));
				produit.setPrixHT(Float.parseFloat(table.getValueAt(i, 2).toString()));
				produit.setPrixTTC(Float.parseFloat(table.getValueAt(i, 4).toString()));
				produit.setQuantite(Integer.parseInt(table.getValueAt(i, 0).toString()));
				produit.setLibelle((String) table.getValueAt(i, 1));
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
			PanelsManager.contentPane.removeAll();
			PanelsManager.contentPane.add(PanelsManager.switchToCommandesClientPanel());
			PanelsManager.contentPane.revalidate();
			PanelsManager.contentPane.repaint();
		});
		btnNewButton_1.setBackground(new Color(242, 193, 102));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1.setBounds(1102, 557, 249, 56);
		panel.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Annuler");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToCommandesClientPanel());
				PanelsManager.contentPane.revalidate();
				PanelsManager.contentPane.repaint();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_1_1.setBounds(1102, 630, 249, 56);
		panel.add(btnNewButton_1_1);
		float prixTotal = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			prixTotal += Float.parseFloat(table.getValueAt(i, 5).toString());
			System.out.println(table.getValueAt(i, 5));
		}
		prixTotal_label.setText(String.valueOf(prixTotal));
		
		JPanel menu = new JPanel();
		menu.setLayout(null);
		menu.setBackground(Color.WHITE);
		menu.setBounds(0, -1, 1440, 90);
		add(menu);
		
		JButton btnRetour = new JButton("");
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
		
		JLabel lblAccueil = new JLabel("Accueil");
		lblAccueil.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAccueil.setBounds(1355, 62, 63, 18);
		menu.add(lblAccueil);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumn("Supprimer").setCellRenderer(new ButtonRenderer());
		table.getColumn("Supprimer").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.addPropertyChangeListener("tableCellEditor", evt -> {
			if (evt.getNewValue() == null) {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				System.out.println(table.getColumnName(column));
				if (table.getColumnName(column).equals("Quantite")) {
					table.setValueAt(Float.parseFloat(table.getValueAt(row, 0).toString()) * (Float.parseFloat(table.getValueAt(row, 2).toString())), row, 3);
					table.setValueAt(Float.parseFloat(table.getValueAt(row, 0).toString()) * (Float.parseFloat(table.getValueAt(row, 4).toString())), row, 5);
					float prixTotal1 = 0;
					for (int i = 0; i < table.getRowCount(); i++) {
						prixTotal1 += Float.parseFloat(table.getValueAt(i, 5).toString());
					}
					prixTotal_label.setText(Float.toString(prixTotal1));
				}
			}

		});
		btnSupprimer.addActionListener(
				event -> {
					int n = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce produit ?", "Supprimer", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
						float prixTotal12 = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							prixTotal12 += Float.parseFloat(table.getValueAt(i, 5).toString());
						}
						prixTotal_label.setText(Float.toString(prixTotal12));
					}
				}
		);
		comboBox.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				ProduitDAO produitDAO = new ProduitDAO();
				List<Produit> listProduits = new ArrayList<>(produitDAO.read());
				int row = table.getSelectedRow();
				for (Produit article: listProduits) {
					if (article.getLibelle().equals(e.getItem())) {
						idList.remove(table.getSelectedRow());
						idList.add(table.getSelectedRow(), article.getId());
						poidsList.remove(table.getSelectedRow());
						poidsList.add(table.getSelectedRow(), article.getPoids());
						table.setValueAt(article.getPrixHT(),row, 2);
						table.setValueAt(article.getPrixTTC(),row, 4);
						table.setValueAt(Float.parseFloat(table.getValueAt(row, 0).toString()) * (Float.parseFloat(table.getValueAt(row, 2).toString())), row, 3);
						table.setValueAt(Float.parseFloat(table.getValueAt(row, 0).toString()) * (Float.parseFloat(table.getValueAt(row, 4).toString())), row, 5);
						float prixTotal13 = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							prixTotal13 += Float.parseFloat(table.getValueAt(i, 5).toString());
						}
						prixTotal_label.setText(Float.toString(prixTotal13));
					}
				}
			}
		});
	}

	public DefaultTableModel liste() {
		String[] col = {
				"Quantite",
				"Libellé",
				"Prix Unitaire HT",
				"Prix total HT",
				"Prix Unitaire TTC",
				"Prix total TTC",
				"Supprimer"
		};
		DefaultTableModel tab = new DefaultTableModel(null, col);

		ProduitDAO produitDAO = new ProduitDAO();
		List<Produit> listProduits = new ArrayList<>(produitDAO.read());
		for (Produit article: listProduits) {
			comboBox.addItem(article.getLibelle());
		}
		if (modify) {
			Gson gson = new Gson();
			Type type = new TypeToken < ArrayList < ArrayList < Produit >>> () {}.getType();
			ArrayList < ArrayList < Produit >> contactList = gson.fromJson(currentCommande.getProduits(), type);
			for (ArrayList < Produit > produit: contactList) {
				Vector<java.io.Serializable> vect = new Vector<>();
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
			Vector<java.io.Serializable> vect = new Vector<>();
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
		List<Produit> listProduits = new ArrayList<>(produitDAO.read());
		Vector<java.io.Serializable> vect = new Vector<>();
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

	static class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}
		public Component getTableCellRendererComponent(JTable table, Object value,
													   boolean isSelected, boolean hasFocus, int row, int column) {
			setText((value == null) ? "Supprimer" : value.toString());
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor {
		private String label;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}
		public Component getTableCellEditorComponent(JTable table, Object value,
													 boolean isSelected, int row, int column) {
			label = (value == null) ? "Supprimer" : value.toString();
			btnSupprimer.setText(label);
			return btnSupprimer;
		}
		public Object getCellEditorValue() {
			return label;
		}
	}

	public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

		private final String datePattern = "dd/MM/yyy";
		private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}
}