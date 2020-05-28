package controller;

import gui.MainFrame;
import gui.Message;
import gui.QueryToolbar;
import resource.implementation.Entity;
import view.EntityView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteButtonController extends AbstractAction {

    private QueryToolbar toolbar;

    public DeleteButtonController(QueryToolbar toolbar) {
        this.toolbar = toolbar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EntityView ev = (EntityView) toolbar.getTabPane().getSelectedComponent();
        JTable table = ev.getTable();
        Entity entity = ev.getEntity();
        List<String> pkeysStrings = new ArrayList<>();
        ArrayList<String> attributeNames = new ArrayList<>();
        ArrayList<String> attributeValues = new ArrayList<>();

        for (int i = 0; i < entity.getPrimaryKeys().size(); i++) {
            pkeysStrings.add(entity.getPrimaryKeys().get(i).getParent().toString());
        }

        for (int j = 0; j < table.getColumnCount(); j++) {
            if (pkeysStrings.contains(table.getColumnName(j))) {
                System.out.println(table.getColumnName(j));
                attributeNames.add(table.getColumnName(j));
                System.out.println(table.getValueAt(table.getSelectedRow(), j).toString());
                attributeValues.add(table.getValueAt(table.getSelectedRow(), j).toString());
            }
        }

        if (MainFrame.getInstance().getDb().delete(entity, attributeNames, attributeValues)) {
            new Message("Red je uspesno obrisan.");
            ev.getTableModel().setRows(MainFrame.getInstance().getDb().readDataFromTable(entity.getName()));
        }
        else
            new Message("Red nije obrisan.");

    }
}
