package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double num1, num2, result;
    private char operator;

    public Calculator() {
        setTitle("Calculator App");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        // Display field
        display = new JTextField();
        display.setBounds(30, 40, 270, 40);
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display);

        // Button labels
        String[] buttonLabels = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+"
        };

        // Button panel
        JPanel panel = new JPanel();
        panel.setBounds(30, 100, 270, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Add buttons
        for (String text : buttonLabels) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel);

        // Clear button
        JButton clearBtn = new JButton("C");
        clearBtn.setFont(new Font("Arial", Font.BOLD, 18));
        clearBtn.setBounds(30, 420, 270, 35);
        clearBtn.addActionListener(e -> display.setText(""));
        add(clearBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            display.setText(display.getText() + command);
        } else if (command.charAt(0) == 'C') {
            display.setText("");
        } else if (command.charAt(0) == '=') {
            num2 = Double.parseDouble(display.getText());

            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 != 0)
                        result = num1 / num2;
                    else {
                        JOptionPane.showMessageDialog(this, "Cannot divide by zero!");
                        display.setText("");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            num1 = result;
        } else {
            // Operator pressed
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
