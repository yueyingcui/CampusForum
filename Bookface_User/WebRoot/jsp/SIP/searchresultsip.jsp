<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Search Result (SIP) </title>
    
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
    <form id=searchresultsip action='SearchResultSSip' name=searchresultsip method="post">
    	Is it the SIP? <br>    	
		<% 
			ArrayList sipid_list = (ArrayList)request.getAttribute("sipid_list");
			ArrayList sipname_list = (ArrayList)request.getAttribute("sipname_list");
			if(sipid_list.isEmpty() && sipname_list.isEmpty())
			{
				;
			}
			
			for(int i = 0; i < sipid_list.size(); i++)
			{%>
				<% out.print(sipname_list.get(i)); %>
				<% String sipid_choose = (String)sipid_list.get(i); %>
				<input type="hidden" name="sipid_choose" value=<%=sipid_choose%>>
				<input type="submit" value="+SIP!">			
			<%}%>
    </form>
  </body>
</html>
