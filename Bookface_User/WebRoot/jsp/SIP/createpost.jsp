<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Create Post (SIP) </title>
    
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
    <form id=createpost action='CreatePostSSip' name=createpost method="post">
        Create post: <br>
        <% String sipid_choose = (String)request.getAttribute("sipid_choose"); %>
        <input id=sipid_choose type="hidden" name=sipid_choose value=<%=sipid_choose%>>
        <input id=content type="text" name=content>
		<input id=createpost type="submit" name=createpost value="Create Post!">
    </form>
  </body>
</html>
