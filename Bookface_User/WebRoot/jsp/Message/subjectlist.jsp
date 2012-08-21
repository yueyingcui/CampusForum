<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Subject List </title>
    
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
    <form id=subjectlist action='SubjectListS' name=subjectlist method="post">
    	Subjects:<br>
    	<% 
			// post list
			ArrayList subjectid_list = (ArrayList)request.getAttribute("subjectid_list");
			ArrayList subjectname_list = (ArrayList)request.getAttribute("subjectname_list");
			if(subjectid_list.isEmpty() || subjectname_list.isEmpty())
			{
				response.sendRedirect("jsp/Login/login.jsp");
			}
			String subjectid_choose = "";
			String subjectname = "";
			for(int i = 0; i < subjectid_list.size(); i++)
			{
				subjectid_choose = (String)subjectid_list.get(i);
				subjectname = (String)subjectname_list.get(i);%>
				<input id=subjectid_choose type="hidden" name=subjectid_choose value=<%=subjectid_choose%>>
				<%out.print(subjectname);%>
				<input id=messagelist type="submit" name=messagelist value="Message List"><br>
			<%}%>
	</form>
  </body>
</html>
