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

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class register extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/html");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentialsdb", "root", "HarshPandat@1234");
            String e = request.getParameter("email");
            String p = request.getParameter("password");
            PreparedStatement ps = con.prepareStatement("select * from member where Email=? and Password=?");
            ps.setString(1, e);
            ps.setString(2, p);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name"); // Retrieve the name from the database
                String email = rs.getString("Email"); // Retrieve the email from the database
                String teacherId = rs.getString("Teacher Id"); // Retrieve the teacherId from the database
                request.getSession().setAttribute("teacherId", teacherId);
                // Print a success message to the response (optional)
                out.println("Login Successful");

                // Set attributes in the request object to pass data to the JSP
                request.setAttribute("lstatus", "success");
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("teacherId", teacherId);

            
                RequestDispatcher rd = request.getRequestDispatcher("maind.jsp");
                rd.forward(request, response);
            } else {
                out.println("login failed<br>");
                out.println("<a href=login.jsp> Try again");
                request.setAttribute("lstatus", "failed");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }

        } catch (ClassNotFoundException e) {
            request.setAttribute("status", "failed");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);

            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            out.println("out error");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}

  