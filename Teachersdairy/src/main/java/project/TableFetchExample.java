package project;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableFetchExample {
    public static void main(String[] args) {
        String fromDate = "October 1, 2023";
        String toDate = "October 30, 2023";
        String semester = "1st semester";
        System.out.println("Table: " );

        // Create a list to store table names within the date range
        List<String> tableNamesInRange = new ArrayList<>();
        
        // Build table names and add them to the list
        while (!fromDate.equals(toDate)) {
            String tableName = fromDate + "_" + semester;
            tableNamesInRange.add(tableName);
            
            // Increment fromDate to the next date
            // You can implement date manipulation logic here (e.g., adding one day)
        }
        
        // Fetch data from the tables in the list
        for (String tableName : tableNamesInRange) {
            fetchDataFromTable(tableName);
        }
    }
    
    private static void fetchDataFromTable(String tableName) {
    	
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentialsdb\", \"root\", \"HarshPandat@1234");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            // Process and display the data
            while (resultSet.next()) {
                // Retrieve data from the result set
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                
                // Process the data as needed
                System.out.println("Table: " + tableName + ", ID: " + id + ", Name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
