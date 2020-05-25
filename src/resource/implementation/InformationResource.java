package resource.implementation;

import resource.DBNode;
import resource.DBNodeComposite;

import javax.swing.tree.TreeNode;

public class InformationResource extends DBNodeComposite {

    public InformationResource(String name) {
        super(name, null);
    }

    @Override
    public void addChild(DBNode child) {
        if (child != null && child instanceof Entity) {
            Entity e = (Entity)child;
            this.getChildren().add(e);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
