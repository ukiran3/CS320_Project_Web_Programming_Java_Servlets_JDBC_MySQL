<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project List</title>
</head>
<body>
<%-- if the guest book is empty, display "no comments yet." --%>

<c:choose>
<c:when test="${empty user}">
<h2 align = "right"><a href="LogInPage">Log in</a></h2>
</c:when>
<c:otherwise>
<h2 align = "left"><a href="MyProfile">My Profile!</a></h2>
<h2 align = "right"><a href="LogOutPage">Log Out</a></h2>
</c:otherwise>
</c:choose>

<c:if test="${fn:length(projects)== 0}">
<p>No Projects!</p>
</c:if>

	<center>
		<table style='text-align: left; width: 80%; margin-left: auto; margin-right: auto;' border='0' cellpadding='2' cellspacing='2'>
		<tbody><tr align='center'><td style='text-align: center;' colspan='1' rowspan='1'><span style='font-weight: bold;'>Projects</span></td>
		</tr><tr><td><table style='text-align: left; width: 100%;' border='1' cellpadding='2' cellspacing='2'> <tbody>
		<tr><td style='text-align: center; font-weight: bold;'>Project</td> <td style='text-align: center; font-weight: bold;'>Posted By</td>
		<td style='text-align: center; font-weight: bold;'>Start Date</td><td style='text-align: center; font-weight: bold;'>Days To Go</td><td style='text-align: center; font-weight: bold;'>Amount Pledged</td><td style='text-align: center; font-weight: bold;'>Percentage Pledged</td></tr>

<c:forEach items="${projects}" var = "project">
		<tr>
			<td><a href = "ProjDescription?id=${project.idSeed}"> ${project.title}</a></td>
			<td> ${project.name}</td>
			<td><fmt:formatDate value="${project.sDate}" pattern="MMM dd, yyyy" /></td>
			<td style='text-align: center;'>${project.daysToGo}</td>
			<td style='text-align: center;'>${project.fundCollected}</td>
			<td style='text-align: center;'>${project.percentCollected} %</td>
		</tr>
</c:forEach>

		</tbody> </table> </td> </tr>

<c:choose>
<c:when test="${not empty user}">
<tr> <td><a href='CreateProject'>Create A Project</a><br> </td> </tr>
</c:when>
<c:otherwise>
<tr> <td><a href='LogInPage'>Create A Project</a><br> </td> </tr>
</c:otherwise>
</c:choose>
		 
		</tbody></table>
	</center>
</body>
</html>