package controller;

import gui.QueryToolbar;
import gui.Report;
import view.EntityView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ReportButtonController extends AbstractAction {

    private QueryToolbar toolbar;

    public ReportButtonController(QueryToolbar toolbar) { this.toolbar = toolbar; }

    @Override
    public void actionPerformed(ActionEvent e) {
        EntityView ev = (EntityView)toolbar.getTabPane().getSelectedComponent();
        new Report(ev);
    }
}
