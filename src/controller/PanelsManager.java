package controller;

import view.AccueilMenu;
import view.ListeFournisseurs;
import view.NewFournisseur;
import view.lambdaPanel;

import javax.swing.*;

public class PanelsManager {
    public static JPanel contentPane;
    public static JPanel switchToLambdaPanel(){return new lambdaPanel();}
    public static JPanel switchToAccueilMenu(){return new AccueilMenu();}
    //public static JPanel switchToCommandesClientView(){return new CommandesClientView();}
    public static JPanel switchToListeFournisseurs(){return new ListeFournisseurs();}
    public static JPanel switchToNewFournisseur(){return new NewFournisseur();}
}
