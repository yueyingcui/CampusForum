package sip;

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

public class ConfirmSipS extends HttpServlet {

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

		System.out.println("-----sip/ConfirmSipS.java | SIP/confirmsip.jsp-----");
		
		// choose "submit" 
		String submit_confirmmoderatortask = request.getParameter("confirmmoderatortask");
		String submit_confirminvitation = request.getParameter("confirminvitation");
		
		if(submit_confirmmoderatortask != null)
		{
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
				int sipid_db = 0;
				String sipname_db = "";
				ArrayList sipid_list = new ArrayList();
				ArrayList<String> sipname_list = new ArrayList<String>();
				// assign hostid
				int hostid = 0;
				HttpSession session = request.getSession();
				hostid = (Integer)session.getAttribute("hostid");
				// select SIPMODERATOR, save select result
				// querry1: sipid
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT SIPID FROM SIPMODERATOR WHERE MODERATORID = '" + hostid + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					sipid_db = rs1.getInt(1);
					
					// add into list
					sipid_list.add("" + sipid_db);

					// select SIP, save select result
					// querry2: sipname
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT SIPNAME FROM SIP WHERE SIPID = '" + sipid_db + "'";
					System.out.println(query2);
					ResultSet rs2 = stmt2.executeQuery(query2);
					while(rs2.next())
					{
						// assign value
						sipname_db = rs2.getString(1);
						System.out.println("sipname: " + sipname_db);
					
						// add into list
						sipname_list.add("" + sipname_db);
						
					}
				}
				
				// set attribute for jsp
				request.setAttribute("sipid_list", sipid_list);
				request.setAttribute("sipname_list", sipname_list);
				System.out.println("sipid_list.size()=" + sipid_list.size());
				System.out.println("sipname_list.size()=" + sipname_list.size());
				// other characteristics!!!
				
				// redirect webpage printwriter
				if(sipid_list.size() == 0)
				{
					System.out.println("No SIP!");
				}
				else
				{
					System.out.println("Show SIPs!");
				}
				request.getRequestDispatcher("jsp/SIP/siplistmoderator.jsp").forward(request,response);
					
				// close connect
				conn.close();
			}
			catch (Exception e) {
			e.printStackTrace();}
		}
		
		if(submit_confirminvitation != null)
		{
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
				int siprequestinvitationid_db = 0;
				int senderid_db = 0;
				String sender_firstname_db = "";
				String sender_lastname_db = "";
				int sipid_db = 0;
				String sipname_db = "";
				String content_db = "";
				ArrayList<String> sender_firstname_list = new ArrayList<String>();
				ArrayList<String> sender_lastname_list = new ArrayList<String>();
				ArrayList<String> sipname_list = new ArrayList<String>();
				ArrayList<String> content_list = new ArrayList<String>();
				ArrayList siprequestinvitationid_list = new ArrayList();
				// assign hostid
				HttpSession session = request.getSession();
				hostid = (Integer)session.getAttribute("hostid");
				// select SIPREQUESTINVITATION, save selecct result
				// querry1: senderid sipid, content
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT SIPREQUESTINVITATIONID, SENDERID, SIPID, CONTENT FROM SIPREQUESTINVITATION WHERE RECEIVERID = '" + hostid + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					siprequestinvitationid_db = rs1.getInt(1);
					senderid_db = rs1.getInt(2);
					sipid_db = rs1.getInt(3);
					content_db = rs1.getString(4);
					
					// add into list
					content_list.add(content_db);
					siprequestinvitationid_list.add("" + siprequestinvitationid_db);
				
					// querry2: sender_firstname, sender_lastname
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
						System.out.println("sender_firstname:" + sender_firstname_db);
						System.out.println("sender_lastname:" + sender_lastname_db);
						sender_firstname_list.add("" + sender_firstname_db);
						sender_lastname_list.add("" + sender_lastname_db);				
					}
					
					// querry3: select SIP, sipname
					Statement stmt3 = conn.createStatement();
					String query3 = "SELECT SIPNAME FROM SIP WHERE SIPID = '" + sipid_db + "'";
					System.out.println(query3);
					ResultSet rs3 = stmt2.executeQuery(query3);
					while(rs3.next())
					{
						// assign value
						sipname_db = rs3.getString(1);
					
						// add into list
						sipname_list.add(sipname_db);			
					}
				}
				
				
				// set attribute for jsp
				request.setAttribute("sipname_list", sipname_list);
				request.setAttribute("sender_firstname_list", sender_firstname_list);
				request.setAttribute("sender_lastname_list", sender_lastname_list);
				request.setAttribute("content_list", content_list);		
				request.setAttribute("siprequestinvitationid_list", siprequestinvitationid_list);	
				System.out.println("sipname_list.size()=" + sipname_list.size());
				System.out.println("firstname_list.size()=" + sender_firstname_list.size());
				System.out.println("lastname_list.size()=" + sender_lastname_list.size());
				System.out.println("content_list.size()=" + content_list.size());
				System.out.println("siprequestinvitationid_list.size()=" + siprequestinvitationid_list.size());
				// other characteristics!!!
				
				//String news = "";
				//request.setAttribute("news", news);
				// redirect webpage printwriter
				if(sipname_list.size() == 0)
				{
					System.out.println("No invitation!");
					//news = "false";
				}
				else
				{
					System.out.println("New invitations!");
					//news = "true";
				}
				request.getRequestDispatcher("jsp/SIP/invitationlist.jsp").forward(request,response);
					
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
