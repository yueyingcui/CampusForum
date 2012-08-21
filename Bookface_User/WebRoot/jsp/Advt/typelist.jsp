<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Type List </title>
    
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
    <form id=typelist action='TypeListS' name=typelist method="post">
        Advertisement type list: <br>
        <%ArrayList type_list = (ArrayList)request.getAttribute("type_list");
        if(type_list.isEmpty())
        {
        	;
        }
        String type = "";
        for(int i = 0; i < type_list.size(); i++)
        {
        	type = (String)type_list.get(i);
        	out.print(type);%>
        	<input id=type_choose type="hidden" name=type_choose value=<%=type%>>
        	<input id=type_choose type="submit" name=type_choose value="Show Advertisement!"><br>
        <%}%>		
    </form>
  </body>
</html>
