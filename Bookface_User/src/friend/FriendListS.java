package friend;

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

public class FriendListS extends HttpServlet {

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

		System.out.println("-----friend/FriendListS.java | Friend/friendlist.jsp-----");
		
		// choose "submit" 
		String submit_addtocircle = request.getParameter("addtocircle");
		String submit_unfriend = request.getParameter("unfriend");
		
		if(submit_addtocircle != null)
		{
			try{
				// JSP information
				// friendid
				response.setContentType("text/html; charset=gbk"); 
				String friendid1 = request.getParameter("friendid_choose");
				int friendid = Integer.parseInt(friendid1);
				System.out.println("friendid: " + friendid);
				String circleid1 = request.getParameter("circleid_choose");
				int circleid = Integer.parseInt(circleid1);
				System.out.println("circleid: " + circleid);
				
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
				// querry1: insert CIRCLEMEMBER
				// get primary key +1
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT CIRCLEMEMBERID FROM CIRCLEMEMBER";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				int circlememberid_db = 0;
				while(rs1.next())
				{
					circlememberid_db = rs1.getInt(1);
				}
				circlememberid_db = circlememberid_db + 1;
				// insert (querry2)
				String query2 = "INSERT INTO CIRCLEMEMBER (CIRCLEMEMBERID, CIRCLEID, CIRCLEMEMBERUSERID) VALUES ('" + circlememberid_db + "', '" + circleid + "', '" + friendid + "')";
				System.out.println(query2);
				stmt1.executeUpdate(query2);
			
				// redirect webpage
				System.out.println("Add friend to circle successfully!");
				response.sendRedirect("jsp/Friend/friend.jsp");
				
				// close connect
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();}
		}
		
		if(submit_unfriend != null)
		{
			try{
				// JSP information
				// senderid
				response.setContentType("text/html; charset=gbk"); 
				String unfriendid_choose1 = request.getParameter("friendid_choose");
				int unfriendid_choose = Integer.parseInt(unfriendid_choose1);
				System.out.println("unfriendid_choose: " + unfriendid_choose);
				
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
				// querry1: delete CIRCLEMEMBER
				Statement stmt1 = conn.createStatement();
				String query1 = "DELETE FROM CIRCLEMEMBER WHERE (CIRCLEMEMBERUSERID = '" + unfriendid_choose + "')";
				System.out.println(query1);
				stmt1.executeUpdate(query1);
				// querry2: delete FRIEND
				Statement stmt2 = conn.createStatement();
				String query2 = "DELETE FROM FRIEND WHERE (OWNERFRIENDID = '" + unfriendid_choose + "')";
				System.out.println(query2);
				stmt2.executeUpdate(query2);
				
				// redirect webpage
					System.out.println("Delete friend successfully!");
					response.sendRedirect("jsp/Friend/friend.jsp");
					
				// close connect
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();}
		}
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
