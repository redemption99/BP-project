package database;

import resource.DBNode;
import resource.data.Row;

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
}
