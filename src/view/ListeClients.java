package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.ClientDao;
import controller.IDao;
import controller.PanelsManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ListeClients extends JPanel {
	DefaultTableModel model;
	private JTable table;
	/**
	 * Create the panel.
	 */
	public ListeClients() {
		setBackground(new Color(255, 235, 205));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1440, 131);
		panel.setBackground(Color.WHITE);
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
		
		/*JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(113, 231, 1217, 509);
		add(scrollPane);*/
		
		// Table
		/*table = new JTable();
		model = new DefaultTableModel();
		Object[] column = {"N°Client","Nom","Prenom","Adresse","Téléphone","Email","Modifier","Historique"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);*/
		
		ClientDao clientDao = new ClientDao();
		if(clientDao.read().isEmpty()) {
			JLabel lblNewLabel = new JLabel("Aucun client repertorié");
			lblNewLabel.setBounds(59, 25, 718, 150);
			panel.add(lblNewLabel);
		} else {
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 0, 911, 321);
			panel.add(scrollPane);
			
			table = new JTable();
			/*table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int id = table.getSelectedRow();
					
					int article_id = (int) table.getModel().getValueAt(id, 0);
					
					contentPane.removeAll();
					ShowArticle show = new ShowArticle(article_id);
					//show.setVisible(true);
					contentPane.add(show);
					contentPane.repaint();
					contentPane.revalidate();*/
		}
		
		// Ajouter un client
		JButton btnNewClient = new JButton("Nouveau Client");
		btnNewClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToNouveauClientPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnNewClient.setBackground(new Color(244, 164, 96));
		btnNewClient.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewClient.setBounds(469, 792, 463, 43);
		add(btnNewClient);
		
		
		
		
		
	}
}
