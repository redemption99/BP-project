package gui;

import app.Main;
import resource.enums.AttributeType;
import resource.implementation.Attribute;
import resource.implementation.Entity;
import view.EntityView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Search extends JDialog {

    private JList attributes;
    private JLabel selected = new JLabel();
    private JComboBox attributesCombo;
    private JComboBox operatorBox = new JComboBox();
    private JTextField value = new JTextField();
    private JButton andButton = new JButton("AND");
    private JButton orButton = new JButton("OR");
    private JTextArea finalQuery = new JTextArea();
    private JButton searchButton = new JButton("Search");
    private JButton selectButton = new JButton("Select");

    private ArrayList<String> selectedAttributes = new ArrayList<>();
    private ArrayList<String> operatorList = new ArrayList<>();
    private ArrayList<String> whereAttributes = new ArrayList<>();

    private EntityView entityView;
    private Entity entity;

    private String[] operators = {">", "<", ">=", "<=", "="};

    public Search(EntityView entityView) {
        this.entityView = entityView;
        this.entity = entityView.getEntity();

        DefaultListModel listModel = new DefaultListModel();

        for (int i = 0; i < entity.getChildCount(); i++) {
            listModel.addElement(entity.getChildAt(i).toString());
        }

        this.attributes = new JList(listModel);
        this.attributes.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        attributesCombo = new JComboBox(entity.getChildren().toArray());
        attributesCombo.addActionListener(e -> {
            Attribute a = (Attribute)attributesCombo.getSelectedItem();
            AttributeType aType = a.getType();

            if (aType == AttributeType.CHAR || aType == AttributeType.VARCHAR || aType == AttributeType.NVARCHAR) {
                operatorBox.removeAllItems();
                operatorBox.addItem("LIKE");
            } else if (aType == AttributeType.INT || aType == AttributeType.NUMERIC || aType == AttributeType.FLOAT) {
                operatorBox.removeAllItems();
                for (int i = 0; i < operators.length; i++) {
                    operatorBox.addItem(operators[i]);
                }
            }
        });

        andButton.addActionListener(e -> {
            if (!value.getText().equals("")) {

                Attribute a = (Attribute)attributesCombo.getSelectedItem();
                AttributeType aType = a.getType();

                if (aType == AttributeType.INT || aType == AttributeType.NUMERIC || aType == AttributeType.FLOAT) {
                    if (isNumeric(value.getText())) {
                        String toAdd = attributesCombo.getSelectedItem().toString() + " " + operatorBox.getSelectedItem() + " " + value.getText();
                        whereAttributes.add(toAdd);
                        operatorList.add("AND");
                        finalQuery.setText(finalQuery.getText() + toAdd + " AND ");
                        value.setText("");
                    } else {
                        new Message("The value must be a number!");
                    }
                } else {
                    String toAdd = attributesCombo.getSelectedItem().toString() + " " + operatorBox.getSelectedItem() + " '" + value.getText() + "'";
                    whereAttributes.add(toAdd);
                    operatorList.add("AND");
                    finalQuery.setText(finalQuery.getText() + toAdd + " AND ");
                    value.setText("");
                }
            } else {
                new Message("Value is empty");
            }
        });

        orButton.addActionListener(e -> {
            if (!value.getText().equals("")) {

                Attribute a = (Attribute)attributesCombo.getSelectedItem();
                AttributeType aType = a.getType();

                if (aType == AttributeType.INT || aType == AttributeType.NUMERIC || aType == AttributeType.FLOAT) {
                    if (isNumeric(value.getText())) {
                        String toAdd = attributesCombo.getSelectedItem().toString() + " " + operatorBox.getSelectedItem() + " " + value.getText();
                        whereAttributes.add(toAdd);
                        operatorList.add("OR");
                        finalQuery.setText(finalQuery.getText() + toAdd + " OR ");
                        value.setText("");
                    } else {
                        new Message("The value must be a number!");
                    }
                } else {
                    String toAdd = attributesCombo.getSelectedItem().toString() + " " + operatorBox.getSelectedItem() + " '" + value.getText() + "'";
                    whereAttributes.add(toAdd);
                    operatorList.add("OR");
                    finalQuery.setText(finalQuery.getText() + toAdd + " OR ");
                    value.setText("");
                }
            } else {
                new Message("Value is empty");
            }
        });

        finalQuery.setEditable(false);
        finalQuery.setLineWrap(true);
        finalQuery.setWrapStyleWord(true);

        selectButton.addActionListener(e -> {
            if (attributes.getSelectedValue() != null && !selectedAttributes.contains(attributes.getSelectedValue().toString())) {
                if (selectedAttributes.size() != 0)
                    selected.setText(selected.getText() + ", " + attributes.getSelectedValue().toString());
                else
                    selected.setText(attributes.getSelectedValue().toString());
                selectedAttributes.add(attributes.getSelectedValue().toString());
            }
        });

        searchButton.addActionListener(e -> {
            if (!value.getText().equals("")) {

                Attribute a = (Attribute)attributesCombo.getSelectedItem();
                AttributeType aType = a.getType();

                if (aType == AttributeType.INT || aType == AttributeType.NUMERIC || aType == AttributeType.FLOAT) {
                    if (isNumeric(value.getText())) {
                        String toAdd = attributesCombo.getSelectedItem().toString() + " " + operatorBox.getSelectedItem() + " " + value.getText();
                        whereAttributes.add(toAdd);
                        finalQuery.setText(finalQuery.getText() + toAdd);
                        value.setText("");
                    } else {
                        new Message("The value must be a number!");
                        return;
                    }
                } else {
                    String toAdd = attributesCombo.getSelectedItem().toString() + " " + operatorBox.getSelectedItem() + " '" + value.getText() + "'";
                    whereAttributes.add(toAdd);
                    finalQuery.setText(finalQuery.getText() + toAdd);
                    value.setText("");
                }

            } else {
                new Message("Add a value to your search");
                return;
            }

            if (selectedAttributes.size() == 0) selectedAttributes.add("*");
            if (MainFrame.getInstance().getDb().search(entity, selectedAttributes, operatorList, whereAttributes).size() != 0)
                entityView.getTableModel().setRows(MainFrame.getInstance().getDb().search(entity, selectedAttributes, operatorList, whereAttributes));
            else
                new Message("NO DATA FOUND");
            dispose();
        });

        setMinimumSize(new Dimension(800, 400));
        setLocationRelativeTo(null);
        setLayout (null);

        attributes.setBounds (30, 15, 600, 95);
        selected.setBounds (30, 120, 685, 25);
        attributesCombo.setBounds (105, 165, 200, 25);
        operatorBox.setBounds (315, 165, 105, 25);
        value.setBounds (430, 165, 200, 25);
        andButton.setBounds (250, 205, 100, 25);
        orButton.setBounds (355, 205, 100, 25);
        finalQuery.setBounds (25, 250, 700, 55);
        searchButton.setBounds (275, 320, 145, 40);
        selectButton.setBounds (640, 50, 100, 25);

        add (attributes);
        add (selected);
        add (attributesCombo);
        add (operatorBox);
        add (value);
        add (andButton);
        add (orButton);
        add (finalQuery);
        add (searchButton);
        add (selectButton);

        setVisible(true);

    }

    private boolean isNumeric(String num) {
        if (num == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
