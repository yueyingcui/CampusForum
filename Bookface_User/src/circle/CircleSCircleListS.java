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

public class CircleSCircleListS extends HttpServlet {

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

		System.out.println("-----circle/CircleSCircleListS.java | Circle/circle.jsp -> Circle/circlelist.jsp-----");
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
			int circleid_db = 0;
			String circlename_db = "";
			ArrayList<String> circleid_list = new ArrayList<String>();
			ArrayList<String> circlename_list = new ArrayList<String>();
			// assign hostid
			HttpSession session = request.getSession();
			hostid = (Integer)session.getAttribute("hostid");
			// select CIRCLE, save selecct result
			// querry1: circleid, circlename
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT CIRCLEID, CIRCLENAME FROM CIRCLE WHERE OWNERID = '" + hostid + "'";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);
			while(rs1.next())
			{
				// assign value
				circleid_db = rs1.getInt(1);
				circlename_db = rs1.getString(2);
				
				// add into list
				System.out.println("circleid:" + circleid_db);
				System.out.println("circlename:" + circlename_db);
				circleid_list.add("" + circleid_db);
				circlename_list.add("" + circlename_db);
			}
			
			// set attribute for jsp
			request.setAttribute("circleid_list", circleid_list);
			request.setAttribute("circlename_list", circlename_list);
			System.out.println("circleid_list.size()=" + circleid_list.size());
			System.out.println("circlename_list.size()=" + circlename_list.size());
			// other characteristics!!!
			
			// redirect webpage printwriter
			if(circleid_list.size() == 0)
			{
				System.out.println("No circle!");
			}
			else
			{
				System.out.println("Show circles!");
			}
			request.getRequestDispatcher("jsp/Circle/circlelist.jsp").forward(request,response);
				
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
