<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Advertisement List (Manager) </title>
    
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
    <form id=advtlist action='AdvtListSM' name=advtlist method="post">
    	Posts:<br>
    	<% 
			// advt list
			ArrayList advertisementid_list = (ArrayList)request.getAttribute("advertisementid_list");
			ArrayList type_list = (ArrayList)request.getAttribute("type_list");
			ArrayList date_list = (ArrayList)request.getAttribute("date_list");
			ArrayList company_list = (ArrayList)request.getAttribute("company_list");
			ArrayList itemname_list = (ArrayList)request.getAttribute("itemname_list");
			ArrayList unitprice_list = (ArrayList)request.getAttribute("unitprice_list");
			ArrayList numberofavailableunits_list = (ArrayList)request.getAttribute("numberofavailableunits_list");
			if(advertisementid_list.isEmpty() || type_list.isEmpty() || date_list.isEmpty() || company_list.isEmpty() || itemname_list.isEmpty() || unitprice_list.isEmpty() || numberofavailableunits_list.isEmpty())
			{
				;
			}
			
			String mailinglist = "";
			String deleteadvt = "";
			String advertisementid_choose = "";
			for(int i = 0; i < advertisementid_list.size(); i++)
			{%>
				Type: <%out.print((String)type_list.get(i));%><br>
				Date: <%out.print((String)date_list.get(i));%><br>
				Company: <%out.print((String)company_list.get(i));%><br>
				Item Name:<%out.print((String)itemname_list.get(i));%><br>
				Unit Price:<%out.print((String)unitprice_list.get(i));%><br>
				Number of Available Units:<%out.print((String)numberofavailableunits_list.get(i));%><br>
								
				<%advertisementid_choose = (String)advertisementid_list.get(i);
				mailinglist = "mailinglist" + Integer.toString(i);
				deleteadvt = "deleteadvt" + Integer.toString(i);
				advertisementid_choose = "advertisementid_choose" + Integer.toString(i);%>
				<%--<input type="hidden" name=<%=advertisementid_choose%> value=<%=advertisementid_choose%>> 		
				<input type="submit" name=<%=mailinglist%> value="Mailing List">
				<input type="submit" name=<%=deleteadvt%> value="Delete Advertisement"><br>--%>
				<input type="hidden" name=advertisementid_choose value=<%=(String)advertisementid_list.get(i)%>>
				<input type="submit" name=mailinglist value="Mailing List">
				<input type="submit" name=deleteadvt value="Delete Advertisement"><br>
				
			<%}%>
	</form>
  </body>
</html>
