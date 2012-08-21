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

public class SipListModeratorS extends HttpServlet {

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

		System.out.println("-----sip/SipListModeratorS.java | SIP/siplistmoderator.jsp-----");
		
		// choose "submit" 
		String submit_confirmmember = request.getParameter("confirmmember");
		String submit_confirmmoderator = request.getParameter("confirmmoderator");
		String submit_createinvitation = request.getParameter("createinvitation");
		
		if(submit_confirmmember != null)
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
				int senderid_db = 0;
				String sender_firstname_db = "";
				String sender_lastname_db = "";
				ArrayList senderid_list = new ArrayList();
				ArrayList<String> sender_firstname_list = new ArrayList<String>();
				ArrayList<String> sender_lastname_list = new ArrayList<String>();
				// assign hostid
				int hostid = 0;
				HttpSession session = request.getSession();
				hostid = (Integer)session.getAttribute("hostid");
				// select SIPREQUESTMEMBER, save select result
				// querry1: sippostid
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT SENDERID FROM SIPREQUESTMEMBER WHERE SIPID = '" + sipid_choose + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					senderid_db = rs1.getInt(1);
					
					// add to list
					senderid_list.add("" + senderid_db);

					// select USER, save select result
					// querry2: firstname, lastname
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT FIRSTNAME, LASTNAME FROM USER WHERE USERID = '" + senderid_db + "'";
					System.out.println(query2);
					ResultSet rs2 = stmt2.executeQuery(query2);
					while(rs2.next())
					{
						// assign value
						sender_firstname_db = rs2.getString(1);
						sender_lastname_db = rs2.getString(2);
					
						// add into list
						sender_firstname_list.add("" + sender_firstname_db);
						sender_lastname_list.add("" + sender_lastname_db);
					}
}
				
				// set attribute for jsp
				request.setAttribute("sipid_choose", "" + sipid_choose);
				request.setAttribute("senderid_list", senderid_list);
				request.setAttribute("sender_firstname_list", sender_lastname_list);
				request.setAttribute("sender_lastname_list", sender_lastname_list);
				System.out.println("senderid_list.size()=" + senderid_list.size());
				System.out.println("sender_firstname_list.size()=" + sender_firstname_list.size());
				System.out.println("sender_lastname_list.size()=" + sender_lastname_list.size());
				// other characteristics!!!
				
				// redirect webpage printwriter
				if(senderid_list.size() == 0)
				{
					System.out.println("No app member!");
				}
				else
				{
					System.out.println("Show app member!");
				}
				request.getRequestDispatcher("jsp/SIP/appmemberlist.jsp").forward(request,response);
					
				// close connect
				conn.close();
			}
			catch (Exception e) {
			e.printStackTrace();}
		}
		
		if(submit_confirmmoderator != null)
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
				int senderid_db = 0;
				String sender_firstname_db = "";
				String sender_lastname_db = "";
				ArrayList senderid_list = new ArrayList();
				ArrayList<String> sender_firstname_list = new ArrayList<String>();
				ArrayList<String> sender_lastname_list = new ArrayList<String>();
				// assign hostid
				int hostid = 0;
				HttpSession session = request.getSession();
				hostid = (Integer)session.getAttribute("hostid");
				// select SIPREQUESTMODERATOR, save select result
				// querry1: sippostid
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT SENDERID FROM SIPREQUESTMODERATOR WHERE SIPID = '" + sipid_choose + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					senderid_db = rs1.getInt(1);
					
					// add to list
					senderid_list.add("" + senderid_db);

					// select USER, save select result
					// querry2: firstname, lastname
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT FIRSTNAME, LASTNAME FROM USER WHERE USERID = '" + senderid_db + "'";
					System.out.println(query2);
					ResultSet rs2 = stmt2.executeQuery(query2);
					while(rs2.next())
					{
						// assign value
						sender_firstname_db = rs2.getString(1);
						sender_lastname_db = rs2.getString(2);
					
						// add into list
						sender_firstname_list.add("" + sender_firstname_db);
						sender_lastname_list.add("" + sender_lastname_db);
					}
}
				
				// set attribute for jsp
				request.setAttribute("sipid_choose", "" + sipid_choose);
				request.setAttribute("senderid_list", senderid_list);
				request.setAttribute("sender_firstname_list", sender_lastname_list);
				request.setAttribute("sender_lastname_list", sender_lastname_list);
				System.out.println("senderid_list.size()=" + senderid_list.size());
				System.out.println("sender_firstname_list.size()=" + sender_firstname_list.size());
				System.out.println("sender_lastname_list.size()=" + sender_lastname_list.size());
				// other characteristics!!!
				
				// redirect webpage printwriter
				if(senderid_list.size() == 0)
				{
					System.out.println("No app moderator!");
				}
				else
				{
					System.out.println("Show app moderator!");
				}
				request.getRequestDispatcher("jsp/SIP/appmoderatorlist.jsp").forward(request,response);
					
				// close connect
				conn.close();
			}
			catch (Exception e) {
			e.printStackTrace();}
		}
		
		if(submit_createinvitation != null)
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
				int hostid = 0;
				String sipname_db = "";
				int friendid_db = 0;
				String friend_firstname_db = "";
				String friend_lastname_db = "";
				ArrayList<String> friendid_list = new ArrayList<String>();
				ArrayList<String> friend_firstname_list = new ArrayList<String>();
				ArrayList<String> friend_lastname_list = new ArrayList<String>();
				// assign hostid
				HttpSession session = request.getSession();
				hostid = (Integer)session.getAttribute("hostid");
				// select FRIEND, save selecct result
				// querry1: friendid
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT OWNERFRIENDID FROM FRIEND WHERE OWNERID = '" + hostid + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					friendid_db = rs1.getInt(1);
					
					// add into list
					System.out.println("friendid:" + friendid_db);
					friendid_list.add("" + friendid_db);
					
					//querry2: firstname, lastname
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT FIRSTNAME, LASTNAME FROM USER WHERE USERID = '" + friendid_db + "'";
					System.out.println(query2);
					ResultSet rs2 = stmt2.executeQuery(query2);
					while(rs2.next())
					{
						// assign value
						friend_firstname_db = rs2.getString(1);
						friend_lastname_db = rs2.getString(2);
						
						// add into list
						System.out.println("friend_firstname:" + friend_firstname_db);
						friend_firstname_list.add("" + friend_firstname_db);
						System.out.println("friend_lastname:" + friend_lastname_db);
						friend_lastname_list.add("" + friend_lastname_db);
					}					
				}
				//querry3: select SIP
				Statement stmt2 = conn.createStatement();
				String query2 = "SELECT SIPNAME FROM SIP WHERE SIPID = '" + sipid_choose + "'";
				System.out.println(query2);
				ResultSet rs2 = stmt2.executeQuery(query2);
				while(rs2.next())
				{
					// assign value
					sipname_db = rs2.getString(1);
				}
				
				// set attribute for jsp
				request.setAttribute("sipid", "" + sipid_choose);
				request.setAttribute("sipname", sipname_db);
				request.setAttribute("friendid_list", friendid_list);
				request.setAttribute("friend_firstname_list", friend_firstname_list);
				request.setAttribute("friend_lastname_list", friend_lastname_list);
				System.out.println("friendid_list.size()=" + friendid_list.size());
				System.out.println("friend_firstname_list.size()=" + friend_firstname_list.size());
				System.out.println("friend_lastname_list.size()=" + friend_lastname_list.size());
				// other characteristics!!!
				
				// redirect webpage printwriter
				//if(friendid_list.size() == 0 || subjectid_list.size() == 0)
				if(friendid_list.size() == 0)
				{
					System.out.println("No friend!");
				}
				else
				{
					System.out.println("Go to create a sip invitation!");
					request.getRequestDispatcher("jsp/SIP/createinvitation.jsp").forward(request,response);
				}
				// close connect
				conn.close();
			}
			catch (Exception e) {
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
