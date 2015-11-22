<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Project</title>
</head>
<body>

<center>

<c:choose>
<c:when test="${empty user}">
<h2 align = "right"><a href="LogInPage">Log in</a></h2>
</c:when>
<c:otherwise>
<h2 align = "left"><a href="MyProfile">My Profile!</a></h2>
<h2 align = "right"><a href="LogOutPage">Log Out</a></h2>
</c:otherwise>
</c:choose>

		<form action = 'CreateProject' method = 'POST'>
		<table style='width: 80%; text-align: left; margin-left: auto; margin-right: auto;' border='0' cellpadding='2' cellspacing='2'>
		<tbody><tr style='font-weight: bold'><td style='text-align: center'>Create Project - Basic Information</td></tr>
		<tr><td><table style= "text-align: left, width: 100%' border='1' cellpadding='2' cellspacing='2' "><tbody><tr><td>Your Name</td>
		
<c:choose>
<c:when test="${empty user}">
<td><input size='40' name='name'></td>
</c:when>
<c:otherwise>
<td>${user}</td>
</c:otherwise>
</c:choose>

		</tr>
		<tr><td>Project Title:</td><td><input size='40' name='title'></td></tr>
		<tr><td style='vertical-align: top;'>Project Description:</td><td style='text-align: left;'><textarea cols='40' rows='5' name='description'></textarea><br></td></tr>
		<tr> <td>Funding Target:</td> <td> $ <input size='10' name='amount'></td> </tr>
		<tr> <td>Start Date:</td>  <td><input size='10' name='start'> (MM/dd/yyy)</td> </tr>   <tr><td>Funding Duration:</td> <td><input size='10' name='days'> Days</td> </tr>
		</tbody> </table> </td> </tr> <tr><td><input name='submit' value='Next' type='submit'></td></tr></tbody>
		</table>

		</form>
</center>

</body>
</html>