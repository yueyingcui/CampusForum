<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Post List (SIP) </title>
    
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
    <form id=postlist action='PostListSSip' name=postlist method="post">
    	Posts:<br>
    	<% 
			// post list
			ArrayList sippostid_list = (ArrayList)request.getAttribute("sippostid_list");
			ArrayList post_owner_firstname_list = (ArrayList)request.getAttribute("post_owner_firstname_list");
			ArrayList post_owner_lastname_list = (ArrayList)request.getAttribute("post_owner_lastname_list");
			ArrayList content_list = (ArrayList)request.getAttribute("content_list");
			ArrayList datetime_list = (ArrayList)request.getAttribute("datetime_list");
			//if(sippostid_list.isEmpty() || post_owner_firstname_list.isEmpty() || post_owner_lastname_list.isEmpty() || content_list.isEmpty() || datetime_list.isEmpty())
			if(sippostid_list.isEmpty())
			{
				;
			}
			
			String post_owner_firstname = "";
			String post_owner_lastname = "";
			String datetime = "";
			String sippostid_choose = "";
			for(int i = 0; i < sippostid_list.size(); i++)
			{
				// post choose
				post_owner_firstname = (String)post_owner_firstname_list.get(i);
				post_owner_lastname = (String)post_owner_lastname_list.get(i);
				datetime = (String)datetime_list.get(i);
				out.print(content_list.get(i));%><br>
				<%out.print(post_owner_firstname);%>. <%out.print(post_owner_lastname);%> <%out.print(datetime);%><br>
								
				<%sippostid_choose = (String)sippostid_list.get(i);%>
				<input type="hidden" name="sippostid_choose" value=<%=sippostid_choose%>>
				<input type="submit" name=createcomment value="Create Comment">
				<input type="submit" name=commentlist value="Comment List">				
				<%//if(source.equals("Wall"))
				{%>
					<input type="submit" name=modifypost value="Modify Post">
					<input type="submit" name=deletepost value="Delete Post"><br>
				<%};
			}%>
	</form>
  </body>
</html>
