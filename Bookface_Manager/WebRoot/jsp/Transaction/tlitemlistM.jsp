<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Item List (to Transaction, Manager) </title>
    
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
  	<form id=tlitemlist action='TLItemListSM' name=tlitemlist method="post">
    	Item List (to Transaction):<br>
    	<% 
			ArrayList advertisementid_list = (ArrayList)request.getAttribute("advertisementid_list");
			ArrayList itemname_list = (ArrayList)request.getAttribute("itemname_list");
			if(advertisementid_list.isEmpty() || itemname_list.isEmpty())
			{
				;
			}
			String advertisementid_choose = "";
			for(int i = 0; i < itemname_list.size(); i++)
			{
				advertisementid_choose = (String)advertisementid_list.get(i);
				out.print(itemname_list.get(i));%><br>
				<input type="hidden" name=advertisementid_choose value=<%=advertisementid_choose%>>
				<input type="submit" name=transactionlist value="Transaction List">
				<input type="submit" name=customerlist value="Customer List"><br>
			<%}%>
	</form>
  </body>
</html>
