package controller;

import gui.Update;
import gui.Message;
import gui.QueryToolbar;
import resource.data.Row;
import view.EntityView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpdateButtonController extends AbstractAction {

    private QueryToolbar toolbar;

    public UpdateButtonController(QueryToolbar toolbar) {
        this.toolbar = toolbar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EntityView entityView = (EntityView) toolbar.getTabPane().getSelectedComponent();
        int idx = entityView.getTable().getSelectedRow();

        if (idx == -1) {
            new Message("Selektuj red koji se uredjuje.");
            return;
        }

        Row row = entityView.getTableModel().getRows().get(idx);

        new Update(entityView, row);
    }
}
