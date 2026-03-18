import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ComplexCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private StringBuilder currentInput;

    public ComplexCalculator() {
        currentInput = new StringBuilder();

        setTitle("Complex Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setPreferredSize(new Dimension(400, 50));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = {
            "7", "8", "9", "/", "C",
            "4", "5", "6", "*", "CE",
            "1", "2", "3", "-", "(",
            "0", ".", "=", "+", ")",
            "sin", "cos", "tan", "log", "sqrt",
            "^", "exp", "mod", "ln", "1/x"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "C":
                currentInput.setLength(0);
                display.setText("");
                break;
            case "CE":
                if (currentInput.length() > 0) {
                    currentInput.setLength(currentInput.length() - 1);
                    display.setText(currentInput.toString());
                }
                break;
            case "=":
                try {
                    String result = evaluateExpression(currentInput.toString());
                    display.setText(result);
                    currentInput.setLength(0);
                    currentInput.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                    currentInput.setLength(0);
                }
                break;
            default:
                currentInput.append(command);
                display.setText(currentInput.toString());
                break;
        }
    }

    private String evaluateExpression(String expression) {
        try {
            if (expression.contains("sin")) {
                expression = expression.replaceAll("sin", "Math.sin");
            }
            if (expression.contains("cos")) {
                expression = expression.replaceAll("cos", "Math.cos");
            }
            if (expression.contains("tan")) {
                expression = expression.replaceAll("tan", "Math.tan");
            }
            if (expression.contains("log")) {
                expression = expression.replaceAll("log", "Math.log10");
            }
            if (expression.contains("ln")) {
                expression = expression.replaceAll("ln", "Math.log");
            }
            if (expression.contains("sqrt")) {
                expression = expression.replaceAll("sqrt", "Math.sqrt");
            }
            if (expression.contains("^")) {
                String[] parts = expression.split("\\^");
                if (parts.length == 2) {
                    return String.valueOf(Math.pow(Double.parseDouble(parts[0]), Double.parseDouble(parts[1])));
                }
            }
            if (expression.contains("exp")) {
                expression = expression.replaceAll("exp", "Math.exp");
            }
            if (expression.contains("mod")) {
                String[] parts = expression.split("mod");
                if (parts.length == 2) {
                    return String.valueOf(Double.parseDouble(parts[0]) % Double.parseDouble(parts[1]));
                }
            }
            if (expression.contains("1/x")) {
                String[] parts = expression.split("1/x");
                if (parts.length == 1) {
                    return String.valueOf(1 / Double.parseDouble(parts[0]));
                }
            }

            return String.valueOf(new javax.script.ScriptEngineManager().getEngineByName("JavaScript").eval(expression));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ComplexCalculator calculator = new ComplexCalculator();
            calculator.setVisible(true);
        });
    }
}
