package database;

import observer.Subscriber;
import resource.DBNode;
import resource.data.Row;
import resource.implementation.Entity;

import java.util.ArrayList;
import java.util.List;

public class DatabaseImplementation implements Database {

    private Repository repo;

    private List<Subscriber> subscribers = new ArrayList<>();

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
        var ret = repo.insert(entity, values);
        notifySubscribers(entity);
        return ret;
    }

    @Override
    public boolean update(Entity entity, ArrayList<String> newValues, Row row) {
        var ret = repo.update(entity, newValues, row);
        notifySubscribers(entity);
        return ret;
    }

    @Override
    public boolean delete(Entity entity, ArrayList<String> attributeNames, ArrayList<String> attributeValues) {
        var ret = repo.delete(entity, attributeNames, attributeValues);
        notifySubscribers(entity);
        return ret;
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
        if (!this.subscribers.contains(sub))
            this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(Subscriber sub) {
        if (this.subscribers.contains(sub))
            this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Entity entity) {
        if (subscribers.size() != 0) {
            for (int i = 0; i < subscribers.size(); i++) {
                subscribers.get(i).update(entity);
            }
        }
    }

}
