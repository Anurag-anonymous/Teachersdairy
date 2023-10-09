package project;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class reset
 */
public class reset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        PrintWriter out = response.getWriter();

		try {
            response.setContentType("text/html");

            // Retrieve form parameters
            String teacherId = request.getParameter("teacherId");
            String email = request.getParameter("email");
            String newPassword = request.getParameter("newPassword");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentialsdb", "root", "HarshPandat@1234");

            // Check if the teacher ID and email match any records in the database
            PreparedStatement checkStmt = con.prepareStatement("SELECT * FROM member WHERE `Teacher id`=? AND Email=?");
            checkStmt.setString(1, teacherId);
            checkStmt.setString(2, email);
            ResultSet checkResult = checkStmt.executeQuery();

            if (checkResult.next()) {
                // Teacher ID and email match, update the password
                PreparedStatement updateStmt = con.prepareStatement("UPDATE member SET Password=? WHERE `Teacher id`=?");
                updateStmt.setString(1, newPassword);
                updateStmt.setString(2, teacherId);
                int rowsAffected = updateStmt.executeUpdate();

                if (rowsAffected > 0) {
                    request.setAttribute("statuss", "success");

                } else {
                    request.setAttribute("statuss", "failed");
                    
                   
                }
            } else {
                out.println("Teacher ID and email do not match.");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);

            con.close();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            out.println("Teacher ID and email do 1 not match.");

        } catch (SQLException e) {

            e.printStackTrace();
            out.println("Teacher ID and email do 2 not match.");

        }
	}
}
