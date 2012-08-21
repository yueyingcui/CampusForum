<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Purchase </title>
    
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
    <form id=purchase action='PurchaseS' name=purchase method="post">
        Set Preference: <br>
        <%String advertisementid_choose = (String)request.getAttribute("advertisementid_choose");
        if(advertisementid_choose.equals(null))
        {
        	;
        }%>
        Number of Unit: <input id=numberofunit type="text" name=numberofunit><br>  
        <input id=advertisementid_choose type="hidden" name=advertisementid_choose value=<%=advertisementid_choose%>>      
		<input id=purchase type="submit" name=purchase value="Submit!"><br>
    </form>
  </body>
</html>
