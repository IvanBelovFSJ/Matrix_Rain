/*
 * Matrix_Rain simulates the character 'rain' effect from the movie The Matrix
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Matrix_Rain extends JPanel {
    private static final String TEXT =
            "アイウエオカキクケコサシスセソタチツテトナニヌネノあいうえお";
    private static final int FONT_SIZE = 18;
    private final Random random = new Random();
    private int columns;
    private int[] y;
    private int[] speed;
    private int[] length;

    public Matrix_Rain() {
        setBackground(Color.BLACK);
        Timer timer = new Timer(120, e -> repaint()); // ~120 FPS
        timer.start();	}

    private void initialize() {
        int width = getWidth();
        int height = getHeight();
        columns = width / FONT_SIZE;
        y = new int[columns];
        speed = new int[columns];
        length = new int[columns];

        for (int i = 0; i < columns; i++) {
            y[i] = random.nextInt(height);
            speed[i] = random.nextInt(5) + 1;
            length[i] = random.nextInt(25) + 15;	}	}

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (y == null) initialize();
        // motion blur fade
        g2.setColor(new Color(0, 0, 0, 40));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setFont(new Font("MS Gothic", Font.PLAIN, FONT_SIZE));
        int height = getHeight();

        for (int i = 0; i < columns; i++) {
            int x = i * FONT_SIZE;
            // head character
            char c = TEXT.charAt(random.nextInt(TEXT.length()));
            // glow effect
            drawGlow(g2, String.valueOf(c), x, y[i]);

            // trailing characters
            for (int j = 1; j < length[i]; j++) {
                int tailY = y[i] - j * FONT_SIZE;
                if (tailY < 0) break;
                float alpha = 1f - ((float) j / length[i]);
                int green = 180 + random.nextInt(75);
                g2.setColor(new Color(0, green, 70, (int) (alpha * 255)));
                char tailChar = TEXT.charAt(random.nextInt(TEXT.length()));
                g2.drawString(String.valueOf(tailChar), x, tailY);	}

            y[i] += speed[i] * FONT_SIZE;
            
            if (y[i] > height + random.nextInt(2000)) {
                y[i] = 0;
                speed[i] = random.nextInt(2) + 1;
                length[i] = random.nextInt(30) + 10;	}	}	}

    private void drawGlow(Graphics2D g2, String text, int x, int y) {

        for (int i = 6; i >= 1; i--) {
            int alpha = 30 / i;
            g2.setColor(new Color(0, 255, 120, alpha));
            g2.drawString(text, x, y);	}

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);	}

    public static void main(String[] args) {

        JFrame frame = new JFrame("Matrix Rain");
        Matrix_Rain rain = new Matrix_Rain();
        frame.add(rain);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);	}	}