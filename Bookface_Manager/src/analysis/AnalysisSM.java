package analysis;

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

public class AnalysisSM extends HttpServlet {

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
		String submit_item = request.getParameter("item");
		String submit_customer = request.getParameter("customer");
		String submit_company = request.getParameter("company");
		
		if(submit_item != null)
		{
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
				int advertisementid_db = 0;
				String itemname_db = "";
				String itemname_exist = "false";
				ArrayList advertisementid_list = new ArrayList<String>();
				ArrayList<String> itemname_list = new ArrayList<String>();
				ArrayList unitprice_list = new ArrayList();
				double unitprice_db = 0;
				int sum_unit_db = 0;
				int numberofunit_db = 0;
				ArrayList sum_unit_list = new ArrayList();
				ArrayList sum_revenue_list = new ArrayList();
				// select ADVERTISEMENT, save select result
				// querry1: type
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT ADVERTISEMENTID, ITEMNAME, UNITPRICE FROM ADVERTISEMENT";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					advertisementid_db = rs1.getInt(1);
					itemname_db = rs1.getString(2).trim();
					unitprice_db = rs1.getDouble(3);
					
					// add into list
					for(int i = 0; i < itemname_list.size(); i++)
					{
						if(itemname_db.equals(itemname_list.get(i)))
						{
							itemname_exist = "true";
							break;
						}
						else
							itemname_exist = "false";
					}
					
					if(itemname_exist.equals("false"))
					{
						advertisementid_list.add("" + advertisementid_db);
						itemname_list.add(itemname_db);
						unitprice_list.add("" + unitprice_db);
						System.out.println("itemname:" + itemname_db);
					}						
				}
				
				for(int i = 0; i < itemname_list.size(); i++)
				{
					// select TRANSACTION, save select result
					// querry1: number of units
					Statement stmt2 = conn.createStatement();
					String query2 = "SELECT NUMBER_OF_UNITS FROM TRANSACTION WHERE ADVERTISEMENTID = '" + advertisementid_db + "'";
					System.out.println(query2);
					ResultSet rs2 = stmt2.executeQuery(query2);
					while(rs2.next())
					{
						// assign value
						numberofunit_db = rs1.getInt(1);
					
						// add to list
						sum_unit_db = sum_unit_db + numberofunit_db;
					}

					sum_unit_list.add("" + sum_unit_db);
					String unitprice2_db = (String)unitprice_list.get(i);
					//int unitprice3_db = Integer.parseInt(unitprice2_db);
					double unitprice1_db = Double.parseDouble(unitprice2_db);
					double sum_unit1_db = (double)sum_unit_db;
					sum_revenue_list.add("" + sum_unit1_db * unitprice1_db);
				}
				// set attribute for jsp
				request.setAttribute("sum_revenue_list", sum_revenue_list);
				request.setAttribute("advertisementid_list", advertisementid_list);
				request.setAttribute("itemname_list", itemname_list);
				System.out.println("advertisementid_list.size()=" + advertisementid_list.size());
				System.out.println("itemname_list.size()=" + itemname_list.size());
				// other characteristics!!!
				
				// find max
				int max_sub = 0;
				for(int i = 0; i < sum_revenue_list.size(); i++)
				{
					double x1 = Double.parseDouble((String)sum_revenue_list.get(i));
					double x2 = Double.parseDouble((String)sum_revenue_list.get(max_sub));
					if(x1 > x2)
						max_sub = i;
				}
				String result_itemname = itemname_list.get(max_sub);
				request.setAttribute("result_itemname", result_itemname);
				
				
				
				// redirect webpage printwriter
				if(itemname_list.size() == 0)
				{
					System.out.println("No item!");
				}
				else
				{
					System.out.println("Show items!");
				}
				request.getRequestDispatcher("jsp/Analysis/itemM.jsp").forward(request,response);
					
				// close connect
				conn.close();
			}
			catch (Exception e) {
			e.printStackTrace();}
		}
		
		if(submit_customer != null)
		{
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
				int advertisementid_db = 0;
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
				ArrayList unitprice_list = new ArrayList();
				double unitprice_db = 0;
				double revenue_singletransaction_db = 0;
				double sum_revenue_db = 0;
				int numberofunit_db = 0;
				ArrayList sum_unit_list = new ArrayList();
				ArrayList sum_revenue_list = new ArrayList();
				// select TRANSACTION, save select result
				// querry1: 
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT TRANSACTIONID, ADVERTISEMENTID, NUMBER_OF_UNITS, USERID FROM TRANSACTION";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					// assign value
					transactionid_db = rs1.getInt(1);
					advertisementid_db = rs1.getInt(2);
					numberofunit_db = rs1.getInt(3);
					userid_db = rs1.getInt(4);
					
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
				
				for(int i = 0; i < firstname_list.size(); i++)
				{
					// select TRANSACTION, save select result
					// querry1: number of units
					Statement stmt3 = conn.createStatement();
					String query3 = "SELECT ADVERTISEMENTID, NUMBER_OF_UNITS FROM TRANSACTION WHERE USERID = '" + userid_list.get(i) + "'";
					System.out.println(query3);
					ResultSet rs3 = stmt3.executeQuery(query3);
					while(rs3.next())
					{
						// assign value
						advertisementid_db = rs3.getInt(1);
						numberofunit_db = rs3.getInt(2);
					
						//select ADVERTISEMENT
						//querry0:
						Statement stmt0 = conn.createStatement();
						String query0 = "SELECT UNITPRICE FROM ADVERTISEMENT WHERE ADVERTISEMENTID = '" + advertisementid_db + "'";
						System.out.println(query0);
						ResultSet rs0 = stmt0.executeQuery(query0);
						while(rs0.next())
						{
							// assign value
							unitprice_db = rs0.getDouble(1);
							
							// add to list
							revenue_singletransaction_db = unitprice_db * numberofunit_db;
						}
						// add to list
						sum_revenue_db = sum_revenue_db + revenue_singletransaction_db;
						
					}

					sum_revenue_list.add("" + sum_revenue_db);
					//String unitprice2_db = (String)unitprice_list.get(i);
					//int unitprice3_db = Integer.parseInt(unitprice2_db);
					//double unitprice1_db = Double.parseDouble(unitprice2_db);
					//double sum_unit1_db = (double)sum_unit_db;
					//sum_revenue_list.add("" + sum_unit1_db * unitprice1_db);
				}
				
				// find max
				int max_sub = 0;
				for(int i = 0; i < sum_revenue_list.size(); i++)
				{
					double x1 = Double.parseDouble((String)sum_revenue_list.get(i));
					double x2 = Double.parseDouble((String)sum_revenue_list.get(max_sub));
					if(x1 > x2)
						max_sub = i;
				}
				String result_firstname = firstname_list.get(max_sub);
				request.setAttribute("result_firstname", result_firstname);
				String result_lastname = lastname_list.get(max_sub);
				request.setAttribute("result_lastname", result_lastname);
				
				
				// set attribute for jsp
				request.setAttribute("sum_revenue_list", sum_revenue_list);
				request.setAttribute("firstname_list", firstname_list);
				request.setAttribute("lastname_list", lastname_list);
				// other characteristics!!!
				
				// redirect webpage printwriter
				if(transactionid_list.size() == 0)
				{
					System.out.println("No revenue!");
				}
				else
				{
					System.out.println("Show revenue!");
				}
				request.getRequestDispatcher("jsp/Analysis/customerM.jsp").forward(request,response);
					
				// close connect
				conn.close();
			}
			catch (Exception e) {
			e.printStackTrace();}
		}
		
		if(submit_company != null)
		{
			
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
