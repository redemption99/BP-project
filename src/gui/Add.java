package gui;

import resource.enums.AttributeType;
import resource.implementation.Attribute;
import resource.implementation.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Add extends JDialog {

    Entity entity;

    JButton button;

    ArrayList<JTextField> textFields;

    public Add(Entity entity) {
        this.entity = entity;

        textFields = new ArrayList<>();

        setLayout(new GridLayout(0, 2));

        setTitle("Add new row");
        setSize(350, entity.getChildren().size() * 40 + 30);
        setLocationRelativeTo(null);
        setVisible(true);

        for (int i = 0; i < entity.getChildren().size(); i++)
            if (entity.getChildren().get(i) instanceof Attribute) {
                Attribute attribute = (Attribute) entity.getChildren().get(i);

                JLabel lbl;

                if (attribute.getType() == AttributeType.DATE || attribute.getType() == AttributeType.DATETIME)
                    lbl = new JLabel(attribute.getName() + " (yyyy-mm-dd)");
                else
                    lbl = new JLabel(attribute.getName());

                lbl.setSize(100, 20);
                lbl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                add(lbl, i, 0);

                JTextField tf = new JTextField();
                textFields.add(tf);
                tf.setSize(100, 20);
                add(tf, i, 1);
            }

        button = new JButton("Add new row");

        button.addActionListener(e -> {
            ArrayList<String> newValues = new ArrayList<>();
            for (JTextField tf: textFields)
                newValues.add(tf.getText());

            if (MainFrame.getInstance().getDb().insert(entity, newValues)) {
                new Message("Dodavanje je uspesno izvrseno.");
                // TODO update
            }
            else
                new Message("Dodavanje nije izvrseno.");

            dispose();

        });

        add(button);


    }
}
