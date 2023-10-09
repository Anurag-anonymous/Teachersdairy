package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Teachersdairy/SaveAttendanceServlet")
public class SaveAttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
        	Class.forName("com.mysql.cj.jdbc.Driver");

            // Get the JSON data from the request
            String postData = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

            // Parse the JSON data into a Java object
            Gson gson = new Gson();
            AttendanceData attendanceData = gson.fromJson(postData, AttendanceData.class);

            // Perform database operations to save attendance data
            boolean success = saveAttendanceData(attendanceData);

            // Prepare the response JSON
            String jsonResponse = "{\"success\":" + success + "}";
            out.write(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // Function to save attendance data to the database
    private boolean saveAttendanceData(AttendanceData attendanceData) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
        	
            // Establish a database connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentialsdb", "root", "HarshPandat@1234");
            if (con != null) {
                System.out.println("Database connection successful save."); // Add this line for debugging
            } else {
                System.out.println("Database connection failed."); // Add this line for debugging
            }
            // Construct the SQL query to save attendance data into a table
            String tableName =  attendanceData.getDate() + "_" + attendanceData.getSemester();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS `" + tableName + "` ("
                    + "RollNo INT,"
                    + "StudentName VARCHAR(255),"
                    + "FatherName VARCHAR(255),"
                    + "Status VARCHAR(1)"
                    + ")";


            ps = con.prepareStatement(createTableQuery);
            ps.execute();

            // Construct the SQL query to insert attendance data
            String insertOrUpdateQuery = "INSERT INTO `" + tableName + "` (`RollNo`, `StudentName`, `FatherName`, `Status`) "
                    + "VALUES (?, ?, ?, ?) "
                    + "ON DUPLICATE KEY UPDATE "
                    + "`StudentName` = VALUES(`StudentName`), "
                    + "`FatherName` = VALUES(`FatherName`), "
                    + "`Status` = VALUES(`Status`)";

            ps = con.prepareStatement(insertOrUpdateQuery);
           
            System.out.println(tableName);
            // Iterate through the attendance data and insert each record into the table
            List<Student> records = attendanceData.getAttendanceData();
            for (Student record : records) {
                ps.setInt(1, record.getRollNo());
                ps.setString(2, record.getStudentName());
                ps.setString(3, record.getFatherName());
                ps.setString(4, record.getStatus());
                ps.executeUpdate();
                System.out.println("data saved");
// Insert the record
            }

            return true;
            // All records inserted successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred while inserting records
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
