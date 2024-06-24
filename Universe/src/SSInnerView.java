import javax.swing.*;
import java.util.ArrayList;

public class SSInnerView extends JFrame {
    private final JLayeredPane panel = new JLayeredPane();
    private SolarSystemView system;
    private ArrayList<UnivComp> planets, moons, suns;

    public SSInnerView(String selectedSS, ArrayList<UnivComp> sunsMap, ArrayList<UnivComp> planetsMap, ArrayList<UnivComp> moonsMap,  SolarSystemView system) {
        this.planets = planetsMap;
        this.moons = moonsMap;
        this.suns = sunsMap;
        this.system = system;

        setTitle("Solar System " + selectedSS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);

        JButton returnButton = new JButton("Go Back to Solar_Systems");
        returnButton.setBounds(10,10,200,20);
        panel.add(returnButton,JLayeredPane.DEFAULT_LAYER);

        returnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                goBackToPreviousWindow();
            }
        });

        for (UnivComp solarSystem : planetsMap) {
            CircleComponent circle = new CircleComponent(solarSystem.size(), solarSystem.color());
            circle.setBounds(solarSystem.x(), solarSystem.y(), solarSystem.size(), solarSystem.size());
            panel.add(circle, JLayeredPane.DEFAULT_LAYER);
        }

        for (UnivComp solarSystem : sunsMap) {
            CircleComponent circle = new CircleComponent(solarSystem.size(), solarSystem.color());
            circle.setBounds(solarSystem.x(), solarSystem.y(), solarSystem.size(), solarSystem.size());
            panel.add(circle, JLayeredPane.DEFAULT_LAYER);
        }

        for (UnivComp solarSystem : moonsMap) {
            CircleComponent circle = new CircleComponent(solarSystem.size(), solarSystem.color());
            circle.setBounds(solarSystem.x(), solarSystem.y(), solarSystem.size(), solarSystem.size());
            panel.add(circle, JLayeredPane.DEFAULT_LAYER);
        }

        getContentPane().add(panel);
        setVisible(true);
    }

    public void addButton(JButton button) {
        panel.add(button);
    }

    public void goBackToPreviousWindow() {
        setVisible(false);
        system.setVisible(true);
    }

    public JLayeredPane getPanel() {
        return panel;
    }

    public void addComp(UnivComp comp){ planets.add(comp); //default
    //todo update view
    }
    public void addCompSuns(UnivComp comp){ suns.add(comp); //special case
    //todo update view
    }

    public int getArraySize(){return planets.size();} //default
    public int getSunArraySize(){return suns.size();} //special case
    private void deleteArr(){planets.clear(); suns.clear(); moons.clear();}
}
