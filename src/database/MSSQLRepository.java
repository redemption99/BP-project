package database;

import utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MSSQLRepository {

    private Connection conn;

    public MSSQLRepository() {}

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:jtds:sqlserver://" + Constants.MSSQL_IP + "/" + Constants.MSSQL_DATABASE,
                                        Constants.MSSQL_USERNAME,
                                        Constants.MSSQL_PASSWORD);
        System.out.println("Connection successful");
    }


}
