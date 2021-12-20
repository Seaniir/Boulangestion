package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.UtilDateModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import controller.FournisseurDao;
import controller.PanelsManager;
import controller.ProduitDAO;
import model.CommandeStock;
import model.Fournisseur;
import model.Produit;

public class DetailsCommandeStock extends JPanel {
	static Fournisseur currentFournisseur = new Fournisseur();
	static CommandeStock currentCmdStock = new CommandeStock();
	private JTable table_1;
	JButton button = new JButton();
	JComboBox comboBox = new JComboBox();
	JLabel prixTotal_label = new JLabel();
	/**
	 * Create the panel.
	 */
	public DetailsCommandeStock() {
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
		
		JLabel lblaccueil = new JLabel("Accueil");
		lblaccueil.setHorizontalAlignment(SwingConstants.CENTER);
		lblaccueil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblaccueil.setBounds(1355, 62, 63, 18);
		menu.add(lblaccueil);
		
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
		
		JLabel lblSociete = new JLabel("Société :");
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
		FournisseurDao fournisseurDao = new FournisseurDao();
		List<Fournisseur> listFournisseurs = new ArrayList <> ();
		listFournisseurs.addAll(fournisseurDao.read());
		
		JLabel societeLabel = new JLabel("");
		societeLabel.setBounds(90, 26, 312, 21);
		infosFournisseur.add(societeLabel);
		
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
		
		JPanel datePanel = new JPanel();
		datePanel.setBackground(new Color(255, 255, 255));
		datePanel.setBounds(723, 11, 693, 165);
		corps.add(datePanel);
		datePanel.setLayout(null);

		JLabel lblDateReception = new JLabel("Date de réception :");
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
		id_commande_label.setText(String.valueOf(currentCmdStock.getId()));
		societeLabel.setText(currentFournisseur.getSociete());
		adresseLabel.setText(currentFournisseur.getAdresse()+" "+currentFournisseur.getCodePostal()+" "+
				currentFournisseur.getVille());
		phoneLabel.setText(currentFournisseur.getTel());
		mailLabel.setText(currentFournisseur.getEmail());
		
		Date date = currentCmdStock.getDateReception();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = dateFormat.format(date);
		created_at_label.setText(strDate);
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
		Gson gson = new Gson();
		Type type = new TypeToken < ArrayList < ArrayList < Produit >>> () {}.getType();
		ArrayList < ArrayList < Produit >> productsList = gson.fromJson(currentCmdStock.getProduits(), type);
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
