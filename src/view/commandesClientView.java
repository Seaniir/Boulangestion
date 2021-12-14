package view;

import controller.CommandeClientDAO;
import controller.PanelsManager;
import model.CommandeClient;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class CommandesClientView extends JPanel {

	private JTable table;
	JButton button = new JButton();
	/**
	 * Create the panel.
	 */
	public CommandesClientView() {
		setBounds(0, 0, 1440, 900);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 248, 220));
		panel.setBounds(0, 100, 1440, 800);
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

								int article_id = (int) table.getModel().getValueAt(id, 0);

								removeAll();

								repaint();
								revalidate();
							}
						});
						
								scrollPane.setViewportView(table);
								table.setRowHeight(100);
								table.setModel(liste());
								
								JButton btnNewButton = new JButton("Nouvelle Commande");
								btnNewButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										PanelsManager.contentPane.removeAll();
										PanelsManager.contentPane.add(PanelsManager.switchToNouvelleCommandePanel());
										PanelsManager.contentPane.revalidate();
										PanelsManager.contentPane.repaint();
									}
								});
								btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
								btnNewButton.setBackground(new Color(255, 165, 0));
								btnNewButton.setForeground(new Color(0, 0, 0));
								btnNewButton.setBounds(467, 663, 499, 53);
								panel.add(btnNewButton);
		button.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						JOptionPane.showMessageDialog(null,"Do you want to modify this line?");
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
		table.getColumn("D\u00e9tails").setCellRenderer(new ButtonRenderer());
		table.getColumn("D\u00e9tails").setCellEditor(new ButtonEditor(new JCheckBox()));
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 1440, 99);
		add(panel_1);
		panel_1.setLayout(null);
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\Quentin\\Downloads\\history.png");
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg);  // transform it back
		
		JButton accueilBtn = new JButton("");
		ImageIcon imageIcon1 = new ImageIcon("C:\\Users\\Quentin\\Downloads\\sign-out.png");
		Image image1 = imageIcon1.getImage(); // transform it
		Image newimg1 = image1.getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		accueilBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToAccueilMenu());
				PanelsManager.contentPane.revalidate();
				PanelsManager.contentPane.repaint();
			}
		});
		imageIcon1 = new ImageIcon(newimg1);  // transform it back
		
		JLabel lblNewLabel = new JLabel("Historique");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 74, 127, 25);
		panel_1.add(lblNewLabel);
		
		JLabel lblAccueil = new JLabel("Accueil");
		lblAccueil.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAccueil.setBounds(1323, 74, 127, 25);
		panel_1.add(lblAccueil);
		accueilBtn.setIcon(imageIcon1);
		accueilBtn.setBounds(1333, 0, 107, 76);
		panel_1.add(accueilBtn);
		
		JButton historiqueBtn = new JButton("");
		historiqueBtn.setIcon(imageIcon);
		historiqueBtn.setBounds(0, 0, 127, 76);
		panel_1.add(historiqueBtn);

	}

	public DefaultTableModel liste() {

		String [] col = {"ID","Date création","Retirer à", "Client", "Nombres d'articles", "Prix Total", "Accompte", "Status", "D\u00e9tails"};
		DefaultTableModel tab = new DefaultTableModel(null, col);

		CommandeClientDAO commandeClientDAO = new CommandeClientDAO();
		List<CommandeClient> listArticle = new ArrayList<>();
		listArticle.addAll(commandeClientDAO.read());
		for (CommandeClient article : listArticle) {
			Vector vect = new Vector();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date dateObj = article.getCreated_at();
			String created_at = df.format(dateObj);
			DateFormat dw = new SimpleDateFormat("dd/MM/yyyy");
			Date dateW = article.getWithdrawal_at();
			String withdrawal_at = dw.format(dateW);
			vect.add(article.getId());
			vect.add(created_at);
			vect.add(withdrawal_at);
			vect.add(article.getFk_client());
			vect.add(article.getNbrArticles());
			vect.add(article.getPrixTotal());
			vect.add(article.isAccompte());
			vect.add(article.getStatus());
			tab.addRow(vect);
		}
		return tab;
	}

	class ButtonRenderer extends JButton implements TableCellRenderer
	{
		public ButtonRenderer() {
			setOpaque(true);
		}
		public Component getTableCellRendererComponent(JTable table, Object value,
													   boolean isSelected, boolean hasFocus, int row, int column) {
			setText((value == null) ? "Modify" : value.toString());
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor
	{
		private String label;

		public ButtonEditor(JCheckBox checkBox)
		{
			super(checkBox);
		}
		public Component getTableCellEditorComponent(JTable table, Object value,
													 boolean isSelected, int row, int column)
		{
			label = (value == null) ? "Modify" : value.toString();
			button.setText(label);
			return button;
		}
		public Object getCellEditorValue()
		{
			return new String(label);
		}
	}
}
