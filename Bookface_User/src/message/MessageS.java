package message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MessageS extends HttpServlet {

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

		System.out.println("-----message/MessageS.java | Message/message.jsp-----");
		
		// choose "submit" 
		String submit_createmessage = request.getParameter("createmessage");
		String submit_subjectlist = request.getParameter("subjectlist");
		
		if(submit_createmessage != null)
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
				int friendid_db = 0;
				String friend_firstname_db = "";
				String friend_lastname_db = "";
				ArrayList<String> friendid_list = new ArrayList<String>();
				ArrayList<String> friend_firstname_list = new ArrayList<String>();
				ArrayList<String> friend_lastname_list = new ArrayList<String>();
				int subjectid_db = 0;
				String subject_name_db = "";
				ArrayList subjectid_list = new ArrayList();
				ArrayList<String> subject_name_list = new ArrayList<String>();
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
				//querry3: select SUBJECT
				Statement stmt3 = conn.createStatement();
				String query3 = "SELECT * FROM SUBJECT";
				System.out.println(query3);
				ResultSet rs3 = stmt3.executeQuery(query3);
				while(rs3.next())
				{
					// assign value
					subjectid_db = rs3.getInt(1);
					subject_name_db = rs3.getString(2);
					
					// add into list
					System.out.println("subjectid:" + subjectid_db);
					subjectid_list.add("" + subjectid_db);
					System.out.println("subject_name:" + subject_name_db);
					subject_name_list.add("" + subject_name_db);
				}
				
				// set attribute for jsp
				request.setAttribute("friendid_list", friendid_list);
				request.setAttribute("friend_firstname_list", friend_firstname_list);
				request.setAttribute("friend_lastname_list", friend_lastname_list);
				System.out.println("friendid_list.size()=" + friendid_list.size());
				System.out.println("friend_firstname_list.size()=" + friend_firstname_list.size());
				System.out.println("friend_lastname_list.size()=" + friend_lastname_list.size());
				request.setAttribute("subjectid_list", subjectid_list);
				request.setAttribute("subject_name_list", subject_name_list);
				System.out.println("subjectid_list.size()=" + subjectid_list.size());
				System.out.println("subject_name_list.size()=" + subject_name_list.size());
				// other characteristics!!!
				
				// redirect webpage printwriter
				//if(friendid_list.size() == 0 || subjectid_list.size() == 0)
				if(friendid_list.size() == 0)
				{
					System.out.println("No friend or no subject!");
				}
				else
				{
					System.out.println("Go to create a message!");
					request.getRequestDispatcher("jsp/Message/createmessage.jsp").forward(request,response);
				}
				// close connect
				conn.close();
			}
			catch (Exception e) {
			e.printStackTrace();}
		}
		
		if(submit_subjectlist != null)
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
				int subjectid1_db = 0;
				String subjectid_db = "";
				String subjectname_db = "";
				ArrayList subjectid_list = new ArrayList();
				ArrayList<String> subjectname_list = new ArrayList<String>();
				int messageid_db = 0;
				// assign hostid
				int hostid = 0;
				HttpSession session = request.getSession();
				hostid = (Integer)session.getAttribute("hostid");
				// select MESSAGERECEIVER, save select result
				// querry1: messageid
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT MESSAGEID FROM MESSAGERECEIVER WHERE RECEIVERID = '" + hostid + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					messageid_db = rs1.getInt(1);

					// querry2: select MESSAGE
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT SUBJECTID FROM MESSAGE WHERE MESSAGEID = '" + messageid_db + "'";
					System.out.println(query2);
					ResultSet rs2 = stmt2.executeQuery(query2);
					while(rs2.next())
					{
						// assign value
						subjectid1_db = rs2.getInt(1);
						subjectid_db = Integer.toString(subjectid1_db);
						
						// add attribute for jsp
						subjectid_list.add(subjectid_db);
						
						//querry3: select SUBJECT
						Statement stmt3 = conn.createStatement();
						String query3 = "SELECT SUBJECTNAME FROM SUBJECT WHERE SUBJECTID = '" + subjectid_db + "'";
						System.out.println(query3);
						ResultSet rs3 = stmt3.executeQuery(query3);
						while(rs3.next())
						{
							// assign value
							subjectname_db = rs3.getString(1);
							
							// add attribute for jsp
							subjectname_list.add(subjectname_db);
						}
					}
				}
				
				// set attribute for jsp
				request.setAttribute("subjectid_list", subjectid_list);
				request.setAttribute("subjectname_list", subjectname_list);
				System.out.println("subjectid_list.size()=" + subjectid_list.size());
				System.out.println("subjectname_list.size()=" + subjectname_list.size());
				// other characteristics!!!
				
				// redirect webpage printwriter
				if(subjectid_list.size() == 0)
				{
					System.out.println("No message!");
				}
				else
				{
					System.out.println("Show subjects!");
				}
				request.getRequestDispatcher("jsp/Message/subjectlist.jsp").forward(request,response);
					
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
