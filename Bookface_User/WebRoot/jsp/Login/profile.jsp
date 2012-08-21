<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> It's Me </title>
    
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
  	<form id=profile action='ProfileS' name=profile method="post">
    	It's Me! <br>
    	<% 
			
			String firstname = (String)request.getAttribute("firstname");
			String lastname = (String)request.getAttribute("lastname");
			String sex = (String)request.getAttribute("sex");
			String email = (String)request.getAttribute("email");
			String dob = (String)request.getAttribute("dob");
			String address = (String)request.getAttribute("address");
			String city = (String)request.getAttribute("city");
			String state = (String)request.getAttribute("state");
			String zipcode = (String)request.getAttribute("zipcode");
			String telephone = (String)request.getAttribute("telephone");
			if(firstname.equals(null) || lastname.equals(null) || sex.equals(null)|| email.equals(null)|| dob.equals(null)|| address.equals(null)|| city.equals(null)|| state.equals(null)|| zipcode.equals(null)|| telephone.equals(null))
			{
				;
			}

			%>First Name: <%out.print(firstname);%><br>
			Last Name: <%out.print(lastname);%><br>
			Sex: <%out.print(sex);%><br>
			Email: <%out.print(email);%><br>
			Date of Birth: <%out.print(dob);%><br>
			Address: <%out.print(address);%><br>
			City: <%out.print(city);%><br>
			State: <%out.print(state);%><br>
			Zip Code: <%out.print(zipcode);%><br>
			Telephone: <%out.print(telephone);%><br>
		
	</form>
  </body>
</html>
