<!DOCTYPE HTML>
<%@ page import="java.util.Date" %>
<html>
<head>
  <title>Welcome <% out.print(request.getParameter("first")+" "+request.getParameter("last")); %></title>
</head>
<body>
  <center>
    <p>
      <h3>
	Password: <% out.println(request.getParameter("password")); %>
      </h3>
    </p>
  </center>
</body>
</html>
