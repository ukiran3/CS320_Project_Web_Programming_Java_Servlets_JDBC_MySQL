<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log In page</title>
</head>
<body>
<h2 align = "right"><a href="SignUp">Sign Up</a></h2>
		<center>

        <form action='LogInPage' method='post'>
			Username: <input type='text' name='username' /> <br />
        	Password: <input type='password' name='password' /> <br />
        	<input type='submit' name='login' value='Login' /> <br />
        </form>

        </center>
</body>
</html>