package customer;

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

public class CustomerListSM extends HttpServlet {

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
			int transactionid_db = 0;
			int userid_db = 0;
			String userid1_db = "";
			String userid_exist = "false";
			String firstname_db = "";
			String lastname_db = "";
			ArrayList transactionid_list = new ArrayList<String>();
			ArrayList<String> userid_list = new ArrayList<String>();
			ArrayList<String> firstname_list = new ArrayList<String>();
			ArrayList<String> lastname_list = new ArrayList<String>();
			// select TRANSACTION, save select result
			// querry1: 
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT TRANSACTIONID, USERID FROM TRANSACTION";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);
			while(rs1.next())
			{
				// assign value
				transactionid_db = rs1.getInt(1);
				userid_db = rs1.getInt(2);
				
				// add into list
				for(int i = 0; i < userid_list.size(); i++)
				{
					userid1_db = Integer.toString(userid_db);
					if(userid1_db.equals(userid_list.get(i)))
					{
						userid_exist = "true";
						break;
					}
					else
						userid_exist = "false";
				}
				
				if(userid_exist.equals("false"))
				{
					userid_list.add("" + userid_db);
					transactionid_list.add("" + transactionid_db);
					System.out.println("transaction:" + transactionid_db);
					
					// select USER, save select result
					// querry2: 
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT FIRSTNAME, LASTNAME FROM USER WHERE USERID = '" + userid_db + "'";
					System.out.println(query1);
					ResultSet rs2 = stmt2.executeQuery(query2);
					while(rs2.next())
					{
						// assign value
						firstname_db = rs2.getString(1);
						lastname_db = rs2.getString(2);
				
						// add into list
						firstname_list.add("" + firstname_db);
						lastname_list.add("" + lastname_db);
					}
				}
			}
			
			// set attribute for jsp
			request.setAttribute("transactionid_list", transactionid_list);
			request.setAttribute("firstname_list", firstname_list);
			request.setAttribute("lastname_list", lastname_list);
			System.out.println("lastname_list.size()=" + lastname_list.size());
			// other characteristics!!!
			
			// redirect webpage printwriter
			if(transactionid_list.size() == 0)
			{
				System.out.println("No customer!");
			}
			else
			{
				System.out.println("Show customers!");
			}
			request.getRequestDispatcher("jsp/Customer/customerlist1M.jsp").forward(request,response);
				
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
