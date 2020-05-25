package resource.implementation;

import resource.DBNode;
import resource.DBNodeComposite;
import resource.enums.ConstraintType;

public class AttributeConstraint extends DBNode {

    private ConstraintType constraintType;

    public AttributeConstraint(String name, DBNode parent, ConstraintType constraintType) {
        super(name, parent);
        this.constraintType = constraintType;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }
}
