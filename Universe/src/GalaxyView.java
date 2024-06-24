import javax.swing.*;
import java.util.ArrayList;

public class GalaxyView extends JFrame {
    private final JLayeredPane panel = new JLayeredPane();
    private ArrayList<UnivComp> galaxyMap;
    private UniverseSelectionView univ;

    public void show(String selectedUniverse, ArrayList<UnivComp> galaxyy, UniverseSelectionView universe) {
        this.galaxyMap = galaxyy;
        this.univ = universe;
        setTitle("Galaxies in " + selectedUniverse);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        panel.setLayout(null); // Using null layout

        JButton returnButton = new JButton("Go Back to Universes");
        returnButton.setBounds(10,10,200,20);
        returnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                goBackToPreviousWindow();
            }
        });
        panel.add(returnButton,JLayeredPane.DEFAULT_LAYER);

        for (UnivComp galaxy : galaxyMap) {
            CircleComponent circle = new CircleComponent(galaxy.size(), galaxy.color());
            circle.setBounds(galaxy.x(), galaxy.y(), galaxy.size(), galaxy.size());
            panel.add(circle, JLayeredPane.DEFAULT_LAYER);
        }

        getContentPane().add(panel);
        setVisible(true);
    }

    public GalaxyView() {}

    public void addButton(JButton button) {
        panel.add(button, JLayeredPane.DEFAULT_LAYER);
    }

    public void goBackToPreviousWindow() {
        setVisible(false);
        univ.setVisible(true);
    }
    public JLayeredPane getPanel() {return panel;}

    public void addComp(UnivComp comp){ galaxyMap.add(comp);}

    public int getArraySize(){return galaxyMap.size();}

    private void deleteArr(){galaxyMap.clear();}
}
