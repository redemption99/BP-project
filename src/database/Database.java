package database;

import resource.DBNode;
import resource.data.Row;
import resource.implementation.Entity;

import java.util.ArrayList;
import java.util.List;

public interface Database {

    DBNode loadResource();
    List<Row> readDataFromTable(String tableName);

    boolean insert(Entity entity, ArrayList<String> values);

}
