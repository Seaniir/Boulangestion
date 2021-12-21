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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProduitsView extends JPanel {

	private final JTable table;
	JButton btnModifier = new JButton();
	JButton btnAnnuler = new JButton();
	ArrayList<Integer> idList = new ArrayList<>();
	/**
	 * Create the panel.
	 */
	public ProduitsView() {
		setBounds(0, 0, 1440, 900);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 245, 232));
		panel.setBounds(0, 89, 1440, 811);
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
		btnNewButton.addActionListener(e -> {
			PanelsManager.contentPane.removeAll();
			PanelsManager.contentPane.add(PanelsManager.switchtoNewProduitViewPanel());
			PanelsManager.contentPane.revalidate();
			PanelsManager.contentPane.repaint();
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBackground(new Color(242, 193, 102));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(467, 663, 499, 53);
		panel.add(btnNewButton);
		
		JPanel menu = new JPanel();
		menu.setLayout(null);
		menu.setBackground(Color.WHITE);
		menu.setBounds(0, -1, 1440, 90);
		add(menu);
		
		JButton btnRetour = new JButton("");
		btnRetour.setIcon(new ImageIcon("C:\\Users\\Quentin\\Documents\\GIT\\Boulangestion\\projetBoulang\\arrow_left.png"));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToAccueilMenu());
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
		btnAccueil.setIcon(new ImageIcon("C:\\Users\\Quentin\\Documents\\GIT\\Boulangestion\\projetBoulang\\exit.png"));
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
		btnModifier.addActionListener(
				event -> {
					int n = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier ce produit ?", "Modifier", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						NouveauProduit.currentProduit = new Produit(idList.get(table.getSelectedRow()), table.getValueAt(table.getSelectedRow(), 0).toString(), table.getValueAt(table.getSelectedRow(), 1).toString(), Float.parseFloat(table.getValueAt(table.getSelectedRow(), 2).toString()), Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString()), Float.parseFloat(table.getValueAt(table.getSelectedRow(), 4).toString()), Float.parseFloat(table.getValueAt(table.getSelectedRow(), 5).toString()));
						NouveauProduit.modify = true;
						PanelsManager.contentPane.removeAll();
						PanelsManager.contentPane.add(PanelsManager.switchtoNewProduitViewPanel());
						PanelsManager.contentPane.revalidate();
						PanelsManager.contentPane.repaint();
					}
				}
		);
		btnAnnuler.addActionListener(
				event -> {
					int n = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce produit ?", "Supprimer", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						ProduitDAO produitDAO = new ProduitDAO();
						produitDAO.delete(idList.get(table.getSelectedRow()));
						PanelsManager.contentPane.removeAll();
						PanelsManager.contentPane.add(PanelsManager.switchtoProduitsViewPanel());
						PanelsManager.contentPane.revalidate();
						PanelsManager.contentPane.repaint();
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
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\Quentin\\Downloads\\history.png");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		ImageIcon imageIcon1 = new ImageIcon("C:\\Users\\Quentin\\Downloads\\sign-out.png");
		Image image1 = imageIcon1.getImage();
		Image newimg1 = image1.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
		imageIcon1 = new ImageIcon(newimg1);

	}

	public DefaultTableModel liste() {

		String[] col = {
				"Libelle",
				"Fabricant",
				"Poids (kg)",
				"Quantite",
				"Prix HT",
				"Prix TTC",
				"Modifier",
				"Supprimer"
		};
		DefaultTableModel tab = new DefaultTableModel(null, col);

		ProduitDAO produitDAO = new ProduitDAO();
		List<Produit> produits = new ArrayList<>(produitDAO.read());
		for (Produit produit: produits) {
			Vector<java.io.Serializable> vect = new Vector<>();
			idList.add(produit.getId());
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

	static class ButtonRenderer extends JButton implements TableCellRenderer {
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
			return label;
		}
	}

	static class SecondButtonRenderer extends JButton implements TableCellRenderer {
		public SecondButtonRenderer() {
			setOpaque(true);
		}
		public Component getTableCellRendererComponent(JTable table, Object value,
													   boolean isSelected, boolean hasFocus, int row, int column) {
			setText((value == null) ? "Supprimer" : value.toString());
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
			label = (value == null) ? "Supprimer" : value.toString();
			btnAnnuler.setText(label);
			return btnAnnuler;
		}
		public Object getCellEditorValue() {
			return label;
		}
	}
}