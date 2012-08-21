package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

public class LoginSM extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		System.out.println("-----login/LoginSM.java | Login/loginM.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String managername = request.getParameter("managername");
			String password = request.getParameter("password");
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//	DB2 information
			// querry1: select MANAGERREGISTER
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM MANAGERREGISTER WHERE MANAGERNAME='" + managername + "'";
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			String managername_db = "";
			String password_db = "";
			int userid_db = 0;
			while(rs.next())
			{
				System.out.println("Managernamename_db:" + rs.getString(2).trim()+ "	" + "Password_db:" + rs.getString(3).trim());
				managername_db = rs.getString(2).trim();
				password_db = rs.getString(3).trim();
				//userid_db = rs.getInt(4);
			}
			System.out.println("Managername:" + managername + "	" + "Password:" + password);
			
			// redirect webpage
			HttpSession session = request.getSession();
			if (managername.equals(managername_db) && password.equals(password_db))
			{
				System.out.println("Login successed!");
				response.sendRedirect("jsp/Login/welcomeM.jsp");
				
				// record userid
				session.setAttribute("hostid", userid_db);
			}
			else
			{
				System.out.println("Login failed!");
				response.sendRedirect("jsp/Login/loginM.jsp");
			}
			// close connect
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
