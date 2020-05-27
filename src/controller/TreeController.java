package controller;

import gui.MainFrame;
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
        }
    }
}
