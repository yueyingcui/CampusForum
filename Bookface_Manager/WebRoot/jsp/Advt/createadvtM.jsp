<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Create Advertisement (Manager) </title>
    
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
    <form id=createadvt action='CreateAdvtSM' name=createadvt method="post">
        Create Advertisement: (Manager) <br>
        Type: <input id=type type="text" name=type><br>
        Company: <input id=company type="text" name=company><br>
        Item Name: <input id=itemname type="text" name=itemname><br>
        Unit Price: <input id=unitprice type="text" name=unitprice><br>
        Number of Available Units: <input id=numberofavailableunits type="text" name=numberofavailableunits><br>
		<input id=createadvt type="submit" name=createadvt value="Create Advertisement!">
    </form>
  </body>
</html>
