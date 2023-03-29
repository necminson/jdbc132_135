import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcUtils {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    //1. Step: Create the connection
    public static Connection connectToDatabase(String hostName,String databaseName,String username, String password ) {

        try {//To handle the SQL exception we use try-catch block. "throws" keyword does not handle the exception.
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+databaseName, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    //2. Step: Create Statement
    public static Statement createStatement() {

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }

    //3. Step: Execute Query
    public static boolean execute(String query) {
        boolean result;
        try {
            result = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    //4. Step: Close Connection and Statement
    public static void closeConnectionAndStatement() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to Drop The Table
    public static void dropTable(String tableName) {

        try {
            statement.execute("DROP TABLE " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //The method to put column data into a list
    public static List<Object> getColumnList(String tableName, String columnName) {
        List<Object> list = new ArrayList<>();

        String sql = "SELECT " + columnName + " FROM " + tableName;

        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                list.add(resultSet.getObject(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}