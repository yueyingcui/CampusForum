package friend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmFriendS extends HttpServlet {

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

		System.out.println("-----friend/ConfirmFriendS.java | Friend/confirmfriend.jsp-----");
		try{
			// JSP information
			// senderid
			response.setContentType("text/html; charset=gbk"); 
			String senderid1 = request.getParameter("userid_choose");
			int senderid = Integer.parseInt(senderid1);
			System.out.println("senderid(int): " + senderid);
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//	DB2 information
			// assign hostid
			int hostid = 0;
			HttpSession session = request.getSession();
			hostid = (Integer)session.getAttribute("hostid");
			// querry1: insert FRIEND
			// get primary key +1
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT FRIENDID FROM FRIEND";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);
			int friendid_db = 0;
			while(rs1.next())
			{
				friendid_db = rs1.getInt(1);
			}
			friendid_db = friendid_db + 1;
			// insert (querry2)
			String query2 = "INSERT INTO FRIEND (FRIENDID, OWNERID, OWNERFRIENDID) VALUES ('" + friendid_db + "', '" + senderid + "', '" + hostid + "')";
			System.out.println(query2);
			stmt1.executeUpdate(query2);
			// insert (querry3)
			String query3 = "INSERT INTO FRIEND (FRIENDID, OWNERID, OWNERFRIENDID) VALUES ('" + friendid_db + "', '" + hostid + "', '" + senderid + "')";
			System.out.println(query3);
			stmt1.executeUpdate(query3);
			// querry4: delete FRIENDREQUEST
			Statement stmt2 = conn.createStatement();
			String query4 = "DELETE FROM FRIENDREQUEST WHERE (SENDERID = '" + senderid + "' AND RECEIVERID = '" + hostid + "')";
			System.out.println(query4);
			stmt2.executeUpdate(query4);
			
			// redirect webpage
				System.out.println("Confirm friend request successfully!");
				response.sendRedirect("jsp/Friend/friend.jsp");
				
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
