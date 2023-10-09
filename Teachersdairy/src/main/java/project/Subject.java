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

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Subject extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // You can get the teacherId from the session or request parameters
        	String teacherId = (String) request.getSession().getAttribute("teacherId");
            // Fetch subjects for the logged-in user from the database
            List<String> subjectsList = fetchSubjectsForUser(teacherId);

            // Convert the subjectsList to JSON using Gson
            Gson gson = new Gson();
            String subjectsJson = gson.toJson(subjectsList);

            // Send the JSON response to the client
            out.write(subjectsJson);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // Method to fetch subjects for the logged-in user from the database
 // Method to fetch subjects for the logged-in user from the database
    private List<String> fetchSubjectsForUser(String teacherId) {
        List<String> subjectsList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establish a database connection (you might need to configure your database connection details)
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentialsdb", "root", "HarshPandat@1234");

            // Construct the SQL query to retrieve subjects based on teacherId
            String query = "SELECT Subjects FROM member WHERE `Teacher id` = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, teacherId); // Set the teacherId parameter

            // Execute the query
            rs = ps.executeQuery();

            // Process the result set and add subjects to the list
            while (rs.next()) {
                String subjects = rs.getString("Subjects");
                if (subjects != null && !subjects.isEmpty()) {
                    // Assuming subjects are comma-separated, split them and add to the list
                    String[] subjectArray = subjects.split(",");
                    for (String subject : subjectArray) {
                        subjectsList.add(subject.trim());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database resources in the reverse order of their creation
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

        return subjectsList;
    }

}