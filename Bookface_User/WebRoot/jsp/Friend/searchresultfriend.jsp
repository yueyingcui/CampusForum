<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" import="java.util.ArrayList" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Search Result (Friend) </title>
    
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
  	<form id=searchresultfriend action='SearchResultSFriend' name=searchresultfriend method="post">
    	He/She is your friend? <br>
		<% 
			
			ArrayList userid_list = (ArrayList)request.getAttribute("userid_list");
			ArrayList firstname_list = (ArrayList)request.getAttribute("firstname_list");
			ArrayList lastname_list = (ArrayList)request.getAttribute("lastname_list");
			//ArrayList<String> userid_list = new ArrayList<String>();
			//ArrayList<String> firstname_list = new ArrayList<String>();
			//ArrayList<String> lastname_list = new ArrayList<String>();
			//String userid = (String)request.getSession().getAttribute("userid");
			//String firstname = (String)request.getSession().getAttribute("firstname");
			//String lastname = (String)request.getSession().getAttribute("lastname");
			//userid_list.add(userid);
			//firstname_list.add(firstname);
			//lastname_list.add(lastname);
			if(firstname_list.isEmpty() && lastname_list.isEmpty())
			{
				;
			}
			
			for(int i = 0; i < firstname_list.size(); i++)
			{%>
				<%-- hide userid!!! --%>
				<% out.print(firstname_list.get(i)); %>.<% out.print(lastname_list.get(i)); %> <% out.print(userid_list.get(i)); %>
				<% String userid_choose = (String)userid_list.get(i); %>
				<%-- <% out.print(userid_choose); %> --%>
				<input type="hidden" name="userid_choose" value=<%=userid_choose%>>
				<input type="submit" value="+Friend">			
			<%}%>
    	
    	
    </form>
  </body>
</html>
