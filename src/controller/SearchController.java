package controller;

import gui.QueryToolbar;
import gui.Search;
import view.EntityView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SearchController extends AbstractAction {

    private QueryToolbar toolbar;

    public SearchController(QueryToolbar toolbar) {
        this.toolbar = toolbar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EntityView ev = (EntityView)toolbar.getTabPane().getSelectedComponent();
        new Search(ev);
    }
}
