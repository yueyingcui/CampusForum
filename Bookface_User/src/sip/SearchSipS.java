package sip;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchSipS extends HttpServlet {

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

		System.out.println("-----sip/SearchSipS.java | SIP/searchsip.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String sipname = request.getParameter("sipname");
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//	DB2 information:
			// initial value
			int sipid_db = 0;
			String sipname_db = "";
			ArrayList sipid_list = new ArrayList();
			ArrayList<String> sipname_list = new ArrayList();
			// querry1: select SIP 
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT SIPID, SIPNAME FROM SIP";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);		
			while(rs1.next())
			{
				// assign value
				sipid_db = rs1.getInt(1);
				sipname_db = rs1.getString(2).trim();

				if(sipname.equals(sipname_db))
				{
					System.out.println("Search successed!");
					sipid_list.add("" +sipid_db);
					sipname_list.add(sipname_db);
				}
				else
				{
					System.out.println("Search failed!");
					sipid_list.add(""+0);
					sipname_list.add("");
					
					// print search failed!!!
					response.sendRedirect("jsp/SIP/sip.jsp");
				}
			}
			
			// set attribute for jsp
			request.setAttribute("sipid_list", sipid_list);
			request.setAttribute("sipname_list", sipname_list);
			System.out.println("sipid_list.size()=" + sipid_list.size());
			System.out.println("sipname_list.size()=" + sipname_list.size());
			// other characteristics!!!
			
			// redirect webpage printwriter
				System.out.println("Doto searchresult page!");
				request.getRequestDispatcher("/jsp/SIP/searchresultsip.jsp").forward(request,response);
				
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
