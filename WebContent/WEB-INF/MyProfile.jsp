<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile!</title>
</head>
<body>

<c:choose>
<c:when test="${empty user}">
<h2 align = "right"><a href="LogInPage">Log in</a></h2>
</c:when>
<c:otherwise>
<h2 align = "right"><a href="LogOutPage">Log Out</a></h2>
</c:otherwise>
</c:choose>

<c:choose>
<c:when test = "${fn:length(pledges) > 0}">
	<h2 align = 'left'>Hello ${pledges[0].fname}, ${pledges[0].lname} !</h2>

<center><h3>This is the page where you can have a look at all pledges you've made and rewards!<br/>
Congratulations! and Thank you.</h3></center><br/><br/>
<h3>
My own Projects(Created by me):
<c:if test = "${fn:length(pledges[0].ownProjs) > 0}">
<ul>
<c:forEach items = "${pledges[0].ownProjs}" var = "proj">
<li>
<a href = "ProjDescription?id=${proj.idSeed}">${proj.title}</a>
</li>
</c:forEach>
</ul>
</c:if>
<c:if test = "${fn:length(pledges[0].ownProjs) == 0}">
<h3>You don't have any own projects, interested to create one? Click "Create a Project" link in "All Projects" page!</h3>
</c:if>

My Pledges and Rewards:
</h3>
<ol>
<c:forEach items = "${pledges}" var = "i">
<li>
		<h3>Project Name:</h3> ${i.title} <br />
		<h3>Amount pledged:</h3> ${i.amount} <br />
		<h3>Reward received:</h3> ${i.rewDes}
</li>
</c:forEach>
</ol>
<center>
<a href = "ProjectList">All Projects!</a>
</center>
</c:when>
<c:otherwise>
<h3> You do not have any pledges at the moment! Please click here on <a href = "ProjectList">All Projects!</a> to Sponsor a project of your wish!</h3>
</c:otherwise>		
</c:choose>

</body>
</html>