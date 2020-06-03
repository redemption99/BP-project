package database;

import observer.Publisher;
import observer.Subscriber;
import resource.DBNode;
import resource.data.Row;
import resource.implementation.Entity;

import java.util.ArrayList;
import java.util.List;

public class DatabaseImplementation implements Database, Publisher {

    private Repository repo;

    public DatabaseImplementation(Repository repo) {
        this.repo = repo;
    }

    @Override
    public DBNode loadResource() {
        return repo.getSchema();
    }

    @Override
    public List<Row> readDataFromTable(String tableName) {
        return repo.get(tableName);
    }

    @Override
    public boolean insert(Entity entity, ArrayList<String> values) {
        return repo.insert(entity, values);
    }

    @Override
    public boolean update(Entity entity, ArrayList<String> newValues, Row row) {
        return repo.update(entity, newValues, row);
    }

    @Override
    public boolean delete(Entity entity, ArrayList<String> attributeNames, ArrayList<String> attributeValues) {
        return repo.delete(entity, attributeNames, attributeValues);
    }

    @Override
    public List<Row> filterAndSort(Entity entity, ArrayList<String> selected, ArrayList<String> ascending, ArrayList<String> descending) {
        return repo.filterAndSort(entity, selected, ascending, descending);
    }

    @Override
    public List<Row> search(Entity entity, ArrayList<String> selected, ArrayList<String> operators, ArrayList<String> whereAttributes) {
        return repo.search(entity, selected, operators, whereAttributes);
    }

    @Override
    public List<Row> report(Entity entity, boolean flag, String reportColumn, List<String> groupByColumns) {
        return repo.report(entity, flag, reportColumn, groupByColumns);
    }

    @Override
    public List<Row> inRelation(Entity topEntity, Entity botEntity, Row selectedRow) {
        return repo.inRelation(topEntity, botEntity, selectedRow);
    }

    @Override
    public void addSubscriber(Subscriber sub) {
        repo.addSubscriber(sub);
    }

    @Override
    public void removeSubscriber(Subscriber sub) {
        repo.removeSubscriber(sub);
    }

    @Override
    public void notifySubscribers(Entity entity) {
        repo.notifySubscribers(entity);
    }


}
