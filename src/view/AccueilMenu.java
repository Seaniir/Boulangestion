package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.PanelsManager;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AccueilMenu extends JPanel {
	private final JLabel lblBoulangestion = new JLabel("\u00A9 2021 Boulangestion");
	private JButton btnVente;
	private JButton btnVenteDirecte;
	private JButton btnDevis;
	private JButton btnCmdClients;
	private JButton btnHistoriqueVC;
	private JButton btnCompta;
	private JButton btnClotureCaisse;
	private JButton btnStatistiques;
	private JButton btnClients;
	private JButton btnListeClients;
	private JButton btnNewClient;
	private JButton btnStocks;
	private JButton btnCmdStock;
	private JButton btnProduits;
	private JButton btnFournisseurs;
	private JButton btnAide;
	private JButton btnDoc;
	private	JButton btnFaq;
	
	/**
	 * Create the panel.
	 */
	
	public AccueilMenu() {
		setBounds(0, 0, 1440, 900);
		setLayout(null);
		
		JPanel panel = new JPanel();
		//les sous-menus disparaissent quand on clique n'importe o� dans la page
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSsBtnFalse();
			}
		});
		panel.setBackground(new Color(254, 245, 232));
		panel.setBounds(0, 0, 1440, 900);
		add(panel);
		panel.setLayout(null);
		lblBoulangestion.setBounds(0, 886, 1440, 14);
		lblBoulangestion.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblBoulangestion);
		
		JLabel lblTitre = new JLabel("Boulangestion");
		lblTitre.setBounds(0, 62, 1440, 93);
		lblTitre.setForeground(new Color(157, 70, 6));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Gentium Book Basic", Font.PLAIN, 80));
		panel.add(lblTitre);
		
		//menu vente
		btnVente = new JButton("Vente");
		btnVente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSsBtnFalse();
				btnVente.setBackground(new Color(253, 232, 202));
				btnVenteDirecte.setVisible(true);
				btnDevis.setVisible(true);
				btnCmdClients.setVisible(true);
				btnHistoriqueVC.setVisible(true);
			}
		});
		
		//menu compta
		btnCompta = new JButton("Comptabilite");
		btnCompta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSsBtnFalse();
				btnCompta.setBackground(new Color(253, 232, 202));
				btnClotureCaisse.setVisible(true);
				btnStatistiques.setVisible(true);
			}
		});
		btnCompta.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnCompta.setBackground(new Color(242, 193, 102));
		btnCompta.setBounds(370, 215, 161, 33);
		panel.add(btnCompta);
		
		//menu clients
		btnClients = new JButton("Clients");
		btnClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSsBtnFalse();
				btnClients.setBackground(new Color(253, 232, 202));
				btnListeClients.setVisible(true);
				btnNewClient.setVisible(true);
			}
		});
		btnClients.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnClients.setBackground(new Color(242, 193, 102));
		btnClients.setBounds(641, 215, 161, 33);
		panel.add(btnClients);
		
		//menu stocks
		btnStocks = new JButton("Stocks");
		btnStocks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSsBtnFalse();
				btnStocks.setBackground(new Color(253, 232, 202));
				btnCmdStock.setVisible(true);
				btnProduits.setVisible(true);
				btnFournisseurs.setVisible(true);
			}
		});
		btnStocks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnStocks.setBackground(new Color(242, 193, 102));
		btnStocks.setBounds(914, 215, 161, 33);
		panel.add(btnStocks);
		
		//menu aide
		btnAide = new JButton("Aide");
		btnAide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSsBtnFalse();
				btnAide.setBackground(new Color(253, 232, 202));
				btnDoc.setVisible(true);
				btnFaq.setVisible(true);
			}
		});
		btnAide.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAide.setBackground(new Color(242, 193, 102));
		//btnAide.setBorder(new RoundedBorder(10));
		btnAide.setBounds(1161, 215, 161, 33);
		panel.add(btnAide);
		
		//bouton de d�connexion qui revient � la page de connexion
		JButton btnDeco = new JButton("");
		btnDeco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToLoginPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnDeco.setIcon(new ImageIcon("C:\\Users\\fredb\\AFPA\\projetBoulang\\power_off.png"));
		btnDeco.setBounds(1360, 820, 80, 80);
		panel.add(btnDeco);
		
		//d�but des sous-menus
		
		btnVente.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnVente.setBackground(new Color(242, 193, 102));
		btnVente.setBounds(114, 215, 161, 33);
		panel.add(btnVente);
		
		btnVenteDirecte = new JButton("Vente directe");
		btnVenteDirecte.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnVenteDirecte.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVenteDirecte.setBackground(new Color(242, 193, 102));
		btnVenteDirecte.setBounds(114, 284, 161, 33);
		btnVenteDirecte.setVisible(false);
		panel.add(btnVenteDirecte);
		
		btnDevis = new JButton("Devis");
		btnDevis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnDevis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDevis.setBackground(new Color(242, 193, 102));
		btnDevis.setBounds(114, 352, 161, 33);
		btnDevis.setVisible(false);
		panel.add(btnDevis);
		
		btnCmdClients = new JButton("Commandes");
		btnCmdClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToCommandesClientPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnCmdClients.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCmdClients.setBackground(new Color(242, 193, 102));
		btnCmdClients.setBounds(114, 424, 161, 33);
		btnCmdClients.setVisible(false);
		panel.add(btnCmdClients);
		
		btnHistoriqueVC = new JButton("Historique");
		btnHistoriqueVC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToHistoriqueVentesCommandesPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnHistoriqueVC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHistoriqueVC.setBackground(new Color(242, 193, 102));
		btnHistoriqueVC.setBounds(114, 496, 161, 33);
		btnHistoriqueVC.setVisible(false);
		panel.add(btnHistoriqueVC);
		
		btnClotureCaisse = new JButton("Cloture caisse");
		btnClotureCaisse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToClotureCaissePanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnClotureCaisse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClotureCaisse.setBackground(new Color(242, 193, 102));
		btnClotureCaisse.setBounds(370, 284, 161, 33);
		btnClotureCaisse.setVisible(false);
		panel.add(btnClotureCaisse);
		
		btnStatistiques = new JButton("Statistiques");
		btnStatistiques.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnStatistiques.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStatistiques.setBackground(new Color(242, 193, 102));
		btnStatistiques.setBounds(370, 352, 161, 33);
		btnStatistiques.setVisible(false);
		panel.add(btnStatistiques);
		
		btnListeClients = new JButton("Liste clients");
		btnListeClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToListeClientsPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnListeClients.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnListeClients.setBackground(new Color(242, 193, 102));
		btnListeClients.setBounds(641, 284, 161, 33);
		btnListeClients.setVisible(false);
		panel.add(btnListeClients);
		
		btnNewClient = new JButton("Nouveau client");
		btnNewClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToNouveauClientPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnNewClient.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewClient.setBackground(new Color(242, 193, 102));
		btnNewClient.setBounds(641, 352, 161, 33);
		btnNewClient.setVisible(false);
		panel.add(btnNewClient);
		
		btnCmdStock = new JButton("Commande");
		btnCmdStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchtoCommandeStockView());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnCmdStock.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCmdStock.setBackground(new Color(242, 193, 102));
		btnCmdStock.setBounds(914, 284, 161, 33);
		btnCmdStock.setVisible(false);
		panel.add(btnCmdStock);
		
		btnProduits = new JButton("Produits");
		btnProduits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchtoProduitsViewPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnProduits.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnProduits.setBackground(new Color(242, 193, 102));
		btnProduits.setBounds(914, 352, 161, 33);
		btnProduits.setVisible(false);
		panel.add(btnProduits);
		
		btnFournisseurs = new JButton("Fournisseurs");
		btnFournisseurs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToListeFournisseurs());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnFournisseurs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFournisseurs.setBackground(new Color(242, 193, 102));
		btnFournisseurs.setBounds(914, 424, 161, 33);
		btnFournisseurs.setVisible(false);
		panel.add(btnFournisseurs);
		
		btnDoc = new JButton("Documentation");
		btnDoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDoc.setBackground(new Color(242, 193, 102));
		btnDoc.setBounds(1161, 284, 161, 33);
		btnDoc.setVisible(false);
		panel.add(btnDoc);
		
		btnFaq = new JButton("FAQ");
		btnFaq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnFaq.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFaq.setBackground(new Color(242, 193, 102));
		btnFaq.setBounds(1161, 352, 161, 33);
		btnFaq.setVisible(false);
		panel.add(btnFaq);

		
	}
	
	//m�thode pour rendre les sous-menus invisibles
	public void setSsBtnFalse() {
		btnVente.setBackground(new Color(242, 193, 102));
		btnVenteDirecte.setVisible(false);
		btnDevis.setVisible(false);
		btnCmdClients.setVisible(false);
		btnHistoriqueVC.setVisible(false);
		btnCompta.setBackground(new Color(242, 193, 102));
		btnClotureCaisse.setVisible(false);
		btnStatistiques.setVisible(false);
		btnClients.setBackground(new Color(242, 193, 102));
		btnListeClients.setVisible(false);
		btnNewClient.setVisible(false);
		btnStocks.setBackground(new Color(242, 193, 102));
		btnCmdStock.setVisible(false);
		btnProduits.setVisible(false);
		btnFournisseurs.setVisible(false);
		btnAide.setBackground(new Color(242, 193, 102));
		btnFaq.setVisible(false);
		btnDoc.setVisible(false);
	}
}
