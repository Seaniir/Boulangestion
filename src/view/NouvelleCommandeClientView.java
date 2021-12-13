package view;

import controller.CommandeClientDAO;
import controller.PanelsManager;
import controller.ProduitDAO;
import model.CommandeClient;
import model.Produit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class NouvelleCommandeClientView extends JPanel {
	private JTable table;
	JButton button = new JButton();
	JComboBox comboBox = new JComboBox();

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
		Image newimg4 = accueilImageImage.getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		accueilImage = new ImageIcon(newimg4);  // transform it back
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
		Image newimg = image.getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg);  // transform it back
		historiqueBtn.setIcon(imageIcon);
		historiqueBtn.setBounds(127, 0, 127, 75);
		panel_1.add(historiqueBtn);
		button.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						JOptionPane.showMessageDialog(null,"Do you want to modify this line?");
					}
				}
		);
		JButton clientBtn = new JButton("");
		ImageIcon imageClient = new ImageIcon("C:\\Users\\Quentin\\Downloads\\user.png");
		Image imageClientImage = imageClient.getImage(); // transform it
		Image newimg2 = imageClientImage.getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageClient = new ImageIcon(newimg2);  // transform it back
		clientBtn.setBounds(0, 0, 127, 75);
		clientBtn.setIcon(imageClient);
		panel_1.add(clientBtn);
		
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
		Image newimg3 = imageReturnImage.getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageReturn = new ImageIcon(newimg3);  // transform it back
		returnBtn.setBounds(253, 0, 127, 75);
		returnBtn.setIcon(imageReturn);
		panel_1.add(returnBtn);
		
		JLabel lblNewLabel = new JLabel("Clients");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 74, 127, 25);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_9 = new JLabel("Historique");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(127, 74, 127, 25);
		panel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Retour");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(253, 74, 127, 25);
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
		lblNewLabel_5.setBounds(238, 42, 78, 14);
		panel_2.add(lblNewLabel_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 1014, 576);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setRowHeight(100);
		scrollPane.setColumnHeaderView(table);
		
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
		lblNewLabel_6_1.setBounds(10, 59, 189, 37);
		panel_3.add(lblNewLabel_6_1);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		String created_at = dtf.format(now);
		JLabel created_at_label = new JLabel(created_at);
		created_at_label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		created_at_label.setBounds(271, 11, 189, 37);
		panel_3.add(created_at_label);
		
		JLabel withdrawal_at_label = new JLabel("");
		withdrawal_at_label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		withdrawal_at_label.setBounds(271, 59, 189, 37);
		panel_3.add(withdrawal_at_label);
		
		JLabel id_commande_label = new JLabel("");
		id_commande_label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		id_commande_label.setBounds(350, 100, 189, 37);
		panel_3.add(id_commande_label);
		
		JLabel lblNewLabel_7 = new JLabel("Total :");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(1202, 351, 116, 35);
		panel.add(lblNewLabel_7);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(1159, 381, 145, 99);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel prixTotal_label = new JLabel("");
		prixTotal_label.setBounds(10, 11, 125, 77);
		panel_4.add(prixTotal_label);
		
		JLabel lblNewLabel_8 = new JLabel("M\u00E9thode de paiment");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(1159, 540, 145, 14);
		panel.add(lblNewLabel_8);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(1159, 562, 145, 77);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Esp\u00E8ces");
		rdbtnNewRadioButton.setBounds(18, 7, 109, 23);
		panel_5.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Carte banquaire");
		rdbtnNewRadioButton_1.setBounds(18, 47, 109, 23);
		panel_5.add(rdbtnNewRadioButton_1);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		table.setRowHeight(100);
		table.setModel(liste());
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		comboBox.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						ProduitDAO produitDAO = new ProduitDAO();
						List<Produit> listProduits = new ArrayList<>();
						listProduits.addAll(produitDAO.read());
						for (Produit article : listProduits) {
							if(article.getLibelle().equals(table.getValueAt(0, 1))) {
								if(table.getValueAt(0, 1) != null) {
									table.setValueAt(article.getPrixUnitaire(), 0, 2);
								}
							}
						}
					}
				}
		);
	}

	public DefaultTableModel liste() {

		String [] col = {"Quantité","Libellé","Prix Unitaire", "Prix total HT", "Prix total TTC", "Montant réglé"};
		DefaultTableModel tab = new DefaultTableModel(null, col);

		ProduitDAO produitDAO = new ProduitDAO();
		List<Produit> listProduits = new ArrayList<>();
		listProduits.addAll(produitDAO.read());
		for (Produit article : listProduits) {
			comboBox.addItem(article.getLibelle());
			Vector vect = new Vector();
			vect.add(0);
			tab.addRow(vect);
		}
		return tab;
	}

	class ButtonRenderer extends JButton implements TableCellRenderer
	{
		public ButtonRenderer() {
			setOpaque(true);
		}
		public Component getTableCellRendererComponent(JTable table, Object value,
													   boolean isSelected, boolean hasFocus, int row, int column) {
			setText((value == null) ? "Modify" : value.toString());
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor
	{
		private String label;

		public ButtonEditor(JCheckBox checkBox)
		{
			super(checkBox);
		}
		public Component getTableCellEditorComponent(JTable table, Object value,
													 boolean isSelected, int row, int column)
		{
			label = (value == null) ? "Modify" : value.toString();
			button.setText(label);
			return button;
		}
		public Object getCellEditorValue()
		{
			return new String(label);
		}
	}
}
