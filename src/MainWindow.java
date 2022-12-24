import javax.swing.*;

public class MainWindow extends JFrame {
    private JTextArea codeInputArea;
    private JTextArea outputArea;
    private JButton lexicalButton;
    private JButton syntacticButton;
    private JButton exitButton;
    private JPanel mainPanel;

    public MainWindow() {
        this.setContentPane(mainPanel);
        this.setVisible(true);
        this.setTitle("HBcompiler");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lexicalButton.addActionListener(e ->
        {
            String inputText = codeInputArea.getText();
            outputArea.setText("");
            try {
                outputArea.setText(Compiler.runLexicalAnalyzer(inputText));
            } catch (Exception ex) {
                outputArea.setText(ex.getMessage());
            }
        });

        syntacticButton.addActionListener(e -> {
            String inputText = codeInputArea.getText();
            outputArea.setText("");
            try {
                outputArea.setText(Compiler.runSyntacticAnalyzer(inputText));
            } catch (Exception ex) {
                outputArea.setText(ex.getMessage());
            }
        });
    }
}