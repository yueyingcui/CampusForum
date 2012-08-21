package advt;

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

public class AdvtListSM extends HttpServlet {

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

		System.out.println("-----advt/AdvtListSM.java | Advt/advtlistM.jsp-----");
		
		// choose "submit" 
		String submit_mailinglist = request.getParameter("mailinglist");
		String submit_deleteadvt = request.getParameter("deleteadvt");
		
		if(submit_mailinglist != null)
		{
			try{
				// JSP information
				response.setContentType("text/html; charset=gbk"); 
				String advertisementid_choose1 = request.getParameter("advertisementid_choose");
				int advertisementid_choose = Integer.parseInt(advertisementid_choose1);
				System.out.println("advertisementid_choose: " + advertisementid_choose);
				
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
				int userid_db = 0;
				String email_db = "";
				ArrayList email_list = new ArrayList();
				// select TRANSACTION, save select result
				// querry1: userid
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT USERID FROM TRANSACTION WHERE ADVERTISEMENTID = '" + advertisementid_choose + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					userid_db = rs1.getInt(1);
					System.out.println("userid_db: " + userid_db);

					// select USER, save select result
					// querry2: email
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT EMAIL FROM USER WHERE USERID = '" + userid_db + "'";
					System.out.println(query2);
					ResultSet rs2 = stmt2.executeQuery(query2);
					while(rs2.next())
					{
						// assign value
						email_db = rs2.getString(1);
						System.out.println("email: " + email_db);
					
						// add into list
						email_list.add("" + email_db);
					}
				}
				
				// set attribute for jsp
				request.setAttribute("email_list", email_list);
				System.out.println("email_list.size()=" + email_list.size());
				// other characteristics!!!
				
				// redirect webpage printwriter
				if(email_list.size() == 0)
				{
					System.out.println("No email!");
				}
				else
				{
					System.out.println("Show emails!");
				}
				request.getRequestDispatcher("jsp/Advt/mailinglistM.jsp").forward(request,response);
					
				// close connect
				conn.close();
			}
			catch (Exception e) {
			e.printStackTrace();}
		}
		
		if(submit_deleteadvt != null)
		{
			try{
				// JSP information
				response.setContentType("text/html; charset=gbk"); 
				String advertisementid_choose1 = request.getParameter("advertisementid_choose");
				int advertisementid_choose = Integer.parseInt(advertisementid_choose1);
				System.out.println("advertisementid_choose: " + advertisementid_choose);
				
				// connect to DB2
				Connection conn = null;
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
				String user = "cseteam49";
				String pass = "Spring2012";
				conn = DriverManager.getConnection(url,user,pass);
				System.out.println("Connected and working!");
			
				//	DB2 information
				// querry1: delete ADVERTISEMENT
				Statement stmt1 = conn.createStatement();
				String query1 = "DELETE FROM ADVERTISEMENT WHERE (ADVERTISEMENTID = '" + advertisementid_choose + "')";
				System.out.println(query1);
				stmt1.executeUpdate(query1);
				// querry2: delete TRANSACTION
				Statement stmt2 = conn.createStatement();
				String query2 = "DELETE FROM TRANSACTION WHERE (ADVERTISEMENTID = '" + advertisementid_choose + "')";
				System.out.println(query2);
				stmt2.executeUpdate(query2);
				
				// redirect webpage
					System.out.println("Delete advertisement successfully!");
					response.sendRedirect("jsp/Advt/advtM.jsp");
					
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
