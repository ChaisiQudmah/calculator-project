import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUICalculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private double num1, num2, result;
    private char operator;

    public GUICalculator() {
        // Frame setup
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input field
        inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        add(inputField, BorderLayout.NORTH);

        // Buttons panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = ((JButton)e.getSource()).getText();

        if (input.matches("[0-9]")) {
            inputField.setText(inputField.getText() + input);
        } else if (input.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(inputField.getText());
            operator = input.charAt(0);
            inputField.setText("");
        } else if (input.equals("=")) {
            num2 = Double.parseDouble(inputField.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        inputField.setText("Error");
                        return;
                    }
                    break;
            }
            inputField.setText(String.valueOf(result));
        } else if (input.equals("C")) {
            inputField.setText("");
            num1 = num2 = result = 0;
            operator = '\0';
        }
    }

    public static void main(String[] args) {
        new GUICalculator();
    }
}
