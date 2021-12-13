package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class nouvelleCommandeView extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public nouvelleCommandeView() {
		setBounds(0, 0, 1440, 900);
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 1440, 99);
		add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Historique");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 74, 127, 25);
		panel_1.add(lblNewLabel);
		
		JLabel lblAccueil = new JLabel("Accueil");
		lblAccueil.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAccueil.setBounds(1333, 74, 127, 25);
		panel_1.add(lblAccueil);
		
		JButton accueilBtn = new JButton("");
		accueilBtn.setBounds(1333, 0, 107, 99);
		panel_1.add(accueilBtn);
		
		JButton historiqueBtn = new JButton("");
		historiqueBtn.setVerticalAlignment(SwingConstants.TOP);
		historiqueBtn.setBounds(0, 0, 127, 99);
		panel_1.add(historiqueBtn);
		
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

	}
}
