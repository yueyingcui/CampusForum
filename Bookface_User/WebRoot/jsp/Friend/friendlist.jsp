<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> Friend List </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <%/*><script language="javascript">
	function msg(value)
	{
		if(value==0)
		{
			location.href="FriendListSUnfriendS";
		}
	}
	</script>*/%>
  
  <body>
  	<form id=friendlist action='FriendListS' name=friendlist method="post">
    	My Friends:<br>
    	<% 
			// friend list
			ArrayList friendid_list = (ArrayList)request.getAttribute("friendid_list");
			ArrayList friend_firstname_list = (ArrayList)request.getAttribute("friend_firstname_list");
			ArrayList friend_lastname_list = (ArrayList)request.getAttribute("friend_lastname_list");
			if(friendid_list.isEmpty() || friend_firstname_list.isEmpty() || friend_lastname_list.isEmpty())
			{
				;
			}
			
			// circle list
			ArrayList circleid_list = (ArrayList)request.getAttribute("circleid_list");
			ArrayList circlename_list = (ArrayList)request.getAttribute("circlename_list");
			if(circleid_list.isEmpty() || circlename_list.isEmpty())
			{
				response.sendRedirect("jsp/Friend/friend.jsp");
			}
			
			for(int i = 0; i < friendid_list.size(); i++)
			{
				// friend choose
				out.print(friend_firstname_list.get(i));%>. <%out.print(friend_lastname_list.get(i));%>
				<%String friendid_choose = (String)friendid_list.get(i);%>
				<input type="hidden" name="friendid_choose" value=<%=friendid_choose%>>
				
				<select name="circlename">
				<% String circleid_choose = "";
				String circlename_choose = "";
					for(int j = 0; j < circleid_list.size(); j++)
					{
						// circle choose
						circleid_choose = (String)circleid_list.get(j);
						circlename_choose = (String)circlename_list.get(j);%>
    					<option value=<%=circlename_choose%>><%=circlename_choose%></option>   				   								
					<%}%>
				</select> 
				<input type="hidden" name="circleid_choose" value=<%=circleid_choose%>>
				<input type="submit" name="addtocircle" value="+2Cirlce!">
			
				<%// Unfriend  
				//<input type="button" onclick="msg(0)" name=unfriend value="Unfriend!"><br>%>
				<input type="submit" name=unfriend value="Unfriend!"><br>
			<%}%>
	</form>
  </body>
</html>
