package message;

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

public class SubjectListS extends HttpServlet {

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

		System.out.println("-----message/SubjectListS.java | Message/subjectlist.jsp-----");
		try{
			// JSP information
			response.setContentType("text/html; charset=gbk"); 
			String subjectid_choose1 = request.getParameter("subjectid_choose");
			int subjectid_choose = Integer.parseInt(subjectid_choose1);
			System.out.println("subjectid_choose: " + subjectid_choose);
			
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
			int messageid_db = 0;
			String content_db = "";
			String senderid_db = "";
			int senderid1_db = 0;
			String sender_firstname_db = "";
			String sender_lastname_db = "";
			java.sql.Timestamp datetime_db = new Timestamp(java.sql.Types.TIMESTAMP);
			ArrayList messageid_list = new ArrayList();
			ArrayList<String> content_list = new ArrayList<String>();
			ArrayList senderid_list = new ArrayList();
			ArrayList<String> sender_firstname_list = new ArrayList();
			ArrayList<String> sender_lastname_list = new ArrayList();
			ArrayList<String> datetime_list = new ArrayList();
			// assign hostid
			int hostid = 0;
			HttpSession session = request.getSession();
			hostid = (Integer)session.getAttribute("hostid");
			// select MESSAGERECEIVER, save select result
			// querry1: messageid
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT MESSAGEID FROM MESSAGERECEIVER WHERE RECEIVERID = '" + hostid + "'";
			System.out.println(query1);
			ResultSet rs1 = stmt1.executeQuery(query1);
			while(rs1.next())
			{
				// assign value
				messageid_db = rs1.getInt(1);
				
				// add attribute for jsp
				messageid_list.add("" + messageid_db);
				System.out.println("messageid_list.size()=" + messageid_list.size());

				// querry2: select MESSAGE
				Statement stmt2 = conn.createStatement();
				String query2 = "SELECT CONTENT, SENDERID, DATETIME FROM MESSAGE WHERE (MESSAGEID = '" + messageid_db + "' AND SUBJECTID = '" + subjectid_choose + "')";
				System.out.println(query2);
				ResultSet rs2 = stmt2.executeQuery(query2);
				while(rs2.next())
				{
					// assign value
					content_db = rs2.getString(1);
					senderid1_db = rs2.getInt(2);
					senderid_db = Integer.toString(senderid1_db);
					datetime_db = rs2.getTimestamp(3);
					
					// add attribute for jsp
					content_list.add(content_db);
					senderid_list.add("senderid_db");
					datetime_list.add("" + datetime_db);	
					
					// querry3: select USER
					Statement stmt3 = conn.createStatement();
					String query3 = "SELECT FIRSTNAME, LASTNAME FROM USER WHERE (USERID = '" + senderid1_db + "')";
					System.out.println(query3);
					ResultSet rs3 = stmt3.executeQuery(query3);
					while(rs3.next())
					{
						// assign value
						sender_firstname_db = rs3.getString(1);
						sender_lastname_db = rs3.getString(2);
						
						// add attribute for jsp
						sender_firstname_list.add(sender_firstname_db);
						sender_lastname_list.add(sender_lastname_db);	
					}
				}
			}
			
			// set attribute for jsp
			request.setAttribute("messageid_list", messageid_list);
			request.setAttribute("content_list", content_list);
			request.setAttribute("senderid_list", senderid_list);
			request.setAttribute("sender_firstname_list", sender_firstname_list);
			request.setAttribute("sender_lastname_list", sender_lastname_list);
			request.setAttribute("datetime_list", datetime_list);
			System.out.println("content_list.size()=" + content_list.size());
			System.out.println("senderid_list.size()=" + senderid_list.size());
			System.out.println("sender_firstname_list.size()=" + sender_firstname_list.size());
			System.out.println("sender_lastname_list.size()=" + sender_lastname_list.size());
			System.out.println("datetime_list.size()=" + datetime_list.size());
			// other characteristics!!!
			
			// redirect webpage printwriter
			if(content_list.size() == 0)
			{
				System.out.println("No message!");
			}
			else
			{
				System.out.println("Show messages!");
			}
			request.getRequestDispatcher("jsp/Message/messagelist.jsp").forward(request,response);
				
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
