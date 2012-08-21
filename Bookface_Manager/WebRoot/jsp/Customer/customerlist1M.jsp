<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'customerlist1M.jsp' starting page</title>
    
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
    <form id=tlcustomerlist action='CustomerList1SM' name=tlcustomerlist method="post">
    	Customer List:<br>
    	<% 
			ArrayList transactionid_list = (ArrayList)request.getAttribute("transactionid_list");
			ArrayList firstname_list = (ArrayList)request.getAttribute("firstname_list");
			ArrayList lastname_list = (ArrayList)request.getAttribute("lastname_list");
			//if(advertisementid_list.isEmpty() || itemname_list.isEmpty())
			{
				;
			}
			String transactionid_choose = "";
			for(int i = 0; i < transactionid_list.size(); i++)
			{
				transactionid_choose = (String)transactionid_list.get(i);%>
				<%out.print(firstname_list.get(i));%>.<%out.print(lastname_list.get(i));%>
				<input type="hidden" name=transactionid_choose value=<%=transactionid_choose%>>
				<input type="submit" name=transactionlist value="Suggestion Item List"><br>
			<%}%>
	</form>
  </body>
</html>
