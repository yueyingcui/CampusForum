<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Circle </title>
    
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
    Go Circles! <br>
    <body>
  	<form id=circlelist action='CircleSCircleListS' name=circlelist method="post"> 
    	<input id=circlelist type="submit"  name=circlelist value="My Circles">
    </form>
    
    <form id=createcircle action='CircleSCreateCircleS' name=createcircle method="post">
    	<input id=createcircle type="submit" name=createcircle value="Create Circle">
    </form>
  </body>
</html>
