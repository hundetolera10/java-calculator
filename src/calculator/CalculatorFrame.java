package calculator;
// This class belongs to the 'calculator' package

import calculator.operations.*;
// Import all operation classes (Addition, Subtraction, etc.)

import javax.swing.*;
// Import Swing GUI components

import java.awt.*;
// Import layout managers and dimension/font

import java.awt.event.*;
// Import event handling classes

public class CalculatorFrame extends JFrame {

    private JTextField textField; // Display bar
    private double num1 = 0;      // First number
    private double num2 = 0;      // Second number
    private String operator = ""; // Current operator
    private boolean startNewNumber = true; // Flag to start new number after operator

    // Constructor
    public CalculatorFrame() {
        setTitle("Simple Calculator"); // Window title
        setSize(400, 500);             // Window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close app on exit
        setLayout(new BorderLayout()); // Border layout

        // Setup display bar
        textField = new JTextField();
        textField.setEditable(false);                       // Prevent typing directly
        textField.setFont(new Font("Arial", Font.BOLD, 40)); // Bigger font
        textField.setHorizontalAlignment(JTextField.RIGHT);  // Right-aligned numbers
        textField.setPreferredSize(new Dimension(400, 60));  // Wider/taller bar
        add(textField, BorderLayout.NORTH);                 // Add to top

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));// 5 rows, 4 columns, spacing

        // All calculator buttons
        String[] buttons = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "0", ".", "C", "/",
                "X", "="
        };

        // Create buttons and attach listener
        for (String text : buttons) {
            JButton button = new JButton(text);// Create button
            button.setFont(new Font("Arial", Font.BOLD, 22));
            panel.add(button);                            // Add to panel
            button.addActionListener(new ButtonHandler()); // Add click handler
        }

        add(panel, BorderLayout.CENTER); // Add panel to center
    }

    // Inner class to handle button clicks
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand(); // Get clicked button text

            // Handle digit buttons (0-9)
            if ("0123456789".contains(cmd)) {
                if (startNewNumber) {           // If starting new number, clear display
                    textField.setText(cmd);
                    startNewNumber = false;     // Reset flag
                } else {
                    textField.setText(textField.getText() + cmd); // Append digit}
                }
                    // Handle decimal point
                } else if (cmd.equals(".")) {
                    if (startNewNumber) {           // Start new number with "0."
                        textField.setText("0.");
                        startNewNumber = false;
                    } else if (!textField.getText().contains(".")) {
                        textField.setText(textField.getText() + "."); // Append dot
                    }

                    // Handle operators +, -, *, /
                } else if ("+-*/".contains(cmd)) {
                    if (!textField.getText().isEmpty()) {
                        num1 = Double.parseDouble(textField.getText()); // Store first number
                        operator = cmd;                                 // Store operator
                        startNewNumber = true;                          // Next input is new number
                    }

                    // Handle equals button
                } else if (cmd.equals("=")) {
                    if (!operator.isEmpty() && !textField.getText().isEmpty()) {
                        num2 = Double.parseDouble(textField.getText()); // Store second number
                        double result = 0;                             // Declare operation

                        // Select the correct operation class
                        switch (operator) {
                            case "+":
                                result = num1 + num2;
                                break;
                            case "-":
                                result = num1 - num2;
                                break;
                            case "*":
                                result = num1 * num2;
                                break;
                            case "/":
                                if (num2 == 0) {
                                    textField.setText("Error");
                                    operator = "";
                                    startNewNumber = true;
                                    return;
                                }
                                result = num1 / num2;
                                break;
                            default:
                                return; // Invalid operator
                        }

                        // Perform calculation
                        textField.setText("" + result);                  // Display result

                        // Prepare for next calculation
                        num1 = result;        // Result becomes first number
                        operator = "";        // Reset operator
                        startNewNumber = true; // Next number will overwrite display
                    }

                    // Handle clear button
                } else if (cmd.equals("C")) {
                    textField.setText(""); // Clear display
                    num1 = 0;              // Reset numbers
                    num2 = 0;
                    operator = "";         // Reset operator
                    startNewNumber = true; // Ready for new input

                    // Handle backspace button
                } else if (cmd.equals("X")) {
                    if (!textField.getText().isEmpty() && !startNewNumber) {
                        String text = textField.getText();
                        textField.setText(text.substring(0, text.length() - 1)); // Remove last char
                    }
                }
            }
        }
    }


