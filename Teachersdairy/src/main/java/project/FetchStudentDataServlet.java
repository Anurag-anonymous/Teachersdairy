package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

public class FetchStudentDataServlet extends HttpServlet {
	 String tableName;
	 String year;
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");

            // Get parameters from the request	
            year = request.getParameter("year");

            // Fetch attendance data from the database
            List<Student> studentData = fetchStudentData(year);

            // Convert the data to JSON
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(studentData);

            // Send the JSON response
            out.write(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }    }

    // Simulated method to fetch student data (replace with actual database query)
    private List<Student> fetchStudentData(String date) {
        List<Student> studentData = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
       
        try {

            // Establish a database connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentialsdb", "root", "HarshPandat@1234");
            if (con != null) {
                System.out.println("Database connection successful."); // Add this line for debugging
            } else {
                System.out.println("Database connection failed."); // Add this line for debugging
            }
tableName = year+"year";
			String query = "select `Roll no`,`Student name`, `Father name`,`Status` from " + tableName;

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            // Iterate through the result set and populate the attendance data
            while (rs.next()) {
                Student record = new Student();
                record.setRollNo(rs.getInt("Roll no"));
                record.setStudentName(rs.getString("Student name"));
                record.setFatherName(rs.getString("Father name"));
                record.setStatus(rs.getString("Status"));
                studentData.add(record);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                	
                    rs.close();
                }
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
        System.out.println("Fetched student data: " + studentData + tableName);
        return studentData;

    }
}
