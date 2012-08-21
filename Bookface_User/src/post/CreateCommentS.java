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

public class CreateCommentS extends HttpServlet {

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

		System.out.println("-----post/CreateCommentS.java | Post/createcomment.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String postid1 = request.getParameter("postid");
			int postid = Integer.parseInt(postid1);
			System.out.println("postid: " + postid);
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
			int comment_ownerid = 0;
			HttpSession session = request.getSession();
			comment_ownerid = (Integer)session.getAttribute("hostid");
			// querry1: insert COMMENT
			// get primary key +1
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT COMMENTID FROM COMMENT";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);
			int commentid_db = 0;
			while(rs1.next())
			{
				commentid_db = rs1.getInt(1);
			}
			commentid_db = commentid_db + 1;
			// (querry2)
			String query2 = "INSERT INTO COMMENT (COMMENTID, OWNERID, CONTENT, DATETIME, POSTID) VALUES ('" + commentid_db + "', '" + comment_ownerid + "', '" + content + "', '" + datetime + "', '" + postid + "')";
			System.out.println(query2);
			stmt1.executeUpdate(query2);
			
			// redirect webpage
				System.out.println("Create comment successfully!");
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
