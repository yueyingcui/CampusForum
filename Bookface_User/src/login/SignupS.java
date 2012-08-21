package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupS extends HttpServlet {

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
		
		System.out.println("-----login/SignupS.java | Login/signup.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Integer userid = 0;
			
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			// String -> Date
			String dob = request.getParameter("year") + request.getParameter("month") + request.getParameter("date");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zipcode = request.getParameter("zipcode");
			String telephone = request.getParameter("telephone");
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//	DB2 information: initial userid, insert tuple
			Statement stmt = conn.createStatement();
			String query1 = "SELECT USERID FROM USERREGISTER";
			System.out.println(query1);
			ResultSet rs = stmt.executeQuery(query1);
			while(rs.next())
			{
				userid = rs.getInt(1);
			}
			userid = userid + 1;
			
			String query2 = "INSERT INTO USERREGISTER (USERNAME, PASSWORD, USERID) VALUES ('" + username + "', '" + password + "', '" + userid + "')";
			System.out.println(query2);
			stmt.executeUpdate(query2);
			
			String query3 = "INSERT INTO USER (USERID, FIRSTNAME, LASTNAME, SEX, EMAIL, DOB, ADDRESS, CITY, STATE, ZIPCODE, TELEPHONE) VALUES ('" + userid + "', '" + firstname + "', '" + lastname + "', '" + sex + "', '" + email + "', TO_DATE('" + month + "/" + date + "/" + year + "', 'mm/dd/yyyy'), '" + address + "', '" + city + "', '" + state + "', '" + zipcode + "', '" + telephone + "')";
			System.out.println(query3);
			stmt.executeUpdate(query3);
			
			// redirect webpage
				System.out.println("SignUp successed!");
				response.sendRedirect("jsp/Login/welcome.jsp");
				
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
