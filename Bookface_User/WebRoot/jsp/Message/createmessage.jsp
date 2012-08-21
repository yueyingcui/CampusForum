<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Create Message </title>
    
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
    <form id=createmessage action='CreateMessageS' name=createmessage method="post">
        Create message: <br>
        Subject:<br>
        <%ArrayList subjectid_list = (ArrayList)request.getAttribute("subjectid_list");
        ArrayList subject_name_list = (ArrayList)request.getAttribute("subject_name_list");
        if(subjectid_list.isEmpty() || subject_name_list.isEmpty())
        {
        	;
        }
        String subjectid = "";
        String subject_name = "";
        out.print("&nbsp" + "&nbsp" + "&nbsp");%> 
        <select name=subjectname>
        <%for(int i = 0; i < subjectid_list.size(); i++)
        {
        	subjectid = (String)subjectid_list.get(i);
        	subject_name = (String)subject_name_list.get(i);%>
        	<option value=<%=subject_name%>><%=subject_name%></option>
        <%}%>
        <option value="new">new</option>
        </select>
        <%out.print("&nbsp" + "&nbsp");%> 
        <input id=newsubjectname type="text" name=newsubjectname><br>
        
        Receiver:<br>
        <%ArrayList friendid_list = (ArrayList)request.getAttribute("friendid_list");
        ArrayList friend_firstname_list = (ArrayList)request.getAttribute("friend_firstname_list");
        ArrayList friend_lastname_list = (ArrayList)request.getAttribute("friend_lastname_list");
        if(friend_firstname_list.isEmpty() || friend_lastname_list.isEmpty())
        {
        	;
        }
        String friendid = "";
        String friend_firstname = "";
        String friend_lastname = "";
		for(int i = 0; i < friend_firstname_list.size(); i++)
        {
        	friendid = (String)friendid_list.get(i);
        	friend_firstname = (String)friend_firstname_list.get(i);
        	friend_lastname = (String)friend_lastname_list.get(i);
        	out.print("&nbsp" + "&nbsp" + "&nbsp");
        	out.print(friend_firstname);%>.<%out.print(friend_lastname);%>
		    <input type="checkbox" name=receiverid value=<%=friendid%>><br>
        <%;}%>

		Content:<input id=content type="text" name=content><br>
		<input id=send type="submit" name=content value="Send!"><br>
    </form>
  </body>
</html>
