package sip;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SipListS extends HttpServlet {

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

		System.out.println("-----sip/SipListS.java | SIP/siplist.jsp-----");
		
		// choose "submit" 
		String submit_createpost = request.getParameter("createpost");
		String submit_postlist = request.getParameter("postlist");
		String submit_appmoderator = request.getParameter("appmoderator");
		
		if(submit_createpost != null)
		{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String sipid_choose1 = request.getParameter("sipid_choose");
			int sipid_choose = Integer.parseInt(sipid_choose1);
			System.out.println("sipid_choose: " + sipid_choose);
						
			// set Attibute
			request.setAttribute("sipid_choose", sipid_choose1);	
				
			// redirect webpage
			System.out.println("Go to create a post!");
			request.getRequestDispatcher("jsp/SIP/createpost.jsp").forward(request,response);
		}
		
		if(submit_postlist != null)
		{
			try{
				// JSP information
				response.setContentType("text/html; charset=gbk"); 
				String sipid_choose1 = request.getParameter("sipid_choose");
				int sipid_choose = Integer.parseInt(sipid_choose1);
				System.out.println("sipid_choose: " + sipid_choose);
				
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
				int sippostid_db = 0;
				int post_ownerid_db = 0;
				String post_owner_firstname_db = "";
				String post_owner_lastname_db = "";
				String content_db = "";
				java.sql.Timestamp datetime_db = new Timestamp(java.sql.Types.TIMESTAMP);
				ArrayList sippostid_list = new ArrayList();
				ArrayList<String> post_owner_firstname_list = new ArrayList<String>();
				ArrayList<String> post_owner_lastname_list = new ArrayList<String>();
				ArrayList<String> content_list = new ArrayList<String>();
				ArrayList<String> datetime_list = new ArrayList<String>();
				// assign hostid
				int hostid = 0;
				HttpSession session = request.getSession();
				hostid = (Integer)session.getAttribute("hostid");
				// select SIPPOST, save select result
				// querry1: sippostid
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT SIPPOSTID, OWNERID, CONTENT, DATETIME FROM SIPPOST WHERE SIPID = '" + sipid_choose + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					sippostid_db = rs1.getInt(1);
					post_ownerid_db = rs1.getInt(2);
					content_db = rs1.getString(3);
					datetime_db = rs1.getTimestamp(4);
					
					// add to list
					sippostid_list.add("" + sippostid_db);
					content_list.add(content_db);
					datetime_list.add("" + datetime_db);

					// select USER, save select result
					// querry2: firstname, lastname
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT FIRSTNAME, LASTNAME FROM USER WHERE USERID = '" + post_ownerid_db + "'";
					System.out.println(query2);
					ResultSet rs2 = stmt2.executeQuery(query2);
					while(rs2.next())
					{
						// assign value
						post_owner_firstname_db = rs2.getString(1);
						post_owner_lastname_db = rs2.getString(2);
					
						// add into list
						post_owner_firstname_list.add("" + post_owner_firstname_db);
						post_owner_lastname_list.add("" + post_owner_lastname_db);
					}
}
				
				// set attribute for jsp
				request.setAttribute("sippostid_list", sippostid_list);
				request.setAttribute("content_list", content_list);
				request.setAttribute("datetime_list", datetime_list);
				request.setAttribute("post_owner_firstname_list", post_owner_lastname_list);
				request.setAttribute("post_owner_lastname_list", post_owner_lastname_list);
				System.out.println("sippostid_list.size()=" + sippostid_list.size());
				System.out.println("content_list.size()=" + content_list.size());
				System.out.println("datetime_list.size()=" + datetime_list.size());
				System.out.println("post_owner_firstname_list.size()=" + post_owner_firstname_list.size());
				System.out.println("post_owner_lastname_list.size()=" + post_owner_lastname_list.size());
				// other characteristics!!!
				
				// redirect webpage printwriter
				if(sippostid_list.size() == 0)
				{
					System.out.println("No post!");
				}
				else
				{
					System.out.println("Show posts!");
				}
				request.getRequestDispatcher("jsp/SIP/postlist.jsp").forward(request,response);
					
				// close connect
				conn.close();
			}
			catch (Exception e) {
			e.printStackTrace();}
		}
		
		if(submit_appmoderator != null)
		{
			try{
				// JSP information
				response.setContentType("text/html; charset=gbk"); 
				String sipid_choose1 = request.getParameter("sipid_choose");
				System.out.println("sipid_choose1.length=" + sipid_choose1.length());
				System.out.println("sipid_choose: " + sipid_choose1);
				int sipid_choose = Integer.parseInt(sipid_choose1);
				
				// connect to DB2
				Connection conn = null;
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
				String user = "cseteam49";
				String pass = "Spring2012";
				conn = DriverManager.getConnection(url,user,pass);
				System.out.println("Connected and working!");
			
				//	DB2 information: 
				// assign hostid
				int hostid = 0;
				HttpSession session = request.getSession();
				hostid = (Integer)session.getAttribute("hostid");
				// querry1: insert SIPREQUESTMODERATOR
				// get primary key +1
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT SIPREQUESTMODERATORID FROM SIPREQUESTMODERATOR";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				int siprequestmoderatorid_db = 0;
				while(rs1.next())
				{
					siprequestmoderatorid_db = rs1.getInt(1);
				}
				siprequestmoderatorid_db = siprequestmoderatorid_db + 1;
				// (querry2)
				String query2 = "INSERT INTO SIPREQUESTMODERATOR (SIPREQUESTMODERATORID, SIPID, SENDERID) VALUES ('" + siprequestmoderatorid_db + "', '" + sipid_choose + "', '" + hostid + "')";
				System.out.println(query2);
				stmt1.executeUpdate(query2);

				
				// redirect webpage
					System.out.println("App moderator request has been sent successfully!");
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
