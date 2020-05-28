package controller;

import gui.Add;
import gui.QueryToolbar;
import resource.implementation.Entity;
import view.EntityView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddButtonController extends AbstractAction {

    private QueryToolbar toolbar;

    public AddButtonController(QueryToolbar toolbar) {
        this.toolbar = toolbar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Entity entity = (Entity) ((EntityView) toolbar.getTabPane().getSelectedComponent()).getEntity();
        new Add(entity);
    }
}
