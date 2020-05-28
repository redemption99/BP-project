package gui;

import resource.implementation.Attribute;
import resource.implementation.Entity;
import view.EntityView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class FilterAndSort extends JDialog {

    private EntityView entityView;
    private Entity entity;
    private JButton selected = new JButton("Select");
    private JButton ascending = new JButton("Ascending");
    private JButton descending = new JButton("Descending");
    private JButton sortButton = new JButton("Sort");
    private JTextArea selectedElements = new JTextArea("Selected columns: ");
    private JTextArea ascedningElements = new JTextArea("Ascend by: ");
    private JTextArea descendingElements = new JTextArea("Descend by: ");

    private ArrayList<String> selectedList = new ArrayList<>();
    private ArrayList<String> ascendingList = new ArrayList<>();
    private ArrayList<String> descedingList = new ArrayList<>();

    private JList attributes;

    public FilterAndSort(EntityView entityView) {
        this.entityView = entityView;
        this.entity = entityView.getEntity();
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < entity.getChildCount(); i++) {
            listModel.addElement(entity.getChildAt(i).getName());
        }
        this.attributes = new JList(listModel);
        selectedElements.setWrapStyleWord(true);
        selectedElements.setLineWrap(true);
        ascedningElements.setWrapStyleWord(true);
        ascedningElements.setLineWrap(true);
        descendingElements.setWrapStyleWord(true);
        descendingElements.setLineWrap(true);

        setMinimumSize(new Dimension(700, 570));
        setMaximumSize(new Dimension(700, 570));
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("Filter and sort");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        selected.addActionListener(e -> {
            if (attributes.getSelectedValue() != null && !selectedList.contains(attributes.getSelectedValue().toString())) {
                selectedList.add(attributes.getSelectedValue().toString());
                selectedElements.setText(selectedElements.getText() + attributes.getSelectedValue().toString() + ", ");
            }
        });

        ascending.addActionListener(e -> {
            if (attributes.getSelectedValue() != null && !ascendingList.contains(attributes.getSelectedValue().toString())) {
                ascendingList.add(attributes.getSelectedValue().toString());
                ascedningElements.setText(ascedningElements.getText() + attributes.getSelectedValue().toString() + ", ");
            }
        });

        descending.addActionListener(e -> {
            if (attributes.getSelectedValue() != null && !descedingList.contains(attributes.getSelectedValue().toString())) {
                descedingList.add(attributes.getSelectedValue().toString());
                descendingElements.setText(descendingElements.getText() + attributes.getSelectedValue().toString() + ", ");
            }
        });

        sortButton.addActionListener(e -> {
            entityView.getTableModel().setRows(MainFrame.getInstance().getDb().filterAndSort(entity, selectedList, ascendingList, descedingList));
            dispose();
        });

        add(attributes);
        add(selected);
        add(ascending);
        add(descending);
        add(sortButton);
        add(selectedElements);
        add(ascedningElements);
        add(descendingElements);

        attributes.setBounds(30, 25, 315, 365);
        sortButton.setBounds(250, 470, 170, 50);
        selected.setBounds(25, 400, 100, 25);
        ascending.setBounds(130, 400, 100, 25);
        descending.setBounds(235, 400, 105, 25);
        selectedElements.setBounds(360, 25, 275, 115);
        ascedningElements.setBounds(360, 165, 275, 115);
        descendingElements.setBounds(360, 315, 275, 110);
        setVisible(true);



    }

}
