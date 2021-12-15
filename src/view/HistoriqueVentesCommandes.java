package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ClientDao;
import controller.PanelsManager;
import model.Client;

import javax.swing.JTable;

public class HistoriqueVentesCommandes extends JPanel {
	private JTable listingHistorique;

	/**
	 * Create the panel.
	 */
	public HistoriqueVentesCommandes() {
		setBounds(0, 0, 1440, 900);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1440, 131);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblAccueil = new JLabel("Accueil");
		lblAccueil.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAccueil.setBounds(1270, 102, 160, 19);
		panel.add(lblAccueil);
		
		JButton btnAccueil = new JButton("");
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToAccueilMenu());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnAccueil.setBackground(Color.WHITE);
		btnAccueil.setIcon(new ImageIcon("C:\\Users\\Julien\\Desktop\\projetBoulang\\exit.png"));
		btnAccueil.setBounds(1270, 10, 160, 82);
		panel.add(btnAccueil);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(new Color(255, 239, 213));
		panel_1.setBounds(0, 128, 1440, 772);
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel listing = new JPanel();
		listing.setBackground(new Color(255, 255, 255));
		listing.setBounds(264, 155, 912, 706);
		add(listing);
		listing.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 912, 706);
		listing.add(scrollPane);
		
		listingHistorique = new JTable();
		listingHistorique.setRowSelectionAllowed(false);
		scrollPane.setViewportView(listingClients);
		listingHistorique.setRowHeight(100);
		listingHistorique.setModel(liste());
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		listingHistorique.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		listingHistorique.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		listingHistorique.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		listingHistorique.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		listingHistorique.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		listingHistorique.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		listingHistorique.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
	}
	
	public DefaultTableModel liste() {
		String [] col = {"N° Client","Nom","Prénom","Adresse", "Téléphone","Email", "Modifier","Historique","Supprimer"};
		DefaultTableModel tab = new DefaultTableModel(null, col);
		
		ClientDao clientDao = new ClientDao();
		List<Client> listClients = new ArrayList<>();
		listClients.addAll(clientDao.read());
		for (Client client : listClients) {
			Vector vect = new Vector();
			 vect.add(client.getId());
			 vect.add(client.getName());
			 vect.add(client.getFirstName());
			 vect.add(client.getAdress()+" "+client.getZip()+" "+client.getCity());
			 vect.add(client.getTel());
			 vect.add(client.getEmail());
			 
			 tab.addRow(vect);
		}
		return tab;
	
	}	
}
