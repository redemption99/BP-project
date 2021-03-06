package resource.implementation;

import resource.DBNode;
import resource.DBNodeComposite;
import resource.enums.AttributeType;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;

public class Attribute extends DBNodeComposite {

    private AttributeType type;
    private int length;

    public Attribute(String name, DBNode parent) {
        super(name, parent);
    }

    public Attribute(String name, DBNode parent, AttributeType type, int length) {
        super(name, parent);
        this.type = type;
        this.length = length;
    }

    @Override
    public void addChild(DBNode child) {
        if (child != null && child instanceof AttributeConstraint) {
            AttributeConstraint ac = (AttributeConstraint)child;
            this.getChildren().add(ac);
        }
    }

    public AttributeType getType() {
        return type;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
