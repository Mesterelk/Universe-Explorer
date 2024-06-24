import javax.swing.*;
import java.awt.*;

public class OpenLogin extends JFrame {
    public OpenLogin(UniverseSelectionController controller,  BooleanHold mode){
        mode.bool=false;
        JPanel panel = new JPanel();

        setTitle("Select User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLayout(new GridLayout(1, 2));
        setLocationRelativeTo(null);
        JButton button1 = new JButton("Guest");
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                dispose();
                controller.openUniverseView();
            }
        });
        panel.add(button1);

        JButton button2 = new JButton("Admin");
        button2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                getContentPane().remove(panel);
                dispose();
                login(mode, controller);
            }
        });
        panel.add(button2);
        getContentPane().add(panel);
        this.setVisible(true);
    }

    private void login(BooleanHold mode, UniverseSelectionController controller){
        JPanel panel = new JPanel();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JTextField passField = new JTextField();
        add(nameLabel);
        add(nameField);
        add(passLabel);
        add(passField);

        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLayout(new GridLayout(3, 2));
        setLocationRelativeTo(null);
        JButton button1 = new JButton("Login");
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                String name = nameField.getText();
                String pass = passField.getText();
                if (name.equals("Mesterel") && pass.equals("Fortnite69"))
                    mode.bool = true;
                dispose();
                controller.openUniverseView();
            }
        });
        panel.add(button1);

        JButton button2 = new JButton("Cancel");
        button2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                dispose();
                controller.openUniverseView();
            }
        });
        panel.add(button2);
        getContentPane().add(panel);
        this.setVisible(true);
    }
}
