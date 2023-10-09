package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginservlet
 */
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    String formAction = request.getParameter("formAction");
//
//	    if ("register".equals(formAction)) {
//	        // Handle registration logic here
//	        String name = request.getParameter("name");
//	        String email = request.getParameter("email");
//	        String password = request.getParameter("password");
//
//	        Member member = new Member(name, email, password);
//
//	        login ldao = new login();
//	        String result = ldao.insert(member);
//	        response.getWriter().print(result);
//	    } else if ("login".equals(formAction)) {
//	        // Redirect to the login form
//	        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//	        dispatcher.forward(request, response);
//	    } else {
//	        // Handle other cases or errors
//	    }


		PrintWriter out = response.getWriter();


		String name = request.getParameter("name");

	String email = request.getParameter("email");
	String password = request.getParameter("password");
	//RequestDispatcher dispatcher = null;
	Connection con = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentialsdb","root","HarshPandat@1234");
		PreparedStatement pst = con.prepareStatement("insert into member(name, email, password) values(?,?,?)");
		pst.setString(1, name);
		pst.setString(2, email);
		pst.setString(3, password);

	int rowcount = pst.executeUpdate();

	 if (rowcount > 0) {
         request.setAttribute("status", "success");
     } else {
         request.setAttribute("status", "failed");

     }

     // Forward the request to the index.jsp page
     RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
     dispatcher.forward(request, response);


	} catch (Exception e) {
		e.printStackTrace();
        request.setAttribute("status", "failed");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);

	}
	}




}