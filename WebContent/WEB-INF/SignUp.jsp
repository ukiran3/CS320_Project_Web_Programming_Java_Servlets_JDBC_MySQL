<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up Page</title>
</head>
<body>
<c:if test="${result ne 'ye'}">
<c:choose>
<c:when test="${result eq 'emp'}">
Required fields are empty.
</c:when>
<c:otherwise>
	<c:choose>
	<c:when test="${result eq '2'}">
	Username is shorter than 4 characters.
	</c:when>
	<c:otherwise>
		<c:choose>
		<c:when test="${result eq '3'}">
			Username conflicts with an existing username.
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${result eq '4'}">
					Password is shorter than 4 characters.
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${result eq '5'}">
						Password and re-typed password do not match.
					</c:when>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
		</c:choose>
	</c:otherwise>
	</c:choose>
</c:otherwise>
</c:choose>
</c:if>
<center>
<form action = "SignUp" method = "POST">
	<table align="center" border="1" cellpadding="2" cellspacing="2" width="60%">
  <tbody>
    <tr>
      <td valign="top" width="50%">Username: <br /></td>
      <td valign="top"><input style="width: 100%;" name = "usname" type="text"><br></td>
    </tr>
    <tr>
      <td valign="top">Password:<br></td>
      <td valign="top"><input style="width: 100%;" name = "pwd" type="password"><br></td>
    </tr>
    <tr>
      <td valign="top">Retype password:<br></td>
      <td valign="top"><input style="width: 100%;" name = "repwd" type="password"><br></td>
    </tr>
    <tr>
      <td valign="top">First Name: <br></td>
      <td valign="top"><input style="width: 100%;" name = "fname" type="text"><br></td>
    </tr>
    <tr>
      <td valign="top">Last Name:<br></td>
      <td valign="top"><input style="width: 100%;" name = "lname" type="text"><br></td>
    </tr>
  </tbody>
</table>
<input type = "submit" name = "submit" value = "submit">
	</form>
	</center>
</body>
</html>