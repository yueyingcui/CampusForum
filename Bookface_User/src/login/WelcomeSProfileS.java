package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WelcomeSProfileS extends HttpServlet {

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

		System.out.println("-----circle/CircleSCircleListS.java | Circle/circle.jsp -> Circle/circlelist.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//DB2 information
			// initial value
			int hostid = 0;
			String firstname_db = "";
			String lastname_db = "";
			String sex_db = "";
			String email_db = "";
			String dob_db = "";
			String address_db = "";
			String city_db = "";
			String state_db = "";
			String zipcode_db = "";
			String telephone_db = "";
			// assign hostid
			HttpSession session = request.getSession();
			hostid = (Integer)session.getAttribute("hostid");
			// select USER, save selecct result
			// querry1: firstname, lastname, sex, email, dob, address, city, state, zipcode, telephone
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT FIRSTNAME, LASTNAME, SEX, EMAIL, DOB, ADDRESS, CITY, STATE, ZIPCODE, TELEPHONE FROM USER WHERE USERID = '" + hostid + "'";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);
			while(rs1.next())
			{
				// assign value
				firstname_db = rs1.getString(1);
				lastname_db = rs1.getString(2);
				sex_db = rs1.getString(3);
				email_db = rs1.getString(4);
				dob_db = rs1.getString(5);
				address_db = rs1.getString(6);
				city_db = rs1.getString(7);
				state_db = rs1.getString(8);
				zipcode_db = rs1.getString(9);
				telephone_db = rs1.getString(10);
				
				// add into list
				System.out.println("firstname:" + firstname_db);
				System.out.println("lastname:" + lastname_db);
				System.out.println("sex:" + sex_db);
				System.out.println("email:" + email_db);
				System.out.println("dob:" + dob_db);
				System.out.println("address:" + address_db);
				System.out.println("city:" + city_db);
				System.out.println("state:" + state_db);
				System.out.println("zipcode:" + zipcode_db);
				System.out.println("telephone:" + telephone_db);

			}
			
			// set attribute for jsp
			request.setAttribute("firstname", firstname_db);
			request.setAttribute("lastname", lastname_db);
			request.setAttribute("sex", sex_db);
			request.setAttribute("email", email_db);
			request.setAttribute("dob", dob_db);
			request.setAttribute("address", address_db);
			request.setAttribute("city", city_db);
			request.setAttribute("state", state_db);
			request.setAttribute("zipcode", zipcode_db);
			request.setAttribute("telephone", telephone_db);
			// other characteristics!!! preference
			
			// redirect webpage printwriter
			System.out.println("Show profile!");
			request.getRequestDispatcher("jsp/Login/profile.jsp").forward(request,response);
				
			// close connect
			conn.close();
		}
		catch (Exception e) {
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
