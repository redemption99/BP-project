package database;

import resource.DBNode;
import resource.data.Row;
import resource.implementation.Entity;

import java.util.ArrayList;
import java.util.List;

public class DatabaseImplementation implements Database{

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

}
