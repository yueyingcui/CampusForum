<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Modify Post (SIP) </title>
    
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
    <form id=modifypost action='ModifyPostSSip' name=modifypost method="post">
        Modify post: <br>
        <% String postid = (String)request.getAttribute("postid");%>
        <input id=postid type="hidden" name=postid value=<%=postid%>>
        <input id=content type="text" name=content>
		<input id=modifypost type="submit" name=modifypost value="Modify Post!">
    </form>
  </body>
</html>
