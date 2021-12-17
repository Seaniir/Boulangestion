package controller;

import view.*;

import javax.swing.*;

public class PanelsManager {
    public static JPanel contentPane;
    public static JPanel switchToLambdaPanel(){return new lambdaPanel();}
    public static JPanel switchToLoginPanel(){return new Login();}
    public static JPanel switchToAccueilMenu(){return new AccueilMenu();}
    public static JPanel switchToNouveauClientPanel(){return new NouveauClient();}
    public static JPanel switchToListeFournisseurs(){return new ListeFournisseurs();}
    public static JPanel switchToNewFournisseur(){return new NewFournisseur();}
    public static JPanel switchToCommandesClientPanel(){return new CommandesClientView();}
    public static JPanel switchToNouvelleCommandePanel(){return new NouvelleCommandeClientView();}
    public static JPanel switchToListeClientsPanel(){return new ListeClients();}
    public static JPanel switchToHistoriqueVentesCommandesPanel(){return new HistoriqueCommandesVentes();}
    public static JPanel switchtoDetailsCommandesClients(){return new DetailsCommandesClient();}
    public static JPanel switchtoCommandeStockView(){return new CommandeStockView();}
    public static JPanel switchtoNewCommandeStock(){return new NewCommandeStock();}
    public static JPanel switchtoDetailsCommandeStock(){return new DetailsCommandeStock();}
    public static JPanel switchtoProduitsViewPanel(){return new ProduitsView();}

}
