package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.FournisseurDao;
import controller.PanelsManager;
import model.Fournisseur;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListeFournisseurs extends JPanel {
	private JTable listingFournisseurs;
	/**
	 * Create the panel.
	 */
	public ListeFournisseurs() {
		setBounds(0, 0, 1440, 900);
		setBackground(new Color(254, 245, 232));
		setLayout(null);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(255, 255, 255));
		menu.setBounds(0, 0, 1440, 90);
		add(menu);
		menu.setLayout(null);
		
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
		
		JLabel lblNewLabel_1 = new JLabel("Accueil");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(1355, 62, 63, 18);
		menu.add(lblNewLabel_1);
		
		JPanel listing = new JPanel();
		listing.setBackground(new Color(255, 255, 255));
		listing.setBounds(264, 155, 912, 706);
		add(listing);
		listing.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 912, 706);
		listing.add(scrollPane);
		
		listingFournisseurs = new JTable();
		listingFournisseurs.setRowSelectionAllowed(false);
		scrollPane.setViewportView(listingFournisseurs);
		listingFournisseurs.setRowHeight(100);
		listingFournisseurs.setModel(liste());
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		listingFournisseurs.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		listingFournisseurs.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		listingFournisseurs.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		listingFournisseurs.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		listingFournisseurs.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		listingFournisseurs.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		listingFournisseurs.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
	}
	public DefaultTableModel liste() {
		String [] col = {"N° Fournisseur","Societe","Adresse","Telephone", "Email","Modifier", "Historique"};
		DefaultTableModel tab = new DefaultTableModel(null, col);
		
		FournisseurDao fournisseurDao = new FournisseurDao();
		List<Fournisseur> listFournisseurs = new ArrayList<>();
		listFournisseurs.addAll(fournisseurDao.read());
		for (Fournisseur fournisseur : listFournisseurs) {
			Vector vect = new Vector();
			vect.add(fournisseur.getId());
			 vect.add(fournisseur.getSociete());
			 vect.add(fournisseur.getAdresse()+" "+fournisseur.getCodePostal()+" "+fournisseur.getVille());
			 vect.add(fournisseur.getTel());
			 vect.add(fournisseur.getEmail());
			 
			 tab.addRow(vect);
		}
		return tab;
		
	}
}
