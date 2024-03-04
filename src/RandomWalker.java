import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWalker extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int STEP_SIZE = 2;
    private static final int NUM_WALKERS = 5; // Number of walkers

    private List<int[]> positions;

    public RandomWalker() {
        positions = new ArrayList<>();

        // Initialize positions for each walker
        for (int i = 0; i < NUM_WALKERS; i++) {
            positions.add(new int[]{WIDTH / 2, HEIGHT / 2}); // Starting position for each walker
        }

        // Set up the panel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

        // Start the walkers
        startWalkers();
    }

    private void startWalkers() {
        Timer timer = new Timer(100, e -> {
            // Move each walker
            for (int[] pos : positions) {
                // Randomly choose direction
                Random random = new Random();
                int direction = random.nextInt(4);

                // Move the walker
                switch (direction) {
                    case 0:
                        pos[1] -= STEP_SIZE * 2; // Up
                        break;
                    case 1:
                        pos[1] += STEP_SIZE * 2; // Down
                        break;
                    case 2:
                        pos[0] -= STEP_SIZE * 2; // Left
                        break;
                    case 3:
                        pos[0] += STEP_SIZE * 2; // Right
                        break;
                }

                // Ensure the walker stays within the screen boundaries
                pos[0] = Math.max(0, Math.min(pos[0], WIDTH - 1));
                pos[1] = Math.max(0, Math.min(pos[1], HEIGHT - 1));
            }

            repaint(); // Redraw the panel to reflect the new walker positions
        });
        timer.start(); // Start the timer to move the walkers periodically
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawWalkers(g); // Draw all the walkers on the panel
    }

    private void drawWalkers(Graphics g) {
        g.setColor(Color.BLACK);
        for (int[] pos : positions) {
            g.fillOval(pos[0] - 10, pos[1] - 10, 20, 20); // Draw each walker as a circle with a radius of 10 pixels
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Random Walkers");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new RandomWalker());
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true); // Make the frame visible
        });
    }
}
