package post;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WallS extends HttpServlet {

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

		System.out.println("-----post/WallS.java | Post/wall.jsp-----");
		
		// choose "submit" 
		String submit_createpost = request.getParameter("createpost");
		String submit_postlist = request.getParameter("postlist");
		
		if(submit_createpost != null)
		{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String page_ownerid1 = request.getParameter("page_ownerid");
			int page_ownerid = Integer.parseInt(page_ownerid1);
			System.out.println("page_ownerid: " + page_ownerid);
						
			// set Attibute
			request.setAttribute("page_ownerid", page_ownerid1);
			System.out.println("page_ownerid: " + page_ownerid);	
				
			// redirect webpage
			System.out.println("Go to create a post!");
			request.getRequestDispatcher("jsp/Post/createpost.jsp").forward(request,response);
		}
		
		if(submit_postlist != null)
		{
			try{
				// JSP information
				response.setContentType("text/html; charset=gbk"); 
				String page_ownerid1 = request.getParameter("page_ownerid");
				int page_ownerid = Integer.parseInt(page_ownerid1);
				System.out.println("page_ownerid: " + page_ownerid);
				
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
				int postid_db = 0;
				int postpageid_db = 0;
				int post_ownerid_db = 0;
				String post_owner_firstname_db = "";
				String post_owner_lastname_db = "";
				String content_db = "";
				// note: datetime
				java.sql.Timestamp datetime1_db = new Timestamp(java.sql.Types.TIMESTAMP);
				//String datetime_db = "";
				ArrayList postid_list = new ArrayList();
				ArrayList<String> post_owner_firstname_list = new ArrayList<String>();
				ArrayList<String> post_owner_lastname_list = new ArrayList<String>();
				ArrayList<String> content_list = new ArrayList<String>();
				ArrayList<String> datetime_list = new ArrayList<String>();
				// assign hostid
				HttpSession session = request.getSession();
				hostid = (Integer)session.getAttribute("hostid");
				// select POSTPAGE, save select result
				// querry1: postpageid
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT POSTPAGEID FROM POSTPAGE WHERE OWNERID = '" + page_ownerid + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					postpageid_db = rs1.getInt(1);

					// select POST, save select result
					// querry2: ownerid, content, datetime
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT POSTID, OWNERID, CONTENT, DATETIME FROM POST WHERE POSTPAGEID = '" + postpageid_db + "'";
					System.out.println(query2);
					ResultSet rs2 = stmt2.executeQuery(query2);
					while(rs2.next())
					{
						// assign value
						postid_db = rs2.getInt(1);
						post_ownerid_db = rs2.getInt(2);
						content_db = rs2.getString(3);
						// note:datetime
						datetime1_db = rs2.getTimestamp(4);
						//datetime_db = datetime_db.toString();
						System.out.println("datetime: " + datetime1_db);
					
						// add into list
						postid_list.add("" + postid_db);
						content_list.add("" + content_db);
						datetime_list.add("" + datetime1_db);
						System.out.println("postid_list.size()=" + postid_list.size());
						System.out.println("content_list.size()=" + content_list.size());
						System.out.println("datetime_list.size()=" + datetime_list.size());
						
						// select USER, save select result
						// querry3: firstname, lastname
						Statement stmt3 = conn.createStatement();
						String query3 = "SELECT FIRSTNAME, LASTNAME FROM USER WHERE USERID = '" + post_ownerid_db + "'";
						System.out.println(query3);
						ResultSet rs3 = stmt3.executeQuery(query3);
						while(rs3.next())
						{
							// assign value
							post_owner_firstname_db = rs3.getString(1);
							post_owner_lastname_db = rs3.getString(2);
						
							// add into list
							post_owner_firstname_list.add("" + post_owner_firstname_db);
							post_owner_lastname_list.add("" + post_owner_lastname_db);
							System.out.println("post_owner_firstname_list.size()=" + post_owner_firstname_list.size());
							System.out.println("post_owner_lastname_list.size()=" + post_owner_lastname_list.size());
						}
					}
				}
				
				// set attribute for jsp
				request.setAttribute("postid_list", postid_list);
				request.setAttribute("post_owner_firstname_list", post_owner_firstname_list);
				request.setAttribute("post_owner_lastname_list", post_owner_lastname_list);
				request.setAttribute("content_list", content_list);
				request.setAttribute("datetime_list", datetime_list);
				// set attribute for "source"
				request.setAttribute("source", "Wall");
				// other characteristics!!!
				
				// redirect webpage printwriter
				if(post_owner_firstname_list.size() == 0)
				{
					System.out.println("No post!");
				}
				else
				{
					System.out.println("Show posts!");
				}
				request.getRequestDispatcher("jsp/Post/postlist.jsp").forward(request,response);
					
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
