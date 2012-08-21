package advt;

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

public class TypeListS extends HttpServlet {

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
	 * @param <real>
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("-----advt/TypeListS.java | Advt/typelist.jsp-----");
		
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk");
			String type_choose = request.getParameter("type_choose");
			System.out.println("type_choose: " + type_choose);
			
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
			String date_db = "";
			String company_db = "";
			String itemname_db = "";
			double unitprice_db = 0;
			int number_of_available_units_db = 0;
			ArrayList advertisementid_list = new ArrayList<String>();
			ArrayList<String> date_list = new ArrayList<String>();
			ArrayList<String> company_list = new ArrayList<String>();
			ArrayList<String> itemname_list = new ArrayList<String>();
			ArrayList unitprice_list = new ArrayList<String>();
			ArrayList number_of_available_units_list = new ArrayList<String>();
			// assign hostid
			int hostid = 0;
			HttpSession session = request.getSession();
			hostid = (Integer)session.getAttribute("hostid");
			// select ADVERTISEMENT, save select result
			// querry1: advertisementid, date, company, itemname, unitprice, numberofavailableunits
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT ADVERTISEMENTID, DATE, COMPANY, ITEMNAME, UNITPRICE, NUMBER_OF_AVAILABLE_UNITS FROM ADVERTISEMENT WHERE TYPE = '" + type_choose + "'";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);
			while(rs1.next())
			{
				// assign value
				advertisementid_db = rs1.getInt(1);
				date_db = rs1.getString(2);
				company_db = rs1.getString(3);
				itemname_db = rs1.getString(4);
				unitprice_db = rs1.getDouble(5);
				number_of_available_units_db = rs1.getInt(6);
				
				// add into list
				advertisementid_list.add("" + advertisementid_db);
				date_list.add("" + date_db);
				company_list.add("" + company_db);
				itemname_list.add("" + itemname_db);
				unitprice_list.add("" + unitprice_db);
				number_of_available_units_list.add("" + number_of_available_units_db);
			}
			
			// set attribute for jsp
			request.setAttribute("advertisementid_list", advertisementid_list);
			request.setAttribute("date_list", date_list);
			request.setAttribute("company_list", company_list);
			request.setAttribute("itemname_list", itemname_list);
			request.setAttribute("unitprice_list", unitprice_list);
			request.setAttribute("number_of_available_units_list", number_of_available_units_list);
			System.out.println("advertisementid_list.size()=" + advertisementid_list.size());
			System.out.println("date_list.size()=" + date_list.size());
			System.out.println("company_list.size()=" + company_list.size());
			System.out.println("itemname_list.size()=" + itemname_list.size());
			System.out.println("unitprice_list.size()=" + unitprice_list.size());
			System.out.println("number_of_available_units_list.size()=" + number_of_available_units_list.size());
			// other characteristics!!!
			
			// redirect webpage printwriter
			if(advertisementid_list.size() == 0)
			{
				System.out.println("No advertisement!");
			}
			else
			{
				System.out.println("Show advertisement!");
			}
			request.getRequestDispatcher("jsp/Advt/advtlist.jsp").forward(request,response);
				
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
