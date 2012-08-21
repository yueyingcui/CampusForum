<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Circle List </title>
    
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
  	<form id=circlelist action='CircleListS' name=circlelist method="post">
    	My Circles: <br>
    	<% 
			
			ArrayList circleid_list = (ArrayList)request.getAttribute("circleid_list");
			ArrayList circlename_list = (ArrayList)request.getAttribute("circlename_list");
			if(circleid_list.isEmpty() || circlename_list.isEmpty())
			{
				;
			}
			
			String circleid_choose = "";
			for(int i = 0; i < circleid_list.size(); i++)
			{
				out.print(circlename_list.get(i));
				circleid_choose = (String)circleid_list.get(i);%>
				<input type="hidden" name="circleid_choose" value=<%=circleid_choose%>>
				<input type="submit" name=whos value="Whos?">
				<input type="submit" name=dropcircle value="Drop Circle!">
			<%}%>
	</form>
  </body>
</html>
