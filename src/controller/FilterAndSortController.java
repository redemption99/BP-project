package controller;

import gui.FilterAndSort;
import gui.QueryToolbar;
import resource.implementation.Entity;
import view.EntityView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FilterAndSortController extends AbstractAction {

    private QueryToolbar toolbar;

    public FilterAndSortController(QueryToolbar toolbar) {
        this.toolbar = toolbar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EntityView ev = (EntityView)toolbar.getTabPane().getSelectedComponent();
        new FilterAndSort(ev);
    }
}
