package database;

import resource.DBNode;
import resource.data.Row;
import resource.implementation.Attribute;
import resource.implementation.Entity;

import java.util.ArrayList;
import java.util.List;

public interface Database {

    DBNode loadResource();
    List<Row> readDataFromTable(String tableName);

    boolean insert(Entity entity, ArrayList<String> values);
    void delete(Entity entity, ArrayList<String> attributeNames, ArrayList<String> attributeValues);

}
