<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Customer List (by item) </title>
    
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
    <form id=tlilcustomerlist action='TLILCutomerListSM' name=tlilcustomerlist method="post">
    	Transaction List (by item):<br>
    	<% 
			ArrayList transactionid_list = (ArrayList)request.getAttribute("transactionid_list");
			ArrayList firstname_list = (ArrayList)request.getAttribute("firstname_list");
			ArrayList lastname_list = (ArrayList)request.getAttribute("lastname_list");
			if(transactionid_list.isEmpty() || firstname_list.isEmpty() || lastname_list.isEmpty())
			{
				;
			}
			for(int i = 0; i < transactionid_list.size(); i++)
			{%>
				Customer: <%out.print(firstname_list.get(i));%>.<%out.print(lastname_list.get(i));%><br>
			<%}%>
	</form>
  </body>
</html>
