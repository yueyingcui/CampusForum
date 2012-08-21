<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Invitation List </title>
    
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
    <form id=invitationlist action='InvitationListS' name=invitationlist method="post">
    	SIP Invitaton:<br>
    	<% 
			// post list
			ArrayList siprequestinvitationid_list = (ArrayList)request.getAttribute("siprequestinvitationid_list");
			ArrayList sipname_list = (ArrayList)request.getAttribute("sipname_list");
			ArrayList sender_firstname_list = (ArrayList)request.getAttribute("sender_firstname_list");
			ArrayList sender_lastname_list = (ArrayList)request.getAttribute("sender_lastname_list");
			ArrayList content_list = (ArrayList)request.getAttribute("content_list");
			if(sipname_list.isEmpty() || sender_firstname_list.isEmpty() || sender_lastname_list.isEmpty() || content_list.isEmpty() || siprequestinvitationid_list.isEmpty())
			{
				;
			}
			
			for(int i = 0; i < sipname_list.size(); i++)
			{
				// post choose%>
				SIP Name:<%out.print(sipname_list.get(i));%><br>
				Content:<%out.print(content_list.get(i));%><br>
				Sender:<%out.print(sender_firstname_list.get(i));%>. <%out.print(sender_lastname_list.get(i));%><br>
				<input id=siprequestinvitationid_choose type="hidden" name=siprequestinvitationid_choose value=<%=(String)siprequestinvitationid_list.get(i)%>>	
				<input id=deleteinvitation type="submit" name=deleteinvitation value="Delete">			
			<%}%>
	</form>
  </body>
</html>
