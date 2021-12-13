package controller;

import view.commandesClientView;
import view.lambdaPanel;
import view.nouvelleCommandeView;

import javax.swing.*;

public class PanelsManager {
    public static JPanel contentPane;
    public static JPanel switchToLambdaPanel(){return new lambdaPanel();}
    public static JPanel switchToCommandesClientPanel(){return new commandesClientView();}
    public static JPanel switchToNouvelleCommandePanel(){return new nouvelleCommandeView();}
}
