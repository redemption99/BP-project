package database;

import observer.Subscriber;
import resource.DBNode;
import resource.data.Row;
import resource.implementation.Entity;

import java.util.ArrayList;
import java.util.List;

public interface Repository {

    DBNode getSchema();
    
    List<Row> get(String from);

    boolean insert(Entity entity, ArrayList<String> newValues);

    boolean update(Entity entity, ArrayList<String> newValues, Row row);

    boolean delete(Entity entity, ArrayList<String> attributeNames, ArrayList<String> attributeValues);

    List<Row> filterAndSort(Entity entity, ArrayList<String> selected, ArrayList<String> ascending, ArrayList<String> descending);

    List<Row> search(Entity entity, ArrayList<String> selected, ArrayList<String> operators, ArrayList<String> whereAttributes);

    List<Row> report(Entity entity, boolean flag, String reportColumn, List<String> groupByColumns);

    List<Row> inRelation(Entity topEntity, Entity botEntity, Row selectedRow);
}
