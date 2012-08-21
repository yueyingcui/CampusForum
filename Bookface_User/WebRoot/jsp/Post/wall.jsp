<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Wall </title>
    
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
    <form id=wall action='WallS' name=wall method="post">
        Go Wall! <br>
        <% String page_ownerid = (String)request.getAttribute("page_ownerid"); %>
        <input id=page_ownerid type="hidden" name=page_ownerid value=<%=page_ownerid%>>
		<input id=createpost type="submit" name=createpost value="Create Post">
		<input id=postlist type="submit" name=postlist value="Post List">
    </form> 
  </body>
</html>
