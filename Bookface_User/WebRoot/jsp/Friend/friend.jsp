<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Friends </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
	<% /*<script language="javascript">
	function msg(value)
	{
		if(value==0)
			location.href="friendlist.jsp";
		if(value==1)
			location.href="searchfriend.jsp";	
		if(value==2)
			location.href="confirmfriend.jsp";
	}
  </script> */%>
  
  <body>
  	Go Friends! <br>
  	<form id=friendlist action='FriendSFriendListS' name=friendlist method="post"> 
    	<%-- friend list!!! --%>
    	<%-- <input id=friendlist type="button"  onclick="msg(0)" name=friendlist value="My Friends"> --%>
    	<input id=friendlist type="submit"  name=friendlist value="My Friends">
    </form>
    
    <form id=searchfriend action='FriendSSearchFriendS' name=searchfriend method="post">
    	<%-- <input id=searchfriend type="button"  onclick="msg(1)" name=searchfriend value="Search Firend"> --%>
    	<input id=searchfriend type="submit" name=searchfriend value="Search Firend">
    </form>
    
    <form id=friendrequest action='FriendSConfirmFriendS' name=friendrequest method="post">
    	<%-- <input id=confirmfriend type="button"  onclick="msg(2)" name=confirmfriend value="+Firend Request"> --%>
    	<input id=confirmfriend type="submit" name=confirmfriend value="+Firend Request">
    	<%-- potential friends!!! --%>
    </form>
  </body>
</html>
