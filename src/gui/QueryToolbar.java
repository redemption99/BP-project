package gui;

import controller.AddButtonController;

import javax.swing.*;

public class QueryToolbar extends JToolBar {

    private JButton addButton = new JButton("Add");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");

    public QueryToolbar() {
        setFloatable(false);

        addButton.addActionListener(new AddButtonController());

        add(addButton);
        add(updateButton);
        add(deleteButton);
    }

}
