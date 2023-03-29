import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "2357");
        Statement statement = connection.createStatement();

        //1.Example: Select the country names whose region id's are 1
        String sql1 = "SELECT country_name FROM countries WHERE region_id = 1";
        boolean r1 = statement.execute(sql1);//If you use  execute() method, it will return true or false.
        System.out.println("r1 = " + r1);//True
        //To see the records we have another method which is executeQuery() that returns ResultSet

//        ResultSet resultSet1 = statement.executeQuery(sql1);
//        resultSet1.next();
//        resultSet1.next();
//        resultSet1.next();
//        String country = resultSet1.getString("country_name");
//        System.out.println("country = " + country);

        ResultSet resultSet1 = statement.executeQuery(sql1);

        while (resultSet1.next()) {
            System.out.println(resultSet1.getString("country_name"));
        }

        //2.Example: Select the country_id and country_name whose region_id's are greater than 2

        String sql2 = "SELECT country_id, country_name FROM countries WHERE region_id > 2";
        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()) {
            System.out.println(resultSet2.getString("country_id") + "--> " + resultSet2.getString("country_name"));
        }

        //3.Example: Select all columns whose number_of_employees is the lowest from companies table
        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet3 = statement.executeQuery(sql3);

        while (resultSet3.next()) {
            System.out.println(resultSet3.getInt(1) + " " + resultSet3.getString(2) + " " + resultSet3.getInt(3));
        }

        connection.close();
        statement.close();
    }
}