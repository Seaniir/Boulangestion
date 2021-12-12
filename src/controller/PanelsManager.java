package controller;

import view.lambdaPanel;

import javax.swing.*;

public class PanelsManager {
    public static JPanel contentPane;
    public static JPanel switchToLambdaPanel(){return new lambdaPanel();}
}
