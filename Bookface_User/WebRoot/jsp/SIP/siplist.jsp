<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> SIP List </title>
    
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
    <form id=siplist action='SipListS' name=siplist method="post">
    	SIPs:<br>
    	<% 
			// sip list
			ArrayList sipid_list = (ArrayList)request.getAttribute("sipid_list");
			ArrayList sipname_list = (ArrayList)request.getAttribute("sipname_list");
			if(sipid_list.isEmpty() || sipname_list.isEmpty())
			{
				;
			}
			
			String sipid_choose = "";
			String sipname = "";
			for(int i = 0; i < sipid_list.size(); i++)
			{
				// sip choose
				sipid_choose = (String)sipid_list.get(i);
				sipname = (String)sipname_list.get(i);
				out.print(sipname);%>
				<input type="hidden" name="sipid_choose" value=<%=sipid_choose%>>
				<input type="submit" name=createpost value="Create Post">
				<input type="submit" name=postlist value="Post List">				
				<%//if(source.equals("Wall"))
				{%>
					<input type="submit" name=appmoderator value="+Moderator">
				<%};%><br>
			<%}%>
	</form>
  </body>
</html>
