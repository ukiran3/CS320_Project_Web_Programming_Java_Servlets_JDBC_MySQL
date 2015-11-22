<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Description</title>
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

        <table style='text-align: left; width: 80%; margin-left: auto; margin-right: auto;' border='0' cellpadding='2' cellspacing='2'>
        <tbody> <tr align='center'> <td style='text-align: center;' colspan='1' rowspan='1'>  <p><span style='font-weight: bold;'> ${proj.title}
        </span></p> <p>by ${proj.name} <br />
        </p> <div style='text-align: left;'><p>${proj.desc}</p>
        <span style='font-weight: bold;'>Funding Target</span>: ${proj.funTarget} 
        <br/><span style='font-weight: bold;'>Start Date:</span> 
        <fmt:formatDate value="${proj.sDate}" pattern="MMM dd, yyyy" />
        <span style='font-weight: bold;'><br>Funding Duration:</span> ${proj.funDur}
        <span style='font-weight: bold;'><br> Days to Go</span>: ${proj.daysToGo}
        
        <c:forEach items = "${proj.rewards}" var="rew">
              	
        	<p style='font-weight: bold;'>Pledge $  ${rew.amount} or more</p><p>&nbsp; ${rew.reward}</p>
        	
        </c:forEach>

        </div> </td> </tr> <tr> <td>
        <a href='ProjectList'>All Projects</a></td>
        
<c:if test = "${not proj.isSponsored()}">
<c:choose>
<c:when test="${empty user}">
<td><h2 align = "right"><a href="LogInPage">Sponsor this project</a></h2></td>
</c:when>
<c:otherwise>
<td><h2 align = "right"><a href="SponsorThisProject?id=${proj.idSeed}">Sponsor this project</a></h2></td>
</c:otherwise>
</c:choose>
</c:if>
        </tr> </tbody> </table>
        </center>
       
</body>
</html>