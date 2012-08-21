package sip;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InvitationListS extends HttpServlet {

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
		
		System.out.println("-----sip/InvitationListS.java | SIP/invitationlist.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String siprequestinvitationid_choose1 = request.getParameter("siprequestinvitationid_choose");
			int siprequestinvitationid_choose = Integer.parseInt(siprequestinvitationid_choose1);
			System.out.println("siprequestinvitationid_choose: " + siprequestinvitationid_choose);
			
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
			// querry1: delete SIPREQUESTINVITATION
			Statement stmt1 = conn.createStatement();
			String query1 = "DELETE FROM SIPREQUESTINVITATION WHERE (SIPREQUESTINVITATIONID = '" + siprequestinvitationid_choose + "')";
			System.out.println(query1);
			stmt1.executeUpdate(query1);
			
			// redirect webpage
				System.out.println("Delete sip invitation request successfully!");
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
