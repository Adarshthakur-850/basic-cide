import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class prac {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Swing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello, World!");
        JButton button = new JButton("Click Me");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(button);

        // Create an ActionListener to handle button clicks
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Trigger button click using AWT Robot
                Robot robot = null;
                try {
                    robot = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                int x = button.getX() + button.getWidth() / 2;
                int y = button.getY() + button.getHeight() / 2;
                robot.mouseMove(x, y);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
        };

        // Add the ActionListener to the button
        button.addActionListener(actionListener);

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}