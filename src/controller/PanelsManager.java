package controller;

import view.CommandesClientView;
import view.lambdaPanel;
import view.NouvelleCommandeClientView;

import javax.swing.*;

public class PanelsManager {
    public static JPanel contentPane;
    public static JPanel switchToLambdaPanel(){return new lambdaPanel();}
    public static JPanel switchToCommandesClientPanel(){return new CommandesClientView();}
    public static JPanel switchToNouvelleCommandePanel(){return new NouvelleCommandeClientView();}
}
