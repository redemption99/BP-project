package resource;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
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

    @Override
    public void insert(MutableTreeNode child, int index) {
        this.getChildren().add((DBNode) child);
    }

    @Override
    public void remove(int index) {
        this.getChildren().remove(index);
    }

    @Override
    public void remove(MutableTreeNode node) {
        this.getChildren().remove(node);
    }

    @Override
    public void setUserObject(Object object) {

    }

    @Override
    public void removeFromParent() {

    }

    @Override
    public void setParent(MutableTreeNode newParent) {

    }

    @Override
    public int getChildCount() {
        return this.getChildren().size();
    }

    @Override
    public int getIndex(TreeNode node) {
        return this.getChildren().indexOf(node);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return null;
    }
}
