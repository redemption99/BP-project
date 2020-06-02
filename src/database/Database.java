package database;

import resource.DBNode;
import resource.data.Row;
import resource.implementation.Attribute;
import resource.implementation.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Database {

    DBNode loadResource();
    
    List<Row> readDataFromTable(String tableName);

    boolean insert(Entity entity, ArrayList<String> values);

    boolean update(Entity entity, ArrayList<String> newValues, Row row);

    boolean delete(Entity entity, ArrayList<String> attributeNames, ArrayList<String> attributeValues);

    List<Row> filterAndSort(Entity entity, ArrayList<String> selected, ArrayList<String> ascending, ArrayList<String> descending);

    List<Row> search(Entity entity, ArrayList<String> selected, ArrayList<String> operators, ArrayList<String> whereAttributes);

    List<Row> report(Entity entity, boolean flag, String reportColumn, List<String> groupByColumns);

    List<Row> inRelation(Entity topEntity, Entity botEntity, Row selectedRow);
}
