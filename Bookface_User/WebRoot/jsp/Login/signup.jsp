<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> SignUp </title>
    
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
  	<form id=signup action='SignupS' name=signup method="post"> 
    	SignUp<br>
    
	    Username <input id=username type="text" name=username><br>
    	Password <input id=password type="password" name=password><br>
    	<%-- Confirm your password!!! --%>
    
    	FirstName <input id=firstname type="text" name=firstname><br>
    	LastName <input id=lastname type="text" name=lastname><br>
    	Sex <input id=sex type="radio" name=sex value="M"> Male
	    	<input id=sex type="radio" name=sex value="F"> Female<br>
    	Email Address <input id=email type="text" name=email><br>
    	Date of Birth 	<select name="year">
    		<option value="year">year</option>
    		<option value="1985">1985</option>
    		<option value="1986">1986</option>
    		<option value="1987">1987</option>
    		<option value="1988">1988</option>
    		<option value="1989">1989</option>
    		<option value="1990">1990</option>
    		<option value="1991">1991</option>
    		<option value="1992">1992</option>
    		<option value="1993">1993</option>
    		<option value="1994">1994</option>
    		<option value="1995">1995</option>
    	</select>year
    					<select name="month">
    		<option value="month">month</option>
    		<option value="01">01</option>
    		<option value="02">02</option>
    		<option value="03">03</option>
    		<option value="04">04</option>
    		<option value="05">05</option>
    		<option value="06">06</option>
    		<option value="07">07</option>
    		<option value="08">08</option>
    		<option value="09">09</option>
    		<option value="10">10</option>
    		<option value="11">11</option>
    		<option value="12">12</option>
    	</select>month
    		<%-- date domain vs. month domain!!! --%>
    					<select name="date">
    		<option value="date">date</option>
    		<option value="01">01</option>
    		<option value="02">02</option>
    		<option value="03">03</option>
    		<option value="04">04</option>
    		<option value="05">05</option>
    		<option value="06">06</option>
    		<option value="07">07</option>
    		<option value="08">08</option>
    		<option value="09">09</option>
    		<option value="10">10</option>
    		<option value="11">11</option>
    		<option value="12">12</option>
    		<option value="13">13</option>
    		<option value="14">14</option>
    		<option value="15">15</option>
    		<option value="16">16</option>
    		<option value="17">17</option>
    		<option value="18">18</option>
    		<option value="19">19</option>
    		<option value="20">20</option>
    		<option value="21">21</option>
    		<option value="22">22</option>
    		<option value="23">23</option>
    		<option value="24">24</option>
    		<option value="25">25</option>
    		<option value="26">26</option>
    		<option value="27">27</option>
    		<option value="28">28</option>
    		<option value="29">29</option>
    		<option value="30">30</option>
    		<option value="31">31</option>
    	</select>date<br>
    	Address <input id=address type="text" name=address><br>
    	City <input id=city type="text" name=city><br>
    	State <input id=state type="text" name=state><br>
    	Zip Code <input id=zipcode type="text" name=zipcode><br>
    	Telephone <input id=telephone type="text" name=telephone><br>
    
    	<input id=signup type="submit" name=signup value="SignUp">
    </form>
  </body>
</html>
