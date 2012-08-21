package post;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PostListS extends HttpServlet {

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

		System.out.println("-----post/PostListS.java | Post/post.jsp-----");
		
		// choose "submit" 
		String submit_createcomment = request.getParameter("createcomment");
		String submit_commentlist = request.getParameter("commentlist");
		String submit_modifypost = request.getParameter("modifypost");
		String submit_deletepost = request.getParameter("deletepost");
		
		if(submit_createcomment != null)
		{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String postid1 = request.getParameter("postid_choose");
			int postid = Integer.parseInt(postid1); 
			String postid_string = Integer.toString(postid);
			System.out.println("postid: " + postid);
				
			// set Attibute
			request.setAttribute("postid", postid_string);
			System.out.println("postid: " + postid);	
					
			// redirect webpage
			System.out.println("Go to create a comment!");
			request.getRequestDispatcher("jsp/Post/createcomment.jsp").forward(request,response);		
		}
		
		if(submit_commentlist != null)
		{
			try{
				// JSP information
				response.setContentType("text/html; charset=gbk"); 
				String postid1 = request.getParameter("postid_choose");
				int postid = Integer.parseInt(postid1); 
				String postid_string = Integer.toString(postid);
				System.out.println("postid: " + postid);
				
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
				int comment_ownerid_db = 0;
				String comment_firstname_db = "";
				String comment_lastname_db = "";
				String content_db = "";
				java.sql.Timestamp datetime_db = new Timestamp(java.sql.Types.TIMESTAMP);
				
				ArrayList<String> comment_firstname_list = new ArrayList<String>();
				ArrayList<String> comment_lastname_list = new ArrayList<String>();
				ArrayList<String> content_list = new ArrayList<String>();
				ArrayList<String> datetime_list = new ArrayList<String>();
				// select COMMENT, save selecct result
				// querry1: ownerid, content, datetime
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT OWNERID, CONTENT, DATETIME FROM COMMENT WHERE POSTID = '" + postid + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					comment_ownerid_db = rs1.getInt(1);
					content_db = rs1.getString(2);
					datetime_db = rs1.getTimestamp(3);
					
					// add into list
					content_list.add("" + content_db);
					datetime_list.add("" + datetime_db);
					System.out.println("content_db:" + content_db);
					System.out.println("datetime_db:" + datetime_db);
					
					
					//querry2: firstname, lastname
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT FIRSTNAME, LASTNAME FROM USER WHERE USERID = '" + comment_ownerid_db + "'";
					System.out.println(query2);
					ResultSet rs2 = stmt2.executeQuery(query2);
					while(rs2.next())
					{
						// assign value
						comment_firstname_db = rs2.getString(1);
						comment_lastname_db = rs2.getString(2);
						
						// add into list
						System.out.println("comment_firstname:" + comment_firstname_db);
						comment_firstname_list.add("" + comment_firstname_db);
						System.out.println("comment_lastname:" + comment_lastname_db);
						comment_lastname_list.add("" + comment_lastname_db);
					}
					
				}
				
				// set attribute for jsp
				request.setAttribute("comment_firstname_list", comment_firstname_list);
				request.setAttribute("comment_lastname_list", comment_lastname_list);
				request.setAttribute("content_list", content_list);
				request.setAttribute("datetime_list", datetime_list);
				System.out.println("comment_firstname_list.size()=" + comment_firstname_list.size());
				System.out.println("comment_lastname_list.size()=" + comment_lastname_list.size());
				System.out.println("content.size()=" + content_list.size());
				System.out.println("datetime_list.size()=" + datetime_list.size());
				// other characteristics!!!
				
				// redirect webpage printwriter
				if(comment_firstname_list.size() == 0)
				{
					System.out.println("No comment!");
				}
				else
				{
					System.out.println("Show comments!");
				}
				request.getRequestDispatcher("jsp/Post/commentlist.jsp").forward(request,response);
					
				// close connect
				conn.close();
			}
			catch (Exception e) {
			e.printStackTrace();}
		}
		
		if(submit_modifypost != null)
		{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String postid1 = request.getParameter("postid_choose");
			int postid = Integer.parseInt(postid1); 
			String postid_string = Integer.toString(postid);
			System.out.println("postid: " + postid);
			
			// set attribute for jsp
			request.setAttribute("postid", postid1);
			System.out.println("postid: " + postid);
			
			// redirect webpage
			System.out.println("Goto modify post successfully!");
			request.getRequestDispatcher("jsp/Post/modifypost.jsp").forward(request,response);
		}
		
		if(submit_deletepost != null)
		{
			try{
				// JSP information
				response.setContentType("text/html; charset=gbk"); 
				String postid_choose1 = request.getParameter("postid_choose");
				int postid_choose = Integer.parseInt(postid_choose1);
				System.out.println("postid_choose: " + postid_choose);
				
				// connect to DB2
				Connection conn = null;
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
				String user = "cseteam49";
				String pass = "Spring2012";
				conn = DriverManager.getConnection(url,user,pass);
				System.out.println("Connected and working!");
			
				//	DB2 information
				// querry1: delete COMMENT
				Statement stmt1 = conn.createStatement();
				String query1 = "DELETE FROM COMMENT WHERE (POSTID = '" + postid_choose + "')";
				System.out.println(query1);
				stmt1.executeUpdate(query1);
				// querry2: delete POST
				Statement stmt2 = conn.createStatement();
				String query2 = "DELETE FROM POST WHERE (POSTID = '" + postid_choose + "')";
				System.out.println(query2);
				stmt2.executeUpdate(query2);
				
				// redirect webpage
					System.out.println("Delete post successfully!");
					response.sendRedirect("jsp/Post/post.jsp");
					
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
