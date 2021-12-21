package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import controller.CommandeStockDao;
import controller.FournisseurDao;
import controller.PanelsManager;
import controller.ProduitDAO;
import model.CommandeStock;
import model.Fournisseur;
import model.Produit;



public class NewCommandeStock extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static public boolean modify = false;
	static Fournisseur currentFournisseur = new Fournisseur();
	static CommandeStock currentCmdStock = new CommandeStock();
	private JTable cmd;
	private JLabel prixTotal_label;
	JButton btnSupprimer = new JButton();
	JComboBox<String> comboBox = new JComboBox<>();
	ArrayList<Integer> idList = new ArrayList<Integer>();
	ArrayList<Float> poidsList = new ArrayList<Float>();
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
				modify = false;
			}
		});
		btnRetour.setIcon(new ImageIcon
			("C:\\Users\\fredb\\AFPA\\workspace-java\\Boulangestion\\projetBoulang\\arrow_left.png"));
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
				modify = false;
			}
		});
		btnAccueil.setIcon(new ImageIcon
			("C:\\Users\\fredb\\AFPA\\workspace-java\\Boulangestion\\projetBoulang\\exit.png"));
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
		
		JLabel lblSociete = new JLabel("Societe :");
		lblSociete.setBounds(11, 26, 57, 14);
		infosFournisseur.add(lblSociete);

		JLabel lblAdress = new JLabel("Adresse :");
		lblAdress.setBounds(11, 63, 57, 14);
		infosFournisseur.add(lblAdress);

		JLabel lblTel = new JLabel("Telephone :");
		lblTel.setBounds(11, 102, 72, 14);
		infosFournisseur.add(lblTel);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(11, 140, 72, 14);
		infosFournisseur.add(lblEmail);
		
		//block info fournisseur:
		JComboBox<String> infoSociete = new JComboBox<>();
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
				if (e.getStateChange() == ItemEvent.SELECTED) { 
					// Check if the value got selected, ignore if it has been deselected
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
							adresseLabel.setText(fournisseur.getAdresse()+" "+
									fournisseur.getCodePostal()+" "+fournisseur.getVille());
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

		JLabel lblDateReception = new JLabel("Date de reception :");
		lblDateReception.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDateReception.setBounds(10, 11, 189, 37);
		datePanel.add(lblDateReception);
		
		JLabel lblNumCmd = new JLabel("Commande n ° :");
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
				int m = cmd.getRowCount();
				ArrayList < ArrayList < Produit >> matrix = new ArrayList < ArrayList < Produit >> ();
					for (int i = 0; i < m; i++) {
						ArrayList < Produit > row = new ArrayList < Produit > ();
						Produit produit = new Produit();
						produit.setId(idList.get(i));
						produit.setPoids(poidsList.get(i));
						produit.setQuantite(Integer.parseInt(cmd.getValueAt(i, 0).toString()));
						produit.setLibelle((String) cmd.getValueAt(i, 1));
						produit.setPrixHT(Float.parseFloat(cmd.getValueAt(i, 2).toString()));
						produit.setPrixTTC(Float.parseFloat(cmd.getValueAt(i, 4).toString()));
						row.add(produit);
						matrix.add(row);
					}
				String json = new Gson().toJson(matrix);
				Date newDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
				
				if (modify) {
					commandeStockDao.update(new CommandeStock(currentCmdStock.getId(),newDate,
						currentFournisseur.getId(), cmd.getRowCount(),
						Float.parseFloat(prixTotal_label.getText()), json),
						currentCmdStock.getId());
					modify = false;
					//retour a la liste apres la modif
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchtoCommandeStockView());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
				}else {
					commandeStockDao.create(new CommandeStock(currentCmdStock.getId(),newDate,
						currentFournisseur.getId(), cmd.getRowCount(),
						Float.parseFloat(prixTotal_label.getText()), json));
				}
			}	
		});
		btnValider.setBackground(Color.ORANGE);
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnValider.setBounds(1102, 557, 249, 56);
		corps.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir annuler?", "Attention",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchtoCommandeStockView());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
				}
			}
		});
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnAnnuler.setBounds(1102, 630, 249, 56);
		corps.add(btnAnnuler);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		cmd.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		cmd.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
		cmd.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		cmd.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		cmd.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		cmd.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		cmd.getColumn("Supprimer").setCellRenderer(new ButtonRenderer());
		cmd.getColumn("Supprimer").setCellEditor(new ButtonEditor(new JCheckBox()));
		cmd.addPropertyChangeListener("tableCellEditor", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getNewValue() == null) {
					int row = cmd.getSelectedRow();
					int column = cmd.getSelectedColumn();
					if (cmd.getColumnName(column).equals("Quantite")) {
						cmd.setValueAt(Float.parseFloat(cmd.getValueAt(row, 0).toString())
							* (Float.parseFloat(cmd.getValueAt(row, 2).toString())), row, 3);
						cmd.setValueAt(Float.parseFloat(cmd.getValueAt(row, 0).toString())
							* (Float.parseFloat(cmd.getValueAt(row, 4).toString())), row, 5);
						float prixTotal = 0;
						for (int i = 0; i < cmd.getRowCount(); i++) {
							prixTotal += Float.parseFloat(cmd.getValueAt(i, 5).toString());
						}
						prixTotal_label.setText(Float.toString(prixTotal));
					}
				}
			}
		});
		btnSupprimer.addActionListener(
				event -> {
					int n = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce produit ?",
						"Supprimer", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						((DefaultTableModel)cmd.getModel()).removeRow(cmd.getSelectedRow());
						float prixTotal12 = 0;
						for (int i = 0; i < cmd.getRowCount(); i++) {
							prixTotal12 += Float.parseFloat(cmd.getValueAt(i, 5).toString());
						}
						prixTotal_label.setText(Float.toString(prixTotal12));
					}
				}
		);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Check if the value got selected, ignore if it has been deselected
					ProduitDAO produitDAO = new ProduitDAO();
					List < Produit > listProduits = new ArrayList < > ();
					listProduits.addAll(produitDAO.read());
					int row = cmd.getSelectedRow();
					for (Produit article: listProduits) {
						if (article.getLibelle().equals(e.getItem())) {
							idList.remove(cmd.getSelectedRow());
							idList.add(cmd.getSelectedRow(),article.getId());
							poidsList.remove(cmd.getSelectedRow());
							poidsList.add(cmd.getSelectedRow(),article.getPoids());
							cmd.setValueAt(article.getPrixHT(),row, 2);
							cmd.setValueAt(article.getPrixTTC(),row, 4);
							float prixTotal = 0;
							for (int i = 0; i < cmd.getRowCount(); i++) {
								prixTotal += Float.parseFloat(cmd.getValueAt(i, 5).toString());
							}
							prixTotal_label.setText(Float.toString(prixTotal));
						}
					}
				}
			}
		});
		float prixTotal = 0;
		for (int i = 0; i < cmd.getRowCount(); i++) {
			prixTotal += Float.parseFloat(cmd.getValueAt(i, 5).toString());
		}
		prixTotal_label.setText(String.valueOf(prixTotal));
	}
	
	//remplissage du tableau en fonction de modify
	public DefaultTableModel liste() {
		String[] col = {
				"Quantite",
				"Libelle",
				"Prix Unitaire HT",
				"Prix total HT",
				"Prix Unitaire TTC",
				"Prix total TTC",
				"Supprimer"
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
			ArrayList < ArrayList < Produit >> contactList = gson.fromJson(currentCmdStock.getProduits(), type);
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
			poidsList.add(listProduits.get(0).getPoids());
			idList.add(listProduits.get(0).getId());
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
		DefaultTableModel model = (DefaultTableModel) cmd.getModel();
		ProduitDAO produitDAO = new ProduitDAO();
		List < Produit > listProduits = new ArrayList < > ();
		listProduits.addAll(produitDAO.read());
		Vector vect = new Vector();
		poidsList.add(listProduits.get(0).getPoids());
		idList.add(listProduits.get(0).getId());
		vect.add(0);
		vect.add(listProduits.get(0).getLibelle());
		vect.add(listProduits.get(0).getPrixHT());
		vect.add(0);
		vect.add(listProduits.get(0).getPrixTTC());
		vect.add(0);
		model.addRow(vect);
		cmd.setModel(model);
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
	public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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
