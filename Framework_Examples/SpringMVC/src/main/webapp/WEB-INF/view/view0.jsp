<!DOCTYPE HTML>
<%@ page import="java.util.*" %>
<html>
  <head>
    <title>Welcome</title>
  </head>
  <body>
    <center>
      <% System.out.println("JSP-Log: Time="+new Date()); %>
      <h1>
	<p>
	  SpringMVC View[0]
	  <% out.println("<h4>"); %>
	  <%= new Date() %>
	  <%= request.getParameter("parameterTest") %>
	  <br>
	  <a href="A/userForm">User Form</a>	
	</p>
	<% out.println("</center></body>"); %>
</html>
<!-- response.sendRedirect( anotherUrl ); -->
