import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CoinFlipApp extends JFrame {
    private JLabel coinLabel;
    private JButton headsButton;
    private JButton tailsButton;
    private JButton quitButton;
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private int score = 0;
    private Random random = new Random();

    public CoinFlipApp() {
        setTitle("Coin Flip-Flop Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        coinLabel = new JLabel();
        coinLabel.setHorizontalAlignment(JLabel.CENTER);
        add(coinLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        headsButton = new JButton("Heads");
        tailsButton = new JButton("Tails");
        quitButton = new JButton("Quit");

        buttonPanel.add(headsButton);
        buttonPanel.add(tailsButton);
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(2, 1));

        resultLabel = new JLabel("Make your guess!", JLabel.CENTER);
        scoreLabel = new JLabel("Score: 0", JLabel.CENTER);

        statusPanel.add(resultLabel);
        statusPanel.add(scoreLabel);

        add(statusPanel, BorderLayout.NORTH);

        headsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flipCoin("Heads");
            }
        });

        tailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flipCoin("Tails");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void flipCoin(String guess) {
        String[] outcomes = {"Heads", "Tails"};
        String flipResult = outcomes[random.nextInt(outcomes.length)];

        ImageIcon icon = new ImageIcon(getClass().getResource("/" + flipResult.toLowerCase() + ".png"));
        coinLabel.setIcon(icon);

        if (guess.equals(flipResult)) {
            score++;
            resultLabel.setText("Correct! It was " + flipResult + ".");
        } else {
            resultLabel.setText("Incorrect. It was " + flipResult + ".");
        }

        scoreLabel.setText("Score: " + score);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CoinFlipApp().setVisible(true);
            }
        });
    }
}
