<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> App Member List </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form id=appmemberlist action='AppMemberListS' name=appmemberlist method="post">
    	App member list:<br>
    	<% 
			// post list
			String sipid_choose = (String)request.getAttribute("sipid_choose");
			ArrayList senderid_list = (ArrayList)request.getAttribute("senderid_list");
			ArrayList sender_firstname_list = (ArrayList)request.getAttribute("sender_firstname_list");
			ArrayList sender_lastname_list = (ArrayList)request.getAttribute("sender_lastname_list");
			if(senderid_list.isEmpty() || sender_firstname_list.isEmpty() || sender_lastname_list.isEmpty())
			{
				;
			}
			
			String senderid_choose = "";
			String sender_firstname = "";
			String sender_lastname = "";
			for(int i = 0; i < senderid_list.size(); i++)
			{
				// post choose
				sender_firstname = (String)sender_firstname_list.get(i);
				sender_lastname = (String)sender_lastname_list.get(i);
				out.print(sender_firstname);%>. <%out.print(sender_lastname);%><br>
								
				<%senderid_choose = (String)senderid_list.get(i);%>
				<input type="hidden" name="senderid_choose" value=<%=senderid_choose%>>
				<input type="hidden" name="sipid_choose" value=<%=sipid_choose%>>
				<input type="submit" name=accept value="Accept">
				<input type="submit" name=deny value="Deny">	
			<%}%>
	</form>
  </body>
</html>
