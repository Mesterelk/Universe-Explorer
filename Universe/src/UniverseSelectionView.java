import javax.swing.*;

public class UniverseSelectionView extends JFrame {

    private final JLayeredPane panel = new JLayeredPane();

    public UniverseSelectionView() {
        setTitle("Select Universe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);

        getContentPane().add(panel);
    }
    public void addButton(JButton button) {
        panel.add(button);
    }
    public JLayeredPane getPanel() { return panel;}
}
