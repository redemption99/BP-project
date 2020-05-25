package resource;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class DBNode extends DefaultMutableTreeNode {

    private String name;
    private DBNode parent;

    public DBNode(String name, DBNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public DBNode getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }
}
