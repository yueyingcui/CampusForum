<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Mailing List (Manager) </title>
    
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
    <form id=mailinglist action='MailingListSM' name=mailinglist method="post">
    	Mailing List:<br>
    	<% 
			// advt list
			ArrayList email_list = (ArrayList)request.getAttribute("email_list");
			if(email_list.isEmpty())
			{
				;
			}
			
			for(int i = 0; i < email_list.size(); i++)
			{%>
				<%out.print(email_list.get(i));%><br>
			<%}%>
	</form>
  </body>
</html>
