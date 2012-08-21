<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Item List (to revenue) </title>
    
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
    <form id=rlitemlist action='RLItemListSM' name=rlitemlist method="post">
    	Item List (to Transaction):<br>
    	<% 
			ArrayList advertisementid_list = (ArrayList)request.getAttribute("advertisementid_list");
			ArrayList itemname_list = (ArrayList)request.getAttribute("itemname_list");
			ArrayList sum_revenue_list = (ArrayList)request.getAttribute("sum_revenue_list");
			if(advertisementid_list.isEmpty() || itemname_list.isEmpty())
			{
				;
			}
			
			//ArrayList revenue_item_list = (ArrayList)request.getAttribute("revenue_item_list");
			for(int i = 0; i < itemname_list.size(); i++)
			{
				out.print(itemname_list.get(i));
				out.print(sum_revenue_list.get(i));%><br>
			<%}%>
	</form>
  </body>
</html>
