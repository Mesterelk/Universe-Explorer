import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetCRUD{
    public SetCRUD(JLayeredPane view, String component, int upperid, String upcomp, UniverseSelectionController controller){
        JButton addButton = new JButton("Add " + component);
        if (component.equals("Sun"))
            addButton.setBounds(0, 900, 150, 20);
        else addButton.setBounds(850, 900, 150, 20);
        if (component.equals("Universe")) {
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CRUDAddUniverse(controller);
                }
            });
        }else addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CRUDAddView(component, upperid, upcomp, controller);
            }
        });
        view.add(addButton);

        JButton delButton = new JButton("Delete " + component);
        if (component.equals("Sun"))
            delButton.setBounds(0, 880, 150, 20);
        else delButton.setBounds(850, 880, 150, 20);
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CRUDDelete(component, upperid, controller);
            }
        });
        view.add(delButton);

        JButton updButton = new JButton("Update " + component);
        if (component.equals("Sun"))
            updButton.setBounds(0, 860, 150, 20);
        else updButton.setBounds(850, 860, 150, 20);
        updButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CRUDUpdate(component, upperid, controller);
            }
        });
        view.add(updButton);
    }
}


