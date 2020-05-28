package controller;

import gui.MainFrame;
import resource.implementation.Attribute;
import resource.implementation.Entity;
import view.*;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class TreeController implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {

        TreePath path = e.getPath();

        if (path.getLastPathComponent() instanceof Entity) {
            Entity entity = (Entity) path.getLastPathComponent();

            EntityView ev = new EntityView(entity);

            MainFrame.getInstance().getTopTp().addTab(ev);

            MainFrame.getInstance().getBotTp().getTabPane().removeAll();

            for (Entity i : entity.getRelations()) {
                MainFrame.getInstance().getBotTp().addTab(new EntityView(i));
            }
        }
    }
}
