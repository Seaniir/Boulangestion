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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.mysql.cj.conf.ConnectionUrlParser;
import controller.CommandeClientDAO;
import controller.PanelsManager;
import model.Client;
import model.CommandeClient;

import javax.swing.JTable;

public class HistoriqueCommandesVentes extends JPanel {
	private JTable listingHistorique;

	public HistoriqueCommandesVentes() {
		setBounds(0, 0, 1440, 900);
		setBackground(new Color(254, 245, 232));
		setLayout(null);
		// Panel TOP
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1440, 94);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblAccueil = new JLabel("Accueil");
		lblAccueil.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAccueil.setBounds(1355, 62, 63, 18);
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
		URL exitURL = ClassLoader.getSystemResource("exit.png");
		btnAccueil.setBackground(Color.WHITE);
		btnAccueil.setIcon(new ImageIcon(exitURL));
		btnAccueil.setBounds(1370, 11, 40, 40);
		panel.add(btnAccueil);
		
		// Panel Historique
		JPanel listing = new JPanel();
		listing.setBackground(new Color(255, 255, 255));
		listing.setBounds(264, 155, 912, 706);
		add(listing);
		listing.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 912, 706);
		listing.add(scrollPane);

		// La mise en forme du tableau 
		listingHistorique = new JTable();
		listingHistorique.setRowSelectionAllowed(false);
		listingHistorique.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = listingHistorique.getSelectedRow();
				CommandeClientDAO commandeClientDAO = new CommandeClientDAO();
				ConnectionUrlParser.Pair < CommandeClient, Client > pair = 
						commandeClientDAO.findById((Integer) listingHistorique.getValueAt(id, 0));
				DetailsCommandesClient.currentCommande = pair.left;
				DetailsCommandesClient.currentClient = pair.right;
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchtoDetailsCommandesClients());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		listingHistorique.setRowSelectionAllowed(false);
		scrollPane.setViewportView(listingHistorique);
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
	}
		// Les intitulés des colonnes du tableau
	public DefaultTableModel liste() {
		String [] col = {
				"N° commande/vente",
				"Date",
				"Client",
				"Nbr articles",
				"Montant réglé",
				"Paiement"
		};
		DefaultTableModel tab = new DefaultTableModel(null, col);
		// Les données du tableau 
		CommandeClientDAO hcv = new CommandeClientDAO();
		ConnectionUrlParser.Pair<ArrayList<CommandeClient>, ArrayList<Client>> pair = hcv.readPairHisto();
		for (int i = 0; i < pair.left.size();i++) {
			Vector vect = new Vector();
			vect.add(pair.left.get(i).getId());
			vect.add(pair.left.get(i).getWithdrawal_at());
			vect.add(pair.right.get(i).getFirstName()+" "+pair.right.get(i).getName());
			vect.add(pair.left.get(i).getNbrArticles());
			vect.add(pair.left.get(i).getPrixTotal());
			vect.add(pair.left.get(i).getTypePaiment());
			tab.addRow(vect);
		}
		return tab;
	}	
}
