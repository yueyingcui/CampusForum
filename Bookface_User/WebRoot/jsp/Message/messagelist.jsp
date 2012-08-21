<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Meesage List </title>
    
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
    <form id=messagelist action='MessageListS' name=messagelist method="post">
    	Messages:<br>
    	<% 
			// message list
			ArrayList messageid_list = (ArrayList)request.getAttribute("messageid_list");
			ArrayList content_list = (ArrayList)request.getAttribute("content_list");
			ArrayList senderid_list = (ArrayList)request.getAttribute("senderid_list");
			ArrayList sender_firstname_list = (ArrayList)request.getAttribute("sender_firstname_list");
			ArrayList sender_lastname_list = (ArrayList)request.getAttribute("sender_lastname_list");
			ArrayList datetime_list = (ArrayList)request.getAttribute("datetime_list");
			if(content_list.isEmpty() || sender_firstname_list.isEmpty() || sender_lastname_list.isEmpty() || datetime_list.isEmpty())
			{
				response.sendRedirect("jsp/Login/login.jsp");
			}
			// assign hostid
			int hostid = 0;
			HttpSession session1 = request.getSession();
			hostid = (Integer)session1.getAttribute("hostid");
			String messageid = "";
			String content = "";
			String senderid = "";
			String sender_firstname = "";
			String sender_lastname = "";
			String datetime = "";
			for(int i = 0; i < content_list.size(); i++)
			{
				messageid = (String)messageid_list.get(i);
				content = (String)content_list.get(i);
				senderid = (String)senderid_list.get(i);
				sender_firstname = (String)sender_firstname_list.get(i);
				sender_lastname = (String)sender_lastname_list.get(i);
				datetime = (String)datetime_list.get(i);%>
				<input id=messageid_choose type="hidden" name=messageid_choose value=<%=messageid%>>
				<%out.print(content);%><br>
				<%out.print(sender_firstname);%>.<%out.print(sender_lastname);%> <%out.print(datetime);%><br>
				<%if(senderid.equals(hostid)) %>
					<input id=deletemessage type="submit" name=deletemessage value="Delete Message!">
			<%;}%>
	</form>
  </body>
</html>
