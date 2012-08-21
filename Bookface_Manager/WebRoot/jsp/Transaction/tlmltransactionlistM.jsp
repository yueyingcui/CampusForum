<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Transaction List (by month) </title>
    
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
    <form id=tlmltransactionlist action='TLMLTransactionListSM' name=tlmltransactionlist method="post">
    	Transaction List (by month):<br>
    	<% 
			ArrayList transactionid_list = (ArrayList)request.getAttribute("transactionid_list");
			ArrayList datetime_list = (ArrayList)request.getAttribute("datetime_list");
			ArrayList numberofunits_list = (ArrayList)request.getAttribute("numberofunits_list");
			ArrayList firstname_list = (ArrayList)request.getAttribute("firstname_list");
			ArrayList lastname_list = (ArrayList)request.getAttribute("lastname_list");
			ArrayList type_list = (ArrayList)request.getAttribute("type_list");
			ArrayList company_list = (ArrayList)request.getAttribute("company_list");
			ArrayList itemname_list = (ArrayList)request.getAttribute("itemname_list");
			ArrayList unitprice_list = (ArrayList)request.getAttribute("unitprice_list");
			if(transactionid_list.isEmpty() || datetime_list.isEmpty() || numberofunits_list.isEmpty() || firstname_list.isEmpty() || lastname_list.isEmpty())
			{
				;//!!!
			}
			for(int i = 0; i < transactionid_list.size(); i++)
			{%>
				DateTime: <%out.print(datetime_list.get(i));%><br>
				Number of Units: <%out.print(numberofunits_list.get(i));%><br>
				Customer: <%out.print(firstname_list.get(i));%>.<%out.print(lastname_list.get(i));%><br>
				Type: <%out.print(type_list.get(i));%><br>
				Company: <%out.print(company_list.get(i));%><br>
				Item Name: <%out.print(itemname_list.get(i));%><br>
				Unit Price: <%out.print(unitprice_list.get(i));%><br>
			<%}%>
	</form>
  </body>
</html>
