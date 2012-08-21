<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Analysis Customer </title>
    
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
    <form id=rlitemlist action='RLItemListSM' name=rlitemlist method="post">
    	Costumer Analysis:<br>
    	<% 
			String result_firstname = (String)request.getAttribute("result_firstname");
			String result_lastname = (String)request.getAttribute("result_lastname");%>
			Most revenue customer is <%out.print(result_firstname);%>.<%out.print(result_lastname);%><br>
	</form>
  </body>
</html>
