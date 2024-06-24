import javax.swing.*;
import java.util.ArrayList;

public class SolarSystemView extends JFrame {
    private final JLayeredPane panel = new JLayeredPane();
    private GalaxyView galaxy;
    //private ArrayList<UnivComp> solarSystemData;
    public SolarSystemView(){}
    public void show(String selectedGalaxy, ArrayList<UnivComp> solarSystemMap, GalaxyView galaxy) {
        //this.solarSystemData = solarSystemMap;
        this.galaxy = galaxy;

        setTitle("Solar Systems in " + selectedGalaxy);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);

        JButton returnButton = new JButton("Go Back to Galaxyes");
        returnButton.setBounds(10,10,200,20);
        returnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                goBackToPreviousWindow();
            }
        });
        panel.add(returnButton,JLayeredPane.DEFAULT_LAYER);

        for (UnivComp solarSystem : solarSystemMap) {
            CircleComponent circle = new CircleComponent(solarSystem.size(), solarSystem.color());
            circle.setBounds(solarSystem.x(), solarSystem.y(), solarSystem.size(), solarSystem.size());
            panel.add(circle, JLayeredPane.DEFAULT_LAYER);
        }

        getContentPane().add(panel);
        setVisible(true);
    }

    public JLayeredPane getPanel() {
        return panel;
    }

    public void addButton(JButton button) {
        panel.add(button);
    }

    public void goBackToPreviousWindow() {
        setVisible(false);
        galaxy.setVisible(true);
    }

    //public int getArraySize(){return solarSystemData.size();}
    //private void deleteArr(){solarSystemData.clear();}
}
