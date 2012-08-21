package sip;

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

public class AppModeratorListS extends HttpServlet {

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

		System.out.println("-----sip/AppModeratorListS.java | SIP/appmoderatorlist.jsp-----");
		
		// choose "submit" 
		String submit_accept = request.getParameter("accept");
		String submit_deny = request.getParameter("deny");
		
		if(submit_accept != null)
		{
			try{
				// JSP information
				response.setContentType("text/html; charset=gbk"); 
				String sipid_choose1 = request.getParameter("sipid_choose");
				int sipid_choose = Integer.parseInt(sipid_choose1);
				System.out.println("sipid_choose: " + sipid_choose);
				String senderid_choose1 = request.getParameter("senderid_choose");
				int senderid_choose = Integer.parseInt(senderid_choose1);
				System.out.println("senderid_choose: " + senderid_choose);
				
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
				// querry1: insert SIPMODERATOR
				// get primary key +1
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT SIPMODERATORID FROM SIPMODERATOR";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				int sipmoderatorid_db = 0;
				while(rs1.next())
				{
					sipmoderatorid_db = rs1.getInt(1);
				}
				sipmoderatorid_db = sipmoderatorid_db + 1;
				// insert (querry2)
				String query2 = "INSERT INTO SIPMODERATOR (SIPMODERATORID, SIPID, MODERATORID) VALUES ('" + sipmoderatorid_db + "', '" + sipid_choose + "', '" + senderid_choose + "')";
				System.out.println(query2);
				stmt1.executeUpdate(query2);
				// querry3: delete SIPREQUESTMODERATOR
				Statement stmt2 = conn.createStatement();
				String query3 = "DELETE FROM SIPREQUESTMODERATOR WHERE (SENDERID = '" + senderid_choose + "' AND SIPID = '" + sipid_choose + "')";
				System.out.println(query3);
				stmt2.executeUpdate(query3);
				
				// redirect webpage
					System.out.println("Accept sip moderator request successfully!");
					response.sendRedirect("jsp/SIP/sip.jsp");
					
				// close connect
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();}
		}
		
		if(submit_deny != null)
		{
			try{
				// JSP information
				response.setContentType("text/html; charset=gbk"); 
				String sipid_choose1 = request.getParameter("sipid_choose");
				int sipid_choose = Integer.parseInt(sipid_choose1);
				System.out.println("sipid_choose: " + sipid_choose);
				String senderid_choose1 = request.getParameter("senderid_choose");
				int senderid_choose = Integer.parseInt(senderid_choose1);
				System.out.println("senderid_choose: " + senderid_choose);
				
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
				// querry1: delete SIPREQUESTMODERATOR
				Statement stmt1 = conn.createStatement();
				String query1 = "DELETE FROM SIPREQUESTMODERATOR WHERE (SENDERID = '" + senderid_choose + "' AND SIPID = '" + sipid_choose + "')";
				System.out.println(query1);
				stmt1.executeUpdate(query1);
				
				// redirect webpage
					System.out.println("Deny sip moderator request successfully!");
					response.sendRedirect("jsp/SIP/sip.jsp");
					
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
