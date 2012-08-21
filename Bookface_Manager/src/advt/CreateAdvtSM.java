package advt;

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

public class CreateAdvtSM extends HttpServlet {

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

		System.out.println("-----advt/CreateAdvtSM.java | Advt/createadvtM.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String type = request.getParameter("type");
			String date=new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Calendar.getInstance().getTime());
			String company = request.getParameter("company");
			String itemname = request.getParameter("itemname");
			String unitprice1 = request.getParameter("unitprice");
			int unitprice = Integer.parseInt(unitprice1);
			String numberofavailableunits1 = request.getParameter("numberofavailableunits");
			int numberofavailableunits = Integer.parseInt(numberofavailableunits1);
			System.out.println("type: " + type);
			System.out.println("date: " + date);
			System.out.println("company: " + company);
			System.out.println("itemname: " + itemname);
			System.out.println("unitprice: " + unitprice1);
			System.out.println("numberofavailableunits: " + numberofavailableunits1);
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//	DB2 information
			// querry1: insert ADVERTISEMENT
			// get primary key +1
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT ADVERTISEMENTID FROM ADVERTISEMENT";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);
			int advertisementid_db = 0;
			while(rs1.next())
			{
				advertisementid_db = rs1.getInt(1);
			}
			advertisementid_db = advertisementid_db + 1;
			// (querry2)
			String query2 = "INSERT INTO ADVERTISEMENT (ADVERTISEMENTID, TYPE, DATE, COMPANY, ITEMNAME, UNITPRICE, NUMBER_OF_AVAILABLE_UNITS) VALUES ('" + advertisementid_db + "', '" + type + "', '" + date + "', '" + company + "', '" + itemname + "', '" + unitprice + "', '" + numberofavailableunits + "')";
			System.out.println(query2);
			stmt1.executeUpdate(query2);
			
			// redirect webpage
				System.out.println("Create advertisement successfully!");
				response.sendRedirect("jsp/Advt/advtM.jsp");
				
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
