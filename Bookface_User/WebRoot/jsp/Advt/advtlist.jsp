<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Advertisement List </title>
    
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
    <form id=advtlist action='AdvtListS' name=advtlist method="post">
        Advertisement list: <br>
        <%ArrayList advertisementid_list = (ArrayList)request.getAttribute("advertisementid_list");
        ArrayList date_list = (ArrayList)request.getAttribute("date_list");
        ArrayList company_list = (ArrayList)request.getAttribute("company_list");
        ArrayList itemname_list = (ArrayList)request.getAttribute("itemname_list");
        ArrayList unitprice_list = (ArrayList)request.getAttribute("unitprice_list");
        ArrayList number_of_available_units_list = (ArrayList)request.getAttribute("number_of_available_units_list");
        if(advertisementid_list.isEmpty())
        {
        	;
        }
        
        for(int i = 0; i < advertisementid_list.size(); i++)
        {%>
        	Item Name: <%out.print((String)itemname_list.get(i));%><br>
        	Unit Price: <%out.print((String)unitprice_list.get(i));%><br>
        	Number of Available Units: <%out.print((String)number_of_available_units_list.get(i));%><br>
        	Date: <%out.print((String)date_list.get(i));%><br>
        	Company Name: <%out.print((String)company_list.get(i));%><br>
        	<input id=advertisementid_choose type="hidden" name=advertisementid_choose value=<%=(String)advertisementid_list.get(i)%>>
        	<input id=purchase type="submit" name=purchase value="Purchase!"><br>
        <%}%>		
    </form>
  </body>
</html>
