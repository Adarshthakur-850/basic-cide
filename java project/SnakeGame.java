import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JFrame {
    public SnakeGame() {
        add(new GamePanel());
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }

    public static class GamePanel extends JPanel implements ActionListener {

        private static final int TILE_SIZE = 20;
        private static final int WIDTH = 600;
        private static final int HEIGHT = 400;
        private static final int GAME_UNITS = (WIDTH * HEIGHT) / TILE_SIZE;
        private static final int DELAY = 140;
        private final int[] x = new int[GAME_UNITS];
        private final int[] y = new int[GAME_UNITS];
        private int bodyParts = 6;
        private int applesEaten;
        private int appleX;
        private int appleY;
        private char direction = 'R';
        private boolean running = false;
        private Timer timer;
        private Random random;

        public GamePanel() {
            random = new Random();
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setBackground(Color.black);
            setFocusable(true);
            addKeyListener(new MyKeyAdapter());
            startGame();
        }

        public void startGame() {
            newApple();
            running = true;
            timer = new Timer(DELAY, this);
            timer.start();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw(g);
        }

        public void draw(Graphics g) {
            if (running) {
                g.setColor(Color.red);
                g.fillOval(appleX, appleY, TILE_SIZE, TILE_SIZE);

                for (int i = 0; i < bodyParts; i++) {
                    if (i == 0) {
                        g.setColor(Color.green);
                        g.fillRect(x[i], y[i], TILE_SIZE, TILE_SIZE);
                    } else {
                        g.setColor(new Color(45, 180, 0));
                        g.fillRect(x[i], y[i], TILE_SIZE, TILE_SIZE);
                    }
                }

                g.setColor(Color.red);
                g.setFont(new Font("Ink Free", Font.BOLD, 40));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Score: " + applesEaten, (WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
            } else {
                gameOver(g);
            }
        }

        public void newApple() {
            appleX = random.nextInt((int) (WIDTH / TILE_SIZE)) * TILE_SIZE;
            appleY = random.nextInt((int) (HEIGHT / TILE_SIZE)) * TILE_SIZE;
        }

        public void move() {
            for (int i = bodyParts; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }

            switch (direction) {
                case 'U':
                    y[0] = y[0] - TILE_SIZE;
                    break;
                case 'D':
                    y[0] = y[0] + TILE_SIZE;
                    break;
                case 'L':
                    x[0] = x[0] - TILE_SIZE;
                    break;
                case 'R':
                    x[0] = x[0] + TILE_SIZE;
                    break;
            }
        }

        public void checkApple() {
            if ((x[0] == appleX) && (y[0] == appleY)) {
                bodyParts++;
                applesEaten++;
                newApple();
            }
        }

        public void checkCollisions() {
            for (int i = bodyParts; i > 0; i--) {
                if ((x[0] == x[i]) && (y[0] == y[i])) {
                    running = false;
                }
            }

            if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
                running = false;
            }

            if (!running) {
                timer.stop();
            }
        }

        public void gameOver(Graphics g) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics metrics1 = getFontMetrics(g.getFont());
            g.drawString("Game Over", (WIDTH - metrics1.stringWidth("Game Over")) / 2, HEIGHT / 2);

            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics2 = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (WIDTH - metrics2.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (running) {
                move();
                checkApple();
                checkCollisions();
            }
            repaint();
        }

        public class MyKeyAdapter extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                }
            }
        }
    }
}
