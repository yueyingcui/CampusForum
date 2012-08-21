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

public class FriendSFriendListS extends HttpServlet {

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
		
		System.out.println("-----friend/FriendSFriendListS.java | Friend/friend.jsp -> Friend/friendlist.jsp-----");
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
			int circleid_db = 0;
			String circlename_db = "";
			ArrayList<String> circleid_list = new ArrayList<String>();
			ArrayList<String> circlename_list = new ArrayList<String>();
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
			// select Circle, save select result
			// querry3: circleid, circlename
			Statement stmt3 = conn.createStatement();
			String query3 = "SELECT CIRCLEID, CIRCLENAME FROM CIRCLE WHERE OWNERID = '" + hostid + "'";
			System.out.println(query3);
			ResultSet rs3 = stmt3.executeQuery(query3);
			while(rs3.next())
			{
				// assign value
				circleid_db = rs3.getInt(1);
				circlename_db = rs3.getString(2);
				
				// add into list
				System.out.println("circleid:" + circleid_db);
				circleid_list.add("" + circleid_db);
				System.out.println("circlename:" + circlename_db);
				circlename_list.add("" + circlename_db);
			}
			
			// set attribute for jsp
			request.setAttribute("friendid_list", friendid_list);
			request.setAttribute("friend_firstname_list", friend_firstname_list);
			request.setAttribute("friend_lastname_list", friend_lastname_list);
			request.setAttribute("circleid_list", circleid_list);
			request.setAttribute("circlename_list", circlename_list);
			System.out.println("friendid_list.size()=" + friendid_list.size());
			System.out.println("friend_firstname_list.size()=" + friend_firstname_list.size());
			System.out.println("friend_lastname_list.size()=" + friend_lastname_list.size());
			System.out.println("circleid_list.size()=" + circleid_list.size());
			System.out.println("circlename_list.size()=" + circlename_list.size());
			// other characteristics!!!
			
			// redirect webpage printwriter
			if(friendid_list.size() == 0)
			{
				System.out.println("No friend!");
			}
			else
			{
				System.out.println("Show friends!");
			}
			request.getRequestDispatcher("jsp/Friend/friendlist.jsp").forward(request,response);
				
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
