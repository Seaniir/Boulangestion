package controller;

import view.CommandesClientView;
import view.ListeClients;
import view.AccueilMenu;
import view.ListeFournisseurs;
import view.NewFournisseur;
import view.Login;
import view.NouveauClient;
import view.lambdaPanel;
import view.NouvelleCommandeClientView;

import javax.swing.*;

public class PanelsManager {
    public static JPanel contentPane;
    public static JPanel switchToLambdaPanel(){return new lambdaPanel();}
    public static JPanel switchToLoginPanel(){return new Login();}
    public static JPanel switchToNouveauClientPanel(){return new NouveauClient();}
    public static JPanel switchToAccueilMenu(){return new AccueilMenu();}
    //public static JPanel switchToCommandesClientView(){return new CommandesClientView();}
    public static JPanel switchToListeFournisseurs(){return new ListeFournisseurs();}
    public static JPanel switchToNewFournisseur(){return new NewFournisseur();}
    public static JPanel switchToCommandesClientPanel(){return new CommandesClientView();}
    public static JPanel switchToNouvelleCommandePanel(){return new NouvelleCommandeClientView();}
    public static JPanel switchToListeClientsPanel(){return new ListeClients();}
}
