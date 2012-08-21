package circle;

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

public class CircleListS extends HttpServlet {

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

		System.out.println("-----circle/CircleListS.java | Circle/circlelist.jsp -> Circle/searchresultcirclefriend.jsp-----");
		
		// choose "submit" 
		String submit_whos = request.getParameter("whos");
		String submit_dropcircle = request.getParameter("dropcircle");
		
		if(submit_whos != null)
		{
			try{
				// JSP information
				response.setContentType("text/html; charset=gbk"); 
				String circleid1 = request.getParameter("circleid_choose");
				int circleid = Integer.parseInt(circleid1)+1; // note: +1
				String circleid_string = Integer.toString(circleid);
				System.out.println("circleid: " + circleid);
				
				// connect to DB2
				Connection conn = null;
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
				String user = "cseteam49";
				String pass = "Spring2012";
				conn = DriverManager.getConnection(url,user,pass);
				System.out.println("Connected and working!");
				
				// DB2 information
				// initial value
				int circlemember_userid_db = 0;
				String circlemember_firstname_db = "";
				String circlemember_lastname_db = "";
				ArrayList<String> circlemember_userid_list = new ArrayList<String>();
				ArrayList<String> circlemember_firstname_list = new ArrayList<String>();
				ArrayList<String> circlemember_lastname_list = new ArrayList<String>();
				// select CIRCLEMEMBER USER, save selecct result
				// querry1: circlemember_userid_db
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT CIRCLEMEMBERUSERID FROM CIRCLEMEMBER WHERE CIRCLEID = '" + circleid + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					circlemember_userid_db = rs1.getInt(1);
					
					// add to ArrayList
					circlemember_userid_list.add("" + circlemember_userid_db);
				
					//querry2: circlemember_firstname_db, circlemember_lastname_db
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT FIRSTNAME, LASTNAME FROM USER WHERE USERID = '" + circlemember_userid_db + "'";
					System.out.println(query2);
					ResultSet rs2 = stmt2.executeQuery(query2);
					while(rs2.next())
					{
						// assign value
						circlemember_firstname_db = rs2.getString(1);
						circlemember_lastname_db = rs2.getString(2);
					}
				
					// add to ArrayList
					circlemember_firstname_list.add(circlemember_firstname_db);
					circlemember_lastname_list.add(circlemember_lastname_db);
				}
			
				// set attribute for jsp
				request.setAttribute("circlemember_userid_list", circlemember_userid_list);
				request.setAttribute("circlemember_firstname_list", circlemember_firstname_list);
				request.setAttribute("circlemember_lastname_list", circlemember_lastname_list);
				System.out.println("circlemember_firstname_list.size()=" + circlemember_firstname_list.size());
				System.out.println("circlemember_lastname_list.size()=" + circlemember_lastname_list.size());
				request.setAttribute("circleid", circleid_string);
				System.out.println("circleid:" + circleid);
				// other characteristics!!!
			
				// redirect webpage printwriter
				if(circlemember_firstname_list.size() == 0)
				{
					System.out.println("No friend in this circle!");
				}
				else
				{
					System.out.println("Show friends in this circle!");
				}
				request.getRequestDispatcher("jsp/Circle/searchresultcirclefriend.jsp").forward(request,response);
				
				// close connect
				conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();}
		}
		
		if(submit_dropcircle != null)
		{
			try{
				// JSP information
				// senderid
				response.setContentType("text/html; charset=gbk"); 
				String circleid1 = request.getParameter("circleid");
				int circleid = Integer.parseInt(circleid1); // note: +1!!!
				System.out.println("circleid: " + circleid);
				
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
				// querry1: delete CIRCLEMEMBER
				Statement stmt1 = conn.createStatement();
				String query1 = "DELETE FROM CIRCLEMEMBER WHERE (CIRCLEID = '" + circleid + "')";
				System.out.println(query1);
				stmt1.executeUpdate(query1);
				// querry2: delete CIRCLE
				Statement stmt2 = conn.createStatement();
				String query2 = "DELETE FROM CIRCLE WHERE (CIRCLEID = '" + circleid + "')";
				System.out.println(query2);
				stmt2.executeUpdate(query2);
				
				// redirect webpage
					System.out.println("Delete cicle successfully!");
					response.sendRedirect("jsp/Circle/circle.jsp");
					
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
