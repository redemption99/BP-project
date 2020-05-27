package view;

import model.TableModel;
import resource.implementation.Entity;
import gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class EntityView extends JPanel {

    // EntityView predstavlja jedan tab u tabbedpaneu

    private Entity entity;

    private JTable table;
    private TableModel tableModel;

    public EntityView(Entity entity) {

        this.entity = entity;

        table = new JTable();
        tableModel = new TableModel();

        tableModel.setRows(MainFrame.getInstance().getDb().readDataFromTable(entity.getName()));
        table.setModel(tableModel);

        setLayout(new BorderLayout());
        add(new JScrollPane(table));
        setVisible(true);
    }

    public String getName() {
        return entity.getName();
    }

}
