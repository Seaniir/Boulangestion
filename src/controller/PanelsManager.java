package controller;

import view.Login;
import view.NouveauClient;
import view.lambdaPanel;

import javax.swing.*;

public class PanelsManager {
    public static JPanel contentPane;
    public static JPanel switchToLambdaPanel(){return new lambdaPanel();}
    public static JPanel switchToLoginPanel(){return new Login();}
    public static JPanel switchToNouveauClientPanel(){return new NouveauClient();}
}
