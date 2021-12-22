package view;

import controller.PanelsManager;
import controller.ProduitDAO;
import model.Produit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;

public class NouveauProduit extends JPanel {
	private final JTextField libelleField;
	private final JTextField fabricantField;
	private final JTextField TTCField;
	private final JTextField quantiteField;
	private final JTextField HTField;
	private final JTextField poidsField;

	public static boolean modify = false;
	public static Produit currentProduit = null;

	public NouveauProduit() {
		setBounds(0, 0, 1440, 900);
		setBackground(new Color(254, 245, 232));
		setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(281, 166, 815, 676);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblName = new JLabel("Libelle :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(28, 110, 130, 35);
		panel_1.add(lblName);

		JLabel lblFirstName = new JLabel("Fabricant :");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFirstName.setBounds(28, 217, 130, 35);
		panel_1.add(lblFirstName);

		JLabel lblNewLabel_2_2 = new JLabel("Poids (kg) :");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(28, 331, 130, 35);
		panel_1.add(lblNewLabel_2_2);

		JLabel quantiteLabel = new JLabel("Quantite :");
		quantiteLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		quantiteLabel.setBounds(405, 331, 130, 35);
		panel_1.add(quantiteLabel);

		JLabel lblTel = new JLabel("Prix toutes taxes comprises :");
		lblTel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTel.setBounds(328, 436, 265, 35);
		panel_1.add(lblTel);

		JLabel lblCity = new JLabel("Prix hors taxe :");
		lblCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCity.setBounds(28, 436, 149, 35);
		panel_1.add(lblCity);

		libelleField = new JTextField();
		libelleField.setBounds(168, 110, 571, 31);
		panel_1.add(libelleField);
		libelleField.setColumns(10);

		fabricantField = new JTextField();
		fabricantField.setColumns(10);
		fabricantField.setBounds(168, 217, 571, 31);
		panel_1.add(fabricantField);

		TTCField = new JTextField();
		TTCField.setColumns(10);
		TTCField.setBounds(596, 436, 143, 31);
		panel_1.add(TTCField);

		quantiteField = new JTextField();
		quantiteField.setColumns(10);
		quantiteField.setBounds(512, 331, 227, 35);
		panel_1.add(quantiteField);

		HTField = new JTextField();
		HTField.setColumns(10);
		HTField.setBounds(179, 442, 137, 31);
		panel_1.add(HTField);

		poidsField = new JTextField();
		poidsField.setBounds(168, 331, 227, 35);
		panel_1.add(poidsField);
		poidsField.setColumns(10);
		panel_1.add(poidsField);
		JButton btnNewButton = new JButton("Valider");
		if (modify) {
			// Pre-remplis les champs.
			fillFields(currentProduit);
			// Form to update a client.
			btnNewButton.setText("Modifier");
			btnNewButton.addActionListener(e -> {
				ProduitDAO produitDAO = new ProduitDAO();
				Produit nouveau = new Produit();
				nouveau.setLibelle(libelleField.getText());
				nouveau.setFabricant(fabricantField.getText());
				nouveau.setPoids(Float.parseFloat(poidsField.getText()));
				nouveau.setQuantite(Integer.parseInt(quantiteField.getText()));
				nouveau.setPrixHT(Float.parseFloat(HTField.getText()));
				nouveau.setPrixTTC(Float.parseFloat(TTCField.getText()));
				produitDAO.update(nouveau, currentProduit.getId());
				modify = false;
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchtoProduitsViewPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnNewButton.setBackground(new Color(242, 193, 102));
			btnNewButton.setBounds(168, 571, 155, 30);
			panel_1.add(btnNewButton);	
		} else {
		// Form to create a.
			btnNewButton.setText("Valider");
		btnNewButton.addActionListener(e -> {
			ProduitDAO produitDAO = new ProduitDAO();
			produitDAO.add(new Produit(libelleField.getText(), fabricantField.getText(), Float.parseFloat(poidsField.getText()), Integer.parseInt(quantiteField.getText()), Float.parseFloat(HTField.getText()), Float.parseFloat(TTCField.getText())));
			PanelsManager.contentPane.removeAll();
			PanelsManager.contentPane.add(PanelsManager.switchtoProduitsViewPanel());
			PanelsManager.contentPane.repaint();
			PanelsManager.contentPane.revalidate();
		});
	}
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(242, 193, 102));
		btnNewButton.setBounds(168, 571, 155, 30);
		panel_1.add(btnNewButton);
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(e -> {
			int n = JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir annuler ?","Annuler",JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				modify = false;
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchtoProduitsViewPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAnnuler.setForeground(new Color(0, 0, 0));
		btnAnnuler.setBackground(new Color(196, 196, 196));
		btnAnnuler.setBounds(584, 576, 155, 30);
		panel_1.add(btnAnnuler);
		
		JPanel menu = new JPanel();
		menu.setLayout(null);
		menu.setBackground(Color.WHITE);
		menu.setBounds(0, 0, 1440, 90);
		add(menu);
		
		JButton btnRetour = new JButton("");
		URL returnURL = ClassLoader.getSystemResource("res/arrow_left.png");
		btnRetour.setIcon(new ImageIcon(returnURL));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modify = false;
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchtoProduitsViewPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
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
		URL exitURL = ClassLoader.getSystemResource("res/exit.png");
		btnAccueil.setIcon(new ImageIcon(exitURL));
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modify = false;
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToAccueilMenu());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnAccueil.setBounds(1370, 11, 40, 40);
		menu.add(btnAccueil);
		
		JLabel lblAccueil = new JLabel("Accueil");
		lblAccueil.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAccueil.setBounds(1355, 62, 63, 18);
		menu.add(lblAccueil);
	}

	// Method pour remplir les champs
	public void fillFields(Produit produit) {
		libelleField.setText(produit.getLibelle());
		fabricantField.setText(produit.getFabricant());
		poidsField.setText(String.valueOf(produit.getPoids()));
		quantiteField.setText(String.valueOf(produit.getQuantite()));
		HTField.setText(String.valueOf(produit.getPrixHT()));
		TTCField.setText(String.valueOf(produit.getPrixTTC()));
	}
}

