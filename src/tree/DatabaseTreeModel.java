package tree;

import resource.DBNode;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class DatabaseTreeModel extends DefaultTreeModel {
    public DatabaseTreeModel(DBNode root) {
        super(root, true);
    }
}
