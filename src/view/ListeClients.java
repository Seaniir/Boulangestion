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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.ClientDao;
import controller.IDao;
import controller.PanelsManager;
import model.Client;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JScrollPane;

public class ListeClients extends JPanel {
	
	private JTable listingClients;
	JButton btnModify = new JButton();
	JButton btnDelete = new JButton();
	JButton btnBrowsingHistory = new JButton();
	
	public ListeClients() {
		setBounds(0, 0, 1440, 900);
		setBackground(new Color(254, 245, 232));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1440, 90);
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
		URL exitURL = ClassLoader.getSystemResource("res/exit.png");
		btnAccueil.setBackground(Color.WHITE);
		btnAccueil.setIcon(new ImageIcon(exitURL));
		btnAccueil.setBounds(1370, 11, 40, 40);
		panel.add(btnAccueil);
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
		btnNewClient.setBackground(new Color(242, 193, 102));
		btnNewClient.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewClient.setBounds(470, 823, 499, 53);
		add(btnNewClient);
		
		// Table
		JPanel listing = new JPanel();
		listing.setBackground(new Color(255, 255, 255));
		listing.setBounds(40, 100, 1360, 624);
		add(listing);
		listing.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1360, 624);
		listing.add(scrollPane);
		
		listingClients = new JTable();
		listingClients.setRowSelectionAllowed(false);
		scrollPane.setViewportView(listingClients);
		listingClients.setRowHeight(100);
		listingClients.setModel(liste());
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		listingClients.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		listingClients.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		listingClients.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		listingClients.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		listingClients.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		listingClients.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		listingClients.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		
		listingClients.getColumn("Modifier").setCellRenderer(new ButtonRenderer());
		listingClients.getColumn("Modifier").setCellEditor(new ButtonEditor(new JCheckBox()));
		listingClients.getColumn("Historique").setCellRenderer(new SecondButtonRenderer());
		listingClients.getColumn("Historique").setCellEditor(new SecondButtonEditor(new JCheckBox()));
		listingClients.getColumn("Supprimer").setCellRenderer(new ThirdButtonRenderer());
		listingClients.getColumn("Supprimer").setCellEditor(new ThirdButtonEditor(new JCheckBox()));
		
		// Lors du clic sur le bouton modifier il se passe: 
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir modifier ?","Modification",JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					NouveauClient.modify = true;
					ClientDao clientDao = new ClientDao();
					clientDao.findById((int)listingClients.getValueAt(listingClients.getSelectedRow(),0));
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchToNouveauClientPanel());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
				} else if (n == JOptionPane.NO_OPTION) {
					
				}
					
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir supprimer ?","Suppression",JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					ClientDao clientDao = new ClientDao();
					clientDao.delete((int) listingClients.getValueAt(listingClients.getSelectedRow(), 0));
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchToListeClientsPanel());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
				} else if (n == JOptionPane.NO_OPTION) {
					
				}
				
			}
		});
		
		btnBrowsingHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchToCommandesClientPanel());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
			}
		});
	}
		// Les intitul?s des colonnes du tableau
		public DefaultTableModel liste() {
			String [] col = {"N? Client",
					"Nom",
					"Pr?nom",
					"Adresse", 
					"T?l?phone",
					"Email", 
					"Modifier",
					"Historique",
					"Supprimer"
			};
			// Les donn?es du tableau
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
	
		
	// Class pour les boutons dans le JTable 
	class ButtonRenderer extends JButton implements TableCellRenderer{
			public ButtonRenderer() {
				setOpaque(true);
			}
			public Component getTableCellRendererComponent(JTable table, Object value,
														   boolean isSelected, boolean hasFocus, int row, int column) {
				setText((value == null) ? "Modifier" : value.toString());
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
			label = (value == null) ? "Modifier" : value.toString();
			btnModify.setText(label);
			return btnModify;
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
	    	setText((value == null) ? "Historique" : value.toString());
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
	      label = (value == null) ? "Historique" : value.toString();
	      btnBrowsingHistory.setText(label);
	      
	      return btnBrowsingHistory;
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
	    	setText((value == null) ? "Supprimer" : value.toString());
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
	      label = (value == null) ? "Supprimer" : value.toString();
	      btnDelete.setText(label);
	      return btnDelete;
	    }
	    public Object getCellEditorValue() 
	    {
	      return new String(label);
	    }
	}
}
