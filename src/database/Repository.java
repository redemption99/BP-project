package database;

import resource.DBNode;
import resource.data.Row;
import resource.implementation.AttributeConstraint;
import resource.implementation.Attribute;
import resource.implementation.Entity;

import java.util.ArrayList;
import java.util.List;

public interface Repository {

    DBNode getSchema();
    
    List<Row> get(String from);

    boolean insert(Entity entity, ArrayList<String> newValues);

    boolean update(Entity entity, ArrayList<String> newValues, Row row);

    void delete(Entity entity, ArrayList<String> attributeNames, ArrayList<String> attributeValues);

}
