import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UniverseSelectionController{
    private UniverseSelectionView view;
    private GalaxyView galaxyView;
    private SolarSystemView solarSystemView;
    private SSInnerView sSInnerView;
    private BooleanHold mode;
    public UniverseSelectionController() {
        mode = new BooleanHold();
        //new OpenLogin(this, mode);
        mode.bool=true;
        openUniverseView();
    }
    public void openUniverseView(){
        //new CRUDAddUniverse(this);
        view = new UniverseSelectionView();
        UniverseModel universeModel = new UniverseModel();
        universeModel.fetchUniverseNamesFromDatabase();
        List<String> universeNames = universeModel.getUniverseNames();
        setUniverseButtons(universeNames);
        view.setVisible(true);
    }
    private void setUniverseButtons(List<String> universeNames) {
        for (int i=0; i<universeNames.size(); ++i) {
            JButton button = new JButton(universeNames.get(i));
            button.setBounds((i+5)*40, (i+5)*45, 200, 40);
            view.addButton(button);
            int finalI = i;
            button.addActionListener(e -> openGalaxyView(universeNames.get(finalI), finalI +1));
        }
        if (mode.bool==true)
            new SetCRUD(view.getPanel(), "Universe", 0, "", this);
    }

    public void openGalaxyView(String univname, int i) {
        GalaxyModel galaxyModel = new GalaxyModel(i);
        ArrayList<UnivComp> galaxy = galaxyModel.getGalaxyData();
        galaxyView = new GalaxyView();
        setGalaxyButtons(galaxy, i);
        galaxyView.show(univname, galaxy, view);
        galaxyView.setVisible(true);
        view.setVisible(false);
    }

    private void setGalaxyButtons(ArrayList<UnivComp> galaxyList, int upperid) {
        for (UnivComp galaxy : galaxyList) {
            JButton button = new JButton();
            button.setBounds(galaxy.x(), galaxy.y(), galaxy.size(), galaxy.size()); // x, y, width, height
            button.setOpaque(false);
            button.setContentAreaFilled(false); // Remove the background
            button.setBorderPainted(false); // Remove the border
            galaxyView.addButton(button);

            JLabel label = new JLabel();
            label.setBounds(galaxy.x(), galaxy.y()-15, 200,15);
            label.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    label.setText(galaxy.name());
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    label.setText("");
                }
            });
            galaxyView.add(label);

            button.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    //System.out.println(galaxy.id()+" ");
                    openSolarSystemView(galaxy.name(), galaxy.id());
                }
            });
        }
        if (mode.bool==true)
            new SetCRUD(galaxyView.getPanel(), "Galaxy", upperid, "universe", this);
    }

    public void openSolarSystemView(String name, int id) {
        SolarSystemModel solarSystemModel = new SolarSystemModel(id);
        ArrayList<UnivComp> systemData = solarSystemModel.getSolarSystemData();

        solarSystemView = new SolarSystemView();
        setSolarSystemButtons(systemData, id);
        solarSystemView.show(name, systemData, galaxyView);
        solarSystemView.setVisible(true);
        galaxyView.setVisible(false);
    }

    private void setSolarSystemButtons(ArrayList<UnivComp> sSMap, int upperid){
        for (UnivComp solarName : sSMap) {
            JButton button = new JButton();
            button.setBounds(solarName.x(), solarName.y(), solarName.size(), solarName.size()); // x, y, width, height
            button.setOpaque(false);
            button.setContentAreaFilled(false); // Remove the background
            button.setBorderPainted(false); // Remove the border
            solarSystemView.addButton(button);

            JLabel label = new JLabel();
            label.setBounds(solarName.x(), solarName.y()-15, 200,15);
            label.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    label.setText(solarName.name());
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    label.setText("");
                }
            });
            solarSystemView.add(label);

            button.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    openSSInnerView(solarName.name(), solarName.id());
                }
            });

        }
        if (mode.bool==true)
            new SetCRUD(solarSystemView.getPanel(), "Solar_system", upperid, "galaxy", this);
    }

    public void openSSInnerView(String name, int id){
        SSInnerModel sSInnerModel = new SSInnerModel();
        sSInnerModel.fetchSolarSystemDataFromDatabase(id);
        sSInnerView = new SSInnerView(name, sSInnerModel.getSuns(), sSInnerModel.getPlanets(), sSInnerModel.getMoons() ,solarSystemView);

        if (mode.bool==true)
            new SetCRUD(sSInnerView.getPanel(), "Planet", id, "solar_system", this); //say planet on top
        if (mode.bool==true)
            new SetCRUD(sSInnerView.getPanel(), "Sun", id, "solar_system", this);
        sSInnerView.setVisible(true);
        solarSystemView.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UniverseSelectionController());
    }

    public UniverseSelectionView getView() {return view;}
    public GalaxyView getGalaxyView() {return galaxyView;}
    public SolarSystemView getSolarSystemView() {return solarSystemView;}
    public SSInnerView getsSInnerView() {return sSInnerView;}
}

