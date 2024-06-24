import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CRUDAddView extends JFrame {
    public CRUDAddView(String component, int upperid, String upcomp, UniverseSelectionController controller) {
        setVisibility(controller, component, false);
        setTitle("Add " + component);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        if (component.equals("Planet") || component.equals("Moon")) {
            setLayout(new GridLayout(6, 2));

        } else setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel sizeLabel = new JLabel("Size:");
        JTextField sizeField = new JTextField();
        JLabel positionLabel = new JLabel("Position:");
        JTextField positionField = new JTextField();
        JLabel colorLabel = new JLabel("Color:");
        JTextField colorField = new JTextField();
        JLabel otherLabel = new JLabel("Other:");
        JTextField otherField = new JTextField();

        add(nameLabel);
        add(nameField);
        add(sizeLabel);
        add(sizeField);
        add(positionLabel);
        add(positionField);
        add(colorLabel);
        add(colorField);

        if (component.equals("Planet") || component.equals("Moon")) {
            add(otherLabel);
            add(otherField);
        }

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String size = sizeField.getText();
            String position = positionField.getText();
            String[] xy = position.split(" ");
            String color = colorField.getText();
            String other = "", otherfield="";
            if (component.equals("Planet") || component.equals("Moon")) {
                otherfield = ", state";
                other = ",'" + otherField.getText()+"'";
            }
            // Handle saving the attributes (perform database operations or other actions)
            try {
                Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\Aplicatii\\aaaSQL\\Universe");

                PreparedStatement statement = connection.prepareStatement("INSERT INTO " + component + "(" + component.substring(0, 1).toLowerCase() + component.substring(1) + "_name, " +
                        "x, y, color, size, " + upcomp + "_id " + otherfield + " ) VALUES ('" + name + "', " + xy[0] + ", " + xy[1] + ", '" + color + "', " + size + ", " + upperid + other + ");");

                statement.executeUpdate();
                statement.close();
                connection.close();
            } catch (SQLException f) {
                f.printStackTrace();
            } catch (ClassNotFoundException f) {
                throw new RuntimeException(f);
            }
            switch (component) {
                case "Galaxy" -> {
                    controller.getGalaxyView().dispose();
                    controller.openGalaxyView("new Galaxy", upperid);
                }
                case "Solar_system" -> {
                    controller.getSolarSystemView().dispose();
                    controller.openSolarSystemView("new Solar System", upperid);
                }
                case "Planet" -> {
                    controller.getsSInnerView().dispose();
                    controller.openSSInnerView("new Planet", upperid);
                }
                case "Sun" -> {
                    controller.getsSInnerView().dispose();
                    controller.openSSInnerView("new Sun", upperid);
                }
                case "Moon" -> {


                    //TODO
                    //TODO
                    //TODO//TODO
                    //TODO
                    //TODO


                    controller.getsSInnerView().dispose();
                    controller.openSSInnerView("new Moon", upperid);
                }
            }
            setVisible(false);
            removeAll();
            dispose(); // Close the attributes input window after saving
        });
        add(saveButton);

        JButton returnButton = new JButton("Cancel");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                removeAll();
                dispose();
            }
        });
        add(returnButton);
//pack();
        setVisibility(controller, component, true);
        setVisible(true);
    }

    void setVisibility(UniverseSelectionController controller, String component, boolean mode){
        switch (component) {
            case "Galaxy" -> {
                controller.getGalaxyView().setVisible(mode);
            }
            case "Solar_system" -> {
                controller.getSolarSystemView().setVisible(mode);
            }
            case "Planet" -> {
                controller.getsSInnerView().setVisible(mode);
            }
            case "Sun" -> {
                controller.getsSInnerView().setVisible(mode);
            }
            case "Moon" -> {
                //controller.getsSInnerView().setVisible(mode);
            }
        }
    }

}
