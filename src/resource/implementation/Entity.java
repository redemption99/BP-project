package resource.implementation;

import resource.DBNode;
import resource.DBNodeComposite;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;

public class Entity extends DBNodeComposite {

    public Entity(String name, DBNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(DBNode child) {
        if (child != null && child instanceof Attribute) {
            Attribute a = (Attribute)child;
            this.getChildren().add(a);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
