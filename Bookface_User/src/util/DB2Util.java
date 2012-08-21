package util;

import java.sql.*;
import java.util.Random;

public class DB2Util {
	public static void main(String[] args) {
		try{
			// connect to DB2
			Connection connection=null;
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url="jdbc:db2://Db2serv01.cs.stonybrook.edu:50000/teamdb49:retrieveMessagesFromServerOnGetMessage=true;";
			String user="cseteam49";
			String pass="Spring2012";
			connection=DriverManager.getConnection(url,user,pass);
			System.out.println("Connected and working!");
			/*
			// create update
			Statement stmt1 = connection.createStatement();
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(100);
			String update1 = "INSERT INTO STUDENT VALUES("+ randomInt +", 'Paul')";
			System.out.println(update1);
			stmt1.executeUpdate(update1);
			// select
			Statement stmt2 = connection.createStatement();
			String query = "SELECT * FROM STUDENT";
			System.out.println(query);
			ResultSet rs = stmt2.executeQuery(query);
			while(rs.next()){
				String id = rs.getString("ID");
				System.out.println(id);
			}
			*/
			// close connect
			connection.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
}
