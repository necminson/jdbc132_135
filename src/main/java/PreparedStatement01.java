import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "2357");
        Statement statement = connection.createStatement();
        //1.Example: Update the number of employees to 9999 if the company name is IBM by using prepared statement
        //1. Step: Create Prepared Statement Query
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";

        //2. Step: Create Prepared Statement Object
        PreparedStatement ps1 = connection.prepareStatement(sql1);

        //3. Step: Assign the values by using 'setInt(), setString() ... methods'
        ps1.setInt(1, 9999);
        ps1.setString(2, "IBM");

        //4. Step: Execute the query
        int numOfRecordsUpdated = ps1.executeUpdate();
        System.out.println("numOfRecordsUpdated = " + numOfRecordsUpdated);

        //2.Example: Update the number of employees to 5555 if the company name is GOOGLE by using prepared statement
        ps1.setInt(1, 5555);
        ps1.setString(2, "GOOGLE");

        int numOfRecordsUpdated2 = ps1.executeUpdate();
        System.out.println("numOfRecordsUpdated2 = " + numOfRecordsUpdated2);

        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet1 = statement.executeQuery(sql2);
        while (resultSet1.next()) {
            System.out.println(resultSet1.getObject(1) + " " + resultSet1.getObject(2) + " " + resultSet1.getObject(3));
        }

        connection.close();
        statement.close();
        ps1.close();

    }
}