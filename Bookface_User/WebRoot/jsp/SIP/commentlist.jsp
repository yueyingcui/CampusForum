<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Comment List (SIP) </title>
    
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
    <form id=commentlist action='CommentListSSip' name=commentlist method="post">
    	Comment:<br>
    	<% 
			// post list
			ArrayList comment_firstname_list = (ArrayList)request.getAttribute("comment_firstname_list");
			ArrayList comment_lastname_list = (ArrayList)request.getAttribute("comment_lastname_list");
			ArrayList content_list = (ArrayList)request.getAttribute("content_list");
			ArrayList datetime_list = (ArrayList)request.getAttribute("datetime_list");
			if(comment_firstname_list.isEmpty() || comment_lastname_list.isEmpty() || content_list.isEmpty() || datetime_list.isEmpty())
			{
				;
			}
			
			String datetime = "";
			for(int i = 0; i < comment_firstname_list.size(); i++)
			{
				datetime = (String)datetime_list.get(i);
				out.print(content_list.get(i));%><br>
				<%out.print(comment_firstname_list.get(i));%>. <%out.print(comment_lastname_list.get(i));%> <%out.print(datetime);%><br>
			<%}%>
	</form>
  </body>
</html>
