package sip;

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

public class CreateSipS extends HttpServlet {

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

		System.out.println("-----sip/CreateSipS.java | SIP/createsip.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String sipname = request.getParameter("sipname");
			System.out.println("sipname: " + sipname);
			String sipdescription = request.getParameter("sipdescription");
			System.out.println("sipdescription: " + sipdescription);
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//	DB2 information
			// assign hostid
			int hostid = 0;
			HttpSession session = request.getSession();
			hostid = (Integer)session.getAttribute("hostid");
			// querry1: insert SIP
			// get primary key +1
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT SIPID FROM SIP";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);
			int sipid_db = 0;
			while(rs1.next())
			{
				sipid_db = rs1.getInt(1);
			}
			sipid_db = sipid_db + 1;
			// (querry2)
			String query2 = "INSERT INTO SIP (SIPID, SIPNAME, SIPDESCRIPTION) VALUES ('" + sipid_db + "', '" + sipname + "', '" + sipdescription + "')";
			System.out.println(query2);
			stmt1.executeUpdate(query2);
			// querry3: insert SIPPAGE
			// get primary key +1
			Statement stmt2 = conn.createStatement();
			String query3 = "SELECT SIPPAGEID FROM SIPPAGE";
			System.out.println(query3);
			ResultSet rs2 = stmt2.executeQuery(query3);
			int sippageid_db = 0;
			while(rs2.next())
			{
				sippageid_db = rs2.getInt(1);
			}
			sippageid_db = sippageid_db + 1;
			// (querry4)
			String query4 = "INSERT INTO SIPPAGE (SIPPAGEID, SIPID) VALUES ('" + sippageid_db + "', '" + sipid_db + "')";
			System.out.println(query4);
			stmt2.executeUpdate(query4);
			// querry5: insert SIPMODERATOT
			// get primary key +1
			Statement stmt3 = conn.createStatement();
			String query5 = "SELECT SIPMODERATORID FROM SIPMODERATOR";
			System.out.println(query5);
			ResultSet rs3 = stmt3.executeQuery(query5);
			int sipmoderatorid_db = 0;
			while(rs3.next())
			{
				sipmoderatorid_db = rs3.getInt(1);
			}
			sipmoderatorid_db = sipmoderatorid_db + 1;
			// (querry6)
			String query6 = "INSERT INTO SIPMODERATOR (SIPMODERATORID, SIPID, MODERATORID) VALUES ('" + sipmoderatorid_db + "', '" + sipid_db + "', '" + hostid + "')";
			System.out.println(query6);
			stmt3.executeUpdate(query6);
			// querry7: insert SIPMEMBER
			// get primary key +1
			Statement stmt4 = conn.createStatement();
			String query7 = "SELECT SIPMEMBERID FROM SIPMEMBER";
			System.out.println(query7);
			ResultSet rs4 = stmt4.executeQuery(query7);
			int sipmemberid_db = 0;
			while(rs4.next())
			{
				sipmemberid_db = rs4.getInt(1);
			}
			sipmemberid_db = sipmemberid_db + 1;
			// (querry8)
			String query8 = "INSERT INTO SIPMEMBER (SIPMEMBERID, SIPID, MEMBERID) VALUES ('" + sipmemberid_db + "', '" + sipid_db + "', '" + hostid + "')";
			System.out.println(query8);
			stmt4.executeUpdate(query8);
			
			// redirect webpage
				System.out.println("Create SIP successfully!");
				response.sendRedirect("jsp/SIP/sip.jsp");
				
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
