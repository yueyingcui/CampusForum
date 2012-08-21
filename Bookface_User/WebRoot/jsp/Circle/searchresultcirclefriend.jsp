<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Search Result (Circle -> Friend) </title>
    
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
    <form id=searchresultcirclefriend action='SearchResultSCircleFriend' name=searchresultcirclefriend method="post">
    	<%-- show circlename --%>
    	My friends in this circle: <br>
    	<% 
		// show friend
		ArrayList circlemember_userid_list = (ArrayList)request.getAttribute("circlemember_userid_list");
		ArrayList circlemember_firstname_list = (ArrayList)request.getAttribute("circlemember_firstname_list");
		ArrayList circlemember_lastname_list = (ArrayList)request.getAttribute("circlemember_lastname_list");
		if(circlemember_firstname_list.isEmpty() || circlemember_lastname_list.isEmpty())
		{
			;
		}
		
		for(int i = 0; i < circlemember_firstname_list.size(); i++)
		{
			out.print(circlemember_firstname_list.get(i));%>.<%out.print(circlemember_lastname_list.get(i));%>
			<input id=friendid_choose type="hidden" name=friendid_choose value=<%=circlemember_userid_list.get(i)%>>
			<input id=removefriendfromcircle type="submit" name=removefriendfromcircle value="Remove from Circle!">		
		<% } %>		
	</form>
  </body>
</html>
