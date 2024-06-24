import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUDAddUniverse extends  JFrame{
    public CRUDAddUniverse(UniverseSelectionController controller){
        controller.getView().setVisible(false);
        setTitle("Add Universe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameField);
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\Aplicatii\\aaaSQL\\Universe");
                    PreparedStatement statement = connection.prepareStatement(
                            "INSERT INTO Universe (universe_name) VALUES ('"+name+"');");

                    statement.executeUpdate();
                    statement.close();
                    connection.close();
                } catch (SQLException f) {
                    f.printStackTrace();
                } catch (ClassNotFoundException f) {
                    throw new RuntimeException(f);
                }
                controller.getView().dispose();
                controller.openUniverseView();
                setVisible(false);
                removeAll();
                dispose(); // Close the attributes input window after saving
            }
        });
        panel.add(saveButton);

        JButton returnButton = new JButton("Cancel");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                removeAll();
                dispose();
            }
        });
        panel.add(returnButton);
        setContentPane(panel);
        //pack();
        controller.getView().setVisible(true);
        setVisible(true);
    }
}
