package friend;

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

public class FriendSConfirmFriendS extends HttpServlet {

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

		System.out.println("-----friend/FriendS_ConfirmFriendS.java | Friend/friend.jsp -> Friend/confirmfriend.jsp-----");
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
			int senderid_db = 0;
			String sender_firstname_db = "";
			String sender_lastname_db = "";
			ArrayList<String> senderid_list = new ArrayList<String>();
			ArrayList<String> sender_firstname_list = new ArrayList<String>();
			ArrayList<String> sender_lastname_list = new ArrayList<String>();
			// assign hostid
			HttpSession session = request.getSession();
			hostid = (Integer)session.getAttribute("hostid");
			// select SENDERID, save selecct result
			// querry1: senderid
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT * FROM FRIENDREQUEST WHERE RECEIVERID = '" + hostid + "'";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);
			while(rs1.next())
			{
				// assign value
				senderid_db = rs1.getInt(2);
				
				// add into list
				System.out.println("senderid:" + senderid_db);
				senderid_list.add("" + senderid_db);
			
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
			}
			
			// set attribute for jsp
			request.setAttribute("senderid_list", senderid_list);
			request.setAttribute("sender_firstname_list", sender_firstname_list);
			request.setAttribute("sender_lastname_list", sender_lastname_list);
			System.out.println("senderid_list.size()=" + senderid_list.size());
			System.out.println("firstname_list.size()=" + sender_firstname_list.size());
			System.out.println("lastname_list.size()=" + sender_lastname_list.size());
			// other characteristics!!!
			
			//String news = "";
			//request.setAttribute("news", news);
			// redirect webpage printwriter
			if(senderid_list.size() == 0)
			{
				System.out.println("No news!");
				//news = "false";
			}
			else
			{
				System.out.println("New requests!");
				//news = "true";
			}
			request.getRequestDispatcher("jsp/Friend/confirmfriend.jsp").forward(request,response);
				
			// close connect
			conn.close();
		}
		catch (Exception e) {
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
