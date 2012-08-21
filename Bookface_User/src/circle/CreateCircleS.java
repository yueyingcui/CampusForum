package circle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateCircleS extends HttpServlet {

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

		System.out.println("-----circle/CreateCircleS.java | Circle/createcircle.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String circlename = request.getParameter("circlename");
			System.out.println("circlename: " + circlename);
			// assign hostid
			int hostid = 0;
			HttpSession session = request.getSession();
			hostid = (Integer)session.getAttribute("hostid");
			int ownerid = hostid;
			System.out.println("ownerid: " + ownerid);
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//	DB2 information: insert CIRCLE
			// get primary key +1
			Statement stmt = conn.createStatement();
			String query1 = "SELECT CIRCLEID FROM CIRCLE";
			System.out.println(query1);
			ResultSet rs1 = stmt.executeQuery(query1);
			int circleid_db = 0;
			while(rs1.next())
			{
				circleid_db = rs1.getInt(1);
			}
			circleid_db = circleid_db + 1;
			// insert
			String query2 = "INSERT INTO CIRCLE (CIRCLEID, CIRCLENAME, OWNERID) VALUES ('" + circleid_db + "', '" + circlename + "', '" + ownerid + "')";
			System.out.println(query2);
			stmt.executeUpdate(query2);

			
			// redirect webpage
				System.out.println("Cirle has been created successfully!");
				response.sendRedirect("jsp/Circle/circle.jsp");
				
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
