<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Month List (to Transaction, Manager) </title>
    
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
    <form id=tlmonthlist action='TLMonthListSM' name=tlmonthlist method="post">
    	Month List (to Transaction):<br>
    	<% 
			int month_choose = 0;
			for(int i = 1; i < 13; i++)
			{
				month_choose = i;
				// Jan...!!!
				out.print(i);%>
				<input type="hidden" name=month_choose value=<%=month_choose%>>
				<input type="submit" name=transactionlist value="Transaction List"><br>
			<%}%>
	</form>
  </body>
</html>
