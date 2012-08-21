package friend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchFriendS<Int, RequestDispatcher> extends HttpServlet {

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
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("-----friend/SearchFriendS.java | Friend/searchfriend.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			String dob1 = request.getParameter("year") + request.getParameter("month") + request.getParameter("date");
			// String -> Date
			String DATE_FORMAT = "yyyyMMdd";
			SimpleDateFormat SDF = new SimpleDateFormat(DATE_FORMAT);
			//System.out.println(dob1);
			java.util.Date dob2 = SDF.parse(dob1);
			java.sql.Date dob = new java.sql.Date(dob2.getTime());
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zipcode = request.getParameter("zipcode");
			String telephone = request.getParameter("telephone");
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//	DB2 information: search USER, save search result
			Statement stmt = conn.createStatement();
			String query1 = "SELECT * FROM USER";
			System.out.println(query1);
			ResultSet rs = stmt.executeQuery(query1);
			// initial value
			int userid_db = 0;
			String firstname_db = "";
			String lastname_db = "";
			String sex_db = "";
			String email_db = "";
			java.util.Date dob_db = new java.util.Date();
			String year_db = "";
			String month_db = "";
			String date_db = "";
			String address_db = "";
			String city_db = "";
			String state_db = "";
			String zipcode_db = "";
			String telephone_db = "";			
			ArrayList<String> userid_list = new ArrayList<String>();
			ArrayList<String> firstname_list = new ArrayList<String>();
			ArrayList<String> lastname_list = new ArrayList<String>();
			while(rs.next())
			{
				// assign value
				userid_db = rs.getInt(1);
				firstname_db = rs.getString(2).trim();
				lastname_db = rs.getString(3).trim();
				sex_db = rs.getString(4).trim(); 
				email_db = rs.getString(5).trim();
				dob_db = rs.getDate(6);
				address_db = rs.getString(7).trim();
				city_db = rs.getString(8).trim();
				state_db = rs.getString(9).trim();
				zipcode_db = rs.getString(10).trim();
				telephone_db = rs.getString(11).trim();
				// add into list
				System.out.println("firstname:" + firstname + " lastname:" + lastname + " sex:" + sex + " email:" + email + " dob:" + dob + " address:" + address + " city:" + city + " state:" + state + " zipcode:" + zipcode + " telephone:" + telephone);
				System.out.println("userid:" + userid_db + " firstname:" + firstname_db + " lastname:" + lastname_db + " sex:" + sex_db + " email:" + email_db + " dob:" + dob_db + " address:" + address_db + " city:" + city_db + " state:" + state_db + " zipcode:" + zipcode_db + " telephone:" + telephone_db);
				if(firstname.equals(firstname_db) || lastname.equals(lastname_db) || sex.equals(sex_db) || email.equals(email_db) || dob.compareTo(dob_db) == 0 || address.equals(address_db) || city.equals(city_db) || state.equals(state_db) || zipcode.equals(zipcode_db) || telephone.equals(telephone_db))
				{
					System.out.println("Search successed!");
					userid_list.add("" +userid_db);
					firstname_list.add(firstname_db);
					lastname_list.add(lastname_db);
				}
				else
				{
					System.out.println("Search failed!");
					userid_list.add(""+0);
					firstname_list.add("");
					lastname_list.add("");
					
					// print resear failed!!!
					response.sendRedirect("jsp/Friend/searchfriend.jsp");
				}
			}
			
			// set attribute for jsp
			//request.getSession().setAttribute("userid_list", userid_list);
			//request.getSession().setAttribute("firstname_list", firstname_list);
			//request.getSession().setAttribute("lastname_list", lastname_list);
			request.setAttribute("userid_list", userid_list);
			request.setAttribute("firstname_list", firstname_list);
			request.setAttribute("lastname_list", lastname_list);
			System.out.println("userid_list.size()=" + userid_list.size());
			System.out.println("firstname_list.size()=" + firstname_list.size());
			System.out.println("lastname_list.size()=" + lastname_list.size());
			// other characteristics!!!
			
			// redirect webpage printwriter
				System.out.println("Doto searchresult page!");
				//this.getServletContext().getRequestDispatcher("/jsp/Friend/searchresult.jsp").forward(request,response);
				request.getRequestDispatcher("/jsp/Friend/searchresultfriend.jsp").forward(request,response);
				//request.getRequestDispatcher("/jsp/Friend/searchresult.jsp");
				
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
