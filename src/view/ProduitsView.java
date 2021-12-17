package view;

import com.mysql.cj.conf.ConnectionUrlParser;
import controller.CommandeClientDAO;
import controller.PanelsManager;
import controller.ProduitDAO;
import model.Client;
import model.CommandeClient;
import model.Produit;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class ProduitsView extends JPanel {

	private JTable table;
	JButton btnModifier = new JButton();
	JButton btnAnnuler = new JButton();
	/**
	 * Create the panel.
	 */
	public ProduitsView() {
		setBounds(0, 0, 1440, 900);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 248, 220));
		panel.setBounds(0, 100, 1440, 800);
		add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		scrollPane.setBounds(21, 11, 1388, 622);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = table.getSelectedRow();

				int article_id = (int) table.getModel().getValueAt(id, 0);

				CommandeClientDAO commandeClientDAO = new CommandeClientDAO();
				ConnectionUrlParser.Pair < CommandeClient, Client > pair = commandeClientDAO.findById((Integer) table.getValueAt(id, 0));
				DetailsCommandesClient.currentCommande = pair.left;
				DetailsCommandesClient.currentClient = pair.right;
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchtoDetailsCommandesClients());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});

		scrollPane.setViewportView(table);
		table.setRowHeight(100);
		table.setModel(liste());

		JButton btnNewButton = new JButton("Nouveau produit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToNouvelleCommandePanel());
				PanelsManager.contentPane.revalidate();
				PanelsManager.contentPane.repaint();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBackground(new Color(255, 165, 0));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(467, 663, 499, 53);
		panel.add(btnNewButton);
		btnModifier.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						int n = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier cette commande ?", "Modifier", JOptionPane.YES_NO_OPTION);
						if (n == JOptionPane.YES_OPTION) {
							CommandeClientDAO commandeClientDAO = new CommandeClientDAO();
							ConnectionUrlParser.Pair < CommandeClient, Client > pair = commandeClientDAO.findById((int) table.getValueAt(table.getSelectedRow(), 0));
							NouvelleCommandeClientView.currentCommande = pair.left;
							NouvelleCommandeClientView.currentClient = pair.right;
							NouvelleCommandeClientView.modify = true;
							PanelsManager.contentPane.removeAll();
							PanelsManager.contentPane.add(PanelsManager.switchToNouvelleCommandePanel());
							PanelsManager.contentPane.revalidate();
							PanelsManager.contentPane.repaint();
						} else {

						}
					}
				}
		);
		btnAnnuler.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						int n = JOptionPane.showConfirmDialog(null, "Voulez-vous annuler cette commande ?", "Annuler", JOptionPane.YES_NO_OPTION);
						if (n == JOptionPane.YES_OPTION) {
							CommandeClientDAO commandeClientDAO = new CommandeClientDAO();
							commandeClientDAO.delete((int) table.getValueAt(table.getSelectedRow(), 0));
							PanelsManager.contentPane.removeAll();
							PanelsManager.contentPane.add(PanelsManager.switchToCommandesClientPanel());
							PanelsManager.contentPane.revalidate();
							PanelsManager.contentPane.repaint();
						} else {

						}
					}
				}
		);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		table.getColumn("Modifier").setCellRenderer(new ButtonRenderer());
		table.getColumn("Modifier").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn("Supprimer").setCellRenderer(new SecondButtonRenderer());
		table.getColumn("Supprimer").setCellEditor(new SecondButtonEditor(new JCheckBox()));
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 1440, 99);
		add(panel_1);
		panel_1.setLayout(null);
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\Quentin\\Downloads\\history.png");
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back

		JButton accueilBtn = new JButton("");
		ImageIcon imageIcon1 = new ImageIcon("C:\\Users\\Quentin\\Downloads\\sign-out.png");
		Image image1 = imageIcon1.getImage(); // transform it
		Image newimg1 = image1.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		accueilBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToAccueilMenu());
				PanelsManager.contentPane.revalidate();
				PanelsManager.contentPane.repaint();
			}
		});
		imageIcon1 = new ImageIcon(newimg1); // transform it back

		JLabel lblNewLabel = new JLabel("Historique");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 74, 127, 25);
		panel_1.add(lblNewLabel);

		JLabel lblAccueil = new JLabel("Accueil");
		lblAccueil.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAccueil.setBounds(1323, 74, 127, 25);
		panel_1.add(lblAccueil);
		accueilBtn.setIcon(imageIcon1);
		accueilBtn.setBounds(1333, 0, 107, 76);
		panel_1.add(accueilBtn);

		JButton historiqueBtn = new JButton("");
		historiqueBtn.setIcon(imageIcon);
		historiqueBtn.setBounds(0, 0, 127, 76);
		panel_1.add(historiqueBtn);

	}

	public DefaultTableModel liste() {

		String[] col = {
				"Libellé",
				"Fabricant",
				"Poids (kg)",
				"Quantité",
				"Prix HT",
				"Prix TTC",
				"Modifier",
				"Historique",
				"Supprimer"
		};
		DefaultTableModel tab = new DefaultTableModel(null, col);

		ProduitDAO produitDAO = new ProduitDAO();
		List <Produit> produits = new ArrayList < > ();
		produits.addAll(produitDAO.read());
		for (Produit produit: produits) {
			Vector vect = new Vector();
			vect.add(produit.getLibelle());
			vect.add(produit.getFabricant());
			vect.add(produit.getPoids());
			vect.add(produit.getQuantite());
			vect.add(produit.getPrixHT());
			vect.add(produit.getPrixTTC());
			tab.addRow(vect);
		}
		return tab;
	}

	class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}
		public Component getTableCellRendererComponent(JTable table, Object value,
													   boolean isSelected, boolean hasFocus, int row, int column) {
			setText((value == null) ? "Modifier" : value.toString());
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
			label = (value == null) ? "Modifier" : value.toString();
			btnModifier.setText(label);
			return btnModifier;
		}
		public Object getCellEditorValue() {
			return new String(label);
		}
	}

	class SecondButtonRenderer extends JButton implements TableCellRenderer {
		public SecondButtonRenderer() {
			setOpaque(true);
		}
		public Component getTableCellRendererComponent(JTable table, Object value,
													   boolean isSelected, boolean hasFocus, int row, int column) {
			setText((value == null) ? "Annuler" : value.toString());
			return this;
		}
	}

	class SecondButtonEditor extends DefaultCellEditor {
		private String label;

		public SecondButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}
		public Component getTableCellEditorComponent(JTable table, Object value,
													 boolean isSelected, int row, int column) {
			label = (value == null) ? "Annuler" : value.toString();
			btnAnnuler.setText(label);
			return btnAnnuler;
		}
		public Object getCellEditorValue() {
			return new String(label);
		}
	}
}