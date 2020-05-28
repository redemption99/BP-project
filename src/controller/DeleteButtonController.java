package controller;

import gui.MainFrame;
import resource.implementation.Entity;
import view.EntityView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteButtonController extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        EntityView ev = (EntityView)MainFrame.getInstance().getTopTp().getTabPane().getSelectedComponent();
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

        MainFrame.getInstance().getDb().delete(entity, attributeNames, attributeValues);
        ev.getTableModel().setRows(MainFrame.getInstance().getDb().readDataFromTable(entity.getName()));
    }
}
