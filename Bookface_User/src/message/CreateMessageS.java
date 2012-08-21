package message;

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

public class CreateMessageS extends HttpServlet {

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

		System.out.println("-----message/CreateMessageS.java | Message/createmessage.jsp-----");
		try{
			// JSP information
			// subject
			response.setContentType("text/html; charset=gbk"); 
			String subjectname = request.getParameter("subjectname");
			System.out.println("subjectname: " + subjectname);
			String subjectname_new = "false";
			if(subjectname.equals("new"))
			{
				subjectname_new = "true";
				subjectname = request.getParameter("newsubjectname");
			}
			System.out.println("newsubjectname: " + subjectname);
			// receiver
			String receiverid_array[] = request.getParameterValues("receiverid");
			System.out.println("receiverid_array.length: " + receiverid_array.length);
			// content
			String content = request.getParameter("content");
			System.out.println(content);
			// datetime
			String datetime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Calendar.getInstance().getTime());
			System.out.println("datetime: " + datetime);
			
			// connect to DB2
			Connection conn = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user = "cseteam49";
			String pass = "Spring2012";
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
		
			//	DB2 information
			// initial value
			int subjectid_db = 0;
			int messageid_db = 0;
			int messagereceiverid_db = 0;
			String receiverid1_db = "";
			int receiverid_db = 0;
			// assign hostid
			int senderid = 0;
			HttpSession session = request.getSession();
			senderid = (Integer)session.getAttribute("hostid");
			// querry1: insert SUBJECT
			if(subjectname_new.equals("true"))
			{
				// get primary key +1
				Statement stmt1 = conn.createStatement();
				String query1 = "SELECT SUBJECTID FROM SUBJECT";
				System.out.println(query1);
				ResultSet rs1 = stmt1.executeQuery(query1);
				while(rs1.next())
				{
					subjectid_db = rs1.getInt(1);
				}
				subjectid_db = subjectid_db + 1;
				// (querry2)
				String query2 = "INSERT INTO SUBJECT (SUBJECTID, SUBJECTNAME) VALUES ('" + subjectid_db + "', '" + subjectname + "')";
				System.out.println(query2);
				stmt1.executeUpdate(query2);
			}
			// querry3: select SUBJECT, save select result
			else
			{
				Statement stmt2 = conn.createStatement();
				String query3 = "SELECT SUBJECTID FROM SUBJECT WHERE SUBJECTNAME = '" + subjectname + "'";
				System.out.println(query3);
				ResultSet rs2 = stmt2.executeQuery(query3);
				while(rs2.next())
				{
					subjectid_db = rs2.getInt(1);
				}
				System.out.println("subjectid: " + subjectid_db);
			}
			// querry4: insert MESSAGE
			// get primary key +1
			Statement stmt3 = conn.createStatement();
			String query4 = "SELECT MESSAGEID FROM MESSAGE";
			System.out.println(query4);
			ResultSet rs3 = stmt3.executeQuery(query4);
			while(rs3.next())
			{
				messageid_db = rs3.getInt(1);
			}
			messageid_db = messageid_db + 1;
			// (querry5)
			String query5 = "INSERT INTO MESSAGE (MESSAGEID, SUBJECTID, CONTENT, SENDERID, DATETIME) VALUES ('" + messageid_db + "', '" + subjectid_db + "', '" + content + "', '" + senderid + "', '" + datetime + "')";
			System.out.println(query5);
			stmt3.executeUpdate(query5);
			// querry6: insert MESSAGERECEIVER: other receivers
			for(int i = 0; i < receiverid_array.length; i++)
			{
				// get primary key +1
				Statement stmt4 = conn.createStatement();
				String query6 = "SELECT MESSAGERECEIVERID FROM MESSAGERECEIVER";
				System.out.println(query6);
				ResultSet rs4 = stmt4.executeQuery(query6);
				while(rs4.next())
				{
					messagereceiverid_db = rs4.getInt(1);
				}
				messagereceiverid_db = messagereceiverid_db + 1;
				// (querry7)
				receiverid1_db = (String)receiverid_array[i];
				receiverid_db = Integer.parseInt(receiverid1_db);
				String query7 = "INSERT INTO MESSAGERECEIVER (MESSAGERECEIVERID, RECEIVERID, MESSAGEID) VALUES ('" + messagereceiverid_db + "', '" + receiverid_db + "', '" + messageid_db + "')";
				System.out.println(query7);
				stmt3.executeUpdate(query7);
			}
			// querry8: insert MESSAGERECEIVER: hostid
			for(int i = 0; i < receiverid_array.length; i++)
			{
				// get primary key +1
				Statement stmt5 = conn.createStatement();
				String query8 = "SELECT MESSAGERECEIVERID FROM MESSAGERECEIVER";
				System.out.println(query8);
				ResultSet rs5 = stmt5.executeQuery(query8);
				while(rs5.next())
				{
					messagereceiverid_db = rs5.getInt(1);
				}
				messagereceiverid_db = messagereceiverid_db + 1;
				// (querry9)
				receiverid1_db = (String)receiverid_array[i];
				receiverid_db = Integer.parseInt(receiverid1_db);
				String query9 = "INSERT INTO MESSAGERECEIVER (MESSAGERECEIVERID, RECEIVERID, MESSAGEID) VALUES ('" + messagereceiverid_db + "', '" + senderid + "', '" + messageid_db + "')";
				System.out.println(query9);
				stmt3.executeUpdate(query9);
			}
								
			// redirect webpage
				System.out.println("Create message successfully!");
				response.sendRedirect("jsp/Message/message.jsp");
				
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
