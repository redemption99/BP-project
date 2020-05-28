package gui;

import controller.AddButtonController;
import view.TabPaneView;

import javax.swing.*;

public class QueryToolbar extends JToolBar {

    private JButton addButton = new JButton("Add");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JTabbedPane tabPane = null;

    public QueryToolbar() {
        setFloatable(false);

        addButton.addActionListener(new AddButtonController(this));

        add(addButton);
        add(updateButton);
        add(deleteButton);

        setVisible(false);
    }

    public JTabbedPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(JTabbedPane tabPane) {
        this.tabPane = tabPane;
    }
}