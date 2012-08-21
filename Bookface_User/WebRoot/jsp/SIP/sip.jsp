<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> SIP </title>
    
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
    <form id=sip action='SipS' name=sip method="post">
        Go SIPs! <br>
		<input id=createsip type="submit" name=createsip value="Create SIP">
		<input id=searchsip type="submit" name=searchsip value="Search SIP">
		<input id=siplist type="submit" name=siplist value="SIP Lists">
		<input id=confirmsip type="submit" name=confirmsip value="Confirm SIP Requests">
    </form>
  </body>
</html>
