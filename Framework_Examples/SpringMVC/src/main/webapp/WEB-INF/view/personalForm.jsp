<!DOCTYPE HTML>
<%@ page import="java.util.*" %>
<html>
  <head><title>Personal Data Form</title></head>
  <body><center>
    <h2>
      Please type in below:
      <form name="input" action="userForm" method="post">
	First name: <input type="text" name="first"><br>
	Last name: <input type="text" name="last"><br>
	Password: <input type="password" name="passwd">
	<input type="submit" value="Submit">
      </form>
    </h2>
    <h4>
      <%= new Date().toString() %>
    </h4>
    </center>
  </body>
</html>
