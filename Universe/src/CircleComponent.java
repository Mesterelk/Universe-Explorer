import javax.swing.*;
import java.awt.*;

public class CircleComponent extends JComponent {
    private final int diameter;
    private final String color;
    public CircleComponent(int diameter, String color) {
        this.diameter = diameter;
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        // Draw the circle using the specified parameters

        g2d.setColor(getColorFromString(color));
        g2d.fillOval(0, 0, diameter, diameter); // Draw the circle at (0,0)

        g2d.dispose();
    }

    public static Color getColorFromString(String colorName) {
        return switch (colorName.toLowerCase()) {
            case "blue" -> Color.BLUE;
            case "red" -> Color.RED;
            case "yellow" -> Color.YELLOW;
            case "green" -> Color.GREEN;
            case "purple" -> new Color(128, 0, 128); // RGB value for purple
            case "orange" -> Color.ORANGE;
            default -> Color.BLACK; // Default color if input doesn't match any case
        };
    }

}