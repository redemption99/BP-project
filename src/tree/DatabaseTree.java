package tree;

import controller.TreeController;
import database.Repository;
import resource.DBNode;
import resource.implementation.Attribute;
import resource.implementation.Entity;
import resource.implementation.InformationResource;

import javax.swing.*;

public class DatabaseTree extends JTree {

    public DatabaseTree() {
        addTreeSelectionListener(new TreeController());
        setEditable(false);
    }

}
