package transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TLMonthListSM extends HttpServlet {

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
		
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String month_choose1 = request.getParameter("month_choose");
			System.out.println("month_choose: " + month_choose1);
			int month_choose = Integer.parseInt(month_choose1);
			System.out.println("month_choose: " + month_choose);
			
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
			int advertisementid_db = 0;
			String type_db = "";
			String company_db = "";
			String itemname_db = "";
			double unitprice_db = 0;
			// calendar			
			java.util.Date datetime_db_date = new Date();
			Calendar calendar_db = Calendar.getInstance(); 
			ArrayList transactionid_list = new ArrayList();
			ArrayList<String> datetime_list = new ArrayList<String>();
			ArrayList numberofunits_list = new ArrayList<String>();
			ArrayList<String> firstname_list = new ArrayList<String>();
			ArrayList<String> lastname_list = new ArrayList<String>();
			ArrayList<String> type_list = new ArrayList<String>();
			ArrayList company_list = new ArrayList<String>();
			ArrayList<String> itemname_list = new ArrayList<String>();
			ArrayList<String> unitprice_list = new ArrayList<String>();
			// select TRANSACTION, save select result
			// querry1: 
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT TRANSACTIONID, DATETIME, ADVERTISEMENTID, NUMBER_OF_UNITS, USERID FROM TRANSACTION";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);
			while(rs1.next())
			{
				// assign value
				transactionid_db = rs1.getInt(1);
				datetime_db = rs1.getTimestamp(2);
				advertisementid_db = rs1.getInt(3);
				numberofunits_db = rs1.getInt(4);
				userid_db = rs1.getInt(5);
				
				long milliseconds = datetime_db.getTime() + (datetime_db.getNanos() / 1000000);
				datetime_db_date = new java.util.Date(milliseconds);
				//int month_db = datetime_db_date.getMonth();
				//
				calendar_db.setTime(datetime_db_date); 
				int month_db = calendar_db.get(Calendar.MONTH);
				
				if(month_db == month_choose)
				{
				
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
					
					// select ADVERTISEMENT, save select result
					// querry3: 
					Statement stmt3 = conn.createStatement();
					String query3 = "SELECT TYPE, COMPANY, ITEMNAME, UNITPRICE FROM ADVERTISEMENT WHERE ADVERTISEMENTID = '" + advertisementid_db + "'";
					System.out.println(query3);
					ResultSet rs3 = stmt3.executeQuery(query3);
					while(rs3.next())
					{
						// assign value
						type_db = rs3.getString(1);
						company_db = rs3.getString(2);
						itemname_db = rs3.getString(3);
						unitprice_db = rs3.getDouble(4);
							
						// add into list
						type_list.add("" + type_db);
						company_list.add("" + company_db);
						itemname_list.add("" + itemname_db);
						unitprice_list.add("" + unitprice_db);
					}
				}
			}
			
			// set attribute for jsp
			request.setAttribute("transactionid_list", transactionid_list);
			request.setAttribute("datetime_list", datetime_list);
			request.setAttribute("numberofunits_list", numberofunits_list);
			request.setAttribute("firstname_list", firstname_list);
			request.setAttribute("lastname_list", lastname_list);
			request.setAttribute("type_list", type_list);
			request.setAttribute("company_list", company_list);
			request.setAttribute("itemname_list", itemname_list);
			request.setAttribute("unitprice_list", unitprice_list);
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
			request.getRequestDispatcher("jsp/Transaction/tlmltransactionlistM.jsp").forward(request,response);
				
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
