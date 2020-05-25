package resource;

import java.util.ArrayList;
import java.util.List;

public abstract class DBNodeComposite extends DBNode{

    private List<DBNode> children;

    public DBNodeComposite(String name, DBNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    public DBNodeComposite(String name, DBNode parent, ArrayList<DBNode> children) {
        super(name, parent);
        this.children = children;
    }

    public abstract void addChild(DBNode child);

    public DBNode getChildByName(String name) {
        for (DBNode child: this.getChildren()) {
            if (name.equals(child.getName())) {
                return child;
            }
        }
        return null;
    }

    public List<DBNode> getChildren() {
        return children;
    }

    public DBNode getChildAt(int i) {
        return children.get(i);
    }
}
