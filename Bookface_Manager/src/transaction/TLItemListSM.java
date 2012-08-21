package transaction;

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

public class TLItemListSM extends HttpServlet {

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

		System.out.println("-----transaction/TLItemListS.java | Transaction/tlitenlist.jsp-----");
		
		// choose "submit" 
		String submit_transactionlist = request.getParameter("transactionlist");
		String submit_customerlist = request.getParameter("customerlist");
		
		if(submit_transactionlist != null)
		{
			try{
				// JSP information
				response.setContentType("text/html; charset=gbk"); 
				String advertisementid_choose1 = request.getParameter("advertisementid_choose");
				System.out.println("advertisementid_choose: " + advertisementid_choose1);
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
				int transactionid_db = 0;
				java.sql.Timestamp datetime_db = new Timestamp(java.sql.Types.TIMESTAMP);
				int numberofunits_db = 0;
				int userid_db = 0;
				String firstname_db = "";
				String lastname_db = "";
				ArrayList transactionid_list = new ArrayList();
				ArrayList<String> datetime_list = new ArrayList<String>();
				ArrayList numberofunits_list = new ArrayList<String>();
				ArrayList<String> firstname_list = new ArrayList<String>();
				ArrayList<String> lastname_list = new ArrayList<String>();
				// select TRANSACTION, save select result
				// querry1: 
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT TRANSACTIONID, DATETIME, NUMBER_OF_UNITS, USERID FROM TRANSACTION WHERE ADVERTISEMENTID = '" + advertisementid_choose + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					transactionid_db = rs1.getInt(1);
					datetime_db = rs1.getTimestamp(2);
					numberofunits_db = rs1.getInt(3);
					userid_db = rs1.getInt(4);
					
					// add to list
					transactionid_list.add("" + transactionid_db);
					datetime_list.add("" + datetime_db);
					numberofunits_list.add("" + numberofunits_db);

					// select USER, save select result
					// querry2: firstname, lastname
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT FIRSTNAME, LASTNAME FROM USER WHERE USERID = '" + userid_db + "'";
					System.out.println(query2);
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
				
				// set attribute for jsp
				request.setAttribute("transactionid_list", transactionid_list);
				request.setAttribute("datetime_list", datetime_list);
				request.setAttribute("numberofunits_list", numberofunits_list);
				request.setAttribute("firstname_list", firstname_list);
				request.setAttribute("lastname_list", lastname_list);
				// other characteristics!!!

				
				// redirect webpage printwriter
				if(transactionid_list.size() == 0)
				{
					System.out.println("No transaction!");
				}
				else
				{
					System.out.println("Show transacton!");
				}
				request.getRequestDispatcher("jsp/Transaction/tliltransactionlistM.jsp").forward(request,response);
					
				// close connect
				conn.close();
			}
			catch (Exception e) {
			e.printStackTrace();}
		}
		
		if(submit_customerlist != null)
		{
			try{
				// JSP information
				response.setContentType("text/html; charset=gbk"); 
				String advertisementid_choose1 = request.getParameter("advertisementid_choose");
				System.out.println("advertisementid_choose: " + advertisementid_choose1);
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
				int transactionid_db = 0;
				int userid_db = 0;
				String firstname_db = "";
				String lastname_db = "";
				ArrayList transactionid_list = new ArrayList();
				ArrayList<String> firstname_list = new ArrayList<String>();
				ArrayList<String> lastname_list = new ArrayList<String>();
				// select TRANSACTION, save select result
				// querry1: 
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT TRANSACTIONID, USERID FROM TRANSACTION WHERE ADVERTISEMENTID = '" + advertisementid_choose + "'";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					transactionid_db = rs1.getInt(1);
					userid_db = rs1.getInt(2);
					
					// add to list
					transactionid_list.add("" + transactionid_db);

					// select USER, save select result
					// querry2: firstname, lastname
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT FIRSTNAME, LASTNAME FROM USER WHERE USERID = '" + userid_db + "'";
					System.out.println(query2);
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
				
				// set attribute for jsp
				request.setAttribute("transactionid_list", transactionid_list);
				request.setAttribute("firstname_list", firstname_list);
				request.setAttribute("lastname_list", lastname_list);
				// other characteristics!!!
				
				// redirect webpage printwriter
				if(transactionid_list.size() == 0)
				{
					System.out.println("No customer!");
				}
				else
				{
					System.out.println("Show customer!");
				}
				request.getRequestDispatcher("jsp/Transaction/tlilcustomerlistM.jsp").forward(request,response);
					
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
