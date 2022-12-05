import Exceptions.Lexical.GeneralLexicalException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame{
    private JTextArea codeInputArea;
    private JTextArea outputArea;
    private JButton lexicalButton;
    private JButton syntacticButton;
    private JButton exitButton;
    private JPanel mainPanel;

    public MainWindow() {
        this.setContentPane(mainPanel);
        this.setVisible(true);
        this.setTitle("MainWindow");
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lexicalButton.addActionListener(e ->
        {
            String inputText = codeInputArea.getText();
            LexicalAnalyzer la = new LexicalAnalyzer(inputText);
            outputArea.setText(la.analyze());
        });

        syntacticButton.addActionListener(e -> {
            String inputText = codeInputArea.getText();
            outputArea.setText(inputText);
        });
    }
}