<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Login (Manager) </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>

	<%-- 1 form & multiple buttons, javascript --%>
	<script language="javascript">
	function msg(value)
	{
		if(value==0)
			location.href="jsp/Login/signup.jsp";
	}
	</script>


  <body>
  	<form id=login action='LoginSM' name=login method="post"> 
  		Login to Bookface (Manager)<br> 
  	
    	ManagerName <input id=managername type="text" name=managername><br>
    	Password <input id=password type="password" name=password><br>
    	
    	<input id=login type="submit" name=login value="Login">
    	<input id=signup type="button" onclick="msg(0)" name=signup value="SignUp"><br>
    </form>
  </body>
</html>
