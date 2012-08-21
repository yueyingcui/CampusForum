<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Welcome (Manager)</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <script language="javascript">
	function msg(value)
	{
		if(value==0)
			location.href="../Advt/advtM.jsp";
		if(value==1)
			location.href="../Transaction/transactionlistM.jsp";
		if(value==2)
			location.href="../Revenue/revenuelistM.jsp";
		if(value==3)
			location.href="../Customer/customerlistM.jsp";
		if(value==4)
			location.href="../Analysis/analysisM.jsp";
	}
  </script>
  
  <body>
  	<form id=welcome action='WelcomeS' name=welcome method=get>
    	Welcome to Bookface! (Menager) <br>
    	
    	<input id=advt type="button" onclick="msg(0)" name=advt value="Advertisement">
    	<input id=transactionlist type="button" onclick="msg(1)" name=transactionlist value="Transaction List">
    	<input id=revenuelist type="button" onclick="msg(2)" name=revenuelist value="Revenue List">
    	<input id=customerlist type="button" onclick="msg(3)" name=customerlist value="Customer List">
    	<input id=analysis type="button" onclick="msg(4)" name=analysis value="Analysis">
    </form>
  </body>
</html>
