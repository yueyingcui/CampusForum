package sip;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateInvitationS extends HttpServlet {

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

		System.out.println("-----sip/CreateInvitationS.java | SIP/createinvitation.jsp-----");
		try{
			// JSP information
			// sipid
			response.setContentType("text/html; charset=gbk"); 
			String sipid1 = request.getParameter("sipid");
			int sipid = Integer.parseInt(sipid1);
			System.out.println("sipid: " + sipid1);
			// receiver
			String receiverid_array[] = request.getParameterValues("receiverid");
			System.out.println("receiverid_array.length: " + receiverid_array.length);
			// content
			String content = request.getParameter("content");
			System.out.println(content);
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//	DB2 information
			// initial value
			int siprequestinvitationid_db = 0;
			int receiverid_db = 0;
			String receiverid1_db = "";
			// assign hostid
			int senderid = 0;
			HttpSession session = request.getSession();
			senderid = (Integer)session.getAttribute("hostid");
			// querry1: insert SIPREQUESTINVITATION: other receivers
			for(int i = 0; i < receiverid_array.length; i++)
			{
				// get primary key +1
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT SIPREQUESTINVITATIONID FROM SIPREQUESTINVITATION";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					siprequestinvitationid_db = rs1.getInt(1);
				}
				siprequestinvitationid_db = siprequestinvitationid_db + 1;
				// (querry2)
				receiverid1_db = (String)receiverid_array[i];
				receiverid_db = Integer.parseInt(receiverid1_db);
				String query2 = "INSERT INTO SIPREQUESTINVITATION (SIPREQUESTINVITATIONID, SENDERID, SIPID, RECEIVERID, CONTENT) VALUES ('" + siprequestinvitationid_db + "', '" + senderid + "', '" + sipid + "', '" + receiverid_db + "', '" + content + "')";
				System.out.println(query2);
				stmt1.executeUpdate(query2);
			}
								
			// redirect webpage
				System.out.println("Create sip invitation successfully!");
				response.sendRedirect("jsp/SIP/sip.jsp");
				
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
