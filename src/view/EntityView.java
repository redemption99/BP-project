package view;

import app.Main;
import model.TableModel;
import observer.Notification;
import observer.Subscriber;
import resource.data.Row;
import resource.implementation.Entity;
import gui.MainFrame;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class EntityView extends JPanel {

    // EntityView predstavlja jedan tab u tabbedpaneu

    private Entity entity;

    private JTable table;
    private TableModel tableModel;

    public EntityView(Entity entity) {

        this.entity = entity;

        table = new JTable();
        tableModel = new TableModel();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1) {

                    JTable mainTable = ((EntityView) MainFrame.getInstance().getTopTp().getTabPane().getSelectedComponent()).getTable();
                    if (mainTable != table)
                        return;

                    //System.out.println("Kliknuo levi klik");

                    //int selectedRow = table.getSelectedRow();
                    //Row selectedRow = (Row) table.getSelectedRow();

                    Row selectedRow = tableModel.getRows().get(table.getSelectedRow());

                    //System.out.println("Selektovan je " + selectedRow.getFields());

                    int nbottp = MainFrame.getInstance().getBotTp().getTabPane().getTabCount();

                    for (int i = 0; i < nbottp; i++) {
                        EntityView botev = (EntityView)MainFrame.getInstance().getBotTp().getTabPane().getComponentAt(i);
                        Entity bote = botev.getEntity();

                        List<Row> rows = MainFrame.getInstance().getDb().inRelation(entity, bote, selectedRow);



                        botev.getTableModel().setRows(rows);

                        //System.out.println(bote.getName());
                    }
                }
            }
        });

        tableModel.setRows(MainFrame.getInstance().getDb().readDataFromTable(entity.getName()));
        table.setModel(tableModel);

        setLayout(new BorderLayout());
        add(new JScrollPane(table));
        setVisible(true);
    }

    public String getName() {
        return entity.getName();
    }

    public Entity getEntity() {
        return entity;
    }

    public JTable getTable() {
        return table;
    }

    public TableModel getTableModel() {
        return tableModel;
    }
}
