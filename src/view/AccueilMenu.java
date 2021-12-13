package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
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
		setLayout(null);
		
		JPanel panel = new JPanel();
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
		
		btnVente = new JButton("Vente");
		btnVente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnVente.setBackground(new Color(253, 232, 202));
				btnVenteDirecte.setVisible(true);
				btnDevis.setVisible(true);
				btnCmdClients.setVisible(true);
				btnHistoriqueVC.setVisible(true);
			}
		});
		btnVente.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnVente.setBackground(new Color(242, 193, 102));
		btnVente.setBounds(114, 215, 161, 33);
		panel.add(btnVente);
		
		btnVenteDirecte = new JButton("Vente directe");
		btnVenteDirecte.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVenteDirecte.setBackground(new Color(242, 193, 102));
		btnVenteDirecte.setBounds(114, 284, 161, 33);
		btnVenteDirecte.setVisible(false);
		panel.add(btnVenteDirecte);
		
		btnDevis = new JButton("Devis");
		btnDevis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDevis.setBackground(new Color(242, 193, 102));
		btnDevis.setBounds(114, 352, 161, 33);
		btnDevis.setVisible(false);
		panel.add(btnDevis);
		
		btnCmdClients = new JButton("Commandes");
		btnCmdClients.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCmdClients.setBackground(new Color(242, 193, 102));
		btnCmdClients.setBounds(114, 424, 161, 33);
		btnCmdClients.setVisible(false);
		panel.add(btnCmdClients);
		
		btnHistoriqueVC = new JButton("Historique");
		btnHistoriqueVC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHistoriqueVC.setBackground(new Color(242, 193, 102));
		btnHistoriqueVC.setBounds(114, 496, 161, 33);
		btnHistoriqueVC.setVisible(false);
		panel.add(btnHistoriqueVC);
		
		btnCompta = new JButton("Comptabilite");
		btnCompta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSsBtnFalse();
				btnCompta.setBackground(new Color(253, 232, 202));
			}
		});
		btnCompta.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnCompta.setBackground(new Color(242, 193, 102));
		btnCompta.setBounds(370, 215, 161, 33);
		panel.add(btnCompta);
		
		btnClients = new JButton("Clients");
		btnClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSsBtnFalse();
				btnClients.setBackground(new Color(253, 232, 202));
			}
		});
		btnClients.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnClients.setBackground(new Color(242, 193, 102));
		btnClients.setBounds(641, 215, 161, 33);
		panel.add(btnClients);
		
		btnStocks = new JButton("Stocks");
		btnStocks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSsBtnFalse();
				btnStocks.setBackground(new Color(253, 232, 202));
			}
		});
		btnStocks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnStocks.setBackground(new Color(242, 193, 102));
		btnStocks.setBounds(914, 215, 161, 33);
		panel.add(btnStocks);
		
		btnAide = new JButton("Aide");
		btnAide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSsBtnFalse();
				btnAide.setBackground(new Color(253, 232, 202));
			}
		});
		btnAide.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAide.setBackground(new Color(242, 193, 102));
		//btnAide.setBorder(new RoundedBorder(10));
		btnAide.setBounds(1161, 215, 161, 33);
		panel.add(btnAide);
		
		JButton btnDeco = new JButton("");
		btnDeco.setIcon(new ImageIcon("C:\\Users\\fredb\\AFPA\\projetBoulang\\power_off.png"));
		btnDeco.setBounds(1360, 820, 80, 80);
		panel.add(btnDeco);
		
		
		btnClotureCaisse = new JButton("Cloture caisse");
		btnClotureCaisse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClotureCaisse.setBackground(new Color(242, 193, 102));
		btnClotureCaisse.setBounds(370, 284, 161, 33);
		btnClotureCaisse.setVisible(false);
		panel.add(btnClotureCaisse);
		
		btnStatistiques = new JButton("Statistiques");
		btnStatistiques.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStatistiques.setBackground(new Color(242, 193, 102));
		btnStatistiques.setBounds(370, 352, 161, 33);
		btnStatistiques.setVisible(false);
		panel.add(btnStatistiques);
		
		btnListeClients = new JButton("Liste clients");
		btnListeClients.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnListeClients.setBackground(new Color(242, 193, 102));
		btnListeClients.setBounds(641, 284, 161, 33);
		btnListeClients.setVisible(false);
		panel.add(btnListeClients);
		
		btnNewClient = new JButton("Nouveau client");
		btnNewClient.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewClient.setBackground(new Color(242, 193, 102));
		btnNewClient.setBounds(641, 352, 161, 33);
		btnNewClient.setVisible(false);
		panel.add(btnNewClient);
		
		btnCmdStock = new JButton("Commande");
		btnCmdStock.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCmdStock.setBackground(new Color(242, 193, 102));
		btnCmdStock.setBounds(914, 284, 161, 33);
		btnCmdStock.setVisible(false);
		panel.add(btnCmdStock);
		
		btnProduits = new JButton("Produits");
		btnProduits.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnProduits.setBackground(new Color(242, 193, 102));
		btnProduits.setBounds(914, 352, 161, 33);
		btnProduits.setVisible(false);
		panel.add(btnProduits);
		
		btnFournisseurs = new JButton("Fournisseurs");
		btnFournisseurs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFournisseurs.setBackground(new Color(242, 193, 102));
		btnFournisseurs.setBounds(914, 424, 161, 33);
		btnFournisseurs.setVisible(false);
		panel.add(btnFournisseurs);
		
		btnDoc = new JButton("Documentation");
		btnDoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDoc.setBackground(new Color(242, 193, 102));
		btnDoc.setBounds(1161, 284, 161, 33);
		btnDoc.setVisible(false);
		panel.add(btnDoc);
		
		btnFaq = new JButton("FAQ");
		btnFaq.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFaq.setBackground(new Color(242, 193, 102));
		btnFaq.setBounds(1161, 352, 161, 33);
		btnFaq.setVisible(false);
		panel.add(btnFaq);

		
	}
	public void setSsBtnFalse() {
		btnVente.setBackground(new Color(242, 193, 102));
		btnVenteDirecte.setVisible(false);
		btnDevis.setVisible(false);
		btnCmdClients.setVisible(false);
		btnHistoriqueVC.setVisible(false);
		btnCompta.setBackground(new Color(242, 193, 102));
		
		btnClients.setBackground(new Color(242, 193, 102));
		btnStocks.setBackground(new Color(242, 193, 102));
		btnAide.setBackground(new Color(242, 193, 102));
		
	}
}
