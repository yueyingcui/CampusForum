<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Welcome </title>
    
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
			location.href="../Friend/friend.jsp";
		if(value==1)
			location.href="../Circle/circle.jsp";
		if(value==2)
			location.href="../Post/post.jsp";
		if(value==3)
			location.href="../Message/message.jsp";
		if(value==4)
			location.href="../SIP/sip.jsp";
		if(value==5)
			location.href="../Advt/advt.jsp";
	}
  </script>
  
  <body>
  	<form  action='WelcomeS' name=welcome method=get>
    	Welcome to Bookface! <br>
    	
    	<input id=friend type="button" onclick="msg(0)" name=friend value="Friend">
    	<input id=circle type="button" onclick="msg(1)" name=circle value="Circle">
    	<input id=post type="button" onclick="msg(2)" name=post value="Post">
    	<input id=message type="button" onclick="msg(3)" name=message value="Message">
    	<input id=sip type="button" onclick="msg(4)" name=sip value="SIP">
    	<input id=advt type="button" onclick="msg(5)" name=advt value="Advertisement">
    </form>
    
    <form  action='WelcomeSProfileS' name=profile method=get>
    	<input id=profile type="submit" name=profile value="Profile"><br>
    </form>
  </body>
</html>
