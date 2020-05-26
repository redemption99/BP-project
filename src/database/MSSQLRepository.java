package database;

import resource.DBNode;
import resource.data.Row;
import resource.enums.AttributeType;
import resource.enums.ConstraintType;
import resource.implementation.Attribute;
import resource.implementation.AttributeConstraint;
import resource.implementation.Entity;
import resource.implementation.InformationResource;
import utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSSQLRepository implements Repository{

    private Connection conn;

    public MSSQLRepository() {}

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:jtds:sqlserver://" + Constants.MSSQL_IP + "/" + Constants.MSSQL_DATABASE,
                                        Constants.MSSQL_USERNAME,
                                        Constants.MSSQL_PASSWORD);
        System.out.println("Connection successful");
    }

    private void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    @Override
    public DBNode getSchema() {

        try {
            this.initConnection();

            DatabaseMetaData metaData = conn.getMetaData();
            InformationResource ir = new InformationResource("Baza");

            String tableType[] = {"TABLE"};
            ResultSet tables = metaData.getTables(null, "dbo", null, tableType);

            while (tables.next()) {

                String tableName = tables.getString("TABLE_NAME");
                if (tableName.equals("sysdiagrams")) continue;
                Entity newTable = new Entity(tableName, ir);
                ir.addChild(newTable);

                // System.out.println(tableName);

                ResultSet columns = metaData.getColumns(null, null, tableName, null);

                while (columns.next()) {

                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    //System.out.println(columnName + " " + columnType);
                    int columnSize = Integer.parseInt(columns.getString("COLUMN_SIZE"));

                    ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
                    ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
                    String isNullable = columns.getString("IS_NULLABLE");
                    String hasDefault = columns.getString("COLUMN_DEF");

                    Attribute attribute = new Attribute(columnName, newTable, AttributeType.valueOf(columnType.toUpperCase()), columnSize);

                    while (primaryKeys.next()) {
                        String pkColumnName = primaryKeys.getString("COLUMN_NAME");
                        if (pkColumnName.equals(attribute.toString())) {
                            AttributeConstraint ac = new AttributeConstraint(ConstraintType.PRIMARY_KEY.toString(), attribute, ConstraintType.PRIMARY_KEY);
                            attribute.addChild(ac);
                        }
                    }

                    while (foreignKeys.next()) {
                        String fkTableName = foreignKeys.getString("FKTABLE_NAME");
                        String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
                        String pkTableName = foreignKeys.getString("PKTABLE_NAME");
                        String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");
                        if (fkColumnName.equals(attribute.toString())) {
                            AttributeConstraint ac = new AttributeConstraint(ConstraintType.FOREIGN_KEY.toString(), attribute, ConstraintType.FOREIGN_KEY);
                            attribute.addChild(ac);
                        }
                    }

                    if (isNullable.equals("NO")) {
                        AttributeConstraint ac = new AttributeConstraint(ConstraintType.NOT_NULL.toString(), attribute, ConstraintType.NOT_NULL);
                        attribute.addChild(ac);
                    }

                    if (hasDefault != null) {
                        AttributeConstraint ac = new AttributeConstraint(ConstraintType.DEFAULT_VALUE.toString(), attribute, ConstraintType.DEFAULT_VALUE);
                        attribute.addChild(ac);
                    }

                    newTable.addChild(attribute);
                }

            }

            return ir;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }

        return null;
    }

    @Override
    public List<Row> get(String from) {

        List<Row> rows = new ArrayList<>();

        try {
            this.initConnection();

            String query = "SELECT * FROM " + from;
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Row row = new Row();
                row.setName(from);

                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.addField(rsmd.getColumnName(i), rs.getString(i));
                }

                rows.add(row);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }

        return rows;
    }
}
