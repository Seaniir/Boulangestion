package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.cj.conf.ConnectionUrlParser;

import controller.CommandeClientDAO;
import controller.PanelsManager;
import model.Client;
import model.CommandeClient;

public class ListeDevis extends JPanel {
	private JTable table;
	JButton btnValidate = new JButton();
	JButton btnModify = new JButton();
	JButton btnDelete = new JButton();
	
	public ListeDevis() {
		setBounds(0, 0, 1440, 900);
		setBackground(new Color(254, 245, 232));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1440, 94);
		panel.setBackground(Color.WHITE);
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
		btnAccueil.setBackground(Color.WHITE);
		URL exitURL = ClassLoader.getSystemResource("exit.png");
		btnAccueil.setIcon(new ImageIcon(exitURL));
		btnAccueil.setBounds(1370, 11, 40, 40);
		panel.add(btnAccueil);
		// Ajouter un client
		
		JButton btnNewClient = new JButton("Nouveau Devis");
		btnNewClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchtoNouveauDevisPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			
			}
		});
		btnNewClient.setBackground(new Color(242, 193, 102));
		btnNewClient.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewClient.setBounds(503, 914, 463, 43);
		add(btnNewClient);
		
		// Table
		JPanel listing = new JPanel();
		listing.setBackground(new Color(255, 255, 255));
		listing.setBounds(264, 155, 912, 706);
		add(listing);
		listing.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 912, 706);
		listing.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		table.setRowHeight(100);
		table.setModel(liste());
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		
		
		table.getColumn("Valider").setCellRenderer(new ButtonRenderer());
		table.getColumn("Valider").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn("Modifier").setCellRenderer(new SecondButtonRenderer());
		table.getColumn("Modifier").setCellEditor(new SecondButtonEditor(new JCheckBox()));
		table.getColumn("Annuler").setCellRenderer(new ThirdButtonRenderer());
		table.getColumn("Annuler").setCellEditor(new ThirdButtonEditor(new JCheckBox()));
		
		// Lors du clic sur le bouton modifier il se passe: 
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "Etes vous sur de modifier ?","Modification",JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					CommandeClientDAO commandeClientDAO = new CommandeClientDAO();
					ConnectionUrlParser.Pair < CommandeClient, Client > pair = 
							commandeClientDAO.findById((int) table.getValueAt(table.getSelectedRow(), 0));
					NouveauDevis.currentCommande = pair.left;
					NouveauDevis.currentClient = pair.right;
					NouveauDevis.modify = true;
	
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchtoNouveauDevisPanel());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
				} else if (n == JOptionPane.NO_OPTION) {
					
				}	
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "Etes vous sur de supprimer ?","Suppression",JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					CommandeClientDAO ccDao = new CommandeClientDAO();
					ccDao.delete((int) table.getValueAt(table.getSelectedRow(), 0));
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchtoListeDevisPanel());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
				} else if (n == JOptionPane.NO_OPTION) {
					
				}
				
			}
		});
		
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "Etes vous sur de passer ce devis en valider ?","Validation",JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					CommandeClientDAO ccDao = new CommandeClientDAO();
					ccDao.archive((int) table.getValueAt(table.getSelectedRow(), 0));
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchtoListeDevisPanel());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
				} else if (n == JOptionPane.NO_OPTION) {
					
				}
				
			}
		});
	}
		// Intitulé des colonnes
		public DefaultTableModel liste() {
			String [] col = {
					"N° Devis",
					"Crée le",
					"Client",
					"Nbr articles", 
					"Prix total TTC",
					"Valider", 
					"Modifier",
					"Annuler",		
			};
			DefaultTableModel tab = new DefaultTableModel(null, col);
			// Les données du tableau 
			CommandeClientDAO hcv = new CommandeClientDAO();
			ConnectionUrlParser.Pair<ArrayList<CommandeClient>, ArrayList<Client>> pair = 
					hcv.readPairDevis();
			for (int i = 0; i < pair.left.size(); i++) {
				Vector vect = new Vector();
				vect.add(pair.left.get(i).getId());
				vect.add(pair.left.get(i).getWithdrawal_at());
				vect.add(pair.right.get(i).getFirstName()+" "+pair.right.get(i).getName());
				vect.add(pair.left.get(i).getNbrArticles());
				vect.add(pair.left.get(i).getPrixTotal());
				tab.addRow(vect);
			}
			return tab;
		}	
		
	// Class qui créer les boutons dans la JTable  
	class ButtonRenderer extends JButton implements TableCellRenderer{
			public ButtonRenderer() {
				setOpaque(true);
			}
			public Component getTableCellRendererComponent(JTable table, Object value,
														   boolean isSelected, boolean hasFocus, int row, int column) {
				setText((value == null) ? "Valider" : value.toString());
				return this;
			}
	}
	class ButtonEditor extends DefaultCellEditor{
		private String label;

		public ButtonEditor(JCheckBox checkBox)
		{
			super(checkBox);
		}
		public Component getTableCellEditorComponent(JTable table, Object value,
													 boolean isSelected, int row, int column)
		{
			label = (value == null) ? "Valider" : value.toString();
			btnValidate.setText(label);
			return btnValidate;
		}
		public Object getCellEditorValue()
		{
			return new String(label);
		}
	}
	// Second bouton du tableau
	class SecondButtonRenderer extends JButton implements TableCellRenderer{
	    public SecondButtonRenderer() {
	      setOpaque(true);
	    }
	    public Component getTableCellRendererComponent(JTable table, Object value,
	    boolean isSelected, boolean hasFocus, int row, int column) {
	    	setText((value == null) ? "Modifier" : value.toString());
			return this;
	    }
	}
	class SecondButtonEditor extends DefaultCellEditor{
	    public SecondButtonEditor(JCheckBox checkBox) {
			super(checkBox);	
		}
		private String label;
	    public Component getTableCellEditorComponent(JTable table, Object value,
	    boolean isSelected, int row, int column){
	      label = (value == null) ? "Modifier" : value.toString();
	      btnModify.setText(label);
	      
	      return btnModify;
	    }
	    public Object getCellEditorValue() 
	    {
	      return new String(label);
	    }
	}
	
	// Troisieme bouton du tableau
	class ThirdButtonRenderer extends JButton implements TableCellRenderer{
	    public ThirdButtonRenderer() {
	      setOpaque(true);
	    }
	    public Component getTableCellRendererComponent(JTable table, Object value,
	    boolean isSelected, boolean hasFocus, int row, int column) {
	    	setText((value == null) ? "Annuler" : value.toString());
			return this;
	    }
	}
	class ThirdButtonEditor extends DefaultCellEditor{
	    public ThirdButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}
		private String label;
	    public Component getTableCellEditorComponent(JTable table, Object value,
	    boolean isSelected, int row, int column){
	      label = (value == null) ? "Annuler" : value.toString();
	      btnDelete.setText(label);
	      return btnDelete;
	    }
	    public Object getCellEditorValue() 
	    {
	      return new String(label);
	    }
	}

}
