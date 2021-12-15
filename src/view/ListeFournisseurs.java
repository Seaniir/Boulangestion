package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.FournisseurDao;
import controller.PanelsManager;
import model.Fournisseur;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListeFournisseurs extends JPanel {
	private JTable listingFournisseurs;
	
	JButton btnModifier = new JButton();
	JButton btnHistorique = new JButton();
	JButton btnDelete = new JButton();
	/**
	 * Create the panel.
	 */
	public ListeFournisseurs() {
		setBounds(0, 0, 1440, 900);
		setBackground(new Color(254, 245, 232));
		setLayout(null);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(255, 255, 255));
		menu.setBounds(0, 0, 1440, 90);
		add(menu);
		menu.setLayout(null);
		
		JButton btnAccueil = new JButton("");
		btnAccueil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToAccueilMenu());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
			}
		});
		btnAccueil.setIcon(new ImageIcon("C:\\Users\\fredb\\AFPA\\workspace-java\\Boulangestion\\projetBoulang\\exit.png"));
		btnAccueil.setBounds(1370, 11, 40, 40);
		menu.add(btnAccueil);
		
		JLabel lblNewLabel_1 = new JLabel("Accueil");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(1355, 62, 63, 18);
		menu.add(lblNewLabel_1);
		
		JPanel listing = new JPanel();
		listing.setBackground(new Color(255, 255, 255));
		listing.setBounds(40, 100, 1360, 624);
		add(listing);
		listing.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1360, 624);
		listing.add(scrollPane);
		
		listingFournisseurs = new JTable();
		listingFournisseurs.setRowSelectionAllowed(false);
		scrollPane.setViewportView(listingFournisseurs);
		listingFournisseurs.setRowHeight(100);
		listingFournisseurs.setModel(liste());
		
		//Centrer le contenu des cellules
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		listingFournisseurs.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		listingFournisseurs.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		listingFournisseurs.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		listingFournisseurs.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		listingFournisseurs.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		listingFournisseurs.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		listingFournisseurs.getColumn("Modifier").setCellRenderer(new ButtonRenderer());
		listingFournisseurs.getColumn("Modifier").setCellEditor(new ButtonEditor(new JCheckBox()));
		listingFournisseurs.getColumn("Historique").setCellRenderer(new SecondButtonRenderer());
		listingFournisseurs.getColumn("Historique").setCellEditor(new SecondButtonEditor(new JCheckBox()));
		listingFournisseurs.getColumn("Supprimer").setCellRenderer(new ThirdButtonRenderer());
		listingFournisseurs.getColumn("Supprimer").setCellEditor(new ThirdButtonEditor(new JCheckBox()));
		
		//bouton de colonne "modifier" qui redirige vers le formulaire Fournisseur à modifier
		btnModifier.addActionListener(new ActionListener(){			        
			@Override
			public void actionPerformed(ActionEvent e) {
				//pop-up de confirmation: si oui va sur la modification du fournisseur, si non, ne fait rien
				if (JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir modifier?", "Attention",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					FournisseurDao fournisseurDao = new FournisseurDao();
					fournisseurDao.findById((int) listingFournisseurs.getValueAt(listingFournisseurs.getSelectedRow(), 0));
					NewFournisseur.modify = true;
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchToNewFournisseur());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
				}
			}
	    });
		
		//bouton de la colonne "historique" qui redirige vers la page de listing commandes fournisseurs filtré par la 
		//société
		btnHistorique.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent event)
	        {
	        	PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToLambdaPanel());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
	        }
	    });
		
		btnDelete.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent event)
	        {
	        	//pop-up de confirmation: si oui va sur la suppression du fournisseur dans ma liste, pas dans la bdd, si non, ne fait rien
				if (JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer?", "Attention",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					FournisseurDao fournisseurDao = new FournisseurDao();
					fournisseurDao.delete((int) listingFournisseurs.getValueAt(listingFournisseurs.getSelectedRow(), 0));
					
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchToListeFournisseurs());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
				}
	        }
	    });
		
		//redirection pour ajouter un nouveau fournisseur
		JButton btnNewFournisseur = new JButton("Nouveau Fournisseur");
		btnNewFournisseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToNewFournisseur());
				PanelsManager.contentPane.revalidate();
				PanelsManager.contentPane.repaint();
			}
		});
		btnNewFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewFournisseur.setBackground(new Color(242, 193, 102));
		btnNewFournisseur.setForeground(new Color(0, 0, 0));
		btnNewFournisseur.setBounds(470, 823, 499, 53);
		add(btnNewFournisseur);
	}
	
	//remplissage du tableau
	public DefaultTableModel liste() {
		String [] col = {"N° Fournisseur","Societe","Correspondant","Adresse","Telephone", "Email","Modifier", "Historique", "Supprimer"};
		DefaultTableModel tab = new DefaultTableModel(null, col);
		
		FournisseurDao fournisseurDao = new FournisseurDao();
		List<Fournisseur> listFournisseurs = new ArrayList<>();
		listFournisseurs.addAll(fournisseurDao.read());
		for (Fournisseur fournisseur : listFournisseurs) {
			Vector vect = new Vector();
			vect.add(fournisseur.getId());
			 vect.add(fournisseur.getSociete());
			 vect.add(fournisseur.getCorrespondant());
			 vect.add(fournisseur.getAdresse()+" "+fournisseur.getCodePostal()+" "+fournisseur.getVille());
			 vect.add(fournisseur.getTel());
			 vect.add(fournisseur.getEmail());
			 
			 tab.addRow(vect);
		}
		return tab;
	}
	
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
	    
	    public ButtonEditor(JCheckBox checkBox){
	      super(checkBox);
	    }
	    public Component getTableCellEditorComponent(JTable table, Object value,
	    boolean isSelected, int row, int column){
	      label = (value == null) ? "Modifier" : value.toString();
	      btnModifier.setText(label);
	      return btnModifier;
	    }
	    public Object getCellEditorValue() 
	    {
	      return new String(label);
	    }
	 }
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
	      btnHistorique.setText(label);
	      return btnHistorique;
	    }
	    public Object getCellEditorValue() 
	    {
	      return new String(label);
	    }
	 }
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

