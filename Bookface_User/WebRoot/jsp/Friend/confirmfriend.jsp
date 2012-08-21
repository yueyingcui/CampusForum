<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Confirm Friend </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <%-- auto call servlet --%>
  <%-- <script>  --%> 
	<%-- if(1 == 1) document.all.showGoodsForm.submit(); --%>
  <%-- </script>  --%> 
  
  <body>
  	<form id=confirmfriend action='ConfirmFriendS' name=confirmfriend method="post">
    	He/She wanna +U! <br>
    	<% 
			
			ArrayList senderid_list = (ArrayList)request.getAttribute("senderid_list");
			ArrayList sender_firstname_list = (ArrayList)request.getAttribute("sender_firstname_list");
			ArrayList sender_lastname_list = (ArrayList)request.getAttribute("sender_lastname_list");
			//String news = (String)request.getAttribute("news");
			
			//if(senderid_list.isEmpty() || sender_firstname_list.isEmpty() || sender_lastname_list.isEmpty())
			if(senderid_list == null || sender_firstname_list == null || sender_lastname_list == null)
			{%>
				No news hahahaha!<br>
			<%}
			//if(news.equals("false"))
			//{
				//No news!<br>
			//}
			else
			{
				for(int i = 0; i < sender_firstname_list.size(); i++)
				{
					out.print(sender_firstname_list.get(i)); %>.<% out.print(sender_lastname_list.get(i)); %> <% out.print(senderid_list.get(i)); %>
					<% String userid_choose = (String)senderid_list.get(i); %>
					<%-- <% out.print(userid_choose); %> --%>
					<input type="hidden" name="userid_choose" value=<%=userid_choose%>>
					<input type="submit" value="+Friend">			
				<%}
			}%>
    </form>
  </body>
</html>
