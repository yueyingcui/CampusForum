package advt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetPreferenceS extends HttpServlet {

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

		System.out.println("-----advt/SetPreferenceS.java | Advt/setpreference.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk");
			String type = "";
			// type
			String type_array[] = request.getParameterValues("type");
			System.out.println("type_array.length: " + type_array.length);
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//	DB2 information
			// initial value
			int userpreferenceid_db = 0;
			// assign hostid
			int hostid = 0;
			HttpSession session = request.getSession();
			hostid = (Integer)session.getAttribute("hostid");
			// querry0: TRUNCATE TABLE
			Statement stmt0 = conn.createStatement();
			String query0 = "DELETE FROM USERPREFERENCE WHERE USERID = '" + hostid + "'";
			System.out.println(query0);
			stmt0.executeUpdate(query0);
			// querry1: insert USERPREFERENCE
			// get primary key +1
			for(int i = 0; i < type_array.length; i++)
			{
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT USERPREFERENCEID FROM USERPREFERENCE";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					userpreferenceid_db = rs1.getInt(1);
				}
				userpreferenceid_db = userpreferenceid_db + 1;
				// (querry2)
				String query2 = "INSERT INTO USERPREFERENCE (USERPREFERENCEID, USERID, PREFERENCE) VALUES ('" + userpreferenceid_db + "', '" + hostid + "', '" + type_array[i] + "')";
				System.out.println(query2);
				stmt1.executeUpdate(query2);
		}
								
			// redirect webpage
				System.out.println("Set preference successfully!");
				response.sendRedirect("jsp/Advt/advt.jsp");
				
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
