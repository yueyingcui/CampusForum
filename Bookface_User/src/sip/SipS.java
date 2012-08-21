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

public class SipS extends HttpServlet {

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

		System.out.println("-----sip/SipS.java | SIP/sip.jsp-----");
		
		// choose "submit" 
		String submit_createsip = request.getParameter("createsip");
		String submit_searchsip = request.getParameter("searchsip");
		String submit_siplist = request.getParameter("siplist");
		String submit_confirmsip = request.getParameter("confirmsip");
		
		if(submit_createsip != null)
		{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 	
				
			// redirect webpage
			System.out.println("Go to create a sip!");
			response.sendRedirect("jsp/SIP/createsip.jsp");
		}
		
		if(submit_searchsip != null)
		{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 	
				
			// redirect webpage
			System.out.println("Go to search a sip!");
			response.sendRedirect("jsp/SIP/searchsip.jsp");
		}
		
		if(submit_siplist != null)
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
				// select SIPMEMBER, save select result
				// querry1: sipid
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT SIPID FROM SIPMEMBER WHERE MEMBERID = '" + hostid + "'";
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
				request.getRequestDispatcher("jsp/SIP/siplist.jsp").forward(request,response);
					
				// close connect
				conn.close();
			}
			catch (Exception e) {
			e.printStackTrace();}
		}
		
		if(submit_confirmsip != null)
		{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 	
				
			// redirect webpage
			System.out.println("Go to confirm sip!");
			response.sendRedirect("jsp/SIP/confirmsip.jsp");
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
