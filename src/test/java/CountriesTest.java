import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountriesTest {
     /*
        Given
          User connects to the database
        When
          User sends the query to get the region ids from "countries" table
        Then
          Verify that the number of region ids greater than 1 is 17.
        And
          User closes the connection
       */

    public static void main(String[] args) throws SQLException {
        //User connects to the database
        JdbcUtils.connectToDatabase("localhost","postgres","postgres","2357");
        JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        String sql1 = "SELECT region_id FROM countries";
        ResultSet resultSet = JdbcUtils.statement.executeQuery(sql1);
        List<Integer> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(resultSet.getInt(1));
        }

        System.out.println("list = " + list);

        //Verify that the number of region ids greater than 1 is 17.
        int counter = 0;
        for(int w:list){
            if(w>1){
                counter++;
            }

        }

        System.out.println("counter = " + counter);

    }
}