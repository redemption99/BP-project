package controller;

import gui.MainFrame;
import resource.implementation.Attribute;
import resource.implementation.Entity;
import view.EntityView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabChangeListener implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane pane = (JTabbedPane)e.getSource();
        int selected = pane.getSelectedIndex();
        EntityView ev = (EntityView)pane.getComponentAt(selected);
        Entity entity = ev.getEntity();

        MainFrame.getInstance().getBotTp().getTabPane().removeAll();

        for (int i = 0; i < entity.getChildCount(); i++) {
            Attribute a = null;
            if (entity.getChildAt(i) instanceof Attribute)
                a = (Attribute)entity.getChildAt(i);
            if (a.getInRelation() != null) {
                MainFrame.getInstance().getBotTp().addTab(new EntityView((Entity) a.getInRelation().getParent()));
            }
        }
    }
}
