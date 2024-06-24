import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class CRUDDelete extends JFrame{
    //the mode is when i recursevely delete, the first delete i want to update the window, then the next ones i only want to dispose the view, not open too the view
    public CRUDDelete(String table, int upperid, UniverseSelectionController controller) {
        setVisibility(controller, table, false);
        setTitle("Delete " + table);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel idLabel = new JLabel("Id:");
        JTextField idField = new JTextField();
        add(nameLabel);
        add(nameField);
        add(idLabel);
        add(idField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            try {
                Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\Aplicatii\\aaaSQL\\Universe");
                Statement statement = connection.createStatement();

                String deleteQuery=null, selectQuery=null;
                String tableLower = table.substring(0, 1).toLowerCase() + table.substring(1);
                if (name.isEmpty()==false) {
                    deleteQuery = "DELETE FROM " + table + " WHERE " + tableLower + "_name = '" + name + "';";
                    selectQuery = "SELECT " + tableLower + "_id FROM " + table + " WHERE " + tableLower + "_name = '" + name + "';";
                }else if (id.isEmpty()==false) {
                    deleteQuery = "DELETE FROM " + table + " WHERE " + tableLower + "_id = " + id;
                    selectQuery = "SELECT " + tableLower + "_id FROM " + table + " WHERE " + tableLower + "_id = " + id;
                }

                ResultSet resultSet = statement.executeQuery(selectQuery);
                int idDel = resultSet.getInt(tableLower+"_id");
                resultSet.close();

                int rowsAffected = statement.executeUpdate(deleteQuery);
                //getidDel
                statement.close();
                connection.close();
                System.out.println(idDel);

                if (rowsAffected > 0) {//delete recursevely only when there is something to delete
                    switch (table) {
                        case "Universe" -> {
                            controller.getView().dispose();
                            deleteRec("Galaxy", "universe" ,idDel);
                            controller.openUniverseView();
                        }
                        case "Galaxy" -> {
                            controller.getGalaxyView().dispose();
                            deleteRec("Solar_system", "galaxy" ,idDel);
                            controller.openGalaxyView("new Galaxy", upperid);
                        }
                        case "Solar_system" -> {
                            controller.getSolarSystemView().dispose();
                            deleteRec("Planet", "solar_system", idDel);
                            deleteRec("Sun", "solar_system", idDel);
                            controller.openSolarSystemView("new Solar System", upperid);
                        }
                        case "Planet" -> {
                            controller.getsSInnerView().dispose();
                            deleteRec("Moon","planet" ,idDel);
                            controller.openSSInnerView("new Planet", upperid);
                        }
                        case "Sun" -> {
                            controller.getsSInnerView().dispose();
                            controller.openSSInnerView("new Sun", upperid);
                        }
                        case "Moon" -> {


                            //TODO    SI SA MAI FAC SA DEA DELETE RECURSIV COI
                            //TODO
                            //TODO//TODO
                            //TODO
                            //TODO MOONS


                            controller.getsSInnerView().dispose();
                            controller.openSSInnerView("new Moon", upperid);
                        }
                    }
                }
                setVisible(false);
                removeAll();
                dispose();
            } catch (SQLException ee) {
                ee.printStackTrace();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
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
        setVisibility(controller, table, true);
        setVisible(true);
    }


    private void deleteRec(String table, String upcomp, int upperid){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\Aplicatii\\aaaSQL\\Universe");
            Statement statement = connection.createStatement();

            String tableLower = table.substring(0, 1).toLowerCase() + table.substring(1);
            String selectQuery = "SELECT " + tableLower + "_id FROM " + table + " WHERE " + upcomp + "_id = " + upperid;
            System.out.println(selectQuery);

            ResultSet resultSet = statement.executeQuery(selectQuery);
            ArrayList<Integer> idDelArr = new ArrayList<>();
            while(resultSet.next())
                idDelArr.add(resultSet.getInt(tableLower + "_id"));
            resultSet.close();

            for(int idDel: idDelArr){
                String deleteQuery = "DELETE FROM " + table + " WHERE " + tableLower + "_id = " + idDel;
                System.out.println(deleteQuery);
                int rowsAffected = statement.executeUpdate(deleteQuery);
                System.out.println(idDel);

                if (rowsAffected > 0) {//delete recursevely only when there is something to delete
                    switch (table) {
                        case "Galaxy" -> {
                            //controller.getGalaxyView().dispose();
                            deleteRec("Solar_system", "galaxy", idDel);
                        }
                        case "Solar_system" -> {
                            //controller.getSolarSystemView().dispose();
                            deleteRec("Planet", "solar_system", idDel);
                            deleteRec("Sun", "solar_system", idDel);
                        }
                        case "Planet" -> {
                            deleteRec("Moon", "planet", idDel);
                        }
                    }
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
         }
    }

    void setVisibility(UniverseSelectionController controller, String component, boolean mode) {
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
