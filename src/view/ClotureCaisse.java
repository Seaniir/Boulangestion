package view;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.CommandeClientDAO;
import controller.FournisseurDao;
import controller.PanelsManager;
import model.CommandeClient;
import model.Fournisseur;
import model.Produit;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class ClotureCaisse extends JPanel {
	private JButton btnValider;
	public static boolean modify = false;

	/**
	 * Create the panel.
	 */
	public ClotureCaisse() {
		setBackground(new Color(254, 245, 232));

		setBounds(0, 0, 1440, 900);
		setLayout(null);

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(
					"C:\\Users\\Quentin\\Downloads\\projetBoulang\\ApplicationFrameHost_9xgfw1hOm4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(1440, 900,
				Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		JPanel menu = new JPanel();
		menu.setLayout(null);
		menu.setBackground(Color.WHITE);
		menu.setBounds(0, 0, 1440, 90);
		add(menu);
		
		JButton btnRetour = new JButton("");
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

		JPanel formulaire = new JPanel();
		formulaire.setBackground(Color.WHITE);
		formulaire.setBounds(265, 155, 911, 467);
		add(formulaire);
		formulaire.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Total esp\u00E8ces :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(63, 73, 162, 37);
		formulaire.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Total cartes banquaires :");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(63, 181, 238, 37);
		formulaire.add(lblNewLabel_2_1);
		
		JLabel totalEspeceLabel = new JLabel("");
		totalEspeceLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalEspeceLabel.setBounds(636, 73, 195, 37);
		formulaire.add(totalEspeceLabel);
		
		JLabel totalCarteLabel = new JLabel("");
		totalCarteLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalCarteLabel.setBounds(636, 181, 195, 37);
		formulaire.add(totalCarteLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Total \u00E0 cl\u00F4turer :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(183, 356, 153, 37);
		formulaire.add(lblNewLabel_3);
		
		JLabel totalClotureLabel = new JLabel("");
		totalClotureLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalClotureLabel.setBounds(438, 356, 153, 37);
		formulaire.add(totalClotureLabel);
		
		JButton btnNewButton = new JButton("Cl\u00F4turer la caisse");
		btnNewButton.setBackground(new Color(242, 193, 102));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(578, 685, 335, 57);
		add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBounds(1069, 89, 371, 50);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Date du jour :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(20, 11, 152, 28);
		panel.add(lblNewLabel_4);

		JLabel dateLabel = new JLabel("");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dateLabel.setBounds(182, 11, 142, 28);
		panel.add(dateLabel);

		btnValider = new JButton("Valider");

		CommandeClientDAO commandeClientDAO = new CommandeClientDAO();
		List<CommandeClient> listCommandes = commandeClientDAO.readIsClosed();
		System.out.println(listCommandes.size());
		float prixEspeceLabel = 0;
		float prixCarteLabel = 0;
		for (int i = 0 ; i < listCommandes.size() ; i++)
		{
			System.out.println(listCommandes.get(i).getId());
			if (listCommandes.get(i).getTypePaiment().equals("Especes") && !listCommandes.get(i).isCloturee())
			{
				prixEspeceLabel += listCommandes.get(i).getPrixTotal();
			}
			else if(listCommandes.get(i).getTypePaiment().equals("Carte banquaire") && !listCommandes.get(i).isCloturee())
			{
				prixCarteLabel += listCommandes.get(i).getPrixTotal();
			}
		}
		totalEspeceLabel.setText(String.valueOf(prixEspeceLabel));
		totalCarteLabel.setText(String.valueOf(prixCarteLabel));
		totalClotureLabel.setText(String.valueOf(prixEspeceLabel + prixCarteLabel));
		String DATE_FORMAT_NOW = "dd/MM/yyyy";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		dateLabel.setText(sdf.format(cal.getTime()));

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0 ; i < listCommandes.size() ; i++)
				{
					System.out.println(listCommandes.get(i).getProduits());
					listCommandes.get(i).setCloturee(true);
					commandeClientDAO.update(listCommandes.get(i), listCommandes.get(i).getId());
				}
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToClotureCaissePanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
	}
}
