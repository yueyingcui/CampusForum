package post;

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

public class CreatePostS extends HttpServlet {

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

		System.out.println("-----post/CreatePostS.java | Post/createpost.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String page_ownerid1 = request.getParameter("page_ownerid");
			int page_ownerid = Integer.parseInt(page_ownerid1);
			System.out.println("page_ownerid: " + page_ownerid);
			String content = request.getParameter("content");
			String datetime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Calendar.getInstance().getTime());
			System.out.println("datetime: " + datetime);
			
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
			int post_ownerid = 0;
			HttpSession session = request.getSession();
			post_ownerid = (Integer)session.getAttribute("hostid");
			// querry1: insert POSTPAGE
			// get primary key +1
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT POSTPAGEID FROM POSTPAGE";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);
			int postpageid_db = 0;
			while(rs1.next())
			{
				postpageid_db = rs1.getInt(1);
			}
			postpageid_db = postpageid_db + 1;
			// (querry2)
			String query2 = "INSERT INTO POSTPAGE (POSTPAGEID, OWNERID) VALUES ('" + postpageid_db + "', '" + page_ownerid + "')";
			System.out.println(query2);
			stmt1.executeUpdate(query2);
			// querry3: insert POST
			// get primary key +1
			Statement stmt2 = conn.createStatement();
			String query3 = "SELECT POSTID FROM POST";
			System.out.println(query3);
			ResultSet rs2 = stmt2.executeQuery(query3);
			int postid_db = 0;
			while(rs2.next())
			{
				postid_db = rs2.getInt(1);
			}
			postid_db = postid_db + 1;
			// (querry4)
			String query4 = "INSERT INTO POST (POSTID, OWNERID, CONTENT, DATETIME, POSTPAGEID) VALUES ('" + postid_db + "', '" + post_ownerid + "', '" + content + "', '" + datetime + "', '" + postpageid_db + "')";
			System.out.println(query4);
			stmt1.executeUpdate(query4);
			
			// redirect webpage
				System.out.println("Create post successfully!");
				response.sendRedirect("jsp/Post/post.jsp");
				
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
