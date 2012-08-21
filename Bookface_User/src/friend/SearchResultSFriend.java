package friend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchResultSFriend extends HttpServlet {

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

		System.out.println("-----friend/SearchResultS.java | Friend/searchresult.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String receiverid1 = request.getParameter("userid_choose");
			System.out.println("receiverid1.length=" + receiverid1.length());
			System.out.println("receiverid(string): " + receiverid1);
			int receiverid = Integer.parseInt(receiverid1);
			System.out.println("receiverid(int): " + receiverid);
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//	DB2 information: insert FRIENDREQUEST
			// assign hostid
			int hostid = 0;
			HttpSession session = request.getSession();
			hostid = (Integer)session.getAttribute("hostid");
			int senderid = hostid;
			System.out.println("senderid(int): " + senderid);
			// get primary key +1
			Statement stmt = conn.createStatement();
			String query1 = "SELECT FRIENDREQUESTID FROM FRIENDREQUEST";
			System.out.println(query1);
			ResultSet rs1 = stmt.executeQuery(query1);
			int friendrequestid_db = 0;
			while(rs1.next())
			{
				friendrequestid_db = rs1.getInt(1);
			}
			friendrequestid_db = friendrequestid_db + 1;
			// insert
			String query2 = "INSERT INTO FRIENDREQUEST (FRIENDREQUESTID, SENDERID, RECEIVERID) VALUES ('" + friendrequestid_db + "', '" + senderid + "', '" + receiverid + "')";
			System.out.println(query2);
			stmt.executeUpdate(query2);

			
			// redirect webpage
				System.out.println("Add request has been sent successfully!");
				response.sendRedirect("jsp/Friend/sendrequest.jsp");
				
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
