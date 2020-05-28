package resource.implementation;

import resource.DBNode;
import resource.DBNodeComposite;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;

public class Entity extends DBNodeComposite {

    private ArrayList<Entity> relations = null;
    private ArrayList<AttributeConstraint> foreignKeys = null;
    private ArrayList<AttributeConstraint> primaryKeys = null;

    public Entity(String name, DBNode parent) {
        super(name, parent);
        relations = new ArrayList<>();
        foreignKeys = new ArrayList<>();
        primaryKeys = new ArrayList<>();
    }

    public ArrayList<Entity> getRelations() {
        return relations;
    }

    public ArrayList<AttributeConstraint> getForeignKeys() {
        return foreignKeys;
    }

    public void addForeignKey(AttributeConstraint ac) {
        foreignKeys.add(ac);
    }

    public ArrayList<AttributeConstraint> getPrimaryKeys() {
        return primaryKeys;
    }

    public void addPrimaryKey(AttributeConstraint ac) {
        primaryKeys.add(ac);
    }

    public boolean inRelation(Entity e) {
        for (AttributeConstraint i: getForeignKeys())
            for (AttributeConstraint j: e.getPrimaryKeys())
                // drugi uslov je za slucaj kada je jedan atribut istovremeno i foreign key i primary key
                if (i.getName().equals(j.getName()) && i.getParent() != j.getParent())
                    return true;
        return false;
    }

    public void addRelation(Entity e) {
        if (!relations.contains(e))
            relations.add(e);
    }

    @Override
    public void addChild(DBNode child) {
        if (child instanceof Attribute) {
            Attribute a = (Attribute)child;
            this.getChildren().add(a);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
