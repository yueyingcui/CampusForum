package advt;

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

public class AdvtS extends HttpServlet {

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

		System.out.println("-----advt/AdvtS.java | Advt/advt.jsp-----");
		
		// choose "submit" 
		String submit_setpreference = request.getParameter("setpreference");
		String submit_typelist = request.getParameter("typelist");
		
		if(submit_setpreference != null)
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
				String type_db = "";
				String type_exist = "false";
				ArrayList<String> type_list = new ArrayList<String>();
				// select ADVERTISEMENT, save select result
				// querry1: type
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT TYPE FROM ADVERTISEMENT";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					type_db = rs1.getString(1).trim();
					
					// add into list
					for(int i = 0; i < type_list.size(); i++)
					{
						if(type_db.equals(type_list.get(i)))
						{
							type_exist = "true";
							break;
						}
						else
							type_exist = "false";
					}
					
					if(type_exist.equals("false"))
					{
						type_list.add(type_db);
						System.out.println("type:" + type_db);
					}
				}
				
				// set attribute for jsp
				request.setAttribute("type_list", type_list);
				System.out.println("type_list.size()=" + type_list.size());
				// other characteristics!!!
				
				// redirect webpage printwriter
				//if(friendid_list.size() == 0 || subjectid_list.size() == 0)
				if(type_list.size() == 0)
				{
					System.out.println("No type!");
				}
				else
				{
					System.out.println("Go to set user's preference!");
					request.getRequestDispatcher("jsp/Advt/setpreference.jsp").forward(request,response);
				}
				// close connect
				conn.close();
			}
			catch (Exception e) {
			e.printStackTrace();}
		}
		
		if(submit_typelist != null) //type = userpreference.preference
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
				String preference_db = "";
				ArrayList<String> preference_list = new ArrayList<String>();
				// assign hostid
				int hostid = 0;
				HttpSession session = request.getSession();
				hostid = (Integer)session.getAttribute("hostid");
				// select USERPREFERENCE, save select result
				// querry1: preference
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT PREFERENCE FROM USERPREFERENCE WHERE USERID = '" + hostid + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					preference_db = rs1.getString(1);
					
					// add into list
					preference_list.add("" + preference_db);
				}
				
				// set attribute for jsp
				request.setAttribute("type_list", preference_list);
				System.out.println("preference_list.size()=" + preference_list.size());
				// other characteristics!!!
				
				// redirect webpage printwriter
				if(preference_list.size() == 0)
				{
					System.out.println("No preference!");
				}
				else
				{
					System.out.println("Show preferences!");
				}
				request.getRequestDispatcher("jsp/Advt/typelist.jsp").forward(request,response);
					
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
