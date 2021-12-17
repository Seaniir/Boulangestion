package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.mysql.cj.conf.ConnectionUrlParser;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import controller.CommandeStockDao;
import controller.FournisseurDao;
import controller.PanelsManager;
import model.CommandeStock;
import model.Fournisseur;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CommandeStockView extends JPanel {
	private JTable listingCmdStock;
	
	JButton btnModifier = new JButton();
	JButton btnHistorique = new JButton();
	JButton btnDelete = new JButton();
	/**
	 * Create the panel.
	 */
	public CommandeStockView() {
		setBounds(0, 0, 1440, 900);
		setBackground(new Color(254, 245, 232));
		setLayout(null);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color(255, 255, 255));
		menu.setBounds(0, 0, 1440, 90);
		add(menu);
		menu.setLayout(null);
		
		JButton btnFournisseur = new JButton("");
		btnFournisseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFournisseur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchToListeFournisseurs());
				PanelsManager.contentPane.repaint();
				PanelsManager.contentPane.revalidate();
				//modify = false;
			}
		});
		btnFournisseur.setIcon(new ImageIcon("C:\\Users\\fredb\\AFPA\\workspace-java\\Boulangestion\\projetBoulang\\accountMenu.png"));
		btnFournisseur.setBounds(38, 11, 40, 40);
		menu.add(btnFournisseur);
		
		JLabel lblFournisseur = new JLabel("Fournisseur");
		lblFournisseur.setHorizontalAlignment(SwingConstants.CENTER);
		lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFournisseur.setBounds(10, 62, 103, 18);
		menu.add(lblFournisseur);
		
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
		
		listingCmdStock = new JTable();
		listingCmdStock.setRowSelectionAllowed(false);
		scrollPane.setViewportView(listingCmdStock);
		listingCmdStock.setRowHeight(100);
		listingCmdStock.setModel(liste());
		
		//Centrer le contenu des cellules
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		listingCmdStock.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		listingCmdStock.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		listingCmdStock.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		listingCmdStock.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		listingCmdStock.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		listingCmdStock.getColumn("Modifier").setCellRenderer(new ButtonRenderer());
		listingCmdStock.getColumn("Modifier").setCellEditor(new ButtonEditor(new JCheckBox()));
		listingCmdStock.getColumn("Annuler").setCellRenderer(new SecondButtonRenderer());
		listingCmdStock.getColumn("Annuler").setCellEditor(new SecondButtonEditor(new JCheckBox()));
		
		//bouton de colonne "modifier" qui redirige vers la commande à modifier
		btnModifier.addActionListener(new ActionListener(){			        
			@Override
			public void actionPerformed(ActionEvent e) {
				//pop-up de confirmation: si oui va sur la modification du fournisseur, si non, ne fait rien
				if (JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir modifier?", "Attention",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					NewFournisseur.modify = true;
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchToNewFournisseur());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
				}
			}
	    });
		
		btnDelete.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent event)
	        {
	        	//pop-up de confirmation: si oui va sur la suppression du fournisseur dans ma liste, pas dans la bdd, si non, ne fait rien
				if (JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer?", "Attention",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					
					//rafraichi la page
					PanelsManager.contentPane.removeAll();
					PanelsManager.contentPane.add(PanelsManager.switchToListeFournisseurs());
					PanelsManager.contentPane.repaint();
					PanelsManager.contentPane.revalidate();
				}
	        }
	    });
		
		//redirection pour créer une nouvelle commande
		JButton btnNewCmdStock = new JButton("Nouvelle Commande");
		btnNewCmdStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelsManager.contentPane.removeAll();
				PanelsManager.contentPane.add(PanelsManager.switchtoNewCommandeStock());
				PanelsManager.contentPane.revalidate();
				PanelsManager.contentPane.repaint();
			}
		});
		btnNewCmdStock.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewCmdStock.setBackground(new Color(242, 193, 102));
		btnNewCmdStock.setForeground(new Color(0, 0, 0));
		btnNewCmdStock.setBounds(470, 823, 499, 53);
		add(btnNewCmdStock);
		
		JButton btnTri = new JButton("Tri par Fournisseur");
		btnTri.setBackground(Color.WHITE);
		btnTri.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTri.setBounds(40, 762, 252, 46);
		add(btnTri);
	}

	//remplissage du tableau
	public DefaultTableModel liste() {
		String [] col = {"N° Commande","Reçue le","Fournisseur","Nbr Articles","Prix total TTC","Modifier", "Annuler"};
		DefaultTableModel tab = new DefaultTableModel(null, col);
		
		CommandeStockDao cmdStockDao = new CommandeStockDao();
		List<CommandeStock> commandes = new ArrayList<>();
		List<Fournisseur> fournisseurs = new ArrayList<>();
		ConnectionUrlParser.Pair<ArrayList<CommandeStock>, ArrayList<Fournisseur>> pair = cmdStockDao.readPair();
		int i=0;
		for (CommandeStock commande : pair.left) {
			Vector vect = new Vector();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date dateObj = pair.left.get(i).getDateReception();
			String recuLe = df.format(dateObj);
			vect.add(pair.left.get(i).getId());
			vect.add(recuLe);
			vect.add(pair.right.get(i).getSociete());
			vect.add(pair.left.get(i).getNbrArticles());
			vect.add(pair.left.get(i).getPrixTotal());
			tab.addRow(vect);
			i++;
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
	    	setText((value == null) ? "Annuler" : value.toString());
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
